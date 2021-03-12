package Model.Statement;


import Exceptions.*;
import Model.ADT.DictionaryInterface;
import Model.ProgramState;
import Model.Expresion.Expression;
import Model.Types.Type;
import Model.Value.*;
import Model.ADT.ListInterface;

public class PrintStatement implements Statement {
    Expression expression;

    public PrintStatement(Expression expression){
        this.expression = expression;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException{
        expression.typeCheck(typeTable);
        return typeTable;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        ListInterface<Value> outputConsole = state.getOutputConsole();
        outputConsole.add(this.expression.evaluate(state.getSymbolTable(),state.getHeap()));
        return null;
    }

    public String toString(){
        return "print( " + expression.toString() + " )" ;
    }
}
