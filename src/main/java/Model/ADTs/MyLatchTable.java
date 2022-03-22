package Model.ADTs;

import Exceptions.KeyException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyLatchTable implements MyILatchTable {
    Map<Integer, Integer> table;
    int newAddress;

    public MyLatchTable() {
        this.table = new ConcurrentHashMap<>();
        this.newAddress = 1;
    }

    @Override
    public int size() {
        return this.table.size();
    }

    @Override
    public boolean isEmpty() {
        return this.table.isEmpty();
    }

    @Override
    public synchronized boolean isDefined(Integer key) {
        return this.table.containsKey(key);
    }

    @Override
    public synchronized Integer lookup(Integer key) throws KeyException {
        if (this.table.containsKey(key)) {
            return this.table.get(key);
        } else {
            throw new KeyException("the key does not belong to the latch table");
        }
    }

    @Override
    public synchronized void insert(Integer key, Integer value) throws KeyException {
        if (!this.table.containsKey(key)) {
            this.table.put(key, value);
        } else {
            throw new KeyException("the given key has already associated a value in the latch table");
        }
    }

    @Override
    public synchronized void update(Integer key, Integer value) throws KeyException {
        if (this.table.containsKey(key)) {
            this.table.replace(key, value);
        } else {
            throw new KeyException("the key does not belong to the latch table");
        }
    }

    @Override
    public synchronized void remove(Integer key) throws KeyException {
        if (this.table.containsKey(key)) {
            this.table.remove(key);
        } else {
            throw new KeyException("the key does not belong to the table");
        }
    }

    @Override
    public synchronized String toString() {
        String str = "";
        for (int key : this.table.keySet()) {
            str += key + " -> " + this.table.get(key).toString();
        }
        return str;
    }

    @Override
    public synchronized String keysToString() {
        String str = "";
        for (int key : this.table.keySet()) {
            str += key + "\n";
        }
        return str;
    }

    @Override
    public synchronized void setContent(Map<Integer, Integer> table) {
        this.table = table;
    }

    @Override
    public synchronized Map<Integer, Integer> getContent() {
        return this.table;
    }

    @Override
    public synchronized int insert(Integer value) {
        int currentAddress = this.newAddress;
        this.newAddress++;
        this.table.put(currentAddress, value);
        return currentAddress;
    }

    @Override
    public MyILatchTable deepCopy() {
        MyILatchTable latchTable = new MyLatchTable();
        for (Integer key : this.table.keySet()) {
            try {
                latchTable.insert(key, this.table.get(key));
            } catch (KeyException ke) {

            }
        }
        return latchTable;
    }
}
