package Model.Statement;

import Exceptions.InterpretorException;
import Model.ADT.DictionaryInterface;
import Model.ADT.StackInterface;
import Model.Expresion.Expression;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

public class RepeatStatement implements Statement{
    Statement statement;
    Expression expression;

    public RepeatStatement(Statement statement, Expression expression){
        this.statement = statement;
        this.expression = expression;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException{
        Type type = expression.typeCheck(typeTable);
        if(type.equals(new BoolType())){
            statement.typeCheck(typeTable.deepCopy());
            return typeTable;
        }
        throw new InterpretorException("The condition of Repeat is not bool type");
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        StackInterface<Statement> executionStack = state.getExecutionStack();
        Value expressionValue = expression.evaluate(state.getSymbolTable(),state.getHeap());

        if(expressionValue.equals(new BoolValue(false))){
            executionStack.push(statement);
            executionStack.push(new RepeatStatement(statement,expression));
        }

        return null;
    }

    @Override
    public String toString() {
        return "Repeat{ " + statement + " } until" + expression;
    }
}
