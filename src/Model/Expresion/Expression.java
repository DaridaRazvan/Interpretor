package Model.Expresion;

import Exceptions.*;
import Model.ADT.HeapInterface;
import Model.Types.Type;
import Model.Value.Value;
import Model.ADT.DictionaryInterface;

public interface Expression {
    Value evaluate(DictionaryInterface<String, Value> table, HeapInterface<Value> tableHeap)throws InterpretorException;
    public Type typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException;
}
