package View;

import Exceptions.InterpretorException;
import Model.ProgramState;

import java.util.List;

public abstract class Command {
    private String key;
    private String description;

    public Command(String key, String description){
        this.key = key;
        this.description = description;
    }

    public abstract List<ProgramState> getList();

    public abstract void execute(boolean firstRun) throws InterpretorException;

    public String getKey(){
        return key;
    }

    public String getDescription(){
        return description;
    }

}
