package Model.Expresion;

import Exceptions.*;
import Model.ADT.HeapInterface;
import Model.Types.*;
import Model.Value.*;
import Model.ADT.DictionaryInterface;

public class LogicalExpression implements Expression{
    Expression firstExpression;
    Expression secondExpression;
    String operation;

    public LogicalExpression(Expression firstExpression, Expression secondExpression, String operation){
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operation = operation;
    }

    @Override
    public Type typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException{
        Type type1, type2;
        type1 = firstExpression.typeCheck(typeTable);
        type2 = secondExpression.typeCheck(typeTable);

        if(type1.equals(new BoolType())){
            if(type2.equals(new BoolType())){
                return new BoolType();
            }
            else throw new InterpretorException("Second operand is not boolean");
        }
        else throw new InterpretorException("The first operand is not boolean");
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table, HeapInterface<Value> tableHeap) throws InterpretorException {
        Value first;
        Value second;

        first = this.firstExpression.evaluate(table,tableHeap);
        second = this.secondExpression.evaluate(table,tableHeap);

        boolean firstTerm = ((BoolValue) first).getValue();
        boolean secondTerm = ((BoolValue) second).getValue();

        switch(this.operation){
            case "and":
                return new BoolValue(firstTerm && secondTerm);
            case "or":
                return new BoolValue(firstTerm || secondTerm);
            default:
                throw new InterpretorException("Invalid operation.");
        }
    }

    public String toString(){
        switch(this.operation){
            case "and": return firstExpression.toString() + " && " + secondExpression.toString();
            case "or": return firstExpression.toString() + " || " + secondExpression.toString();
            default: return " ";
        }
    }
}
