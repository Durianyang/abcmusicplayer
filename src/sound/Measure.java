package sound;

import java.util.LinkedList;
import java.util.List;

/**
 * Mutable object representing a measure of a piece.  Measure contains
 * voices and a key which is used to keep track of accidentals.
 * @author Josh
 *
 */
public class Measure {
	private List<Voice> voices = new LinkedList<Voice>();
	private int measureNumber;
	private Key accidentalKey = new Key();
	private double notesPerMeasure;
	
	// Keep track of repeats and alternate endings
	private int repeatStartMeasure;
	private int alternateEnding;
	private boolean hasRepeat = false;
	private boolean played = false;
	private boolean hasAlternateEnding = false;
	
	
	
	/**
	 * Object representing a measure of a piece.  Contains Voices (which contain Musical elements)
	 * Also contains a key and stores the amount of notesPerMeasure.
	 * The sum of all of the ticks in every voice and in all lyrics should be <= ticksPerMeasure
	 * @param key: key of measure, used to keep track of accidentals
	 * @param notesPerMeasure: amount of notes in each measure
	 */
	public Measure(double notesPerMeasure, int measureNumber) {
		this.notesPerMeasure = notesPerMeasure;
		this.measureNumber = measureNumber;
	}

	/**
	 * Sets measure to have repeat and stores what the starting measure
	 * of that repeat sequence is
	 * @param repeatStartMeasure: int representing the measure # of the 
	 * starting measure
	 */
	public void setRepeat(int repeatStartMeasure) {
		this.repeatStartMeasure = repeatStartMeasure;
		this.hasRepeat = true;
	}
	
	/**
	 * Sets measure to have alternate ending and stores measure number
	 * of that ending
	 * @param alternateEnding: int representing measure # of alternate ending 
	 */
	public void setAlternateEnding(int alternateEnding) {
		this.alternateEnding = alternateEnding;
		this.hasAlternateEnding = true;
	}
	
	/**
	 * Add a voice to this measure
	 * @param voice: Voice to add to measure - sum of durations of notes in voice should
	 * be <= ticksPerMeasure
	 */
	public void addVoice(Voice voice) {
		try {
			if (voice.getNotesPerVoice() == this.notesPerMeasure) {
				this.voices.add(voice.clone());
			} else {
				throw new Exception("Voices must have same duration as measures");
			}
		} catch (Exception e) {}
	}
	
	/**
	 * Returns the number of the measure that should be played after this one
	 * No guarantee that this measure is actually in the song though
	 * @return
	 */
	public int getNextMeasure() {
		if (!this.hasRepeat && !this.hasAlternateEnding) {
			this.played = true;
			return this.measureNumber + 1;
		} else if(this.hasAlternateEnding) {
			
			if (this.played) {
				return this.alternateEnding;
			} else {
				this.played = true;
				if (this.hasRepeat) {
					return this.repeatStartMeasure;
				} else {
					return this.measureNumber + 1;
				}
			}
			
		} else {  // We have a repeat and do not have alternate ending
			if (!this.played) {
				this.played = true;
				return this.repeatStartMeasure;
			} else {
				return this.measureNumber + 1;
			}
		} 
	}
	
	/**
	 * Modify key as we go along in case we hit an accidental
	 * @param note
	 * @param accidentalString
	 */
	public void modifyKey(String note, String accidentalString) {
		this.accidentalKey.alterKey(note, accidentalString);
	}
	
	/**
	 * Checks if pitch is in key of accidentals
	 * @param note: String representing note to check
	 * @return True if in accidental key
	 */
	public boolean isPitchInAccidentalKey(String note) {
		return this.accidentalKey.containsNote(note);
	}
	
	/**
	 * Returns the pitch of the note in the accidental key
	 * Precondition: note must already be a in accidental key
	 * @param note: String representing note to get
	 * @return pitch in accidental key
	 */
	public Pitch getPitchInAccidentalKey(String note) {
		return this.accidentalKey.getPitch(note);
	}
	
	/**
	 * Returns list of voices in measure
	 * @return List<Voice>: voices in measure
	 */
	public List<Voice> getVoices() {
		List<Voice> clonedVoices = new LinkedList<Voice>();
		for (Voice v: this.voices) {
			clonedVoices.add(v.clone());
		}
		return clonedVoices;
	}
	
	/**
	 * Returns the ticks per whole note required to play this measure
	 * @return int: ticks per whole note
	 */
	public int getTicksPerWholeNote() {
		int maxTicks = 0;
		for (Voice v: this.voices) {
			int ticks = v.getTicksPerWholeNote();
			if (ticks > maxTicks) {
				maxTicks = ticks;
			}
		}
		
		return maxTicks;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		//return Integer.toString(this.measureNumber);
		StringBuilder measureStrings = new StringBuilder();
    	for(Voice voice : this.voices){
    		measureStrings.append(voice.toString()+"\n");
    	}
    	return measureStrings.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accidentalKey == null) ? 0 : accidentalKey.hashCode());
		result = prime * result + alternateEnding;
		result = prime * result + (hasAlternateEnding ? 1231 : 1237);
		result = prime * result + (hasRepeat ? 1231 : 1237);
		result = prime * result + measureNumber;
		long temp;
		temp = Double.doubleToLongBits(notesPerMeasure);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (played ? 1231 : 1237);
		result = prime * result + repeatStartMeasure;
		result = prime * result + ((voices == null) ? 0 : voices.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Measure)) {
			return false;
		}
		Measure other = (Measure) obj;
		if (accidentalKey == null) {
			if (other.accidentalKey != null) {
				return false;
			}
		} else if (!accidentalKey.equals(other.accidentalKey)) {
			return false;
		}
		if (alternateEnding != other.alternateEnding) {
			return false;
		}
		if (hasAlternateEnding != other.hasAlternateEnding) {
			return false;
		}
		if (hasRepeat != other.hasRepeat) {
			return false;
		}
		if (measureNumber != other.measureNumber) {
			return false;
		}
		if (Double.doubleToLongBits(notesPerMeasure) != Double
				.doubleToLongBits(other.notesPerMeasure)) {
			return false;
		}
		if (played != other.played) {
			return false;
		}
		if (repeatStartMeasure != other.repeatStartMeasure) {
			return false;
		}
		if (voices == null) {
			if (other.voices != null) {
				return false;
			}
		} else if (!voices.equals(other.voices)) {
			return false;
		}
		return true;
	}


}