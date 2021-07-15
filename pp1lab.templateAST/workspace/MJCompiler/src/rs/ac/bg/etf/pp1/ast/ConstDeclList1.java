// generated with ast extension for cup
// version 0.8
// 29/5/2021 21:48:4


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclList1 extends ConstDeclList {

    private ConstDeclList ConstDeclList;
    private String constName;
    private ConstDeclListMust ConstDeclListMust;

    public ConstDeclList1 (ConstDeclList ConstDeclList, String constName, ConstDeclListMust ConstDeclListMust) {
        this.ConstDeclList=ConstDeclList;
        if(ConstDeclList!=null) ConstDeclList.setParent(this);
        this.constName=constName;
        this.ConstDeclListMust=ConstDeclListMust;
        if(ConstDeclListMust!=null) ConstDeclListMust.setParent(this);
    }

    public ConstDeclList getConstDeclList() {
        return ConstDeclList;
    }

    public void setConstDeclList(ConstDeclList ConstDeclList) {
        this.ConstDeclList=ConstDeclList;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public ConstDeclListMust getConstDeclListMust() {
        return ConstDeclListMust;
    }

    public void setConstDeclListMust(ConstDeclListMust ConstDeclListMust) {
        this.ConstDeclListMust=ConstDeclListMust;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclList!=null) ConstDeclList.accept(visitor);
        if(ConstDeclListMust!=null) ConstDeclListMust.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclList!=null) ConstDeclList.traverseTopDown(visitor);
        if(ConstDeclListMust!=null) ConstDeclListMust.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclList!=null) ConstDeclList.traverseBottomUp(visitor);
        if(ConstDeclListMust!=null) ConstDeclListMust.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclList1(\n");

        if(ConstDeclList!=null)
            buffer.append(ConstDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        if(ConstDeclListMust!=null)
            buffer.append(ConstDeclListMust.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclList1]");
        return buffer.toString();
    }
}
