// generated with ast extension for cup
// version 0.8
// 29/5/2021 21:48:4


package rs.ac.bg.etf.pp1.ast;

public class FormsParsNoMust1 extends FormsParsNoMust {

    private FormsPars FormsPars;

    public FormsParsNoMust1 (FormsPars FormsPars) {
        this.FormsPars=FormsPars;
        if(FormsPars!=null) FormsPars.setParent(this);
    }

    public FormsPars getFormsPars() {
        return FormsPars;
    }

    public void setFormsPars(FormsPars FormsPars) {
        this.FormsPars=FormsPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormsPars!=null) FormsPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormsPars!=null) FormsPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormsPars!=null) FormsPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormsParsNoMust1(\n");

        if(FormsPars!=null)
            buffer.append(FormsPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormsParsNoMust1]");
        return buffer.toString();
    }
}
