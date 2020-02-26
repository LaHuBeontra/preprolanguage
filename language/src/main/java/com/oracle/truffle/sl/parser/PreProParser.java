// Generated from language/src/main/java/com/oracle/truffle/sl/parser/PrePro.g4 by ANTLR 4.7.2
package com.oracle.truffle.sl.parser;

// DO NOT MODIFY - generated from PrePro.g4 using "mx create-sl-parser"

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.sl.PreProLanguage;
import com.oracle.truffle.sl.nodes.PreProExpressionNode;
import com.oracle.truffle.sl.nodes.PreProRootNode;
import com.oracle.truffle.sl.nodes.PreProStatementNode;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PreProParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, WS=27, COMMENT=28, LINE_COMMENT=29, TYPE=30, IDENTIFIER=31, 
		STRING_LITERAL=32, NUMERIC_LITERAL=33;
	public static final int
		RULE_prepro = 0, RULE_mainFunction = 1, RULE_function = 2, RULE_functionArguments = 3, 
		RULE_statement = 4, RULE_assignment = 5, RULE_throwStatement = 6, RULE_arithmetic = 7, 
		RULE_term = 8, RULE_factor = 9, RULE_functionCallStatement = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"prepro", "mainFunction", "function", "functionArguments", "statement", 
			"assignment", "throwStatement", "arithmetic", "term", "factor", "functionCallStatement"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'function'", "'main'", "'('", "')'", "'{'", "'}'", "'returns'", 
			"'return'", "';'", "','", "'debugger'", "'='", "'throw'", "'+'", "'-'", 
			"'*'", "'/'", "'X'", "'**'", "'=='", "'<='", "'>='", "'&&'", "'||'", 
			"'<'", "'>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "WS", "COMMENT", "LINE_COMMENT", "TYPE", "IDENTIFIER", 
			"STRING_LITERAL", "NUMERIC_LITERAL"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "PrePro.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	private PreProNodeFactory factory;
	private Source source;

	private static final class BailoutErrorListener extends BaseErrorListener {
	    private final Source source;
	    BailoutErrorListener(Source source) {
	        this.source = source;
	    }
	    @Override
	    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
	        throwParseError(source, line, charPositionInLine, (Token) offendingSymbol, msg);
	    }
	}

	public void SemErr(Token token, String message) {
	    assert token != null;
	    throwParseError(source, token.getLine(), token.getCharPositionInLine(), token, message);
	}

	private static void throwParseError(Source source, int line, int charPositionInLine, Token token, String message) {
	    int col = charPositionInLine + 1;
	    String location = "-- line " + line + " col " + col + ": ";
	    int length = token == null ? 1 : Math.max(token.getStopIndex() - token.getStartIndex(), 0);
	    throw new PreProParseError(source, line, col, length, String.format("Error(s) parsing script:%n" + location + message));
	}

	public static Map<String, RootCallTarget> parsePrePro(PreProLanguage language, Source source) {
	    PreProLexer lexer = new PreProLexer(CharStreams.fromString(source.getCharacters().toString()));
	    PreProParser parser = new PreProParser(new CommonTokenStream(lexer));
	    lexer.removeErrorListeners();
	    parser.removeErrorListeners();
	    BailoutErrorListener listener = new BailoutErrorListener(source);
	    lexer.addErrorListener(listener);
	    parser.addErrorListener(listener);
	    parser.factory = new PreProNodeFactory(language, source);
	    parser.source = source;
	    parser.prepro();
	    return parser.factory.getAllFunctions();
	}

	public PreProParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class PreproContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(PreProParser.EOF, 0); }
		public List<MainFunctionContext> mainFunction() {
			return getRuleContexts(MainFunctionContext.class);
		}
		public MainFunctionContext mainFunction(int i) {
			return getRuleContext(MainFunctionContext.class,i);
		}
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public PreproContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prepro; }
	}

	public final PreproContext prepro() throws RecognitionException {
		PreproContext _localctx = new PreproContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prepro);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(22);
				mainFunction();
				}
				break;
			case 2:
				{
				setState(23);
				function();
				}
				break;
			}
			setState(30);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				setState(28);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(26);
					mainFunction();
					}
					break;
				case 2:
					{
					setState(27);
					function();
					}
					break;
				}
				}
				setState(32);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(33);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MainFunctionContext extends ParserRuleContext {
		public Token main;
		public Token s;
		public StatementContext statement;
		public Token e;
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public MainFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainFunction; }
	}

	public final MainFunctionContext mainFunction() throws RecognitionException {
		MainFunctionContext _localctx = new MainFunctionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_mainFunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(T__0);
			setState(36);
			((MainFunctionContext)_localctx).main = match(T__1);
			setState(37);
			((MainFunctionContext)_localctx).s = match(T__2);
			 factory.startFunction(((MainFunctionContext)_localctx).main, ((MainFunctionContext)_localctx).s); 
			setState(39);
			match(T__3);
			 factory.startBlock();
			                                                  List<PreProStatementNode> body = new ArrayList<>(); 
			setState(41);
			match(T__4);
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__10) | (1L << T__12) | (1L << TYPE) | (1L << IDENTIFIER) | (1L << STRING_LITERAL) | (1L << NUMERIC_LITERAL))) != 0)) {
				{
				{
				setState(42);
				((MainFunctionContext)_localctx).statement = statement();
				 body.add(((MainFunctionContext)_localctx).statement.result); 
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(50);
			((MainFunctionContext)_localctx).e = match(T__5);
			factory.finishFunction(
			                                                  factory.finishBlock(body, ((MainFunctionContext)_localctx).s.getStartIndex(), ((MainFunctionContext)_localctx).e.getStopIndex() - ((MainFunctionContext)_localctx).s.getStartIndex() + 1)
			                                                );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public Token IDENTIFIER;
		public Token s;
		public Token TYPE;
		public StatementContext statement;
		public Token r;
		public ArithmeticContext arithmetic;
		public Token e;
		public TerminalNode IDENTIFIER() { return getToken(PreProParser.IDENTIFIER, 0); }
		public FunctionArgumentsContext functionArguments() {
			return getRuleContext(FunctionArgumentsContext.class,0);
		}
		public TerminalNode TYPE() { return getToken(PreProParser.TYPE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ArithmeticContext arithmetic() {
			return getRuleContext(ArithmeticContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(T__0);
			setState(54);
			((FunctionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(55);
			((FunctionContext)_localctx).s = match(T__2);
			 factory.startFunction(((FunctionContext)_localctx).IDENTIFIER, ((FunctionContext)_localctx).s); 
			setState(57);
			functionArguments();
			setState(58);
			match(T__3);
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(59);
				match(T__6);
				setState(60);
				((FunctionContext)_localctx).TYPE = match(TYPE);
				factory.addReturnType(((FunctionContext)_localctx).TYPE);
				}
			}

			 factory.startBlock();
			                                                  List<PreProStatementNode> body = new ArrayList<>(); 
			setState(65);
			match(T__4);
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__10) | (1L << T__12) | (1L << TYPE) | (1L << IDENTIFIER) | (1L << STRING_LITERAL) | (1L << NUMERIC_LITERAL))) != 0)) {
				{
				{
				setState(66);
				((FunctionContext)_localctx).statement = statement();
				 body.add(((FunctionContext)_localctx).statement.result); 
				}
				}
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(74);
				((FunctionContext)_localctx).r = match(T__7);
				setState(75);
				((FunctionContext)_localctx).arithmetic = arithmetic();
				 body.add(factory.createReturn(((FunctionContext)_localctx).r, ((FunctionContext)_localctx).arithmetic.result));
				setState(77);
				match(T__8);
				}
			}

			setState(81);
			((FunctionContext)_localctx).e = match(T__5);
			factory.finishFunction(
			                                                  factory.finishBlock(body, ((FunctionContext)_localctx).s.getStartIndex(), ((FunctionContext)_localctx).e.getStopIndex() - ((FunctionContext)_localctx).s.getStartIndex() + 1)
			                                                );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionArgumentsContext extends ParserRuleContext {
		public Token TYPE;
		public Token IDENTIFIER;
		public List<TerminalNode> TYPE() { return getTokens(PreProParser.TYPE); }
		public TerminalNode TYPE(int i) {
			return getToken(PreProParser.TYPE, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(PreProParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PreProParser.IDENTIFIER, i);
		}
		public FunctionArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionArguments; }
	}

	public final FunctionArgumentsContext functionArguments() throws RecognitionException {
		FunctionArgumentsContext _localctx = new FunctionArgumentsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_functionArguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TYPE) {
				{
				setState(84);
				((FunctionArgumentsContext)_localctx).TYPE = match(TYPE);
				setState(85);
				((FunctionArgumentsContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 factory.addFormalParameter(((FunctionArgumentsContext)_localctx).TYPE, factory.createStringLiteral(((FunctionArgumentsContext)_localctx).IDENTIFIER, false)); 
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(87);
					match(T__9);
					setState(88);
					((FunctionArgumentsContext)_localctx).TYPE = match(TYPE);
					setState(89);
					((FunctionArgumentsContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					 factory.addFormalParameter(((FunctionArgumentsContext)_localctx).TYPE, factory.createStringLiteral(((FunctionArgumentsContext)_localctx).IDENTIFIER, false)); 
					}
					}
					setState(95);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public PreProStatementNode result;
		public AssignmentContext assignment;
		public ArithmeticContext arithmetic;
		public ThrowStatementContext throwStatement;
		public Token d;
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public ArithmeticContext arithmetic() {
			return getRuleContext(ArithmeticContext.class,0);
		}
		public ThrowStatementContext throwStatement() {
			return getRuleContext(ThrowStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(98);
				((StatementContext)_localctx).assignment = assignment();
				setState(99);
				match(T__8);
				 ((StatementContext)_localctx).result =  ((StatementContext)_localctx).assignment.result; 
				}
				break;
			case 2:
				{
				setState(102);
				((StatementContext)_localctx).arithmetic = arithmetic();
				setState(103);
				match(T__8);
				 ((StatementContext)_localctx).result =  ((StatementContext)_localctx).arithmetic.result; 
				}
				break;
			case 3:
				{
				setState(106);
				((StatementContext)_localctx).throwStatement = throwStatement();
				setState(107);
				match(T__8);
				 ((StatementContext)_localctx).result =  ((StatementContext)_localctx).throwStatement.result; 
				}
				break;
			case 4:
				{
				setState(110);
				((StatementContext)_localctx).d = match(T__10);
				 ((StatementContext)_localctx).result =  factory.createDebugger(((StatementContext)_localctx).d); 
				setState(112);
				match(T__8);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public PreProStatementNode result;
		public Token TYPE;
		public Token IDENTIFIER;
		public ArithmeticContext arithmetic;
		public TerminalNode IDENTIFIER() { return getToken(PreProParser.IDENTIFIER, 0); }
		public ArithmeticContext arithmetic() {
			return getRuleContext(ArithmeticContext.class,0);
		}
		public TerminalNode TYPE() { return getToken(PreProParser.TYPE, 0); }
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TYPE) {
				{
				setState(115);
				((AssignmentContext)_localctx).TYPE = match(TYPE);
				}
			}

			setState(118);
			((AssignmentContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			 PreProExpressionNode assignmentName = factory.createStringLiteral(((AssignmentContext)_localctx).IDENTIFIER, false); 
			setState(120);
			match(T__11);
			{
			setState(121);
			((AssignmentContext)_localctx).arithmetic = arithmetic();
			 ((AssignmentContext)_localctx).result =  factory.createAssignment(((AssignmentContext)_localctx).TYPE, assignmentName, ((AssignmentContext)_localctx).arithmetic.result); 
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ThrowStatementContext extends ParserRuleContext {
		public PreProStatementNode result;
		public Token STRING_LITERAL;
		public TerminalNode STRING_LITERAL() { return getToken(PreProParser.STRING_LITERAL, 0); }
		public ThrowStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throwStatement; }
	}

	public final ThrowStatementContext throwStatement() throws RecognitionException {
		ThrowStatementContext _localctx = new ThrowStatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_throwStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(T__12);
			{
			setState(125);
			((ThrowStatementContext)_localctx).STRING_LITERAL = match(STRING_LITERAL);
			 ((ThrowStatementContext)_localctx).result =  factory.createThrow((((ThrowStatementContext)_localctx).STRING_LITERAL!=null?((ThrowStatementContext)_localctx).STRING_LITERAL.getText():null).substring(1, (((ThrowStatementContext)_localctx).STRING_LITERAL!=null?((ThrowStatementContext)_localctx).STRING_LITERAL.getText():null).length() - 1)); 
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArithmeticContext extends ParserRuleContext {
		public PreProExpressionNode result;
		public TermContext term;
		public Token op;
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public ArithmeticContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic; }
	}

	public final ArithmeticContext arithmetic() throws RecognitionException {
		ArithmeticContext _localctx = new ArithmeticContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_arithmetic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			((ArithmeticContext)_localctx).term = term();
			 ((ArithmeticContext)_localctx).result =  ((ArithmeticContext)_localctx).term.result; 
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13 || _la==T__14) {
				{
				{
				setState(130);
				((ArithmeticContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__13 || _la==T__14) ) {
					((ArithmeticContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(131);
				((ArithmeticContext)_localctx).term = term();
				 ((ArithmeticContext)_localctx).result =  factory.createBinary(((ArithmeticContext)_localctx).op, _localctx.result, ((ArithmeticContext)_localctx).term.result); 
				}
				}
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public PreProExpressionNode result;
		public FactorContext factor;
		public Token op;
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			((TermContext)_localctx).factor = factor();
			 ((TermContext)_localctx).result =  ((TermContext)_localctx).factor.result; 
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25))) != 0)) {
				{
				{
				setState(141);
				((TermContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25))) != 0)) ) {
					((TermContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(142);
				((TermContext)_localctx).factor = factor();
				 ((TermContext)_localctx).result =  factory.createBinary(((TermContext)_localctx).op, _localctx.result, ((TermContext)_localctx).factor.result); 
				}
				}
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public PreProExpressionNode result;
		public Token IDENTIFIER;
		public FunctionCallStatementContext functionCallStatement;
		public Token STRING_LITERAL;
		public Token NUMERIC_LITERAL;
		public Token s;
		public ArithmeticContext expr;
		public Token e;
		public TerminalNode IDENTIFIER() { return getToken(PreProParser.IDENTIFIER, 0); }
		public FunctionCallStatementContext functionCallStatement() {
			return getRuleContext(FunctionCallStatementContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(PreProParser.STRING_LITERAL, 0); }
		public TerminalNode NUMERIC_LITERAL() { return getToken(PreProParser.NUMERIC_LITERAL, 0); }
		public ArithmeticContext arithmetic() {
			return getRuleContext(ArithmeticContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_factor);
		try {
			setState(167);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				((FactorContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 PreProExpressionNode assignmentName = factory.createStringLiteral(((FactorContext)_localctx).IDENTIFIER, false); 
				setState(156);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__2:
					{
					setState(152);
					((FactorContext)_localctx).functionCallStatement = functionCallStatement(assignmentName);
					 ((FactorContext)_localctx).result =  ((FactorContext)_localctx).functionCallStatement.result; 
					}
					break;
				case T__3:
				case T__8:
				case T__9:
				case T__13:
				case T__14:
				case T__15:
				case T__16:
				case T__17:
				case T__18:
				case T__19:
				case T__20:
				case T__21:
				case T__22:
				case T__23:
				case T__24:
				case T__25:
					{
					 ((FactorContext)_localctx).result =  factory.createRead(assignmentName); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				((FactorContext)_localctx).STRING_LITERAL = match(STRING_LITERAL);
				 ((FactorContext)_localctx).result =  factory.createStringLiteral(((FactorContext)_localctx).STRING_LITERAL, true); 
				}
				break;
			case NUMERIC_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(160);
				((FactorContext)_localctx).NUMERIC_LITERAL = match(NUMERIC_LITERAL);
				 ((FactorContext)_localctx).result =  factory.createNumericLiteral(((FactorContext)_localctx).NUMERIC_LITERAL); 
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 4);
				{
				setState(162);
				((FactorContext)_localctx).s = match(T__2);
				setState(163);
				((FactorContext)_localctx).expr = arithmetic();
				setState(164);
				((FactorContext)_localctx).e = match(T__3);
				 ((FactorContext)_localctx).result =  factory.createParenExpression(((FactorContext)_localctx).expr.result, ((FactorContext)_localctx).s.getStartIndex(), ((FactorContext)_localctx).e.getStopIndex() - ((FactorContext)_localctx).s.getStartIndex() + 1); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallStatementContext extends ParserRuleContext {
		public PreProExpressionNode assignmentName;
		public PreProExpressionNode result;
		public ArithmeticContext arithmetic;
		public Token e;
		public List<ArithmeticContext> arithmetic() {
			return getRuleContexts(ArithmeticContext.class);
		}
		public ArithmeticContext arithmetic(int i) {
			return getRuleContext(ArithmeticContext.class,i);
		}
		public FunctionCallStatementContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public FunctionCallStatementContext(ParserRuleContext parent, int invokingState, PreProExpressionNode assignmentName) {
			super(parent, invokingState);
			this.assignmentName = assignmentName;
		}
		@Override public int getRuleIndex() { return RULE_functionCallStatement; }
	}

	public final FunctionCallStatementContext functionCallStatement(PreProExpressionNode assignmentName) throws RecognitionException {
		FunctionCallStatementContext _localctx = new FunctionCallStatementContext(_ctx, getState(), assignmentName);
		enterRule(_localctx, 20, RULE_functionCallStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(T__2);
			 List<PreProExpressionNode> parameters = new ArrayList<>();
			                                      ((FunctionCallStatementContext)_localctx).result =  factory.createRead(assignmentName); 
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << IDENTIFIER) | (1L << STRING_LITERAL) | (1L << NUMERIC_LITERAL))) != 0)) {
				{
				setState(171);
				((FunctionCallStatementContext)_localctx).arithmetic = arithmetic();
				 parameters.add(((FunctionCallStatementContext)_localctx).arithmetic.result); 
				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(173);
					match(T__9);
					setState(174);
					((FunctionCallStatementContext)_localctx).arithmetic = arithmetic();
					 parameters.add(((FunctionCallStatementContext)_localctx).arithmetic.result); 
					}
					}
					setState(181);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(184);
			((FunctionCallStatementContext)_localctx).e = match(T__3);
			 ((FunctionCallStatementContext)_localctx).result =  factory.createCall(_localctx.result, parameters, ((FunctionCallStatementContext)_localctx).e); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3#\u00be\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\3\2\3\2\5\2\33\n\2\3\2\3\2\7\2\37\n\2\f\2\16\2\"\13\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\60\n\3\f\3\16\3\63\13"+
		"\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4A\n\4\3\4\3\4\3"+
		"\4\3\4\3\4\7\4H\n\4\f\4\16\4K\13\4\3\4\3\4\3\4\3\4\3\4\5\4R\n\4\3\4\3"+
		"\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5^\n\5\f\5\16\5a\13\5\5\5c\n\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6t\n\6\3"+
		"\7\5\7w\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\7\t\u0089\n\t\f\t\16\t\u008c\13\t\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u0094"+
		"\n\n\f\n\16\n\u0097\13\n\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u009f\n\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00aa\n\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00b4\n\f\f\f\16\f\u00b7\13\f\5\f\u00b9\n"+
		"\f\3\f\3\f\3\f\3\f\2\2\r\2\4\6\b\n\f\16\20\22\24\26\2\4\3\2\20\21\3\2"+
		"\22\34\2\u00c7\2\32\3\2\2\2\4%\3\2\2\2\6\67\3\2\2\2\bb\3\2\2\2\ns\3\2"+
		"\2\2\fv\3\2\2\2\16~\3\2\2\2\20\u0082\3\2\2\2\22\u008d\3\2\2\2\24\u00a9"+
		"\3\2\2\2\26\u00ab\3\2\2\2\30\33\5\4\3\2\31\33\5\6\4\2\32\30\3\2\2\2\32"+
		"\31\3\2\2\2\33 \3\2\2\2\34\37\5\4\3\2\35\37\5\6\4\2\36\34\3\2\2\2\36\35"+
		"\3\2\2\2\37\"\3\2\2\2 \36\3\2\2\2 !\3\2\2\2!#\3\2\2\2\" \3\2\2\2#$\7\2"+
		"\2\3$\3\3\2\2\2%&\7\3\2\2&\'\7\4\2\2\'(\7\5\2\2()\b\3\1\2)*\7\6\2\2*+"+
		"\b\3\1\2+\61\7\7\2\2,-\5\n\6\2-.\b\3\1\2.\60\3\2\2\2/,\3\2\2\2\60\63\3"+
		"\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\64\3\2\2\2\63\61\3\2\2\2\64\65\7\b"+
		"\2\2\65\66\b\3\1\2\66\5\3\2\2\2\678\7\3\2\289\7!\2\29:\7\5\2\2:;\b\4\1"+
		"\2;<\5\b\5\2<@\7\6\2\2=>\7\t\2\2>?\7 \2\2?A\b\4\1\2@=\3\2\2\2@A\3\2\2"+
		"\2AB\3\2\2\2BC\b\4\1\2CI\7\7\2\2DE\5\n\6\2EF\b\4\1\2FH\3\2\2\2GD\3\2\2"+
		"\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JQ\3\2\2\2KI\3\2\2\2LM\7\n\2\2MN\5\20"+
		"\t\2NO\b\4\1\2OP\7\13\2\2PR\3\2\2\2QL\3\2\2\2QR\3\2\2\2RS\3\2\2\2ST\7"+
		"\b\2\2TU\b\4\1\2U\7\3\2\2\2VW\7 \2\2WX\7!\2\2X_\b\5\1\2YZ\7\f\2\2Z[\7"+
		" \2\2[\\\7!\2\2\\^\b\5\1\2]Y\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`c\3"+
		"\2\2\2a_\3\2\2\2bV\3\2\2\2bc\3\2\2\2c\t\3\2\2\2de\5\f\7\2ef\7\13\2\2f"+
		"g\b\6\1\2gt\3\2\2\2hi\5\20\t\2ij\7\13\2\2jk\b\6\1\2kt\3\2\2\2lm\5\16\b"+
		"\2mn\7\13\2\2no\b\6\1\2ot\3\2\2\2pq\7\r\2\2qr\b\6\1\2rt\7\13\2\2sd\3\2"+
		"\2\2sh\3\2\2\2sl\3\2\2\2sp\3\2\2\2t\13\3\2\2\2uw\7 \2\2vu\3\2\2\2vw\3"+
		"\2\2\2wx\3\2\2\2xy\7!\2\2yz\b\7\1\2z{\7\16\2\2{|\5\20\t\2|}\b\7\1\2}\r"+
		"\3\2\2\2~\177\7\17\2\2\177\u0080\7\"\2\2\u0080\u0081\b\b\1\2\u0081\17"+
		"\3\2\2\2\u0082\u0083\5\22\n\2\u0083\u008a\b\t\1\2\u0084\u0085\t\2\2\2"+
		"\u0085\u0086\5\22\n\2\u0086\u0087\b\t\1\2\u0087\u0089\3\2\2\2\u0088\u0084"+
		"\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b"+
		"\21\3\2\2\2\u008c\u008a\3\2\2\2\u008d\u008e\5\24\13\2\u008e\u0095\b\n"+
		"\1\2\u008f\u0090\t\3\2\2\u0090\u0091\5\24\13\2\u0091\u0092\b\n\1\2\u0092"+
		"\u0094\3\2\2\2\u0093\u008f\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0093\3\2"+
		"\2\2\u0095\u0096\3\2\2\2\u0096\23\3\2\2\2\u0097\u0095\3\2\2\2\u0098\u0099"+
		"\7!\2\2\u0099\u009e\b\13\1\2\u009a\u009b\5\26\f\2\u009b\u009c\b\13\1\2"+
		"\u009c\u009f\3\2\2\2\u009d\u009f\b\13\1\2\u009e\u009a\3\2\2\2\u009e\u009d"+
		"\3\2\2\2\u009f\u00aa\3\2\2\2\u00a0\u00a1\7\"\2\2\u00a1\u00aa\b\13\1\2"+
		"\u00a2\u00a3\7#\2\2\u00a3\u00aa\b\13\1\2\u00a4\u00a5\7\5\2\2\u00a5\u00a6"+
		"\5\20\t\2\u00a6\u00a7\7\6\2\2\u00a7\u00a8\b\13\1\2\u00a8\u00aa\3\2\2\2"+
		"\u00a9\u0098\3\2\2\2\u00a9\u00a0\3\2\2\2\u00a9\u00a2\3\2\2\2\u00a9\u00a4"+
		"\3\2\2\2\u00aa\25\3\2\2\2\u00ab\u00ac\7\5\2\2\u00ac\u00b8\b\f\1\2\u00ad"+
		"\u00ae\5\20\t\2\u00ae\u00b5\b\f\1\2\u00af\u00b0\7\f\2\2\u00b0\u00b1\5"+
		"\20\t\2\u00b1\u00b2\b\f\1\2\u00b2\u00b4\3\2\2\2\u00b3\u00af\3\2\2\2\u00b4"+
		"\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b9\3\2"+
		"\2\2\u00b7\u00b5\3\2\2\2\u00b8\u00ad\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9"+
		"\u00ba\3\2\2\2\u00ba\u00bb\7\6\2\2\u00bb\u00bc\b\f\1\2\u00bc\27\3\2\2"+
		"\2\23\32\36 \61@IQ_bsv\u008a\u0095\u009e\u00a9\u00b5\u00b8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}