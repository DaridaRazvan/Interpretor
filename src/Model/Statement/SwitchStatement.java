package Model.Statement;

import Exceptions.InterpretorException;
import Model.ADT.DictionaryInterface;
import Model.ADT.StackInterface;
import Model.Expresion.BinaryExpression;
import Model.Expresion.Expression;
import Model.Expresion.RelationalExpression;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

import javax.swing.plaf.nimbus.State;

public class SwitchStatement implements Statement{
    Expression expression;
    Expression expression1;
    Expression expression2;
    Statement statement1;
    Statement statement2;
    Statement statement3;

    public SwitchStatement(Expression expression, Expression expression1, Statement statement1, Expression expression2, Statement statement2, Statement statement3){
        this.expression = expression;
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.statement1 = statement1;
        this.statement2 = statement2;
        this.statement3 = statement3;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException {
        Type type = expression.typeCheck(typeTable);
        Type type1 = expression1.typeCheck(typeTable);
        if(type.equals(type1)) {
            Type type2 = expression2.typeCheck(typeTable);
            if(type.equals(type2)){
                statement1.typeCheck(typeTable.deepCopy());
                statement2.typeCheck(typeTable.deepCopy());
                statement3.typeCheck(typeTable.deepCopy());
                return typeTable;
            }else throw new InterpretorException("Expression 3 has a different type.");
        }else throw new InterpretorException("Expression 1 and 2 have different types.");

    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        StackInterface<Statement> executionStack = state.getExecutionStack();
        //Value expressionValue = expression.evaluate(state.getSymbolTable(),state.getHeap());
        //Value expression1Value = expression1.evaluate(state.getSymbolTable(),state.getHeap());
        //Value expression2Value = expression2.evaluate(state.getSymbolTable(),state.getHeap());

        executionStack.push(new IfStatement(new RelationalExpression(expression,expression1, BinaryExpression.OPERATION.EQUAL),statement1,
                new IfStatement(new RelationalExpression(expression,expression2, BinaryExpression.OPERATION.EQUAL),statement2,statement3)));

        /*
        if(expressionValue.equals(expression1Value)){
            executionStack.push(statement1);
        }
        else if (expressionValue.equals(expression2Value)){
            executionStack.push(statement2);
        }
        else {
            executionStack.push(statement3);
        } */

        return null;
    }

    @Override
    public String toString() {
        return "(Switch(" + expression.toString() + ")" + "\n" +
                "case(" + expression1 + ")" + ":" + statement1.toString() + "\n" +
                "case(" + expression2 + ")" + ":" + statement2.toString() + "\n" +
                "default:" + statement3;
    }
}
