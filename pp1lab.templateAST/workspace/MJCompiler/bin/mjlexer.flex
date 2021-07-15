package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.test.*;
%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROG, yytext()); }
"read" 		{ return new_symbol(sym.READ, yytext()); }
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"new" 		{ return new_symbol(sym.NEW, yytext()); }
"const" 	{ return new_symbol(sym.CONST, yytext()); }
"if" 		{ return new_symbol(sym.IF, yytext()); }
"else" 		{ return new_symbol(sym.ELSE, yytext()); }
"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"++" 		{ return new_symbol(sym.INCREMENT, yytext()); }
"--" 		{ return new_symbol(sym.DECREMENT, yytext()); }
"=" 		{ return new_symbol(sym.EQUAL, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }
"-" 		{ return new_symbol(sym.MINUS, yytext()); }
"*" 		{ return new_symbol(sym.MULTIPLICATION, yytext()); }
"/" 		{ return new_symbol(sym.DIVISION, yytext()); }
"%" 		{ return new_symbol(sym.MODULO, yytext()); }
"[" 		{ return new_symbol(sym.LEFTSQUARE, yytext()); }
"]" 		{ return new_symbol(sym.RIGHTSQUARE, yytext()); }
"?" 		{ return new_symbol(sym.QUESTION, yytext()); }
":"         { return new_symbol(sym.COLON, yytext()); }
"=="        { return new_symbol(sym.ISEQUAL, yytext()); }
"!="        { return new_symbol(sym.ISNOTEQUAL, yytext()); }
">"         { return new_symbol(sym.ISGREATER, yytext()); }
">="        { return new_symbol(sym.ISGREATEREQUAL, yytext()); }
"<"         { return new_symbol(sym.ISLOWER, yytext()); }
"<="        { return new_symbol(sym.ISLOWEREQUAL, yytext()); }
"&&"        { return new_symbol(sym.AND, yytext()); }
"||"        { return new_symbol(sym.OR, yytext()); }
"#"         { return new_symbol(sym.HASH, yytext()); }

"//" 		{ yybegin(COMMENT); }

<YYINITIAL> "//" 		     { yybegin(COMMENT); }
<COMMENT> .      { yybegin(COMMENT); }
<COMMENT> "\r\n" { yybegin(YYINITIAL); }

[0-9]+  { return new_symbol(sym.NUMCONST, new Integer (yytext())); }
"'"[\040-\176]"'" {return new_symbol(sym.CHARCONST, new Character(yytext().charAt(1)));}
"true" | "false" {return new_symbol(sym.BOOLCONST, new Boolean(yytext()));}

([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{return new_symbol (sym.IDENT, yytext()); }

. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); CompilerError error = new CompilerError(yyline+1, "Leksicka greska ", CompilerError.CompilerErrorType.LEXICAL_ERROR); MyCompiler.ret.add(error);}
