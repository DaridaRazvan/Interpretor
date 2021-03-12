package Model.ADT;

import Exceptions.*;
import java.util.Stack;

public class StackImp<T> implements StackInterface<T>{
    Stack<T> stack;

    public StackImp(){
        this.stack = new Stack<T>();
    }

    public T peek() {
        return stack.peek();
    }

    public void push(T pushElem){
        this.stack.push(pushElem);
    }
    public boolean isEmpty(){
        return this.stack.isEmpty();
    }

    public T pop() throws InterpretorException{
        if(this.stack.isEmpty())
            throw new InterpretorException("Empty Stack.");
        return this.stack.pop();
    }

    public String toString(){
        return stack.toString();
    }
}
