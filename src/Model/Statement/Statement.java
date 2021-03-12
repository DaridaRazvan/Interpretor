package Model.Statement;

import Exceptions.*;
import Model.ADT.DictionaryInterface;
import Model.ProgramState;
import Model.Types.Type;

public interface Statement {
    ProgramState execute(ProgramState state) throws InterpretorException;
    DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException;
}
