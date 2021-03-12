package Model.Statement;

import Exceptions.*;
import Model.ADT.DictionaryInterface;
import Model.Expresion.Expression;
import Model.ProgramState;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Value.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenFileStatement implements Statement{
    Expression expression;

    public OpenFileStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException{
        if(!expression.typeCheck(typeTable).equals(new StringType()))
            throw new InterpretorException("Open file requires a string expression");
        return typeTable;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        DictionaryInterface<String, BufferedReader> fileTable = state.getFileTable();

        Value evaluateExpression = expression.evaluate(state.getSymbolTable(), state.getHeap());

        StringValue fileName = (StringValue) evaluateExpression;
        if(fileTable.containsKey(fileName.getValue()))
            throw new InterpretorException("File" + fileName + " is open!");

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName.getValue()));
            fileTable.add(fileName.getValue(), bufferedReader);
        }catch(IOException exception){
            throw new InterpretorException("IO exception: " + exception.getMessage());
        }

        return null;
    }

    public String toString(){
        return "open: " + expression.toString();
    }
}
