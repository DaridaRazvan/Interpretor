package Model.Statement;

import Exceptions.*;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Expresion.Expression;
import Model.ProgramState;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Value.RefValue;
import Model.Value.Value;

import java.util.concurrent.atomic.AtomicInteger;

public class WriteHeapStatement implements Statement{
    String variableName;
    Expression expression;

    public WriteHeapStatement(String variableName, Expression expression){
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException{
        if(typeTable.getElementWithKey(variableName).equals(new RefType(expression.typeCheck(typeTable))))
            return typeTable;
        throw new InterpretorException("right side and left side have different types.");
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException{
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        HeapInterface<Value> heapTable  = state.getHeap();

        if(symbolTable.isVariableDefined(variableName)){
            Value value = symbolTable.getElementWithKey(variableName);
            if(value.getType() instanceof RefType){
                int referenceAddress = ((RefValue)value).getAddr();
                if(heapTable.exists(referenceAddress)){
                    Value value1 = expression.evaluate(symbolTable,heapTable);
                    if(value1.getType().equals(heapTable.get(referenceAddress).getType())){
                        heapTable.put(referenceAddress,value1);
                    }
                    else throw new InterpretorException("Expression not of variable type.");
                }
                else throw new InterpretorException("Value does not exist on heap.");
            }
            else throw new InterpretorException("Value is not a reference.");
        }
        else throw new InterpretorException("Variable not declared.");

        return null;
    }

    public String toString(){
        return "wH( " + variableName + ", " + expression.toString() + " )";
    }
}
