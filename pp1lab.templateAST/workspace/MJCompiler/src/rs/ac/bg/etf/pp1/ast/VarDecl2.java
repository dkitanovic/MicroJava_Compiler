// generated with ast extension for cup
// version 0.8
// 29/5/2021 21:48:4


package rs.ac.bg.etf.pp1.ast;

public class VarDecl2 extends VarDecl {

    private Type Type;
    private String varName;
    private VarDeclNoMustList VarDeclNoMustList;

    public VarDecl2 (Type Type, String varName, VarDeclNoMustList VarDeclNoMustList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.varName=varName;
        this.VarDeclNoMustList=VarDeclNoMustList;
        if(VarDeclNoMustList!=null) VarDeclNoMustList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public VarDeclNoMustList getVarDeclNoMustList() {
        return VarDeclNoMustList;
    }

    public void setVarDeclNoMustList(VarDeclNoMustList VarDeclNoMustList) {
        this.VarDeclNoMustList=VarDeclNoMustList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(VarDeclNoMustList!=null) VarDeclNoMustList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarDeclNoMustList!=null) VarDeclNoMustList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarDeclNoMustList!=null) VarDeclNoMustList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDecl2(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(VarDeclNoMustList!=null)
            buffer.append(VarDeclNoMustList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDecl2]");
        return buffer.toString();
    }
}
