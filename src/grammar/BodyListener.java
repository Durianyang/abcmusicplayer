package grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import sound.*;
import grammar.ABCMusicBodyParser;

/**
 * BODY LISTENER
 */
public class BodyListener extends ABCMusicBodyParserBaseListener {
	// Keep track of measures
	private ArrayList<Measure> measures = new ArrayList<Measure>();
	private int currentMeasureNum = 0;
	private Measure currentMeasure;
	
	// Keep track of voices
	private Voice currentVoice;
	private int voiceStartMeasure = 0;
	private String currentVoiceName = "default";
	private HashMap<String, Boolean> voicesAdded = new HashMap<String, Boolean>();
	
	// Keep track of chords and elements
	// These will be populated to build out notes and chords while walking the tree
	private List<MusicalElement> currentElements = new LinkedList<MusicalElement>();
	private List<MusicalElement> chordElements = new LinkedList<MusicalElement>();
	private boolean inChord = false;
	
	// Track Note Pitches, lengths and accidentals
	private String currentPitchStr;
	private Fraction currentLen;
	private String currentAccidental = "";

	// Track repeats
	private int openRepeat = 0;
	private int altEnding1;
	
	private Song song;
	
	/**
	 * Constructor for BodyListener object - requires that a pre-initialized song created by
	 * Header Listener be passed in as parameter
	 * @param s: Song created by HeaderListener
	 */
	public BodyListener(Song s) {
		super();
		this.song = s;
		this.currentLen = this.song.getDefaultNoteLen();
	}

	// Whole Song
	@Override public void enterAbc_tune_body(ABCMusicBodyParser.Abc_tune_bodyContext ctx) { }
	@Override public void exitAbc_tune_body(ABCMusicBodyParser.Abc_tune_bodyContext ctx) { 
		this.song.addMeasures(this.measures);
	}
	
	
	// Voices
	@Override public void enterVoice(ABCMusicBodyParser.VoiceContext ctx) { 
		this.currentVoiceName = ctx.getText();
		if (this.voicesAdded.containsKey(this.currentVoiceName)) {
			// Hitting the next section of voices - move measure tracker forward
			this.voiceStartMeasure = this.currentMeasureNum;	
			this.voicesAdded.clear();
		} else {
			// Hitting new voice which is same set of measures as last voice
			this.currentMeasureNum = this.voiceStartMeasure;
		}
	}
	@Override 
	public void exitVoice(ABCMusicBodyParser.VoiceContext ctx) {
		// Keep track of voices that have already been added
		voicesAdded.put(currentVoiceName, new Boolean(true));		
	}

	// Measures
	@Override 
	public void enterMeasure(ABCMusicBodyParser.MeasureContext ctx) { 
		// Add new measure to list
		Measure measure;
		if (this.currentMeasureNum < this.measures.size()) {
			measure = this.measures.get(currentMeasureNum);
		} else {
			// Create new measure
			measure = new Measure(this.song.getNotesPerMeasure(), currentMeasureNum);
			this.measures.add(measure);	
		}
		this.currentMeasure = measure;
		// create current voice
		this.currentVoice = new Voice(this.currentVoiceName, this.song.getNotesPerMeasure());	
		
	}
	@Override public void exitMeasure(ABCMusicBodyParser.MeasureContext ctx) {
		// Add voice to currentMeasure
		this.currentMeasure.addVoice(this.currentVoice);
		// Increment measure tracker
		this.currentMeasureNum ++;	
	}
	
	
	// Keep track of alternate endings for measure
	@Override public void enterNth_repeat(ABCMusicBodyParser.Nth_repeatContext ctx) { }
	@Override public void exitNth_repeat(ABCMusicBodyParser.Nth_repeatContext ctx) { 
		if(ctx.getText().equals("[1")) {
			this.altEnding1 = this.currentMeasureNum;
		} else if (ctx.getText().equals("[2")) {
			Measure m = this.measures.get(this.altEnding1);
			m.setAlternateEnding(this.currentMeasureNum );
		}
	}
	
	// Keep track of repeats
	@Override public void enterBar_line(ABCMusicBodyParser.Bar_lineContext ctx) { }
	@Override public void exitBar_line(ABCMusicBodyParser.Bar_lineContext ctx) { 
		if(ctx.getText().equals("|:")) {
			this.openRepeat = this.currentMeasureNum;
		} else if (ctx.getText().equals(":|")) {
			Measure m = this.measures.get(this.currentMeasureNum - 1);
			m.setRepeat(this.openRepeat);
		}
				
	}
	
	
	// Musical Elements - Notes, Rests, Chords and Tuplets
	@Override 
	public void enterMulti_note(ABCMusicBodyParser.Multi_noteContext ctx) {
		this.inChord = true;
	}
	@Override public void exitMulti_note(ABCMusicBodyParser.Multi_noteContext ctx) {
		List<Note> chordNotes = new LinkedList<Note>();
		for (int i=0; i<this.chordElements.size(); i++) {
			chordNotes.add((Note) this.chordElements.remove(0));
		}
		this.currentElements.add(new Chord(chordNotes));
		this.inChord = false;
	}

	@Override public void enterTuplet_element(ABCMusicBodyParser.Tuplet_elementContext ctx) {}
	@Override public void exitTuplet_element(ABCMusicBodyParser.Tuplet_elementContext ctx) {
		List<MusicalElement> elements = new LinkedList<MusicalElement>();
		for (int i=0; i<this.currentElements.size(); i++) {
			elements.add(this.currentElements.remove(0));
		}
		this.currentElements.add(new Tuplet(elements));			
	}
	@Override public void enterNote_element(ABCMusicBodyParser.Note_elementContext ctx) {}
	@Override public void exitNote_element(ABCMusicBodyParser.Note_elementContext ctx) {
		try {
			this.currentVoice.addMusicalElement(this.currentElements.remove(0));
		} catch (Exception e) {
			// Should not happen because we are populating list before popping
			e.printStackTrace();
		}	
	}
	
	@Override public void enterNote(ABCMusicBodyParser.NoteContext ctx) {}
	@Override 
	public void exitNote(ABCMusicBodyParser.NoteContext ctx) {
		MusicalElement newElement;
		if (this.currentPitchStr.equals("z")) {
			newElement = new Rest(this.currentLen);
		} else {
			// add new note to currentElements
			Pitch pitch;
			if (this.currentMeasure.isPitchInAccidentalKey(this.currentPitchStr)) {
				pitch = this.currentMeasure.getPitchInAccidentalKey(this.currentPitchStr);
			} else {
				pitch = this.song.getPitchInKey(this.currentPitchStr);
			}
			newElement = new Note(pitch, this.currentLen);
			
		}
		
		//If we're constructing a chord, add to chord list instead of normal list 
		if(this.inChord) {
			this.chordElements.add(newElement);
		} else {
			this.currentElements.add(newElement);
		}
		
		this.currentLen = this.song.getDefaultNoteLen();		
	}
	
	@Override public void enterAccidental(ABCMusicBodyParser.AccidentalContext ctx) {}
	@Override 
	public void exitAccidental(ABCMusicBodyParser.AccidentalContext ctx) {
		this.currentAccidental = ctx.getText();		
	}
	
	@Override public void enterPitch(ABCMusicBodyParser.PitchContext ctx) { }
	@Override public void exitPitch(ABCMusicBodyParser.PitchContext ctx) {
		// Modify accidental key for measure
		if (this.currentAccidental != "") {
			this.currentMeasure.modifyKey(this.currentPitchStr, this.currentAccidental);
		}
		// Reset accidental string
		this.currentAccidental = "";
	}
	
	@Override public void enterBase_note_octave(ABCMusicBodyParser.Base_note_octaveContext ctx) { }
	@Override public void exitBase_note_octave(ABCMusicBodyParser.Base_note_octaveContext ctx) {
		this.currentPitchStr = ctx.getText();
	}	
	
	@Override public void enterRest(ABCMusicBodyParser.RestContext ctx) { }
	@Override public void exitRest(ABCMusicBodyParser.RestContext ctx) {
		this.currentPitchStr = ctx.getText();	
	}
	
	
	@Override public void enterNote_length(ABCMusicBodyParser.Note_lengthContext ctx) { }
	@Override 
	public void exitNote_length(ABCMusicBodyParser.Note_lengthContext ctx) {
		this.currentLen = this.song.parseDurationFromString(ctx.getText());
	}
	
	
	



	
	@Override public void enterLyric_element(ABCMusicBodyParser.Lyric_elementContext ctx) { }
	@Override public void exitLyric_element(ABCMusicBodyParser.Lyric_elementContext ctx) { }

	@Override public void enterLyric_text(ABCMusicBodyParser.Lyric_textContext ctx) { }
	@Override public void exitLyric_text(ABCMusicBodyParser.Lyric_textContext ctx) { }

	@Override public void enterLyric(ABCMusicBodyParser.LyricContext ctx) { }
	@Override public void exitLyric(ABCMusicBodyParser.LyricContext ctx) { }



//	@Override public void enterTuplet_digit(ABCMusicBodyParser.Tuplet_digitContext ctx) { }
//	@Override public void exitTuplet_digit(ABCMusicBodyParser.Tuplet_digitContext ctx) { }
//	
//	@Override public void enterTuplet_spec(ABCMusicBodyParser.Tuplet_specContext ctx) { }
//	@Override public void exitTuplet_spec(ABCMusicBodyParser.Tuplet_specContext ctx) { }
//	
//	
//	@Override public void enterField_voice(ABCMusicBodyParser.Field_voiceContext ctx) { }
//	@Override public void exitField_voice(ABCMusicBodyParser.Field_voiceContext ctx) { }
//		
//	@Override public void enterNote_or_rest(ABCMusicBodyParser.Note_or_restContext ctx) { }
//	@Override public void exitNote_or_rest(ABCMusicBodyParser.Note_or_restContext ctx) { }
//	
//	@Override public void enterAbc_music(ABCMusicBodyParser.Abc_musicContext ctx) { }
//	@Override public void exitAbc_music(ABCMusicBodyParser.Abc_musicContext ctx) { }
//	
//	@Override public void enterAbc_line(ABCMusicBodyParser.Abc_lineContext ctx) { }
//	@Override public void exitAbc_line(ABCMusicBodyParser.Abc_lineContext ctx) { }
//
//	@Override public void enterElement(ABCMusicBodyParser.ElementContext ctx) { }
//	@Override public void exitElement(ABCMusicBodyParser.ElementContext ctx) { }
//
//	@Override public void enterMid_tune_field(ABCMusicBodyParser.Mid_tune_fieldContext ctx) { }
//	@Override public void exitMid_tune_field(ABCMusicBodyParser.Mid_tune_fieldContext ctx) { }
//	
//	@Override public void enterComment(ABCMusicBodyParser.CommentContext ctx) { }
//	@Override public void exitComment(ABCMusicBodyParser.CommentContext ctx) { }
//	
//	@Override public void enterSpace(ABCMusicBodyParser.SpaceContext ctx) { }
//	@Override public void exitSpace(ABCMusicBodyParser.SpaceContext ctx) { }
//

//
//	@Override public void enterEol(ABCMusicBodyParser.EolContext ctx) { }
//	@Override public void exitEol(ABCMusicBodyParser.EolContext ctx) { }
	
	public Song getSong(){
		return this.song;
	}
}