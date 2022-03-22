package Model.ADTs;

import Exceptions.KeyException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyHeap<V> implements MyIHeap<V> {
    Map<Integer, V> map;
    int newAddress;

    public MyHeap() {
        this.map = new ConcurrentHashMap<>();
        this.newAddress = 1;
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
    public boolean isDefined(Integer key) {
        return this.map.containsKey(key);
    }

    @Override
    public V lookup(Integer key) throws KeyException {
        if (this.map.containsKey(key))
            return this.map.get(key);
        else
            throw new KeyException("the address does not belong to the heap");
    }

    @Override
    public void insert(Integer key, V value) throws KeyException {
        if (!this.map.containsKey(key))
            this.map.put(key, value);
        else
            throw new KeyException("the given address has already associated a value in the heap");
    }

    @Override
    public void update(Integer key, V value) throws KeyException {
        if (this.map.containsKey(key))
            this.map.replace(key, value);
        else
            throw new KeyException("the address does not belong to the heap");
    }


    @Override
    public void remove(Integer key) throws KeyException {
        if (this.map.containsKey(key))
            this.map.remove(key);
        else
            throw new KeyException("the address does not belong to the heap");
    }

    @Override
    public String toString() {
        String str = "";
        for (int key : this.map.keySet()) {
            str += key + " -> " + this.map.get(key).toString() + "\n";
        }
        return str;
    }

    @Override
    public String keysToString() {
        String str = "";
        for (int key : this.map.keySet()) {
            str += key + "\n";
        }
        return str;
    }

    @Override
    public void setContent(Map<Integer, V> map) {
        this.map = map;
    }

    @Override
    public Map<Integer, V> getContent() {
        return this.map;
    }

    @Override
    public int insert(V value) {
        int currentAddress = this.newAddress;
        this.newAddress++;
        this.map.put(currentAddress, value);
        return currentAddress;
    }

    @Override
    public MyIHeap<V> deepCopy() {
        MyIHeap<V> heap = new MyHeap<>();
        for (Integer key : this.map.keySet()) {
            try {
                heap.insert(key, this.map.get(key));
            } catch (KeyException ke) {
            }
        }
        return heap;
    }
}
