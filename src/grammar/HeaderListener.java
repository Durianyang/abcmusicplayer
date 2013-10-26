package grammar;

import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import grammar.ABCMusicHeaderParser;

/**
 * HEADER LISTENER
 */
public class HeaderListener extends ABCMusicHeaderParserBaseListener {
        
	@Override public void enterField_tempo(ABCMusicHeaderParser.Field_tempoContext ctx) { }
	@Override public void exitField_tempo(ABCMusicHeaderParser.Field_tempoContext ctx) { }

	@Override public void enterOther_fields(ABCMusicHeaderParser.Other_fieldsContext ctx) { }
	@Override public void exitOther_fields(ABCMusicHeaderParser.Other_fieldsContext ctx) { }

	@Override public void enterAbc_header(ABCMusicHeaderParser.Abc_headerContext ctx) { }
	@Override public void exitAbc_header(ABCMusicHeaderParser.Abc_headerContext ctx) { }

	@Override public void enterKey_note(ABCMusicHeaderParser.Key_noteContext ctx) { }
	@Override public void exitKey_note(ABCMusicHeaderParser.Key_noteContext ctx) { }

	@Override public void enterField_composer(ABCMusicHeaderParser.Field_composerContext ctx) { }
	@Override public void exitField_composer(ABCMusicHeaderParser.Field_composerContext ctx) { }

	@Override public void enterField_key(ABCMusicHeaderParser.Field_keyContext ctx) { }
	@Override public void exitField_key(ABCMusicHeaderParser.Field_keyContext ctx) { }

	@Override public void enterMeter(ABCMusicHeaderParser.MeterContext ctx) { }
	@Override public void exitMeter(ABCMusicHeaderParser.MeterContext ctx) { }

	@Override public void enterField_default_length(ABCMusicHeaderParser.Field_default_lengthContext ctx) { }
	@Override public void exitField_default_length(ABCMusicHeaderParser.Field_default_lengthContext ctx) { }

	@Override public void enterField_meter(ABCMusicHeaderParser.Field_meterContext ctx) { }
	@Override public void exitField_meter(ABCMusicHeaderParser.Field_meterContext ctx) { }

	@Override public void enterEol(ABCMusicHeaderParser.EolContext ctx) { }
	@Override public void exitEol(ABCMusicHeaderParser.EolContext ctx) { }

	@Override public void enterField_number(ABCMusicHeaderParser.Field_numberContext ctx) { }
	@Override public void exitField_number(ABCMusicHeaderParser.Field_numberContext ctx) { }

	@Override public void enterComment(ABCMusicHeaderParser.CommentContext ctx) { }
	@Override public void exitComment(ABCMusicHeaderParser.CommentContext ctx) { }

	@Override public void enterField_title(ABCMusicHeaderParser.Field_titleContext ctx) { }
	@Override public void exitField_title(ABCMusicHeaderParser.Field_titleContext ctx) { }

	@Override public void enterAbc_tune_header(ABCMusicHeaderParser.Abc_tune_headerContext ctx) { }
	@Override public void exitAbc_tune_header(ABCMusicHeaderParser.Abc_tune_headerContext ctx) { }

	@Override public void enterKey(ABCMusicHeaderParser.KeyContext ctx) { }
	@Override public void exitKey(ABCMusicHeaderParser.KeyContext ctx) { }

	@Override public void enterField_voice(ABCMusicHeaderParser.Field_voiceContext ctx) { }
	@Override public void exitField_voice(ABCMusicHeaderParser.Field_voiceContext ctx) { }
}