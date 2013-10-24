// Generated from ABCMusicBody.g4 by ANTLR 4.0

package grammar;

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface ABCMusicBodyListener extends ParseTreeListener {
	void enterElement(ABCMusicBodyParser.ElementContext ctx);
	void exitElement(ABCMusicBodyParser.ElementContext ctx);

	void enterMid_tune_field(ABCMusicBodyParser.Mid_tune_fieldContext ctx);
	void exitMid_tune_field(ABCMusicBodyParser.Mid_tune_fieldContext ctx);

	void enterAbc_music(ABCMusicBodyParser.Abc_musicContext ctx);
	void exitAbc_music(ABCMusicBodyParser.Abc_musicContext ctx);

	void enterMulti_note(ABCMusicBodyParser.Multi_noteContext ctx);
	void exitMulti_note(ABCMusicBodyParser.Multi_noteContext ctx);

	void enterKey_note(ABCMusicBodyParser.Key_noteContext ctx);
	void exitKey_note(ABCMusicBodyParser.Key_noteContext ctx);

	void enterAbc_line(ABCMusicBodyParser.Abc_lineContext ctx);
	void exitAbc_line(ABCMusicBodyParser.Abc_lineContext ctx);

	void enterNote_element(ABCMusicBodyParser.Note_elementContext ctx);
	void exitNote_element(ABCMusicBodyParser.Note_elementContext ctx);

	void enterTuplet_spec(ABCMusicBodyParser.Tuplet_specContext ctx);
	void exitTuplet_spec(ABCMusicBodyParser.Tuplet_specContext ctx);

	void enterNote_length(ABCMusicBodyParser.Note_lengthContext ctx);
	void exitNote_length(ABCMusicBodyParser.Note_lengthContext ctx);

	void enterNote_or_rest(ABCMusicBodyParser.Note_or_restContext ctx);
	void exitNote_or_rest(ABCMusicBodyParser.Note_or_restContext ctx);

	void enterAbc_tune_body(ABCMusicBodyParser.Abc_tune_bodyContext ctx);
	void exitAbc_tune_body(ABCMusicBodyParser.Abc_tune_bodyContext ctx);

	void enterMeter(ABCMusicBodyParser.MeterContext ctx);
	void exitMeter(ABCMusicBodyParser.MeterContext ctx);

	void enterLyric(ABCMusicBodyParser.LyricContext ctx);
	void exitLyric(ABCMusicBodyParser.LyricContext ctx);

	void enterTuplet_element(ABCMusicBodyParser.Tuplet_elementContext ctx);
	void exitTuplet_element(ABCMusicBodyParser.Tuplet_elementContext ctx);

	void enterPitch(ABCMusicBodyParser.PitchContext ctx);
	void exitPitch(ABCMusicBodyParser.PitchContext ctx);

	void enterNote(ABCMusicBodyParser.NoteContext ctx);
	void exitNote(ABCMusicBodyParser.NoteContext ctx);

	void enterKey(ABCMusicBodyParser.KeyContext ctx);
	void exitKey(ABCMusicBodyParser.KeyContext ctx);
}