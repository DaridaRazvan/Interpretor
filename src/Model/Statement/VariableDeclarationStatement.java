package Model.Statement;

import Exceptions.*;
import Model.ProgramState;
import Model.Value.*;
import Model.Types.*;
import Model.ADT.DictionaryInterface;


public class VariableDeclarationStatement implements Statement{
    String variableName;
    Type variableType;

    public VariableDeclarationStatement(String variableName, Type variableType){
        this.variableName = variableName;
        this.variableType = variableType;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException{
        typeTable.add(variableName,variableType);
        return typeTable;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        if(symbolTable.isVariableDefined(this.variableName))
            throw new InterpretorException("Variable defined");
        else{
            if(this.variableType.equals(new IntType()))
                symbolTable.add(this.variableName, new IntValue());
            else if(this.variableType.equals(new BoolType()))
                symbolTable.add(this.variableName, new BoolValue());
            else if(this.variableType.equals(new StringType()))
                symbolTable.add(this.variableName, new StringValue());
            else if(this.variableType.equals(new RefType()))
                symbolTable.add(this.variableName,new RefType().defaultValue());
            else if(this.variableType.equals(new RefType(new RefType())))
                symbolTable.add(this.variableName,new RefType(new RefType()).defaultValue());
            else throw new InterpretorException("Invalid type");
        }

        return null;
    }

    public String toString() {
        return this.variableType.toString() + " " + this.variableName;
    }
}
