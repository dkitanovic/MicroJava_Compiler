package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.test.Compiler;
import rs.ac.bg.etf.pp1.test.CompilerError;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;

public class MyCompiler implements Compiler{

	public static List<CompilerError> ret = new ArrayList<CompilerError>();
	
	@Override
	public List<CompilerError> compile(String sourceFilePath, String outputFilePath) {
		Logger log = Logger.getLogger(MyCompiler.class);
		
		Reader br = null;
		try {
			
			File sourceCode = new File(sourceFilePath);
			System.out.println("Ulazni fajl je " + sourceFilePath);
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());
			
			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			
			MJParser p = new MJParser(lexer);
	        Symbol s = p.parse();  //pocetak parsiranja
	        
	        Program prog = (Program)(s.value); 
	        NewTab.init();
			// ispis sintaksnog stabla
			log.info(prog.toString(""));
			log.info("===================================");

			// ispis prepoznatih programskih konstrukcija
			SematicAnalyzer v = new SematicAnalyzer();
			prog.traverseBottomUp(v); 
	      
			log.info("Global variables : " + v.globalVariables);
			log.info("Global constants : " + v.globalConsts);
			log.info("Global arrays : " + v.globalArrays);
			log.info("Local variables : " + v.localVariables);
			log.info("Statements : " + v.statements);
			//log.info(" Print count calls = " + v.printCallCount);

			//log.info(" Deklarisanih promenljivih ima = " + v.varDeclCount);
			
			log.info("===================================");
			NewTab.dump();
			
			if(!p.errorDetected && v.passed()){
				
				File objFile = new File(outputFilePath);
				System.out.println("Izlazni fajl je " + outputFilePath);
				if(objFile.exists()) objFile.delete();
				
				CodeGenerator codeGenerator = new CodeGenerator();
				prog.traverseBottomUp(codeGenerator);
				Code.dataSize = v.nVars;
				Code.mainPc = codeGenerator.getMainPc();
				Code.write(new FileOutputStream(objFile));
				log.info("Parsiranje uspesno zavrseno!");
			}else{
				log.error("Parsiranje NIJE uspesno zavrseno!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
		}
		
		return ret;
	}

	
}