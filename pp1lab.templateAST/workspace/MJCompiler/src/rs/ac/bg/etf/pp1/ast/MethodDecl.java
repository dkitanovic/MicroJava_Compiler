// generated with ast extension for cup
// version 0.8
// 29/5/2021 21:48:4


package rs.ac.bg.etf.pp1.ast;

public class MethodDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private MethodDeclMust MethodDeclMust;
    private FormsParsNoMust FormsParsNoMust;
    private VarDeclList VarDeclList;
    private StatementList StatementList;

    public MethodDecl (MethodDeclMust MethodDeclMust, FormsParsNoMust FormsParsNoMust, VarDeclList VarDeclList, StatementList StatementList) {
        this.MethodDeclMust=MethodDeclMust;
        if(MethodDeclMust!=null) MethodDeclMust.setParent(this);
        this.FormsParsNoMust=FormsParsNoMust;
        if(FormsParsNoMust!=null) FormsParsNoMust.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public MethodDeclMust getMethodDeclMust() {
        return MethodDeclMust;
    }

    public void setMethodDeclMust(MethodDeclMust MethodDeclMust) {
        this.MethodDeclMust=MethodDeclMust;
    }

    public FormsParsNoMust getFormsParsNoMust() {
        return FormsParsNoMust;
    }

    public void setFormsParsNoMust(FormsParsNoMust FormsParsNoMust) {
        this.FormsParsNoMust=FormsParsNoMust;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodDeclMust!=null) MethodDeclMust.accept(visitor);
        if(FormsParsNoMust!=null) FormsParsNoMust.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDeclMust!=null) MethodDeclMust.traverseTopDown(visitor);
        if(FormsParsNoMust!=null) FormsParsNoMust.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDeclMust!=null) MethodDeclMust.traverseBottomUp(visitor);
        if(FormsParsNoMust!=null) FormsParsNoMust.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDecl(\n");

        if(MethodDeclMust!=null)
            buffer.append(MethodDeclMust.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormsParsNoMust!=null)
            buffer.append(FormsParsNoMust.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDecl]");
        return buffer.toString();
    }
}
