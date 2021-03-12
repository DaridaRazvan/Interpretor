package View;


import Exceptions.InterpretorException;
import Model.ProgramState;

import java.util.List;

public class ExitCommand extends Command {
    public ExitCommand(String key, String description){
        super(key,description);
    }

    public  List<ProgramState> getList(){
        return null;
    }

    public void execute(boolean firstRun) throws InterpretorException {
        System.exit(0);
    }

}
