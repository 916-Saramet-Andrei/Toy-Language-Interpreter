package Model.ADTs;

import Exceptions.KeyException;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {
    Map<K, V> map;

    public MyDictionary() {
        this.map = new HashMap<>();
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean isDefined(K key) {
        return this.map.containsKey(key);
    }

    @Override
    public V lookup(K key) throws KeyException {
        if (this.map.containsKey(key))
            return this.map.get(key);
        else
            throw new KeyException("the key does not exist in the dictionary");
    }

    @Override
    public void insert(K key, V value) throws KeyException {
        if (!this.map.containsKey(key))
            this.map.put(key, value);
        else
            throw new KeyException("the given key has already associated a value in the dictionary");
    }

    @Override
    public void update(K key, V value) throws KeyException {
        if (this.map.containsKey(key))
            this.map.replace(key, value);
        else
            throw new KeyException("the key does not exist in the dictionary");
    }


    @Override
    public void remove(K key) throws KeyException {
        if (this.map.containsKey(key))
            this.map.remove(key);
        else
            throw new KeyException("the key does not exist in the dictionary");
    }

    @Override
    public String toString() {
        String str = "";
        for (K key : this.map.keySet()) {
            str += key.toString() + " --> " + this.map.get(key).toString() + "\n";
        }
        return str;
    }

    @Override
    public String keysToString() {
        String str = "";
        for (K key : this.map.keySet()) {
            str += key.toString() + "\n";
        }
        return str;
    }

    @Override
    public void setContent(Map<K, V> map) {
        this.map = map;
    }

    @Override
    public Map<K, V> getContent() {
        return this.map;
    }

    @Override
    public MyIDictionary<K, V> deepCopy() {
        MyIDictionary<K, V> map = new MyDictionary<>();
        for (K key : this.map.keySet()) {
            try {
                map.insert(key, this.map.get(key));
            } catch (KeyException ke) {
            }
        }
        return map;
    }
}
