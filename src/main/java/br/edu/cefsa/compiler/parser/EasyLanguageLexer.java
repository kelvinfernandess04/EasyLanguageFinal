// Generated from EasyLanguage.g4 by ANTLR 4.12.0
package br.edu.cefsa.compiler.parser;

	import br.edu.cefsa.compiler.datastructures.EasySymbol;
	import br.edu.cefsa.compiler.datastructures.EasyVariable;
	import br.edu.cefsa.compiler.datastructures.EasySymbolTable;
	import br.edu.cefsa.compiler.exceptions.EasySemanticException;
	import br.edu.cefsa.compiler.abstractsyntaxtree.EasyProgram;
	import br.edu.cefsa.compiler.abstractsyntaxtree.AbstractCommand;
	import br.edu.cefsa.compiler.abstractsyntaxtree.CommandLeitura;
	import br.edu.cefsa.compiler.abstractsyntaxtree.CommandEscrita;
	import br.edu.cefsa.compiler.abstractsyntaxtree.CommandAtribuicao;
	import br.edu.cefsa.compiler.abstractsyntaxtree.CommandDecisao;
	import java.util.ArrayList;
	import java.util.Stack;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class EasyLanguageLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, AP=14, FP=15, SC=16, VIR=17, ACH=18, 
		FCH=19, ATTR=20, OPREL=21, ID=22, NUMBER=23, WS=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "AP", "FP", "SC", "VIR", "ACH", "FCH", 
			"ATTR", "OPREL", "ID", "NUMBER", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'leia'", "'escreva'", 
			"'se'", "'senao'", "'+'", "'-'", "'*'", "'/'", "'^'", "'('", "')'", "';'", 
			"','", "'{'", "'}'", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "AP", "FP", "SC", "VIR", "ACH", "FCH", "ATTR", "OPREL", "ID", 
			"NUMBER", "WS"
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


		private int _tipo;                       // usado na declaração de variáveis
		private String _varName;
		private String _varValue;
		private EasySymbolTable symbolTable = new EasySymbolTable();
		private EasySymbol symbol;
		private EasyProgram program = new EasyProgram();
		private ArrayList<AbstractCommand> curThread;
		private Stack<ArrayList<AbstractCommand>> stack = new Stack<>();
		private String _readID;
		private String _writeID;
		private String _exprID;
		private String _exprContent;
		private String _exprDecision;
		private ArrayList<AbstractCommand> listaTrue;
		private ArrayList<AbstractCommand> listaFalse;

		public void verificaID(String id){
			if (!symbolTable.exists(id)){
				throw new EasySemanticException("Symbol '"+id+"' not declared");
			}
		}

		public void exibeComandos(){
			for (AbstractCommand c: program.getComandos()){
				System.out.println(c);
			}
		}

		public void generateCode(){
			program.generateTarget();
		}


	public EasyLanguageLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "EasyLanguage.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0018\u00a1\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u0088"+
		"\b\u0014\u0001\u0015\u0001\u0015\u0005\u0015\u008c\b\u0015\n\u0015\f\u0015"+
		"\u008f\t\u0015\u0001\u0016\u0004\u0016\u0092\b\u0016\u000b\u0016\f\u0016"+
		"\u0093\u0001\u0016\u0001\u0016\u0004\u0016\u0098\b\u0016\u000b\u0016\f"+
		"\u0016\u0099\u0003\u0016\u009c\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0000\u0000\u0018\u0001\u0001\u0003\u0002\u0005\u0003\u0007"+
		"\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b"+
		"\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013"+
		"\'\u0014)\u0015+\u0016-\u0017/\u0018\u0001\u0000\u0005\u0002\u0000<<>"+
		">\u0001\u0000az\u0003\u000009AZaz\u0001\u000009\u0003\u0000\t\n\r\r  "+
		"\u00a8\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000"+
		"\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000"+
		"\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000"+
		"\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000"+
		"\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000"+
		"\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000"+
		"\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000"+
		"\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000"+
		"!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001"+
		"\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000"+
		"\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000"+
		"\u0000/\u0001\u0000\u0000\u0000\u00011\u0001\u0000\u0000\u0000\u0003:"+
		"\u0001\u0000\u0000\u0000\u0005C\u0001\u0000\u0000\u0000\u0007J\u0001\u0000"+
		"\u0000\u0000\tP\u0001\u0000\u0000\u0000\u000bU\u0001\u0000\u0000\u0000"+
		"\r]\u0001\u0000\u0000\u0000\u000f`\u0001\u0000\u0000\u0000\u0011f\u0001"+
		"\u0000\u0000\u0000\u0013h\u0001\u0000\u0000\u0000\u0015j\u0001\u0000\u0000"+
		"\u0000\u0017l\u0001\u0000\u0000\u0000\u0019n\u0001\u0000\u0000\u0000\u001b"+
		"p\u0001\u0000\u0000\u0000\u001dr\u0001\u0000\u0000\u0000\u001ft\u0001"+
		"\u0000\u0000\u0000!v\u0001\u0000\u0000\u0000#x\u0001\u0000\u0000\u0000"+
		"%z\u0001\u0000\u0000\u0000\'|\u0001\u0000\u0000\u0000)\u0087\u0001\u0000"+
		"\u0000\u0000+\u0089\u0001\u0000\u0000\u0000-\u0091\u0001\u0000\u0000\u0000"+
		"/\u009d\u0001\u0000\u0000\u000012\u0005p\u0000\u000023\u0005r\u0000\u0000"+
		"34\u0005o\u0000\u000045\u0005g\u0000\u000056\u0005r\u0000\u000067\u0005"+
		"a\u0000\u000078\u0005m\u0000\u000089\u0005a\u0000\u00009\u0002\u0001\u0000"+
		"\u0000\u0000:;\u0005f\u0000\u0000;<\u0005i\u0000\u0000<=\u0005m\u0000"+
		"\u0000=>\u0005p\u0000\u0000>?\u0005r\u0000\u0000?@\u0005o\u0000\u0000"+
		"@A\u0005g\u0000\u0000AB\u0005;\u0000\u0000B\u0004\u0001\u0000\u0000\u0000"+
		"CD\u0005n\u0000\u0000DE\u0005u\u0000\u0000EF\u0005m\u0000\u0000FG\u0005"+
		"e\u0000\u0000GH\u0005r\u0000\u0000HI\u0005o\u0000\u0000I\u0006\u0001\u0000"+
		"\u0000\u0000JK\u0005t\u0000\u0000KL\u0005e\u0000\u0000LM\u0005x\u0000"+
		"\u0000MN\u0005t\u0000\u0000NO\u0005o\u0000\u0000O\b\u0001\u0000\u0000"+
		"\u0000PQ\u0005l\u0000\u0000QR\u0005e\u0000\u0000RS\u0005i\u0000\u0000"+
		"ST\u0005a\u0000\u0000T\n\u0001\u0000\u0000\u0000UV\u0005e\u0000\u0000"+
		"VW\u0005s\u0000\u0000WX\u0005c\u0000\u0000XY\u0005r\u0000\u0000YZ\u0005"+
		"e\u0000\u0000Z[\u0005v\u0000\u0000[\\\u0005a\u0000\u0000\\\f\u0001\u0000"+
		"\u0000\u0000]^\u0005s\u0000\u0000^_\u0005e\u0000\u0000_\u000e\u0001\u0000"+
		"\u0000\u0000`a\u0005s\u0000\u0000ab\u0005e\u0000\u0000bc\u0005n\u0000"+
		"\u0000cd\u0005a\u0000\u0000de\u0005o\u0000\u0000e\u0010\u0001\u0000\u0000"+
		"\u0000fg\u0005+\u0000\u0000g\u0012\u0001\u0000\u0000\u0000hi\u0005-\u0000"+
		"\u0000i\u0014\u0001\u0000\u0000\u0000jk\u0005*\u0000\u0000k\u0016\u0001"+
		"\u0000\u0000\u0000lm\u0005/\u0000\u0000m\u0018\u0001\u0000\u0000\u0000"+
		"no\u0005^\u0000\u0000o\u001a\u0001\u0000\u0000\u0000pq\u0005(\u0000\u0000"+
		"q\u001c\u0001\u0000\u0000\u0000rs\u0005)\u0000\u0000s\u001e\u0001\u0000"+
		"\u0000\u0000tu\u0005;\u0000\u0000u \u0001\u0000\u0000\u0000vw\u0005,\u0000"+
		"\u0000w\"\u0001\u0000\u0000\u0000xy\u0005{\u0000\u0000y$\u0001\u0000\u0000"+
		"\u0000z{\u0005}\u0000\u0000{&\u0001\u0000\u0000\u0000|}\u0005=\u0000\u0000"+
		"}(\u0001\u0000\u0000\u0000~\u0088\u0007\u0000\u0000\u0000\u007f\u0080"+
		"\u0005>\u0000\u0000\u0080\u0088\u0005=\u0000\u0000\u0081\u0082\u0005<"+
		"\u0000\u0000\u0082\u0088\u0005=\u0000\u0000\u0083\u0084\u0005=\u0000\u0000"+
		"\u0084\u0088\u0005=\u0000\u0000\u0085\u0086\u0005!\u0000\u0000\u0086\u0088"+
		"\u0005=\u0000\u0000\u0087~\u0001\u0000\u0000\u0000\u0087\u007f\u0001\u0000"+
		"\u0000\u0000\u0087\u0081\u0001\u0000\u0000\u0000\u0087\u0083\u0001\u0000"+
		"\u0000\u0000\u0087\u0085\u0001\u0000\u0000\u0000\u0088*\u0001\u0000\u0000"+
		"\u0000\u0089\u008d\u0007\u0001\u0000\u0000\u008a\u008c\u0007\u0002\u0000"+
		"\u0000\u008b\u008a\u0001\u0000\u0000\u0000\u008c\u008f\u0001\u0000\u0000"+
		"\u0000\u008d\u008b\u0001\u0000\u0000\u0000\u008d\u008e\u0001\u0000\u0000"+
		"\u0000\u008e,\u0001\u0000\u0000\u0000\u008f\u008d\u0001\u0000\u0000\u0000"+
		"\u0090\u0092\u0007\u0003\u0000\u0000\u0091\u0090\u0001\u0000\u0000\u0000"+
		"\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0091\u0001\u0000\u0000\u0000"+
		"\u0093\u0094\u0001\u0000\u0000\u0000\u0094\u009b\u0001\u0000\u0000\u0000"+
		"\u0095\u0097\u0005.\u0000\u0000\u0096\u0098\u0007\u0003\u0000\u0000\u0097"+
		"\u0096\u0001\u0000\u0000\u0000\u0098\u0099\u0001\u0000\u0000\u0000\u0099"+
		"\u0097\u0001\u0000\u0000\u0000\u0099\u009a\u0001\u0000\u0000\u0000\u009a"+
		"\u009c\u0001\u0000\u0000\u0000\u009b\u0095\u0001\u0000\u0000\u0000\u009b"+
		"\u009c\u0001\u0000\u0000\u0000\u009c.\u0001\u0000\u0000\u0000\u009d\u009e"+
		"\u0007\u0004\u0000\u0000\u009e\u009f\u0001\u0000\u0000\u0000\u009f\u00a0"+
		"\u0006\u0017\u0000\u0000\u00a00\u0001\u0000\u0000\u0000\u0007\u0000\u0087"+
		"\u008b\u008d\u0093\u0099\u009b\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}