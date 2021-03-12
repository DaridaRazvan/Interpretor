package Model.Statement;

import Exceptions.*;
import Model.ADT.DictionaryInterface;
import Model.ProgramState;
import Model.ADT.StackInterface;
import Model.Types.Type;

public class CompoundStatement implements Statement {
    Statement First;
    Statement Second;

    public CompoundStatement(Statement First, Statement Second){
        this.First = First;
        this.Second = Second;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException{
        return Second.typeCheck(First.typeCheck(typeTable));
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException{
        StackInterface<Statement> stack = state.getExecutionStack();
        stack.push(this.Second);
        stack.push(this.First);
        return null;
    }

    public String toString(){
        return this.First.toString() + " ; " + this.Second.toString();
    }
}
