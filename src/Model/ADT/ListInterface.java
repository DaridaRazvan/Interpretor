package Model.ADT;

import Exceptions.*;
import java.util.LinkedList;

public interface ListInterface<T> {
    void add(T element);
    void insert(int position, T element);
    void remove(T element) throws InterpretorException;
    LinkedList<T> getElements();
}
