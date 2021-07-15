package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import org.apache.log4j.Logger;


import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.test.CompilerError;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;


public class SematicAnalyzer extends VisitorAdaptor {
	
	public int globalVariables = 0;
	public int globalConsts = 0;
	public int globalArrays = 0;
	public int localVariables = 0;
	public int statements = 0;
	private boolean errorDetected = false;
	private Obj globl = null;
	private Logger log = Logger.getLogger(getClass());	
	public int nVars=0;
	private int printCallCount=0;
	private int varDeclCount=0;
	private boolean equal=false;
	private Obj currentMethod = null;
	private Obj currentDesignator=null;
	private Struct currentType=null;
	private Struct currentDesignatorType=null;
	
    private static Struct boolType = NewTab.insert(Obj.Type, "bool", new Struct(Struct1.Bool)).getType();
    
	public void report_error(String message, SyntaxNode info) { // prilagodjen kodu sa lab vezbi
		CompilerError error = new CompilerError(info.getLine(), message, CompilerError.CompilerErrorType.SEMANTIC_ERROR);
		MyCompiler.ret.add(error);
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0) {
			msg.append(" na liniji ").append(line).append("!");
		}
		log.error(msg.toString());
	}
	
	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0) {
			msg.append(" na liniji ").append(line);
		}
		log.info(msg.toString());
	}
	
	public String TypeOfStruct(int x) {
        if(x == 0) return "none";
        else if(x == 1) return "int";
        else if(x == 2) return "char";
        else if(x == 3) return "array";
        else if(x == 4) return "class";
        else if(x == 5) return "bool";
        else return "";
    }

    public String TypeOfObj(int x) {
        if(x == 0) return "Con";
        else if(x == 1) return "Var";
        else if(x == 2) return "Type";
        else if(x == 3) return "Meth";
        else if(x == 4) return "Fld";
        else if(x == 5) return "Elem";
        else if(x == 6) return "Prog";
        else return "";
    }
	
	public void visit(Program program) { 
		nVars = NewTab.currentScope.getnVars();
    	NewTab.chainLocalSymbols(program.getProgName().obj);
    	NewTab.closeScope();
	}
	
	public void visit(ProgName progName){
    	progName.obj = NewTab.insert(Obj.Prog, progName.getProgName(), NewTab.noType);
    	NewTab.openScope();
    }
	
	
	
	public void visit(ConstDecl1 constDecl) {
		NewTab.insert(Obj.Con, constDecl.getConstName(), constDecl.getType().struct).setAdr(constDecl.getN2());
		globalConsts++;
		currentType = constDecl.getType().struct;
	}
	
	public void visit(ConstDecl2 constDecl) {
		NewTab.insert(Obj.Con, constDecl.getConstName(), constDecl.getType().struct).setAdr(constDecl.getC2());
		globalConsts++;
		currentType = constDecl.getType().struct;
	}
	
	public void visit(ConstDecl3 constDecl) {
		NewTab.insert(Obj.Con, constDecl.getConstName(), constDecl.getType().struct).setAdr(constDecl.getB2()?1:0);
		globalConsts++;
		currentType = constDecl.getType().struct;
	}
	
	public void visit(ConstDeclList1 constDeclList) {
		Obj constNode = NewTab.insert(Obj.Con, constDeclList.getConstName(), currentType);
		
	}
	
	public void visit(VarDecl1 varDecl){
		varDeclCount++;
		if (currentMethod == null) globalVariables++;
		else localVariables++;
		Obj varNode = NewTab.insert(Obj.Var, varDecl.getVarName(), varDecl.getType().struct);
		currentType = varDecl.getType().struct;
	}
	
	public void visit(VarDecl2 varDecl){
		varDeclCount++;
		if (currentMethod == null) {
			globalVariables++;
			globalArrays++;
		}else localVariables++;
		Obj varNode = NewTab.insert(Obj.Var, varDecl.getVarName(), new Struct(Struct.Array,varDecl.getType().struct));
		currentType = varDecl.getType().struct;
	}

	public void visit(VarDeclNoMust1 varDeclNoMust) {
		varDeclCount++;
		Obj varNode = NewTab.insert(Obj.Var, varDeclNoMust.getVarName(), currentType);
	}
	
	public void visit(VarDeclNoMust2 varDeclNoMust) {
		varDeclCount++;
		Obj varNode = NewTab.insert(Obj.Var, varDeclNoMust.getVarName(), new Struct(Struct.Array, currentType));
	}
	
	public void visit(MethodDecl methodDecl){
    	if(currentMethod.getType() != NewTab.noType){
			report_error("Semanticka greska na liniji " + methodDecl.getLine() + ": funkcija " + currentMethod.getName() + " nema return iskaz!", null);
    	}
    	NewTab.chainLocalSymbols(currentMethod);
    	NewTab.closeScope();
  
    	currentMethod = null;
    }
	
	public void visit(MethodDeclMustVoid methodDeclMustVoid){
		//Ostaje problem Type-Void da se resi kasnije
    	currentMethod = NewTab.insert(Obj.Meth, methodDeclMustVoid.getMethName(), NewTab.noType);
    	methodDeclMustVoid.obj = currentMethod;
    	NewTab.openScope();
		
    }
	
	public void visit(FormsPars1 formsPars) {
		Obj varNode = NewTab.insert(Obj.Var, formsPars.getParName(), formsPars.getType().struct);
	}
	
	public void visit(FormsPars2 formsPars) {
		Obj varNode = NewTab.insert(Obj.Var, formsPars.getParName(), formsPars.getType().struct);
	}
	
	public void visit(FormsParsList1 formsParsList) {
		Obj varNode = NewTab.insert(Obj.Var, formsParsList.getParName(), formsParsList.getType().struct);
	}
	
	public void visit(FormsParsList2 formsParsList) {
		Obj varNode = NewTab.insert(Obj.Var, formsParsList.getParName(), formsParsList.getType().struct);
	}
	
    public void visit(Type type){
    	Obj typeNode = NewTab.find(type.getTypeName());
    	if(typeNode == NewTab.noObj){
    		report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", null);
    		type.struct = NewTab.noType;
    	}else{
    		if(Obj.Type == typeNode.getKind()){
    			type.struct = typeNode.getType();
    		}else{
    			report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
    			type.struct = NewTab.noType;
    		}
    	}
    }

	public void visit(Designator1 designator){
			
    	Obj obj = NewTab.find(designator.getName());
       	if(obj == NewTab.noObj){
			report_error("Greska na liniji " + designator.getLine()+ " : ime "+designator.getName()+" nije deklarisano! ", null);
    	}
    	if (obj.getType().getKind() == Struct.Array) {
    		report_info("Pretraga na " + designator.getLine() + "(" + designator.getName() + ")"  
    				+ ", nadjeno " + TypeOfObj(obj.getKind()) + " " + designator.getName() + ": " + TypeOfStruct(obj.getType().getKind())
    				+ " of " + TypeOfStruct(obj.getType().getElemType().getKind())
    				+ ", " + obj.getAdr() + ", " + obj.getLevel(),designator);
    	}else {
    		report_info("Pretraga na " + designator.getLine() + "(" + designator.getName() + ")"  
    				+ ", nadjeno " + TypeOfObj(obj.getKind()) + " " + designator.getName() + ": " + TypeOfStruct(obj.getType().getKind())
    				+ ", " + obj.getAdr() + ", " + obj.getLevel(),designator);
    	}
    	designator.obj = obj;	
    }
	
	public void visit(Designator2 designator){
    	
		if (!Compatible(designator.getExpr().struct,NewTab.intType)) {
			report_error("Index niza mora biti tip int",designator);
		}
		designator.obj = designator.getDesignator().obj;
		designator.obj = new Obj(Obj.Elem, designator.obj.getName(), designator.obj.getType());
    }
	
	public void visit(Term term) {
		
		MulopFactorList mulopFactorList = term.getMulopFactorList();
		if (!mulopFactorList.getClass().equals(NoMulopFactorList.class)) {
			if (!Compatible(term.getFactor().struct,mulopFactorList.struct)){
				report_error("Nije isti tip kod mnozenja", term);
			}
		}
		term.struct = term.getFactor().struct;
	}
	
	public void visit(Factor1 factor) {
		factor.struct = factor.getDesignator().obj.getType();
	}
	
	public void visit(Factor2 factor) {
		factor.struct = NewTab.intType;
	}
	
	public void visit(Factor3 factor) {
		factor.struct = NewTab.charType;
	}
	
	public void visit(Factor4 factor) {
		factor.struct = factor.getExpr().struct;
	}
	
	public void visit(Factor5 factor) {
		factor.struct = boolType;
	}
	
	public void visit(Factor6 factor) {
		
		if (!Compatible(factor.getExpr().struct,NewTab.intType)) {
			report_error("Index niza mora biti tipa int",factor);
		}
		Struct str = new Struct(Struct.Array, factor.getType().struct);
		factor.struct = str;
	}
		
	public void visit(Statement3 statement3) {
		printCallCount++;
	}
	
	public void visit(Statement4 statement4) {
		printCallCount++;
	}
	
	public void visit(StatementList1 statement3) {
		statements++;
		
	}
	
	public void visit(StatementList2 statement4) {
		statements++;
		
	}

	public void visit(DesignatorStatement designatorStatement) {
		
		Designator designator = designatorStatement.getDesignator();
		DesignatorStatementMust designatorStatementMust = designatorStatement.getDesignatorStatementMust();
		
		if (designator.getClass() == Designator3.class) {
			
		}else if (!Compatible(designator.obj.getType(),designatorStatementMust.struct)) {
			report_error("Nije isti tip sa leve i desne strane jednakosti", designatorStatement);
		}
		
	}
	
	public void visit(DesignatorStatementMust1 designatorStatementMust) {
		designatorStatementMust.struct = designatorStatementMust.getExpr().struct;
	}
	
	public void visit(DesignatorStatementMust2 DesignatorStatementMust) {
		DesignatorStatementMust.struct = NewTab.intType;
	}
	
	public void visit(DesignatorStatementMust3 DesignatorStatementMust) {
		DesignatorStatementMust.struct = NewTab.intType;
	}
	
	public void visit(ConstDeclListMust1 ConstDeclList) {
		globalConsts++;
		if (!currentType.equals(NewTab.intType)) {
			report_error("Greska:  Konstanta nije int",ConstDeclList);
		}
	}
	
	public void visit(ConstDeclListMust2 ConstDeclList) {
		globalConsts++;
		if (!currentType.equals(NewTab.charType)) {
			report_error("Greska:  Konstanta nije char",ConstDeclList);
		}
	}
	
	public void visit(ConstDeclListMust3 ConstDeclList) {
		globalConsts++;
		if (!currentType.equals(boolType)) {
			report_error("Greska:  Konstanta nije bool",ConstDeclList);
		}
	}
	
	public void visit(Expr0 expr) {
		expr.struct = expr.getExpr1().struct;
	}
	
	public void visit(Expr11 expr) {
		
		AddopTermList addOpTermList = expr.getAddopTermList();
		if (!addOpTermList.getClass().equals(NoAddopTermList.class)) {		
			if (!Compatible(expr.getTerm().struct,addOpTermList.struct)) {
				report_error("Nije isti tip kod sabiranja",expr);
			}
		}
		expr.struct = expr.getTerm().struct;
	}
	
	public void visit(Expr12 expr) {
		expr.struct = expr.getTerm().struct;
	}
	
	public void visit(AddopTermList1  addopTermList) {
		Term term = addopTermList.getTerm();
		if (!Compatible(term.struct,NewTab.intType)) {
			report_error("Sabiranje radi samo sa tipom int", addopTermList);
		}
		addopTermList.struct = NewTab.intType;
	}
	
	public void visit(MulopFactorList1  mulopFactorList) {
		Factor factor = mulopFactorList.getFactor();
		if (!Compatible(factor.struct,NewTab.intType)) {
			
			
			report_error("Mnozenje radi samo sa tipom int", mulopFactorList);
		}
		mulopFactorList.struct = NewTab.intType;
	}
	
	public void visit(CondFact1 condFact) {
		if (!Compatible(condFact.getExpr().struct,boolType)) {
			report_error("Nije dobar tip u statementu", condFact);
		}
		condFact.struct = condFact.getExpr().struct; 
	}
	
	public void visit(CondFact2 condFact) {
		if (Compatible(condFact.getExpr1().struct, boolType) && condFact.getRelop().getClass()!=Relop1.class && condFact.getRelop().getClass()!=Relop2.class) {
			report_error("Ne moze bool posle relacionog operatora", condFact);
		}
		if (!Compatible(condFact.getExpr().struct, condFact.getExpr1().struct)) {
			report_error("Nije isti tip sa leve i desne strane relacionog operatora", condFact);
		}
		//condFact.struct = condFact.getExpr().struct;
		condFact.struct = boolType;
	}
	
	public void visit(CondFactList1 condFactList) {
		if (!Compatible(condFactList.getCondFact().struct, boolType)) {
			report_error("Nije dobar tip u statementu", condFactList);
		}
		condFactList.struct = condFactList.getCondFact().struct;
	}
	
	public void visit(CondTerm condTerm) {
		condTerm.struct = condTerm.getCondFact().struct;
	}
	
	public void visit(CondTermList1 condTermList) {
		if (!Compatible(condTermList.getCondTerm().struct, boolType)) {
			report_error("Nije dobar tip u statementu", condTermList);
		}
	}
	
	boolean Compatible(Struct s1, Struct s2) {
		if (s1.getKind() == Struct.Array && s2.getKind() != Struct.Array) {
			if (!s1.getElemType().equals(s2)) {
				return false;
			}
		}
		else if (s1.getKind() != Struct.Array && s2.getKind() == Struct.Array) {
			if (!s1.equals(s2.getElemType())) {
				return false;
			}
		}
		else if (!s1.equals(s2)) {
			return false;
		}
		return true;
	}
	
	public boolean passed(){
    	return !errorDetected;
    }
	
}
