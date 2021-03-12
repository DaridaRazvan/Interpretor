package Model.Statement;

import Exceptions.InterpretorException;
import Model.ADT.DictionaryInterface;
import Model.ADT.StackInterface;
import Model.ProgramState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Value.IntValue;

public class SleepStatement implements Statement{
    IntValue number;

    public SleepStatement(IntValue number){
        this.number = number;
    }

    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException {
        if(!number.getType().equals(new IntType()))
            throw new InterpretorException("Number has to be an integer");
        if(number.getValue() < 1)
            throw new InterpretorException("Number has to be positive");

        return typeTable;
    }

    public ProgramState execute(ProgramState state) throws InterpretorException {
        StackInterface<Statement> stack = state.getExecutionStack();
        if(number.getValue() != 0){
            stack.push(new SleepStatement(new IntValue(number.getValue() - 1)));
        }

        return null;
    }

    public String toString(){
        return "sleep(" + number + ")";
    }
}
