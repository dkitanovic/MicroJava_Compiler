package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;
import rs.etf.pp1.symboltable.visitors.SymbolTableVisitor;

public class NewTab extends Tab{
	
	public static void init() {
		Tab.init();
	}
	
	public static void chainLocalSymbols(Obj outerScopeObj) {
		Tab.chainLocalSymbols(outerScopeObj);
	}
	
	public static void chainLocalSymbols(Struct innerClass) {
		Tab.chainLocalSymbols(innerClass);
	}
	
	public static void openScope() {
		Tab.openScope();
	}
	
	public static void closeScope() {
		Tab.closeScope();
	}

	public static Obj insert(int kind, String name, Struct type) {
		return Tab.insert(kind,name,type);
	}

	public static Obj find(String name) {
		return Tab.find(name);
	}
	
	public static Scope currentScope() {
		return Tab.currentScope();
	}
	
	public static void dump(SymbolTableVisitor stv) {
		System.out.println("=====================SYMBOL TABLE DUMP=========================");
		if (stv == null)
			stv = new NewDump();
		for (Scope s = currentScope; s != null; s = s.getOuter()) {
			s.accept(stv);
		}
		System.out.println(stv.getOutput());
	}
	
	public static void dump() {
		dump(null);
	}
}
