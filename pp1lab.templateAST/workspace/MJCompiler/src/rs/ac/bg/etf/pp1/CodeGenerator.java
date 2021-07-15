package rs.ac.bg.etf.pp1;



import java.util.Stack;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.AddopMinus;
import rs.ac.bg.etf.pp1.ast.AddopPlus;
import rs.ac.bg.etf.pp1.ast.AddopTermList1;
import rs.ac.bg.etf.pp1.ast.CondFact1;
import rs.ac.bg.etf.pp1.ast.CondFact2;
import rs.ac.bg.etf.pp1.ast.CondFactList1;
import rs.ac.bg.etf.pp1.ast.CondTermList1;
import rs.ac.bg.etf.pp1.ast.Condition;
import rs.ac.bg.etf.pp1.ast.ConstDeclListMust1;
import rs.ac.bg.etf.pp1.ast.Designator;
import rs.ac.bg.etf.pp1.ast.Designator1;
import rs.ac.bg.etf.pp1.ast.Designator2;
import rs.ac.bg.etf.pp1.ast.Designator3;
import rs.ac.bg.etf.pp1.ast.DesignatorStatement;
import rs.ac.bg.etf.pp1.ast.DesignatorStatementMust1;
import rs.ac.bg.etf.pp1.ast.DesignatorStatementMust2;
import rs.ac.bg.etf.pp1.ast.DesignatorStatementMust3;
import rs.ac.bg.etf.pp1.ast.Expr0;
import rs.ac.bg.etf.pp1.ast.Expr1;
import rs.ac.bg.etf.pp1.ast.Expr11;
import rs.ac.bg.etf.pp1.ast.Factor;
import rs.ac.bg.etf.pp1.ast.Factor1;
import rs.ac.bg.etf.pp1.ast.Factor2;
import rs.ac.bg.etf.pp1.ast.Factor3;
import rs.ac.bg.etf.pp1.ast.Factor5;
import rs.ac.bg.etf.pp1.ast.Factor6;
import rs.ac.bg.etf.pp1.ast.If;
import rs.ac.bg.etf.pp1.ast.If1;
import rs.ac.bg.etf.pp1.ast.If2;
import rs.ac.bg.etf.pp1.ast.MethodDecl;
import rs.ac.bg.etf.pp1.ast.MethodDeclMust;
import rs.ac.bg.etf.pp1.ast.MethodDeclMustVoid;
import rs.ac.bg.etf.pp1.ast.Mulop1;
import rs.ac.bg.etf.pp1.ast.Mulop2;
import rs.ac.bg.etf.pp1.ast.Mulop3;
import rs.ac.bg.etf.pp1.ast.MulopFactorList1;
import rs.ac.bg.etf.pp1.ast.Relop1;
import rs.ac.bg.etf.pp1.ast.Relop2;
import rs.ac.bg.etf.pp1.ast.Relop3;
import rs.ac.bg.etf.pp1.ast.Relop4;
import rs.ac.bg.etf.pp1.ast.Relop5;
import rs.ac.bg.etf.pp1.ast.Relop6;
import rs.ac.bg.etf.pp1.ast.Statement;
import rs.ac.bg.etf.pp1.ast.Statement1;
import rs.ac.bg.etf.pp1.ast.Statement2;
import rs.ac.bg.etf.pp1.ast.Statement3;
import rs.ac.bg.etf.pp1.ast.Statement4;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;
	private int sign = 1;
	private int[] tern_nums = new int[100];
	
	private int currentIf = 0;
	private int tern_num = 0;
	private int adr = 0;
	private int currentRelOp = 0;
	Stack<Integer> adrese = new Stack<Integer>();
	
	public int getMainPc(){
		return mainPc;
	}
	
	
	public void visit(Statement3 printStmt){
		Struct niz_int = new Struct(Struct.Array,Tab.intType);
		if (printStmt.getExpr().struct.equals(niz_int)) {     //Ovde ispitujem da li je niz intova i zato mi treba ova struktura
			Code.loadConst(5);
			Code.put(Code.print);
		}else 
		if(printStmt.getExpr().struct == Tab.intType){ //Ovde ispitujem da li je int
			Code.loadConst(5);
			Code.put(Code.print);
		}else{
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
		
		if (tern_nums[currentIf] == 1) {
			if (printStmt.getParent().getClass() == If2.class) Code.putJump(0);
			adr = adrese.pop();
			Code.fixup(adr);
			if (printStmt.getParent().getClass() == If2.class) {
				adr = Code.pc -2;
				adrese.push(adr);
			}
			tern_nums[currentIf]++;
		}else if (tern_nums[currentIf] == 2) {
			adr = adrese.pop();
			Code.fixup(adr);
		}
	}
	
	public void visit(Statement4 printStmt){
		int sirina = printStmt.getN2().intValue();
		Struct niz_int = new Struct(Struct.Array,Tab.intType);
		if (printStmt.getExpr().struct.equals(niz_int)) {     //Ovde ispitujem da li je niz intova i zato mi treba ova struktura
			Code.loadConst(sirina);
			Code.put(Code.print);
		}else 
		if(printStmt.getExpr().struct == Tab.intType){ //Ovde ispitujem da li je int
			Code.loadConst(sirina);
			Code.put(Code.print);
		}else{
			Code.loadConst(sirina);
			Code.put(Code.bprint);
		}
		
		if (tern_nums[currentIf] == 1) {
			if (printStmt.getParent().getClass() == If2.class) Code.putJump(0);
			adr = adrese.pop();
			Code.fixup(adr);
			if (printStmt.getParent().getClass() == If2.class) {
				adr = Code.pc -2;
				tern_nums[currentIf]++;
			}
		}else if (tern_nums[currentIf] == 2) {
			adr = adrese.pop();
			Code.fixup(adr);
		}
	}

	public void visit(MethodDeclMustVoid methodTypeName){
		
		if("main".equalsIgnoreCase(methodTypeName.getMethName())){
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		int brojArgumenata = methodTypeName.obj.getLevel();
        int brojArgPlusLokal = methodTypeName.obj.getLocalSymbols().size();

        Code.put(Code.enter);
        Code.put(brojArgumenata);
        Code.put(brojArgPlusLokal);
	
	}
	
	public void visit(MethodDecl methodDecl){
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(DesignatorStatement designatorStatement) {
		Designator designator = designatorStatement.getDesignator();
		
		if (designator.getClass() == Designator2.class) {
			Obj obj = new Obj(Obj.Elem,designator.obj.getName(),designator.obj.getType());
			Code.store(obj);
		}else if (designator.getClass() == Designator3.class){
			Code.put(Code.astore);
		} else Code.store(designatorStatement.getDesignator().obj);
	}
	
	public void visit(DesignatorStatementMust2 designatorStatementMust) {
		SyntaxNode designatorStatement = designatorStatementMust.getParent();
		if (designatorStatement.getClass() == DesignatorStatement.class) {	
			Struct struktura = new Struct(Struct.Array,Tab.intType);
			if (((DesignatorStatement) designatorStatement).getDesignator().obj.getType().equals(struktura)) {
				Code.put(Code.dup2);
				Code.put(Code.aload);
			}else Code.load(((DesignatorStatement) designatorStatement).getDesignator().obj);
		}
		Code.loadConst(1);
		Code.put(Code.add);		
	}
	
	public void visit(DesignatorStatementMust3 designatorStatementMust) {
		SyntaxNode designatorStatement = designatorStatementMust.getParent();
		if (designatorStatement.getClass() == DesignatorStatement.class) {
			Code.load(((DesignatorStatement) designatorStatement).getDesignator().obj);
		}
		Code.loadConst(1);
		Code.put(Code.sub);		
	}
	
	public void visit(Factor2 factor) {	
		Code.loadConst(factor.getN1());
	}
	
	public void visit(Factor3 factor) {
		
		Code.loadConst(factor.getC1());
	}
	
	public void visit(Factor5 factor) {
		Code.loadConst(factor.getB1()?1:0);
	}
	
	public void visit(Factor6 factor) {
		Struct niz_int = new Struct(Struct.Array,Tab.intType);
		SyntaxNode designatorStatement = factor.getParent().getParent().getParent().getParent().getParent();
		if (designatorStatement.getClass() == DesignatorStatement.class) {
			if (((DesignatorStatement) designatorStatement).getDesignator().obj.getType().equals(niz_int)) {
				Code.put(Code.newarray);
				Code.put(1);
			}else {
				Code.put(Code.newarray);
				Code.put(0);
			}
			
		}
		
	}
	
	public void visit(AddopTermList1 addOpTermList) {
		if (addOpTermList.getAddop().getClass() == AddopPlus.class) {
			Code.put(Code.add);
		}else if (addOpTermList.getAddop().getClass() == AddopMinus.class) {
			Code.put(Code.sub);
		} 
	}
	
	public void visit(MulopFactorList1 mulOpFactorList) {
		if (mulOpFactorList.getMulop().getClass() == Mulop1.class) {
			Code.put(Code.mul);
		}else if (mulOpFactorList.getMulop().getClass() == Mulop2.class) {
			Code.put(Code.div);
		}else if (mulOpFactorList.getMulop().getClass() == Mulop3.class) {
			Code.put(Code.rem);
		} 
	}
	
	public void visit(Designator2 designator) {
		SyntaxNode parent = designator.getParent();
		while(parent != null) {
			if (parent.getClass() == DesignatorStatementMust1.class) {
				Code.put(Code.aload);
				break;
			}
			if (parent.getClass() == Statement3.class) {
				Code.put(Code.aload);
				break;
			}
			if (parent.getClass() == Statement4.class) {
				Code.put(Code.aload);
				break;
			}
			if (parent.getClass() == Designator2.class){
				Code.put(Code.aload);
				break;
			}
			if (parent.getClass() == Designator3.class){
				Code.put(Code.aload);
				Code.loadConst('a');
				Code.put(Code.sub);
				
				break;
			}
			parent = parent.getParent();
		}
	}
	
	public void visit(Designator1 designator) {
		SyntaxNode parent = designator.getParent();
		if (parent.getClass() == Designator3.class) {
			Designator3 d = (Designator3) parent;
			if (d.getDesignator().equals(designator)) {
				Code.load(designator.obj);
				Code.loadConst('a');
				Code.put(Code.sub);
			}
			else if (parent.getClass() != DesignatorStatement.class && parent.getClass() != Statement2.class) {
				Designator3 d1 = (Designator3) parent;
				if (d1.getDesignator1().equals(designator)) {
					Code.loadConst(5);
					Code.put(Code.rem);
					Code.load(designator.obj);
					Code.put(Code.dup_x1);
					Code.put(Code.pop);
				}
			}
		}
		else if (parent.getClass() != DesignatorStatement.class && parent.getClass() != Statement2.class) {
			Code.load(designator.obj);
		}
	}
	
	public void visit(Expr11 expr) {
		if (expr.getParent().getParent().getClass() == CondFact1.class 
				|| expr.getParent().getParent().getClass() == CondFact2.class){
			
			if (tern_nums[currentIf+1] == 0) {
				/*if ((expr.getParent().getParent().getClass() != DesignatorStatementMust1.class) 
						&& (expr.getParent().getParent().getClass() != CondFact2.class)) Code.loadConst(1);*/
				
			}
		}
	}
	
	public void visit(Statement1 statement) {
		
		if (tern_nums[currentIf] == 1) {
			if (statement.getParent().getClass() == If2.class) Code.putJump(0);
			adr = adrese.pop();
			Code.fixup(adr);
			if (statement.getParent().getClass() == If2.class) {
				adr = Code.pc -2;
				adrese.push(adr);
			}
			tern_nums[currentIf]++;
		}else if (tern_nums[currentIf] == 2) {
				adr = adrese.pop();
				Code.fixup(adr);
			
		}
	}
	
	public void visit(Statement2 statement) {
		
		Obj des = statement.getDesignator().obj;
		if (Compatible(des.getType(),Tab.intType)) {
			Code.put(Code.read);
		}else Code.put(Code.bread);
		
		Code.store(des);
		
		
		if (tern_nums[currentIf] == 1) {
			if (statement.getParent().getClass() == If2.class) Code.putJump(0);
			adr = adrese.pop();
			Code.fixup(adr);
			adr = Code.pc -2;
			tern_nums[currentIf]++;
		}else if (tern_nums[currentIf] == 2) {
			adr = adrese.pop();
			Code.fixup(adr);
		}
	}
	
	public void visit(Condition condition) {
		Code.loadConst(0);
		currentIf++;
		if (tern_nums[currentIf]==0) {
			Code.putFalseJump(4,0);
			adr = Code.pc -2;
			adrese.push(adr);
			tern_nums[currentIf]++;
			currentRelOp=0;
		}
	}
	
	public void visit(CondFact1 condFact) {
		//currentIf++;
	}
	
	public void visit(CondFact2 condFact) {
		
		Code.putFalseJump(currentRelOp,0);
		adr=Code.pc-2;
		Code.loadConst(1);
		Code.putJump(0);
		int adr1=Code.pc-2;
		Code.fixup(adr);
		Code.loadConst(0);
		Code.fixup(adr1);
		//Code.loadConst(1);
		currentRelOp=0;
		//currentIf++;
	}
	
	public void visit(CondFactList1 condFactList) {
		Code.put(Code.mul);
	}
	
	public void visit(CondTermList1 condTermList) {
		Code.put(Code.add);
	}
	
	public void visit(Relop1 relOp) {
		currentRelOp=0;
	}
	
	public void visit(Relop2 relOp) {
		currentRelOp=1;
	}
	
	public void visit(Relop3 relOp) {
		currentRelOp=2;
	}
	
	public void visit(Relop4 relOp) {
		currentRelOp=3;
	}
	
	public void visit(Relop5 relOp) {
		currentRelOp=4;
	}
	
	public void visit(Relop6 relOp) {
		currentRelOp=5;
	}
	
	public void visit(If1 statement) {
		tern_nums[currentIf] = 0;
		currentIf--;
		if (tern_nums[currentIf] == 1) {
			if (statement.getParent().getClass() == If2.class) Code.putJump(0);
			adr = adrese.pop();
			Code.fixup(adr);
			if (statement.getParent().getClass() == If2.class) {
				adr = Code.pc -2;
				adrese.push(adr);
			}
			tern_nums[currentIf]++;
		}else if (tern_nums[currentIf] == 2) {
			
				adr = adrese.pop();
				Code.fixup(adr);
			
		}
	}
	
	public void visit(If2 statement) {
		tern_nums[currentIf] = 0;
		currentIf--;
		if (tern_nums[currentIf] == 1) {
			if (statement.getParent().getClass() == If2.class) Code.putJump(0);
			adr = adrese.pop();
			Code.fixup(adr);
			if (statement.getParent().getClass() == If2.class) {
				adr = Code.pc -2;
				adrese.push(adr);
			}
			tern_nums[currentIf]++;
		}else if (tern_nums[currentIf] == 2) {
				adr = adrese.pop();
				Code.fixup(adr);
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
	
}
