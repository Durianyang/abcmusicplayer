package grammar;

import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import grammar.ABCMusicBodyParser;

/**
 * BODY LISTENER
 */
public class BodyListener extends ABCMusicBodyParserBaseListener {

	@Override public void enterElement(ABCMusicBodyParser.ElementContext ctx) { }
	@Override public void exitElement(ABCMusicBodyParser.ElementContext ctx) { }

	@Override public void enterMid_tune_field(ABCMusicBodyParser.Mid_tune_fieldContext ctx) { }
	@Override public void exitMid_tune_field(ABCMusicBodyParser.Mid_tune_fieldContext ctx) { }

	@Override public void enterAbc_music(ABCMusicBodyParser.Abc_musicContext ctx) { }
	@Override public void exitAbc_music(ABCMusicBodyParser.Abc_musicContext ctx) { }

	@Override public void enterMulti_note(ABCMusicBodyParser.Multi_noteContext ctx) { }
	@Override public void exitMulti_note(ABCMusicBodyParser.Multi_noteContext ctx) { }

	@Override public void enterKey_note(ABCMusicBodyParser.Key_noteContext ctx) { }
	@Override public void exitKey_note(ABCMusicBodyParser.Key_noteContext ctx) { }

	@Override public void enterAbc_line(ABCMusicBodyParser.Abc_lineContext ctx) { }
	@Override public void exitAbc_line(ABCMusicBodyParser.Abc_lineContext ctx) { }

	@Override public void enterNote_element(ABCMusicBodyParser.Note_elementContext ctx) { }
	@Override public void exitNote_element(ABCMusicBodyParser.Note_elementContext ctx) { }

	@Override public void enterTuplet_spec(ABCMusicBodyParser.Tuplet_specContext ctx) { }
	@Override public void exitTuplet_spec(ABCMusicBodyParser.Tuplet_specContext ctx) { }

	@Override public void enterNote_length(ABCMusicBodyParser.Note_lengthContext ctx) { }
	@Override public void exitNote_length(ABCMusicBodyParser.Note_lengthContext ctx) { }

	@Override public void enterNote_or_rest(ABCMusicBodyParser.Note_or_restContext ctx) { }
	@Override public void exitNote_or_rest(ABCMusicBodyParser.Note_or_restContext ctx) { }

	@Override public void enterAbc_tune_body(ABCMusicBodyParser.Abc_tune_bodyContext ctx) { }
	@Override public void exitAbc_tune_body(ABCMusicBodyParser.Abc_tune_bodyContext ctx) { }

	@Override public void enterLyric(ABCMusicBodyParser.LyricContext ctx) { }
	@Override public void exitLyric(ABCMusicBodyParser.LyricContext ctx) { }

	@Override public void enterTuplet_element(ABCMusicBodyParser.Tuplet_elementContext ctx) { }
	@Override public void exitTuplet_element(ABCMusicBodyParser.Tuplet_elementContext ctx) { }

	@Override public void enterPitch(ABCMusicBodyParser.PitchContext ctx) { }
	@Override public void exitPitch(ABCMusicBodyParser.PitchContext ctx) { }

	@Override public void enterNote(ABCMusicBodyParser.NoteContext ctx) { }
	@Override public void exitNote(ABCMusicBodyParser.NoteContext ctx) { }

	@Override public void enterKey(ABCMusicBodyParser.KeyContext ctx) { }
	@Override public void exitKey(ABCMusicBodyParser.KeyContext ctx) { }
}