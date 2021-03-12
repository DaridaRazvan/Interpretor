package Repository;

import Model.ProgramState;
import Exceptions.*;

import java.util.List;

public interface RepositoryInterface {
    List<ProgramState> getProgramStatesList();
    void addProgramState(ProgramState state);
    void logProgramState(ProgramState state) throws InterpretorException;
    void setProgramStatesList(List<ProgramState> programStates);
}
