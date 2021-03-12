package Model.Expresion;

import Exceptions.*;
import Model.ADT.HeapInterface;
import Model.Types.Type;
import Model.Value.Value;
import Model.ADT.DictionaryInterface;

public class ValueExpression implements Expression {
    Value value;

    public ValueExpression(Value value){
        this.value = value;
    }

    @Override
    public Type typeCheck(DictionaryInterface<String, Type> typeTable){
        return value.getType();
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table, HeapInterface<Value> heapTable) throws InterpretorException {
        return this.value;
    }

    public String toString(){
        return value.toString();
    }
}
