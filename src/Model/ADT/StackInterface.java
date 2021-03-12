package Model.ADT;

import Exceptions.*;

public interface StackInterface<T> {
    void push(T pushElem);
    boolean isEmpty();
    T pop() throws InterpretorException;
    T peek();
}
