package Controller;

import Exceptions.*;
//import Model.ADT.List;
import Model.ADT.DictionaryInterface;
import Model.ProgramState;
import Model.Types.Type;
import Model.Value.RefValue;
import Model.Value.Value;
import Repository.RepositoryInterface;
import Model.Statement.Statement;
import Model.ADT.StackInterface;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    RepositoryInterface repository;
    ExecutorService executorService;
    boolean displayFlag;

    public Controller(RepositoryInterface repository){
        this.repository = repository;
        this.displayFlag = false;
    }

    public void setDisplayFlag(boolean displayFlag){
        this.displayFlag = displayFlag;
    }

    public String stateToString(ProgramState state){
        return state.toString();
    }

    public void addProgramState(ProgramState state){
        this.repository.addProgramState(state);
    }

    private Map<Integer, Value> unsafeGarbageCollector(Set<Integer> symbolTableAddress, Map<Integer, Value> heap){
        return heap.entrySet().stream()
                .filter(e -> symbolTableAddress.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

    List<ProgramState> removeCompletedPrograms(List<ProgramState> programStates){
        return programStates.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    Set<Integer> getAddress(List<Collection<Value>> symbolTableValues, Map<Integer, Value> heap){
        Set<Integer> addresses = new HashSet<>();
        symbolTableValues.forEach(symbolTable ->symbolTable.stream()
        .filter(value -> value instanceof RefValue)
        .forEach(value -> {
                while (value instanceof RefValue) {
                    int address = ((RefValue) value).getAddr();
                    addresses.add(address);
                    value = heap.get(address);
                }
            }));
        return addresses;
    }

    void conservativeGarbageCollector(List<ProgramState> programStateList){
        ProgramState programState = programStateList.get(0);

        List<Collection<Value>> symbolTableValues = programStateList.stream()
                .map(currentProgramState -> currentProgramState.getSymbolTable().getDictionary().values())
                .collect(Collectors.toList());

        Map<Integer, Value> heap = programState.getHeap().getContent();

        Set<Integer> addresses = getAddress(symbolTableValues,heap);

        Map<Integer,Value> newHeap = unsafeGarbageCollector(addresses,heap);

        programState.getHeap().setContent(newHeap);
    }

    public void oneStepForAllPrograms(List<ProgramState> programStates) throws InterpretorException{

        programStates.forEach(programState -> {
            try{
                repository.logProgramState(programState);
            } catch (InterpretorException InterpretorException){
                InterpretorException.printStackTrace();
            }
        });

        List<Callable<ProgramState>> callableList = programStates.stream()
                .map((ProgramState programState) -> (Callable<ProgramState>)(programState::oneStep))
                .collect(Collectors.toList());

        try{
            List<ProgramState> newProgramStateList = executorService.invokeAll(callableList).stream()
                    .map(programStateFuture -> {
                        try{
                            return programStateFuture.get();
                        } catch (InterruptedException  | ExecutionException exception){
                            System.out.println(exception.getMessage());
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            programStates.addAll(newProgramStateList);

        } catch( InterruptedException interruptedException){
            throw new InterpretorException("An InterruptedException occured:" + interruptedException.getMessage());
        }

        programStates.forEach(programState -> {
            try{
                repository.logProgramState(programState);
            } catch (InterpretorException InterpretorException){
                InterpretorException.printStackTrace();
            }
        });
        repository.setProgramStatesList(programStates);
    }

    public ProgramState allSteps(boolean firstRun) throws InterpretorException{

        boolean lastRun = false;

        if(firstRun == true) {

            for (ProgramState state : repository.getProgramStatesList()) {
                DictionaryInterface<String, Type> typeTable = new Model.ADT.Dictionary<>();
                state.getExecutionStack().peek().typeCheck(typeTable);
            }

            executorService = Executors.newFixedThreadPool(2);
        }

        List<ProgramState> programStateList = removeCompletedPrograms(repository.getProgramStatesList());
        var programState = programStateList.get(0);

        //while(programStateList.size() > 0){
        if(programStateList.size() > 0){
            conservativeGarbageCollector(programStateList);
            oneStepForAllPrograms(programStateList);
            programStateList = removeCompletedPrograms(programStateList);
        }

        if(programStateList.size() == 0){
            lastRun = true;
        }

        if(lastRun == true) {
            executorService.shutdown();

            repository.setProgramStatesList(programStateList);
        }

        return programState;
        //System.out.println(programState.getOutputConsole().toString());
    }

    public List<ProgramState> programStateList(){
        return repository.getProgramStatesList();
    }

}
