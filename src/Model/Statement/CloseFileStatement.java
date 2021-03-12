package Model.Statement;

import Model.ADT.DictionaryInterface;
import Model.Expresion.*;
import Model.ProgramState;
import Exceptions.*;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFileStatement implements Statement{
    Expression expression;

    public CloseFileStatement(Expression expression){
        this.expression = expression;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException{
        if(!expression.typeCheck(typeTable).equals(new StringType()))
            throw new InterpretorException("Close file requires a string expression");
        return typeTable;
    }

    public ProgramState execute(ProgramState state) throws InterpretorException{
        DictionaryInterface<String, BufferedReader> fileTable = state.getFileTable();

        Value evaluateExpression = expression.evaluate(state.getSymbolTable(),state.getHeap());

        StringValue fileName = (StringValue) evaluateExpression;
        if(!fileTable.containsKey(fileName.getValue()))
            throw new InterpretorException("File:" + fileName + " does not exist!");

        try{
            BufferedReader bufferedReader = fileTable.getElementWithKey(fileName.getValue());
            bufferedReader.close();
            fileTable.remove(fileName.getValue());
        }catch (IOException exception){
            throw new InterpretorException("An IO Exception occured: "+ exception.getMessage());
        }

        return null;
    }

    public String toString(){
        return "close:" + expression.toString();
    }
}
