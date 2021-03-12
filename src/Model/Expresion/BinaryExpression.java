package Model.Expresion;

public abstract class BinaryExpression implements Expression {
    protected Expression leftSide;
    protected Expression rightSide;
    protected OPERATION operation;

    public enum OPERATION{
        LESS,
        LESS_OR_EQUAL,
        MORE,
        MORE_OR_EQUAL,
        EQUAL,
        NOT_EQUAL
    }

    public BinaryExpression(Expression leftSide,Expression rightSide,OPERATION operation){
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.operation = operation;
    }

    public String toString(){
        return leftSide.toString() + " " + operation.toString() + " " + rightSide.toString();
    }
}
