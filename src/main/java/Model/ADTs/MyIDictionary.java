package Model.ADTs;

import Exceptions.KeyException;

import java.util.Map;

public interface MyIDictionary<K, V> {
    int size();

    boolean isEmpty();

    boolean isDefined(K key);

    V lookup(K key) throws KeyException;

    void insert(K key, V value) throws KeyException;

    void update(K key, V value) throws KeyException;

    void remove(K key) throws KeyException;

    String keysToString();

    void setContent(Map<K, V> map);

    Map<K, V> getContent();

    MyIDictionary<K, V> deepCopy();
}
