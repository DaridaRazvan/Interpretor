package Model.Statement;

import Exceptions.InterpretorException;
import Model.ADT.DictionaryInterface;
import Model.ADT.StackInterface;
import Model.Expresion.Expression;
import Model.Expresion.RelationalExpression;
import Model.Expresion.VariableExpression;
import Model.ProgramState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Value.Value;

import static Model.Expresion.BinaryExpression.OPERATION.LESS;

public class ForStatement implements Statement {
    String id;
    Expression expression1, expression2, expression3;
    Statement statement;

    public ForStatement(String id, Expression expression1, Expression expression2, Expression expression3, Statement statement){
        this.id = id;
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;
        this.statement = statement;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException{
        /*v, exp1, exp2, and exp3 have the type int.*/
        boolean definedVariable = typeTable.isVariableDefined(id);
        if(definedVariable) {
            Type variableType = typeTable.getElementWithKey(id);
            if(!variableType.equals(new IntType())) {
                throw new InterpretorException("variable " + id + " is defined but does not have integer type!");
            }
        }
        else{
            typeTable.add(id, new IntType());
        }
        Type expression1Type = expression1.typeCheck(typeTable);
        if (expression1Type.equals(new IntType())) {
            Type expression2Type = expression2.typeCheck(typeTable);
            if (expression2Type.equals(new IntType())) {
                Type expression3Type = expression3.typeCheck(typeTable);
                if (expression3Type.equals(new IntType())) {
                    return typeTable;
                } else throw new InterpretorException("expression3 value not of type int!");
            } else throw new InterpretorException("expression2 value not of type int!");
        } else throw new InterpretorException("expression1 value not of type int!");
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException{
        StackInterface<Statement> executionStack = state.getExecutionStack();
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();

        if(symbolTable.isVariableDefined(id)){
            executionStack.push(new CompoundStatement(new AssignmentStatement("v", expression1),  new WhileStatement(new RelationalExpression(new VariableExpression("v"),expression2, LESS), new CompoundStatement(statement, new AssignmentStatement("v", expression3)))));
        }else{
            executionStack.push(new CompoundStatement(new CompoundStatement(new VariableDeclarationStatement(id, new IntType()), new AssignmentStatement("v", expression1)), new WhileStatement(new RelationalExpression(new VariableExpression("v"), expression2,LESS), new CompoundStatement(statement, new AssignmentStatement("v", expression3)))));
        }

        state.setExecutionStack(executionStack);
        return null;
    }

    @Override
    public String toString() {
        return "for( " + id + " = " + expression1.toString() + "; " + id + " < " + expression2.toString() + "; " + id + " = " + expression3.toString() + ") {" + statement.toString() + "}";
    }
}
