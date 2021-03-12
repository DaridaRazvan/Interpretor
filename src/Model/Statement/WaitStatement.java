package Model.Statement;

import Exceptions.InterpretorException;
import Model.ADT.DictionaryInterface;
import Model.ADT.StackInterface;
import Model.Expresion.BinaryExpression;
import Model.Expresion.RelationalExpression;
import Model.Expresion.ValueExpression;
import Model.Expresion.VariableExpression;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Value.IntValue;

public class WaitStatement implements Statement{
    IntValue number;

    public WaitStatement(IntValue number){
        this.number = number;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException {
        if(!number.getType().equals(new IntType()))
            throw new InterpretorException("Number has to be an integer");
        if(number.getValue() < 1)
            throw new InterpretorException("Number has to be positive");

        return typeTable;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        StackInterface<Statement> stack = state.getExecutionStack();

     /*   if(number.getValue() != 0){
            stack.push(new WaitStatement(new IntValue(number.getValue() - 1)));
            stack.push(new PrintStatement(new ValueExpression(new IntValue(number.getValue()))));
        } */

        stack.push(new IfStatement(new RelationalExpression(new ValueExpression(number),new ValueExpression(new IntValue(0)), BinaryExpression.OPERATION.EQUAL),
                new NopStatement(),new CompoundStatement(new PrintStatement(new ValueExpression(new IntValue(number.getValue()))),new WaitStatement(new IntValue(number.getValue() - 1)))));

        return null;
    }

    @Override
    public String toString() {
        return "wait(" + number.toString() + ")";
    }
}
