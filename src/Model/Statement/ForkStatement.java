package Model.Statement;

import Exceptions.*;
import Model.ADT.DictionaryInterface;
import Model.ADT.StackInterface;
import Model.ADT.StackImp;
import Model.ProgramState;
import Model.Types.Type;

public class ForkStatement implements Statement{
    Statement statement;

    public ForkStatement(Statement statement){
        this.statement = statement;
    }

    @Override
    public DictionaryInterface<String, Type> typeCheck(DictionaryInterface<String, Type> typeTable) throws InterpretorException{
        statement.typeCheck(typeTable.deepCopy());
        return typeTable;
    }

    public ProgramState execute(ProgramState state) throws InterpretorException{
        StackInterface<Statement> newExecutionStack = new StackImp<>();
        newExecutionStack.push(statement);
        return new ProgramState(newExecutionStack,
                state.getSymbolTable().deepCopy(),state.getOutputConsole(),state.getHeap(),state.getFileTable());
    }

    public String toString(){
        return "fork(" + statement.toString() + ")";
    }
}
