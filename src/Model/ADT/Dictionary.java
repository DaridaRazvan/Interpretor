package Model.ADT;

import java.util.HashMap;
import Exceptions.*;

public class Dictionary<K,V> implements DictionaryInterface<K,V> {
    HashMap<K,V> dictionary;

    public Dictionary(){
        this.dictionary = new HashMap<>();
    }

    public void add(K key, V value){
        this.dictionary.put(key,value);
    }

    public V getElementWithKey(K key){
        return this.dictionary.get(key);
    }

    public void remove(K key) throws InterpretorException{
        if(this.dictionary.isEmpty())
            throw new InterpretorException("Empty Dictionary.");
        this.dictionary.remove(key);
    }

    public boolean isVariableDefined(K key){
        return this.dictionary.containsKey(key);
    }

    public void update(K key, V value){
        this.dictionary.replace(key,value);
    }

    public HashMap<K,V> getDictionary(){
        return this.dictionary;
    }

    public String toString(){
        String result = "";
        for(K key: dictionary.keySet()){
            result += key.toString();
            result += "->";
            result += dictionary.get(key).toString();
            result += '\n';
        }
        return result;
    }

    public boolean containsKey(K key){
        if(dictionary.containsKey(key))
            return true;
        return false;
    }

    public DictionaryInterface<K, V> deepCopy(){
        synchronized (dictionary){
            DictionaryInterface<K, V> copy = new Dictionary<>();
            dictionary.keySet().forEach(e -> copy.add(e,dictionary.get(e)));
            return copy;
        }
    }

}

