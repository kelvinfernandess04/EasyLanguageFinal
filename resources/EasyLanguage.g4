grammar EasyLanguage;

@header{
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
}

@members{
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
}

///////////////////////////////////////////////////////////////////////////////
//  Regras principais
///////////////////////////////////////////////////////////////////////////////

prog
	: 'programa' decl bloco 'fimprog;'
		{
			program.setVarTable(symbolTable);
			program.setComandos(stack.pop());
		}
	;

decl
	: (declaravar)+
	;

declaravar
	: tipo ID
		{
			_varName = _input.LT(-1).getText();
			if (_varName.equals("aAA")) {
				throw new EasySemanticException("Variable name 'aAA' is not allowed");
			}
			_varValue = null;
			symbol = new EasyVariable(_varName, _tipo, _varValue);
			if (!symbolTable.exists(_varName)) {
				symbolTable.add(symbol);
			} else {
				throw new EasySemanticException("Symbol '"+_varName+"' already declared");
			}
		}
		( VIR ID
			{
				_varName = _input.LT(-1).getText();
				_varValue = null;
				symbol = new EasyVariable(_varName, _tipo, _varValue);
				if (!symbolTable.exists(_varName)) {
					symbolTable.add(symbol);
				} else {
					throw new EasySemanticException("Symbol '"+_varName+"' already declared");
				}
			}
		)*
		SC
	;

tipo
	: 'numero' { _tipo = EasyVariable.NUMBER; }
	| 'texto'  { _tipo = EasyVariable.TEXT; }
	;

bloco
	: { 
			curThread = new ArrayList<>(); 
			stack.push(curThread); 
	  }
	  (cmd)+
	;

cmd
	: cmdleitura
	| cmdescrita
	| cmdattrib
	| cmdselecao
	;

cmdleitura
	: 'leia' AP ID
		{
			verificaID(_input.LT(-1).getText());
			_readID = _input.LT(-1).getText();
		}
	  FP SC
		{
			EasyVariable var = (EasyVariable) symbolTable.get(_readID);
			CommandLeitura cmd = new CommandLeitura(_readID, var);
			stack.peek().add(cmd);
			// Após a leitura, considera-se que a variável foi inicializada:
			var.setValue("READ"); // marca como inicializada (conteúdo real virá em tempo de execução)
		}
	;

cmdescrita
	: 'escreva' AP ID
		{
			verificaID(_input.LT(-1).getText());
			_writeID = _input.LT(-1).getText();
		}
	  FP SC
		{
			CommandEscrita cmd = new CommandEscrita(_writeID);
			stack.peek().add(cmd);
		}
	;

cmdattrib
	: ID
		{
			verificaID(_input.LT(-1).getText());
			_exprID = _input.LT(-1).getText();
		}
	  ATTR e=expr SC
		{
			_exprContent = $e.text;
			EasyVariable var = (EasyVariable) symbolTable.get(_exprID);
			// Verifica se tipos são compatíveis
			if (var.getType() != $e.tipoExpr) {
				throw new EasySemanticException(
					"Type error: Cannot assign expression of type "
					+ ($e.tipoExpr == EasyVariable.NUMBER ? "NUMBER" : "TEXT")
					+ " to variable '" + _exprID + "' of type "
					+ (var.getType() == EasyVariable.NUMBER ? "NUMBER" : "TEXT")
				);
			}
			// Marca a variável como inicializada, guardando o conteúdo da expressão:
			var.setValue($e.text);
			CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
			stack.peek().add(cmd);
		}
	;

cmdselecao
	: 'se' AP left=expr op=OPREL right=expr FP ACH
		{
			curThread = new ArrayList<>();
			stack.push(curThread);
		}
	  (cmd)+
	  FCH
		{
			listaTrue = stack.pop();
		}
	  ('senao' ACH
		{
			curThread = new ArrayList<>();
			stack.push(curThread);
		}
		(cmd)+
		FCH
		{
			listaFalse = stack.pop();
			_exprDecision = $left.text + $op.text + $right.text;
			CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
			stack.peek().add(cmd);
		}
	  )?
	;

///////////////////////////////////////////////////////////////////////////////
//  Regras para expressões, com checagem semântica de tipos e inicialização
///////////////////////////////////////////////////////////////////////////////

expr returns [int tipoExpr]
	: e1=term
		{
			$tipoExpr = $e1.tipoTerm;
		}
	  (op=('+'|'-') e2=term
		{
			// Se algum dos lados for TEXT, não permite '+' ou '-'
			if ($tipoExpr == EasyVariable.TEXT || $e2.tipoTerm == EasyVariable.TEXT) {
				throw new EasySemanticException(
					"Type error: Cannot use '" + $op.text + "' with TEXT type."
				);
			}
			$tipoExpr = EasyVariable.NUMBER;
		}
	  )*
	;

term returns [int tipoTerm]
	: e1=factor
		{
			$tipoTerm = $e1.tipoFactor;
		}
	  (op=('*'|'/') e2=factor
		{
			// Se algum dos lados for TEXT, não permite '*' ou '/'
			if ($tipoTerm == EasyVariable.TEXT || $e2.tipoFactor == EasyVariable.TEXT) {
				throw new EasySemanticException(
					"Type error: Cannot use '" + $op.text + "' with TEXT type."
				);
			}
			$tipoTerm = EasyVariable.NUMBER;
		}
	  )*
	;

factor returns [int tipoFactor]
	: e1=g
		{
			$tipoFactor = $e1.tipoG;
		}
	  ('^' e2=g
		{
			// Se algum dos lados for TEXT, não permite '^'
			if ($tipoFactor == EasyVariable.TEXT || $e2.tipoG == EasyVariable.TEXT) {
				throw new EasySemanticException(
					"Type error: Cannot use '^' with TEXT type."
				);
			}
			$tipoFactor = EasyVariable.NUMBER;
		}
	  )*
	;

g returns [int tipoG]
	: '(' expr ')'
		{
			$tipoG = $expr.tipoExpr;
		}
	| ID
		{
			verificaID(_input.LT(-1).getText());
			EasyVariable var = (EasyVariable) symbolTable.get(_input.LT(-1).getText());
			// Verifica se a variável foi inicializada:
			if (var.getValue() == null) {
				throw new EasySemanticException("Variable '"+_input.LT(-1).getText()+"' not initialized");
			}
			$tipoG = var.getType();
		}
	| NUMBER
		{
			$tipoG = EasyVariable.NUMBER;
		}
	;

///////////////////////////////////////////////////////////////////////////////
//  Terminais / Tokens
///////////////////////////////////////////////////////////////////////////////

AP		: '(' ;
FP		: ')' ;
SC		: ';' ;
VIR		: ',' ;
ACH		: '{' ;
FCH		: '}' ;
ATTR	: '=' ;
OPREL	: '>' | '<' | '>=' | '<=' | '==' | '!=' ;
ID		: [a-z] ([a-z] | [A-Z] | [0-9])* ;
NUMBER	: [0-9]+ ('.' [0-9]+)? ;
WS		: (' ' | '\t' | '\n' | '\r') -> skip ;
