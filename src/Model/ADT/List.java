package Model.ADT;

import Exceptions.*;
import java.util.LinkedList;

public class List<T> implements ListInterface<T>{
    LinkedList<T> ElementList;

    public List(){
        this.ElementList = new LinkedList<>();
    }

    public void add(T element){
        this.ElementList.add(element);
    }

    public void remove(T element) throws InterpretorException{
        if(this.ElementList.isEmpty())
            throw new InterpretorException("Empty list");
        this.ElementList.remove(element);
    }

    public void insert(int position, T element){
        this.ElementList.add(position, element);
    }

    public LinkedList<T> getElements(){
        return this.ElementList;
    }

    public String toString(){
        String result = "";
        for(T elem: ElementList){
            if(elem != null){
                result += elem.toString();
                result += '\n';
            }
        }
        return result;
    }
}
