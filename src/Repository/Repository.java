package Repository;

import Model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import Exceptions.*;

public class Repository implements RepositoryInterface{
    List<ProgramState> programStates;
    String logFilePath;

    public Repository(String logFilePath){
        this.programStates = new ArrayList<>();
        this.logFilePath = logFilePath;
    }

   public List<ProgramState> getProgramStatesList(){
        return programStates;
   }

    public void setProgramStatesList(List<ProgramState> programStates){
        this.programStates = programStates;
    }

    public void addProgramState(ProgramState state){
        this.programStates.add(state);
    }

    public void logProgramState(ProgramState state) throws InterpretorException{
        PrintWriter logFile;

        try{
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath,true)));
        } catch(IOException exception){
            throw new InterpretorException("IO Exception:" + exception.getMessage());
        }

        logFile.println(state.toString());
        logFile.close();
    }
}
