package Model.Statement;

import Exceptions.*;
import Model.ADT.DictionaryInterface;
import Model.Expresion.Expression;
import Model.ProgramState;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements Statement{
    Expression expression;
    String variableName;

    public ReadFileStatement(Expression expression, String variableName){
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException{
        if(!expression.typeCheck(typeTable).equals(new StringType()))
            throw new InterpretorException("Read file requires a string.");
        if(!typeTable.getElementWithKey(variableName).equals(new IntType()))
            throw new InterpretorException("Read file requires an int as variable paramenter");
        return typeTable;
    }

    public ProgramState execute(ProgramState state) throws InterpretorException{
        var symbolTable = state.getSymbolTable();
        var fileTable = state.getFileTable();

        if(!symbolTable.containsKey(variableName))
            throw new InterpretorException("Variable is not defined");

        Value variableValue = symbolTable.getElementWithKey(variableName);
        if(!variableValue.getType().equals(new IntType()))
            throw new InterpretorException("Variable is not int");

        Value evaluatedExpression = expression.evaluate(symbolTable, state.getHeap());
        if(!evaluatedExpression.getType().equals(new StringType()))
            throw new InterpretorException("Expression is not string type");

        StringValue fileName = (StringValue) evaluatedExpression;
        if(!fileTable.containsKey(fileName.getValue()))
            throw new InterpretorException("File is not open!");

        BufferedReader bufferedReader = fileTable.getElementWithKey(fileName.getValue());

        try{
            String lineRead = bufferedReader.readLine();
            if(lineRead == null)
                lineRead = "0";
            symbolTable.add(variableName, new IntValue(Integer.parseInt(lineRead)));
        }catch (IOException e){
            throw new InterpretorException("IO Exception:" + e.getMessage());
        }

        return null;
    }

    public String toString(){
        return "Read:" + variableName + " " + expression.toString();
    }
}
