package View;

import Controller.Controller;
import Exceptions.InterpretorException;
import Model.ProgramState;
import javafx.scene.control.Alert;

import java.util.List;

public class RunExample extends Command{
    Controller controller;

    public RunExample(String key, String description, Controller controller){
        super(key,description);
        this.controller = controller;
    }

    public List<ProgramState> getList(){
        return controller.programStateList();
    }

    public void execute(boolean firstRun) throws InterpretorException {
        //try{
            controller.allSteps(firstRun);
        //} catch(Exception e){
          //  System.out.println("Error: " + e.getMessage());
        //}

    }

}
