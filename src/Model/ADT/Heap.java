package Model.ADT;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Heap<T> implements HeapInterface<T> {
    AtomicInteger freeValue;
    Map<Integer, T> heap;

    public Heap(HashMap<Integer, T> heap){
        this.heap = heap;
        freeValue = new AtomicInteger(0);
    }

    public Heap(){
        this.heap = new HashMap<>();
        freeValue = new AtomicInteger(0);
    }

    @Override
    public int allocate(T value){
        heap.put(freeValue.incrementAndGet(),value);

        return freeValue.get();
    }

    @Override
    public T get(int address){
        return heap.get(address);
    }

    @Override
    public void put(int address, T value){
        heap.put(address, value);
    }

    @Override
    public T deallocate(int address){
        return heap.remove(address);
    }

    @Override
    public Map<Integer,T> getContent(){
        return heap;
    }

    @Override
    public boolean exists(int address){
        return heap.containsKey(address);
    }

    @Override
    public void setContent(Map<Integer, T> map){
        heap = map;
    }

    @Override
    public String toString(){
        String result = "";
        for(var elem: heap.keySet()){
            if(elem != null) {
                result += elem.toString();
                result += " -> ";
                result += heap.get(elem).toString();
                result += '\n';
            }
        }
        return result;
    }

}
