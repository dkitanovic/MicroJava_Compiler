// generated with ast extension for cup
// version 0.8
// 29/5/2021 21:48:4


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclListMust2 extends ConstDeclListMust {

    private Character C1;

    public ConstDeclListMust2 (Character C1) {
        this.C1=C1;
    }

    public Character getC1() {
        return C1;
    }

    public void setC1(Character C1) {
        this.C1=C1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclListMust2(\n");

        buffer.append(" "+tab+C1);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclListMust2]");
        return buffer.toString();
    }
}
