package Model.Statement;

import Exceptions.*;
import Model.ADT.DictionaryInterface;
import Model.ProgramState;
import Model.Expresion.Expression;
import Model.Value.*;
import Model.Types.*;
import Model.ADT.StackInterface;

public class IfStatement implements Statement {
    Expression expression;
    Statement thenStatement;
    Statement elseStatement;

    public IfStatement(Expression expression, Statement thenStatement, Statement elseStatement){
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException{
        if(!expression.typeCheck(typeTable).equals(new BoolType()))
            throw new InterpretorException("The condition doesn't have the type bool.");
        thenStatement.typeCheck(typeTable.deepCopy());
        elseStatement.typeCheck(typeTable.deepCopy());
        return typeTable;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        StackInterface<Statement> stack = state.getExecutionStack();
        Value evaluation = this.expression.evaluate(state.getSymbolTable(),state.getHeap());
        if(evaluation.getType().equals(new BoolType())){
            if(evaluation.equals(new BoolValue(true)))
                stack.push(this.thenStatement);
            else
                stack.push(this.elseStatement);
        }
        else throw new InterpretorException("Can't evaluate.");

        return null;
    }

    public String toString(){
        return "If(" + this.expression.toString() + ") Then(" + this.thenStatement.toString() +") Else(" + this.elseStatement.toString() +")";
    }
}
