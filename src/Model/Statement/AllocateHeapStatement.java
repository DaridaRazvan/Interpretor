package Model.Statement;

import Exceptions.*;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.ADT.StackInterface;
import Model.Expresion.Expression;
import Model.ProgramState;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Value.RefValue;
import Model.Value.Value;


public class AllocateHeapStatement implements Statement {
    String variableName;
    Expression expression;

    public AllocateHeapStatement(String variableName, Expression expression){
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException{
        Type typeVariable = typeTable.getElementWithKey(variableName);
        Type typeExpression = expression.typeCheck(typeTable);
        if(!typeVariable.equals(new RefType(typeExpression)))
            throw new InterpretorException("Right side and left side have different types.");
        return typeTable;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        StackInterface<Statement> stack = state.getExecutionStack();
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        HeapInterface<Value> heapTable = state.getHeap();

        if(symbolTable.isVariableDefined(this.variableName)){
            if(symbolTable.getElementWithKey(variableName).getType() instanceof RefType){
            Value value = expression.evaluate(symbolTable,heapTable);
            if(value.getType().equals(((RefType)(symbolTable.getElementWithKey(this.variableName).getType())).getInner())){
                int addr = heapTable.allocate(value);
                symbolTable.update(variableName,new RefValue(addr,value.getType()));
                }
                else throw new InterpretorException("Value is not of correct type.");
            }
            else throw new InterpretorException("Variable is not of reference type");
        }
        else throw new InterpretorException("Variable is not declared");

        return null;
    }

    public String toString(){
        return "new(" + variableName + ", " + expression.toString() + ")";
    }

}
