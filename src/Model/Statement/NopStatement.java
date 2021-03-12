package Model.Statement;

import Exceptions.*;
import Model.ADT.DictionaryInterface;
import Model.ProgramState;
import Model.Types.Type;

public class NopStatement implements Statement {
    public NopStatement() {}

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException{
        return typeTable;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        return null;
    }

    public String toString(){
        return " ";
    }
}
