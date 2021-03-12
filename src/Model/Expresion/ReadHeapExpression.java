package Model.Expresion;


import Exceptions.*;
import Model.ADT.Dictionary;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Value.RefValue;
import Model.Value.Value;

public class ReadHeapExpression implements Expression{
    Expression expression;

    public ReadHeapExpression(Expression expression){
        this.expression = expression;
    }

    @Override
    public Type typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException{
        Type type = expression.typeCheck(typeTable);
        if(type instanceof RefType){
            RefType reference = (RefType) type;
            return reference.getInner();
        }
        else throw new InterpretorException("The readHeap argument is not of Reference Type");
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table, HeapInterface<Value> heap) throws InterpretorException {
        Value value = expression.evaluate(table, heap);
        RefValue refVal = (RefValue) value;
        int address = refVal.getAddr();
        if(heap.exists(address)){
            return heap.get(address);
        }
        else throw new InterpretorException("Not allocated on heap");
    }

    public String toString(){
        return "readHeap(" + expression.toString() + ")";
    }
}

