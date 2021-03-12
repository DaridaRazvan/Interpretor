package Model.Expresion;

import Exceptions.*;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Types.*;
import Model.Value.*;

public class ArithmeticExpression implements Expression {
    char operation;
    Expression firstExpression;
    Expression secondExpression;

    public ArithmeticExpression(char operation,Expression firstExpression, Expression secondExpression) {
        this.operation = operation;
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    @Override
    public Type typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException{
        Type type1, type2;
        type1 = firstExpression.typeCheck(typeTable);
        type2 = secondExpression.typeCheck(typeTable);

        if(type1.equals(new IntType())){
            if(type2.equals(new IntType())){
                return new IntType();
            }
            else throw new InterpretorException("Second operand is not an integer!");
        }
        else throw new InterpretorException("The first operand is not an integer");
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table, HeapInterface<Value> tableHeap) throws InterpretorException{
        Value first;
        Value second;

        first = this.firstExpression.evaluate(table,tableHeap);
        second = this.secondExpression.evaluate(table,tableHeap);

        int firstTerm = ((IntValue) first).getValue();
        int secondTerm = ((IntValue) second).getValue();

        switch (this.operation) {
            case '+':
                return new IntValue(firstTerm + secondTerm);
            case '-':
                return new IntValue(firstTerm - secondTerm);
            case '*':
                return new IntValue(firstTerm * secondTerm);
            case '/':
                if (secondTerm == 0)
                    throw new InterpretorException("Division by zero");
                return new IntValue(firstTerm / secondTerm);
            default:
                throw new InterpretorException("Invalid operation.");
        }
    }

    public String toString(){

        switch(this.operation){
            case '+': return firstExpression.toString() + " + " + secondExpression.toString();
            case '-': return firstExpression.toString() + " - " + secondExpression.toString();
            case '*': return firstExpression.toString() + " * " + secondExpression.toString();
            case '/': return firstExpression.toString() + " / " + secondExpression.toString();
            default: return " ";
        }
    }
}
