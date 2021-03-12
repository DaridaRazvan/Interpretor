package Model.Expresion;

import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Value.*;
import Exceptions.*;

public class RelationalExpression extends BinaryExpression {

    public RelationalExpression(Expression leftSide, Expression rightSide, OPERATION operation){
        super(leftSide, rightSide, operation);
    }

    public static IntValue getValue(DictionaryInterface<String, Value> table, HeapInterface<Value> heapTable, Expression expression) throws InterpretorException{
        Value afterEvaluation = expression.evaluate(table,heapTable);
        if(afterEvaluation instanceof IntValue)
            return (IntValue)afterEvaluation;
        throw new InterpretorException("Operand is not integer!!");
    }

    @Override
    public Type typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException {
        Type type1, type2;
        type1 = leftSide.typeCheck(typeTable);
        type2 = rightSide.typeCheck(typeTable);


        if(type1.equals(new IntType())){
            if(type2.equals(new IntType())){
                return new BoolType();
            }
            else throw new InterpretorException("Second operand is not an integer!!");
        }
        else throw new InterpretorException("The first operand is not an integer");
    }

    public Value evaluate(DictionaryInterface<String, Value> table,HeapInterface<Value> heapTable) throws InterpretorException{
        IntValue leftSide = getValue(table, heapTable,this.leftSide);
        IntValue rightSide = getValue(table, heapTable,this.rightSide);

        switch(operation){
            case LESS: return new BoolValue(leftSide.getValue() < rightSide.getValue());
            case LESS_OR_EQUAL: return new BoolValue(leftSide.getValue() <= rightSide.getValue());
            case MORE: return new BoolValue(leftSide.getValue() > rightSide.getValue());
            case MORE_OR_EQUAL: return new BoolValue(leftSide.getValue() >= rightSide.getValue());
            case EQUAL: return new BoolValue(leftSide.getValue() == rightSide.getValue());
            case NOT_EQUAL: return new BoolValue(leftSide.getValue() != rightSide.getValue());
            default: return new BoolValue();
        }
    }
}
