package Model.Statement;

import Exceptions.*;
import Model.ADT.DictionaryInterface;
import Model.ADT.StackInterface;
import Model.Expresion.Expression;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

public class WhileStatement implements Statement {
    Expression expression;
    Statement statement;

    public WhileStatement(Expression expression, Statement statement){
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException{
        Type type = expression.typeCheck(typeTable);
        if(type.equals(new BoolType())){
            statement.typeCheck(typeTable.deepCopy());
            return typeTable;
        }
        throw new InterpretorException("The condition of While is not bool type");
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        StackInterface<Statement> stack = state.getExecutionStack();
        Value expressionValue = expression.evaluate(state.getSymbolTable(),state.getHeap());
        if(expressionValue.equals(new BoolValue(true))){
            stack.push(new WhileStatement(expression, statement));
            stack.push(statement);
        }

        return null;
    }

    public String toString(){
        return "while(" + expression.toString() + ") {" + statement.toString() + "}";
    }

}
