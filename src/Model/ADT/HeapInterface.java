package Model.ADT;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public interface HeapInterface<T> {
    int allocate(T value);
    T get(int address);
    void put(int address, T value);
    T deallocate(int address);
    Map<Integer,T> getContent();
    boolean exists(int address);
    void setContent(Map<Integer, T> map);
    public String toString();
    }
