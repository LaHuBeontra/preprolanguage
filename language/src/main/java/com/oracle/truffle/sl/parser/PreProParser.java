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
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, WS=31, COMMENT=32, 
		LINE_COMMENT=33, TYPE=34, IDENTIFIER=35, STRING_LITERAL=36, NUMERIC_LITERAL=37;
	public static final int
		RULE_prepro = 0, RULE_mainFunction = 1, RULE_function = 2, RULE_functionArguments = 3, 
		RULE_importDefinitions = 4, RULE_exportDefinitions = 5, RULE_statement = 6, 
		RULE_assignment = 7, RULE_throwStatement = 8, RULE_arithmetic = 9, RULE_term = 10, 
		RULE_factor = 11, RULE_functionCallStatement = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"prepro", "mainFunction", "function", "functionArguments", "importDefinitions", 
			"exportDefinitions", "statement", "assignment", "throwStatement", "arithmetic", 
			"term", "factor", "functionCallStatement"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'function'", "'main'", "'('", "')'", "'{'", "'import'", "';'", 
			"'export'", "'}'", "'returns'", "'return'", "','", "'optional'", "'debugger'", 
			"'='", "'throw'", "'+'", "'-'", "'*'", "'/'", "'X'", "'**'", "'=='", 
			"'<='", "'>='", "'&&'", "'||'", "'<'", "'>'", "'exists'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "WS", "COMMENT", "LINE_COMMENT", 
			"TYPE", "IDENTIFIER", "STRING_LITERAL", "NUMERIC_LITERAL"
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
			setState(28);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
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
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				setState(32);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(30);
					mainFunction();
					}
					break;
				case 2:
					{
					setState(31);
					function();
					}
					break;
				}
				}
				setState(36);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(37);
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
		public ImportDefinitionsContext importDefinitions() {
			return getRuleContext(ImportDefinitionsContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ExportDefinitionsContext exportDefinitions() {
			return getRuleContext(ExportDefinitionsContext.class,0);
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
			setState(39);
			match(T__0);
			setState(40);
			((MainFunctionContext)_localctx).main = match(T__1);
			setState(41);
			((MainFunctionContext)_localctx).s = match(T__2);
			 factory.startFunction(((MainFunctionContext)_localctx).main, ((MainFunctionContext)_localctx).s); 
			setState(43);
			match(T__3);
			 factory.startBlock();
			                                                  List<PreProStatementNode> body = new ArrayList<>(); 
			setState(45);
			match(T__4);
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(46);
				match(T__5);
				setState(47);
				importDefinitions();
				setState(48);
				match(T__6);
				}
			}

			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__13) | (1L << T__15) | (1L << T__29) | (1L << TYPE) | (1L << IDENTIFIER) | (1L << STRING_LITERAL) | (1L << NUMERIC_LITERAL))) != 0)) {
				{
				{
				setState(52);
				((MainFunctionContext)_localctx).statement = statement();
				 body.add(((MainFunctionContext)_localctx).statement.result); 
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(60);
				match(T__7);
				setState(61);
				exportDefinitions();
				setState(62);
				match(T__6);
				}
			}

			setState(66);
			((MainFunctionContext)_localctx).e = match(T__8);
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
			setState(69);
			match(T__0);
			setState(70);
			((FunctionContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(71);
			((FunctionContext)_localctx).s = match(T__2);
			 factory.startFunction(((FunctionContext)_localctx).IDENTIFIER, ((FunctionContext)_localctx).s); 
			setState(73);
			functionArguments();
			setState(74);
			match(T__3);
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(75);
				match(T__9);
				setState(76);
				((FunctionContext)_localctx).TYPE = match(TYPE);
				factory.addReturnType(((FunctionContext)_localctx).TYPE);
				}
			}

			 factory.startBlock();
			                                                  List<PreProStatementNode> body = new ArrayList<>(); 
			setState(81);
			match(T__4);
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__13) | (1L << T__15) | (1L << T__29) | (1L << TYPE) | (1L << IDENTIFIER) | (1L << STRING_LITERAL) | (1L << NUMERIC_LITERAL))) != 0)) {
				{
				{
				setState(82);
				((FunctionContext)_localctx).statement = statement();
				 body.add(((FunctionContext)_localctx).statement.result); 
				}
				}
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(90);
				((FunctionContext)_localctx).r = match(T__10);
				setState(91);
				((FunctionContext)_localctx).arithmetic = arithmetic();
				 body.add(factory.createReturn(((FunctionContext)_localctx).r, ((FunctionContext)_localctx).arithmetic.result));
				setState(93);
				match(T__6);
				}
			}

			setState(97);
			((FunctionContext)_localctx).e = match(T__8);
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
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TYPE) {
				{
				setState(100);
				((FunctionArgumentsContext)_localctx).TYPE = match(TYPE);
				setState(101);
				((FunctionArgumentsContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 factory.addFormalParameter(((FunctionArgumentsContext)_localctx).TYPE, factory.createStringLiteral(((FunctionArgumentsContext)_localctx).IDENTIFIER, false)); 
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__11) {
					{
					{
					setState(103);
					match(T__11);
					setState(104);
					((FunctionArgumentsContext)_localctx).TYPE = match(TYPE);
					setState(105);
					((FunctionArgumentsContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					 factory.addFormalParameter(((FunctionArgumentsContext)_localctx).TYPE, factory.createStringLiteral(((FunctionArgumentsContext)_localctx).IDENTIFIER, false)); 
					}
					}
					setState(111);
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

	public static class ImportDefinitionsContext extends ParserRuleContext {
		public List<TerminalNode> TYPE() { return getTokens(PreProParser.TYPE); }
		public TerminalNode TYPE(int i) {
			return getToken(PreProParser.TYPE, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(PreProParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PreProParser.IDENTIFIER, i);
		}
		public ImportDefinitionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDefinitions; }
	}

	public final ImportDefinitionsContext importDefinitions() throws RecognitionException {
		ImportDefinitionsContext _localctx = new ImportDefinitionsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_importDefinitions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12 || _la==TYPE) {
				{
				setState(119);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case TYPE:
					{
					setState(114);
					match(TYPE);
					setState(115);
					match(IDENTIFIER);
					}
					break;
				case T__12:
					{
					setState(116);
					match(T__12);
					setState(117);
					match(TYPE);
					setState(118);
					match(IDENTIFIER);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(132);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__11) {
					{
					{
					setState(128);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						setState(121);
						match(T__11);
						setState(122);
						match(TYPE);
						setState(123);
						match(IDENTIFIER);
						}
						break;
					case 2:
						{
						setState(124);
						match(T__11);
						setState(125);
						match(T__12);
						setState(126);
						match(TYPE);
						setState(127);
						match(IDENTIFIER);
						}
						break;
					}
					}
					}
					setState(134);
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

	public static class ExportDefinitionsContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(PreProParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PreProParser.IDENTIFIER, i);
		}
		public ExportDefinitionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exportDefinitions; }
	}

	public final ExportDefinitionsContext exportDefinitions() throws RecognitionException {
		ExportDefinitionsContext _localctx = new ExportDefinitionsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_exportDefinitions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(IDENTIFIER);
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(138);
				match(T__11);
				setState(139);
				match(IDENTIFIER);
				}
				}
				setState(144);
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
		enterRule(_localctx, 12, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE:
				{
				setState(145);
				((StatementContext)_localctx).assignment = assignment();
				setState(146);
				match(T__6);
				 ((StatementContext)_localctx).result =  ((StatementContext)_localctx).assignment.result; 
				}
				break;
			case T__2:
			case T__29:
			case IDENTIFIER:
			case STRING_LITERAL:
			case NUMERIC_LITERAL:
				{
				setState(149);
				((StatementContext)_localctx).arithmetic = arithmetic();
				setState(150);
				match(T__6);
				 ((StatementContext)_localctx).result =  ((StatementContext)_localctx).arithmetic.result; 
				}
				break;
			case T__15:
				{
				setState(153);
				((StatementContext)_localctx).throwStatement = throwStatement();
				setState(154);
				match(T__6);
				 ((StatementContext)_localctx).result =  ((StatementContext)_localctx).throwStatement.result; 
				}
				break;
			case T__13:
				{
				setState(157);
				((StatementContext)_localctx).d = match(T__13);
				 ((StatementContext)_localctx).result =  factory.createDebugger(((StatementContext)_localctx).d); 
				setState(159);
				match(T__6);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		public TerminalNode TYPE() { return getToken(PreProParser.TYPE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PreProParser.IDENTIFIER, 0); }
		public ArithmeticContext arithmetic() {
			return getRuleContext(ArithmeticContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			((AssignmentContext)_localctx).TYPE = match(TYPE);
			setState(163);
			((AssignmentContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			 PreProExpressionNode assignmentName = factory.createStringLiteral(((AssignmentContext)_localctx).IDENTIFIER, false); 
			setState(165);
			match(T__14);
			{
			setState(166);
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
		enterRule(_localctx, 16, RULE_throwStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(T__15);
			{
			setState(170);
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
		enterRule(_localctx, 18, RULE_arithmetic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			((ArithmeticContext)_localctx).term = term();
			 ((ArithmeticContext)_localctx).result =  ((ArithmeticContext)_localctx).term.result; 
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16 || _la==T__17) {
				{
				{
				setState(175);
				((ArithmeticContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__16 || _la==T__17) ) {
					((ArithmeticContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(176);
				((ArithmeticContext)_localctx).term = term();
				 ((ArithmeticContext)_localctx).result =  factory.createBinary(((ArithmeticContext)_localctx).op, _localctx.result, ((ArithmeticContext)_localctx).term.result); 
				}
				}
				setState(183);
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
		enterRule(_localctx, 20, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			((TermContext)_localctx).factor = factor();
			 ((TermContext)_localctx).result =  ((TermContext)_localctx).factor.result; 
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28))) != 0)) {
				{
				{
				setState(186);
				((TermContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28))) != 0)) ) {
					((TermContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(187);
				((TermContext)_localctx).factor = factor();
				 ((TermContext)_localctx).result =  factory.createBinary(((TermContext)_localctx).op, _localctx.result, ((TermContext)_localctx).factor.result); 
				}
				}
				setState(194);
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
		enterRule(_localctx, 22, RULE_factor);
		try {
			setState(217);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(195);
				((FactorContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 PreProExpressionNode assignmentName = factory.createStringLiteral(((FactorContext)_localctx).IDENTIFIER, false); 
				setState(201);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__2:
					{
					setState(197);
					((FactorContext)_localctx).functionCallStatement = functionCallStatement(assignmentName);
					 ((FactorContext)_localctx).result =  ((FactorContext)_localctx).functionCallStatement.result; 
					}
					break;
				case T__3:
				case T__6:
				case T__11:
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
				case T__26:
				case T__27:
				case T__28:
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
				setState(203);
				((FactorContext)_localctx).STRING_LITERAL = match(STRING_LITERAL);
				 ((FactorContext)_localctx).result =  factory.createStringLiteral(((FactorContext)_localctx).STRING_LITERAL, true); 
				}
				break;
			case NUMERIC_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(205);
				((FactorContext)_localctx).NUMERIC_LITERAL = match(NUMERIC_LITERAL);
				 ((FactorContext)_localctx).result =  factory.createNumericLiteral(((FactorContext)_localctx).NUMERIC_LITERAL); 
				}
				break;
			case T__29:
				enterOuterAlt(_localctx, 4);
				{
				setState(207);
				match(T__29);
				setState(208);
				match(T__2);
				setState(209);
				((FactorContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(210);
				match(T__3);
				 PreProExpressionNode assignmentName = factory.createStringLiteral(((FactorContext)_localctx).IDENTIFIER, false);
				                                      ((FactorContext)_localctx).result =  factory.createRead(assignmentName);
				                                      ((FactorContext)_localctx).result =  factory.createExists(_localctx.result); 
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 5);
				{
				setState(212);
				((FactorContext)_localctx).s = match(T__2);
				setState(213);
				((FactorContext)_localctx).expr = arithmetic();
				setState(214);
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
		enterRule(_localctx, 24, RULE_functionCallStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(T__2);
			 List<PreProExpressionNode> parameters = new ArrayList<>();
			                                      ((FunctionCallStatementContext)_localctx).result =  factory.createRead(assignmentName); 
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__29) | (1L << IDENTIFIER) | (1L << STRING_LITERAL) | (1L << NUMERIC_LITERAL))) != 0)) {
				{
				setState(221);
				((FunctionCallStatementContext)_localctx).arithmetic = arithmetic();
				 parameters.add(((FunctionCallStatementContext)_localctx).arithmetic.result); 
				setState(229);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__11) {
					{
					{
					setState(223);
					match(T__11);
					setState(224);
					((FunctionCallStatementContext)_localctx).arithmetic = arithmetic();
					 parameters.add(((FunctionCallStatementContext)_localctx).arithmetic.result); 
					}
					}
					setState(231);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(234);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\'\u00f0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\5\2\37\n\2\3\2\3\2\7\2#\n\2\f\2"+
		"\16\2&\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\65"+
		"\n\3\3\3\3\3\3\3\7\3:\n\3\f\3\16\3=\13\3\3\3\3\3\3\3\3\3\5\3C\n\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4Q\n\4\3\4\3\4\3\4\3\4"+
		"\3\4\7\4X\n\4\f\4\16\4[\13\4\3\4\3\4\3\4\3\4\3\4\5\4b\n\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5n\n\5\f\5\16\5q\13\5\5\5s\n\5\3\6\3\6"+
		"\3\6\3\6\3\6\5\6z\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0083\n\6\7\6\u0085"+
		"\n\6\f\6\16\6\u0088\13\6\5\6\u008a\n\6\3\7\3\7\3\7\7\7\u008f\n\7\f\7\16"+
		"\7\u0092\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\5\b\u00a3\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\7\13\u00b6\n\13\f\13\16\13\u00b9\13\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\7\f\u00c1\n\f\f\f\16\f\u00c4\13\f\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\5\r\u00cc\n\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\5\r\u00dc\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00e6"+
		"\n\16\f\16\16\16\u00e9\13\16\5\16\u00eb\n\16\3\16\3\16\3\16\3\16\2\2\17"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\2\4\3\2\23\24\3\2\25\37\2\u00fe\2\36"+
		"\3\2\2\2\4)\3\2\2\2\6G\3\2\2\2\br\3\2\2\2\n\u0089\3\2\2\2\f\u008b\3\2"+
		"\2\2\16\u00a2\3\2\2\2\20\u00a4\3\2\2\2\22\u00ab\3\2\2\2\24\u00af\3\2\2"+
		"\2\26\u00ba\3\2\2\2\30\u00db\3\2\2\2\32\u00dd\3\2\2\2\34\37\5\4\3\2\35"+
		"\37\5\6\4\2\36\34\3\2\2\2\36\35\3\2\2\2\37$\3\2\2\2 #\5\4\3\2!#\5\6\4"+
		"\2\" \3\2\2\2\"!\3\2\2\2#&\3\2\2\2$\"\3\2\2\2$%\3\2\2\2%\'\3\2\2\2&$\3"+
		"\2\2\2\'(\7\2\2\3(\3\3\2\2\2)*\7\3\2\2*+\7\4\2\2+,\7\5\2\2,-\b\3\1\2-"+
		".\7\6\2\2./\b\3\1\2/\64\7\7\2\2\60\61\7\b\2\2\61\62\5\n\6\2\62\63\7\t"+
		"\2\2\63\65\3\2\2\2\64\60\3\2\2\2\64\65\3\2\2\2\65;\3\2\2\2\66\67\5\16"+
		"\b\2\678\b\3\1\28:\3\2\2\29\66\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<"+
		"B\3\2\2\2=;\3\2\2\2>?\7\n\2\2?@\5\f\7\2@A\7\t\2\2AC\3\2\2\2B>\3\2\2\2"+
		"BC\3\2\2\2CD\3\2\2\2DE\7\13\2\2EF\b\3\1\2F\5\3\2\2\2GH\7\3\2\2HI\7%\2"+
		"\2IJ\7\5\2\2JK\b\4\1\2KL\5\b\5\2LP\7\6\2\2MN\7\f\2\2NO\7$\2\2OQ\b\4\1"+
		"\2PM\3\2\2\2PQ\3\2\2\2QR\3\2\2\2RS\b\4\1\2SY\7\7\2\2TU\5\16\b\2UV\b\4"+
		"\1\2VX\3\2\2\2WT\3\2\2\2X[\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Za\3\2\2\2[Y\3\2"+
		"\2\2\\]\7\r\2\2]^\5\24\13\2^_\b\4\1\2_`\7\t\2\2`b\3\2\2\2a\\\3\2\2\2a"+
		"b\3\2\2\2bc\3\2\2\2cd\7\13\2\2de\b\4\1\2e\7\3\2\2\2fg\7$\2\2gh\7%\2\2"+
		"ho\b\5\1\2ij\7\16\2\2jk\7$\2\2kl\7%\2\2ln\b\5\1\2mi\3\2\2\2nq\3\2\2\2"+
		"om\3\2\2\2op\3\2\2\2ps\3\2\2\2qo\3\2\2\2rf\3\2\2\2rs\3\2\2\2s\t\3\2\2"+
		"\2tu\7$\2\2uz\7%\2\2vw\7\17\2\2wx\7$\2\2xz\7%\2\2yt\3\2\2\2yv\3\2\2\2"+
		"z\u0086\3\2\2\2{|\7\16\2\2|}\7$\2\2}\u0083\7%\2\2~\177\7\16\2\2\177\u0080"+
		"\7\17\2\2\u0080\u0081\7$\2\2\u0081\u0083\7%\2\2\u0082{\3\2\2\2\u0082~"+
		"\3\2\2\2\u0083\u0085\3\2\2\2\u0084\u0082\3\2\2\2\u0085\u0088\3\2\2\2\u0086"+
		"\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0086\3\2"+
		"\2\2\u0089y\3\2\2\2\u0089\u008a\3\2\2\2\u008a\13\3\2\2\2\u008b\u0090\7"+
		"%\2\2\u008c\u008d\7\16\2\2\u008d\u008f\7%\2\2\u008e\u008c\3\2\2\2\u008f"+
		"\u0092\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\r\3\2\2\2"+
		"\u0092\u0090\3\2\2\2\u0093\u0094\5\20\t\2\u0094\u0095\7\t\2\2\u0095\u0096"+
		"\b\b\1\2\u0096\u00a3\3\2\2\2\u0097\u0098\5\24\13\2\u0098\u0099\7\t\2\2"+
		"\u0099\u009a\b\b\1\2\u009a\u00a3\3\2\2\2\u009b\u009c\5\22\n\2\u009c\u009d"+
		"\7\t\2\2\u009d\u009e\b\b\1\2\u009e\u00a3\3\2\2\2\u009f\u00a0\7\20\2\2"+
		"\u00a0\u00a1\b\b\1\2\u00a1\u00a3\7\t\2\2\u00a2\u0093\3\2\2\2\u00a2\u0097"+
		"\3\2\2\2\u00a2\u009b\3\2\2\2\u00a2\u009f\3\2\2\2\u00a3\17\3\2\2\2\u00a4"+
		"\u00a5\7$\2\2\u00a5\u00a6\7%\2\2\u00a6\u00a7\b\t\1\2\u00a7\u00a8\7\21"+
		"\2\2\u00a8\u00a9\5\24\13\2\u00a9\u00aa\b\t\1\2\u00aa\21\3\2\2\2\u00ab"+
		"\u00ac\7\22\2\2\u00ac\u00ad\7&\2\2\u00ad\u00ae\b\n\1\2\u00ae\23\3\2\2"+
		"\2\u00af\u00b0\5\26\f\2\u00b0\u00b7\b\13\1\2\u00b1\u00b2\t\2\2\2\u00b2"+
		"\u00b3\5\26\f\2\u00b3\u00b4\b\13\1\2\u00b4\u00b6\3\2\2\2\u00b5\u00b1\3"+
		"\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8"+
		"\25\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bb\5\30\r\2\u00bb\u00c2\b\f\1"+
		"\2\u00bc\u00bd\t\3\2\2\u00bd\u00be\5\30\r\2\u00be\u00bf\b\f\1\2\u00bf"+
		"\u00c1\3\2\2\2\u00c0\u00bc\3\2\2\2\u00c1\u00c4\3\2\2\2\u00c2\u00c0\3\2"+
		"\2\2\u00c2\u00c3\3\2\2\2\u00c3\27\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00c6"+
		"\7%\2\2\u00c6\u00cb\b\r\1\2\u00c7\u00c8\5\32\16\2\u00c8\u00c9\b\r\1\2"+
		"\u00c9\u00cc\3\2\2\2\u00ca\u00cc\b\r\1\2\u00cb\u00c7\3\2\2\2\u00cb\u00ca"+
		"\3\2\2\2\u00cc\u00dc\3\2\2\2\u00cd\u00ce\7&\2\2\u00ce\u00dc\b\r\1\2\u00cf"+
		"\u00d0\7\'\2\2\u00d0\u00dc\b\r\1\2\u00d1\u00d2\7 \2\2\u00d2\u00d3\7\5"+
		"\2\2\u00d3\u00d4\7%\2\2\u00d4\u00d5\7\6\2\2\u00d5\u00dc\b\r\1\2\u00d6"+
		"\u00d7\7\5\2\2\u00d7\u00d8\5\24\13\2\u00d8\u00d9\7\6\2\2\u00d9\u00da\b"+
		"\r\1\2\u00da\u00dc\3\2\2\2\u00db\u00c5\3\2\2\2\u00db\u00cd\3\2\2\2\u00db"+
		"\u00cf\3\2\2\2\u00db\u00d1\3\2\2\2\u00db\u00d6\3\2\2\2\u00dc\31\3\2\2"+
		"\2\u00dd\u00de\7\5\2\2\u00de\u00ea\b\16\1\2\u00df\u00e0\5\24\13\2\u00e0"+
		"\u00e7\b\16\1\2\u00e1\u00e2\7\16\2\2\u00e2\u00e3\5\24\13\2\u00e3\u00e4"+
		"\b\16\1\2\u00e4\u00e6\3\2\2\2\u00e5\u00e1\3\2\2\2\u00e6\u00e9\3\2\2\2"+
		"\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00eb\3\2\2\2\u00e9\u00e7"+
		"\3\2\2\2\u00ea\u00df\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec"+
		"\u00ed\7\6\2\2\u00ed\u00ee\b\16\1\2\u00ee\33\3\2\2\2\31\36\"$\64;BPYa"+
		"ory\u0082\u0086\u0089\u0090\u00a2\u00b7\u00c2\u00cb\u00db\u00e7\u00ea";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}