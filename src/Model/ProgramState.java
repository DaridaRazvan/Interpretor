package Model;

import Exceptions.*;
import Model.ADT.*;
import Model.Value.*;
import Model.Statement.Statement;
import java.io.BufferedReader;
import java.util.concurrent.atomic.AtomicInteger;

public class ProgramState {
    private StackInterface<Statement> executionStack;
    private DictionaryInterface<String, Value> symbolTable;
    private ListInterface<Value> outputConsole;
    private HeapInterface<Value> heap;
    private DictionaryInterface<String, BufferedReader> fileTable;
    private Statement original;

    private static final AtomicInteger lastProgramStateId = new AtomicInteger(1);
    private final AtomicInteger programStateId;

    public ProgramState(StackInterface<Statement> executionStack, DictionaryInterface<String,Value> symbolTable,ListInterface<Value> outputConsole,HeapInterface<Value> heap,Statement original,DictionaryInterface<String, BufferedReader> fileTable){
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.outputConsole = outputConsole;
        this.heap = heap;
        this.fileTable = fileTable;
        this.original = original;
        programStateId = new AtomicInteger(1);
    }

    public ProgramState(StackInterface<Statement> executionStack, DictionaryInterface<String,Value> symbolTable,ListInterface<Value> outputConsole,HeapInterface<Value> heap,DictionaryInterface<String, BufferedReader> fileTable){
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.outputConsole = outputConsole;
        this.heap = heap;
        this.fileTable = fileTable;
        programStateId = new AtomicInteger(newId());
    }

    public AtomicInteger getProgramStateId(){
        return programStateId;
    }

    public AtomicInteger getLastProgramState(){
        return lastProgramStateId;
    }

    public static synchronized int newId(){
        return lastProgramStateId.incrementAndGet();
    }

    public static AtomicInteger getLastProgramStateId(){
        return lastProgramStateId;
    }

    public boolean isNotCompleted(){
        return !executionStack.isEmpty();
    }

    public ProgramState oneStep() throws InterpretorException {
        if(executionStack.isEmpty())
            throw new InterpretorException("Execution stack is empty!");
        var currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    public StackInterface<Statement> getExecutionStack(){
        return this.executionStack;
    }

    public void setExecutionStack(StackInterface<Statement> executionStack){
        this.executionStack = executionStack;
    }

    public DictionaryInterface<String, Value> getSymbolTable(){
        return this.symbolTable;
    }

    public void setSymbolTable(DictionaryInterface<String,Value> symbolTable){
        this.symbolTable = symbolTable;
    }

    public ListInterface<Value> getOutputConsole(){
        return this.outputConsole;
    }

    public void setOutputConsole(ListInterface<Value> outputConsole){
        this.outputConsole = outputConsole;
    }

    public DictionaryInterface<String, BufferedReader> getFileTable(){
        return fileTable;
    }

    public void setFileTable(DictionaryInterface<String, BufferedReader> fileTable){
        this.fileTable = fileTable;
    }

    public HeapInterface<Value> getHeap() {
        return heap;
    }

    public void setHeap(HeapInterface<Value> heap) {
        this.heap = heap;
    }

    public String toString(){
        return  "ProgramId: " + programStateId + "\n" +
                "Execution Stack: \n" +
                this.executionStack.toString() + "\n" +
                "Symbol Table: \n" +
                this.symbolTable.toString() +"\n" +
                "Output Console: \n" +
                this.outputConsole.toString() +"\n" +
                "Heap: \n" +
                this.heap.toString() + '\n' +
                "File Table: \n" +
                this.fileTable.toString() + "\n" +
                "-------------- \n";
    }

}
