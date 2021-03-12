package Model.Statement;

import Exceptions.*;
import Model.ProgramState;
import Model.Types.Type;
import Model.Value.Value;
import Model.Expresion.Expression;
import Model.ADT.DictionaryInterface;
import Model.ADT.StackInterface;

public class AssignmentStatement implements Statement{
    String variableName;
    Expression expression;

     public AssignmentStatement(String variableName, Expression expression){
         this.variableName = variableName;
         this.expression = expression;
     }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException{
        if(!typeTable.getElementWithKey(variableName).equals(expression.typeCheck(typeTable)))
            throw new InterpretorException("Right side and left side have different types.(Type check)");
        return typeTable;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        StackInterface<Statement> stack = state.getExecutionStack();
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();

        if(symbolTable.isVariableDefined(this.variableName)){
            Value value = this.expression.evaluate(symbolTable,state.getHeap());
            if(value.getType().equals(symbolTable.getElementWithKey(this.variableName).getType())) {
                symbolTable.update(this.variableName, value);
            }else {
                throw new InterpretorException("Types do not match.");
            }
        } else{
            throw new InterpretorException("Variable not defined.");
        }

        return null;
    }

    public String toString(){
         return variableName + "= " + expression.toString();
    }
}
