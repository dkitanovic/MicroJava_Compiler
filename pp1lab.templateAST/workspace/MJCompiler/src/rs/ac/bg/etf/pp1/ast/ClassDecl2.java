// generated with ast extension for cup
// version 0.8
// 29/5/2021 21:48:4


package rs.ac.bg.etf.pp1.ast;

public class ClassDecl2 extends ClassDecl {

    private String I1;
    private VarDeclList VarDeclList;
    private ClassDeclNoMust ClassDeclNoMust;

    public ClassDecl2 (String I1, VarDeclList VarDeclList, ClassDeclNoMust ClassDeclNoMust) {
        this.I1=I1;
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.ClassDeclNoMust=ClassDeclNoMust;
        if(ClassDeclNoMust!=null) ClassDeclNoMust.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public ClassDeclNoMust getClassDeclNoMust() {
        return ClassDeclNoMust;
    }

    public void setClassDeclNoMust(ClassDeclNoMust ClassDeclNoMust) {
        this.ClassDeclNoMust=ClassDeclNoMust;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(ClassDeclNoMust!=null) ClassDeclNoMust.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(ClassDeclNoMust!=null) ClassDeclNoMust.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(ClassDeclNoMust!=null) ClassDeclNoMust.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDecl2(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassDeclNoMust!=null)
            buffer.append(ClassDeclNoMust.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDecl2]");
        return buffer.toString();
    }
}
