// generated with ast extension for cup
// version 0.8
// 29/5/2021 21:48:4


package rs.ac.bg.etf.pp1.ast;

public class VarDeclNoMustList1 extends VarDeclNoMustList {

    private VarDeclNoMustList VarDeclNoMustList;
    private VarDeclNoMust VarDeclNoMust;

    public VarDeclNoMustList1 (VarDeclNoMustList VarDeclNoMustList, VarDeclNoMust VarDeclNoMust) {
        this.VarDeclNoMustList=VarDeclNoMustList;
        if(VarDeclNoMustList!=null) VarDeclNoMustList.setParent(this);
        this.VarDeclNoMust=VarDeclNoMust;
        if(VarDeclNoMust!=null) VarDeclNoMust.setParent(this);
    }

    public VarDeclNoMustList getVarDeclNoMustList() {
        return VarDeclNoMustList;
    }

    public void setVarDeclNoMustList(VarDeclNoMustList VarDeclNoMustList) {
        this.VarDeclNoMustList=VarDeclNoMustList;
    }

    public VarDeclNoMust getVarDeclNoMust() {
        return VarDeclNoMust;
    }

    public void setVarDeclNoMust(VarDeclNoMust VarDeclNoMust) {
        this.VarDeclNoMust=VarDeclNoMust;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclNoMustList!=null) VarDeclNoMustList.accept(visitor);
        if(VarDeclNoMust!=null) VarDeclNoMust.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclNoMustList!=null) VarDeclNoMustList.traverseTopDown(visitor);
        if(VarDeclNoMust!=null) VarDeclNoMust.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclNoMustList!=null) VarDeclNoMustList.traverseBottomUp(visitor);
        if(VarDeclNoMust!=null) VarDeclNoMust.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclNoMustList1(\n");

        if(VarDeclNoMustList!=null)
            buffer.append(VarDeclNoMustList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclNoMust!=null)
            buffer.append(VarDeclNoMust.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclNoMustList1]");
        return buffer.toString();
    }
}
