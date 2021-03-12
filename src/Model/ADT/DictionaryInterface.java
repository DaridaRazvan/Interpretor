package Model.ADT;

import Exceptions.*;
import java.util.HashMap;

public interface DictionaryInterface<K,V> {
    void add(K key,V value);
    V getElementWithKey(K key);
    void remove(K key) throws InterpretorException;
    void update(K key, V value);
    HashMap<K,V> getDictionary();
    boolean isVariableDefined(K key);
    boolean containsKey(K key);
    DictionaryInterface<K, V> deepCopy();
}
