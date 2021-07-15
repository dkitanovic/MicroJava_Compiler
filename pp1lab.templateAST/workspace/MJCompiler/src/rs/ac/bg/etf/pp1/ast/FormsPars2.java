// generated with ast extension for cup
// version 0.8
// 29/5/2021 21:48:4


package rs.ac.bg.etf.pp1.ast;

public class FormsPars2 extends FormsPars {

    private Type Type;
    private String ParName;
    private FormsParsList FormsParsList;

    public FormsPars2 (Type Type, String ParName, FormsParsList FormsParsList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ParName=ParName;
        this.FormsParsList=FormsParsList;
        if(FormsParsList!=null) FormsParsList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getParName() {
        return ParName;
    }

    public void setParName(String ParName) {
        this.ParName=ParName;
    }

    public FormsParsList getFormsParsList() {
        return FormsParsList;
    }

    public void setFormsParsList(FormsParsList FormsParsList) {
        this.FormsParsList=FormsParsList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(FormsParsList!=null) FormsParsList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(FormsParsList!=null) FormsParsList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(FormsParsList!=null) FormsParsList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormsPars2(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+ParName);
        buffer.append("\n");

        if(FormsParsList!=null)
            buffer.append(FormsParsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormsPars2]");
        return buffer.toString();
    }
}
