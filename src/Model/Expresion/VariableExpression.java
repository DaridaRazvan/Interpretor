package Model.Expresion;

import Exceptions.*;
import Model.ADT.HeapInterface;
import Model.Types.Type;
import Model.Value.Value;
import Model.ADT.DictionaryInterface;

public class VariableExpression implements Expression{
    String variable;

    public VariableExpression(String variable){
        this.variable = variable;
    }

    @Override
    public Type typeCheck(DictionaryInterface<String, Type> typeTable){
        return typeTable.getElementWithKey(variable);
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table, HeapInterface<Value> heapTable) throws InterpretorException {
        return table.getElementWithKey(this.variable);
    }

    public String toString(){
        return variable;
    }
}
