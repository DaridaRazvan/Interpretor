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

public class ConditionalStatement implements Statement {
    String id;
    Expression expression1;
    Expression expression2;
    Expression expression3;

    public ConditionalStatement(String id, Expression expression1, Expression expression2, Expression expression3){
        this.id = id;
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException {
        boolean definedVariable = typeTable.isVariableDefined(id);
        if(definedVariable == false){
            throw new InterpretorException("Variable not defined");
        }
        Type type1 = expression1.typeCheck(typeTable);
        if(type1.equals(new BoolType())){
            Type idType = typeTable.getElementWithKey(id);
            Type type2 = expression2.typeCheck(typeTable);
            if(idType.equals(type2)){
                Type type3 = expression3.typeCheck(typeTable);
                if(idType.equals(type3)){
                    return typeTable;
                } else throw new InterpretorException("Expression 3 has a different type");
            }else throw new InterpretorException("Id and Expression 2 have different types");
        }else throw new InterpretorException("Expression 1 is not bool");
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        StackInterface<Statement> executionStack = state.getExecutionStack();

        executionStack.push(new IfStatement(expression1, new AssignmentStatement(id, expression2), new AssignmentStatement(id, expression3)));

        return null;
    }

    @Override
    public String toString() {
        return id + "=" + expression1 + "?" + expression2 +":"+expression3;
    }
}
