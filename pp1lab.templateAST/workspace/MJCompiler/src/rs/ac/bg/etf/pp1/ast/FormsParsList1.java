// generated with ast extension for cup
// version 0.8
// 29/5/2021 21:48:4


package rs.ac.bg.etf.pp1.ast;

public class FormsParsList1 extends FormsParsList {

    private FormsParsList FormsParsList;
    private Type Type;
    private String ParName;

    public FormsParsList1 (FormsParsList FormsParsList, Type Type, String ParName) {
        this.FormsParsList=FormsParsList;
        if(FormsParsList!=null) FormsParsList.setParent(this);
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ParName=ParName;
    }

    public FormsParsList getFormsParsList() {
        return FormsParsList;
    }

    public void setFormsParsList(FormsParsList FormsParsList) {
        this.FormsParsList=FormsParsList;
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormsParsList!=null) FormsParsList.accept(visitor);
        if(Type!=null) Type.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormsParsList!=null) FormsParsList.traverseTopDown(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormsParsList!=null) FormsParsList.traverseBottomUp(visitor);
        if(Type!=null) Type.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormsParsList1(\n");

        if(FormsParsList!=null)
            buffer.append(FormsParsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+ParName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormsParsList1]");
        return buffer.toString();
    }
}
