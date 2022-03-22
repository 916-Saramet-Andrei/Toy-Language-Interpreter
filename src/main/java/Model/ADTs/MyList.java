package Model.ADTs;

import Exceptions.IndexException;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    List<T> list;

    public MyList() {
        this.list = new ArrayList<T>();
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public void append(T value) {
        this.list.add(value);
    }

    @Override
    public void add(int index, T value) throws IndexException {
        if (index < 0 || index > this.list.size())
            throw new IndexException();
        else
            this.list.add(index, value);
    }

    @Override
    public T remove(int index) throws IndexException {
        if (index < 0 || index >= this.list.size())
            throw new IndexException();
        else
            return list.remove(index);
    }

    @Override
    public T get(int index) throws IndexException {
        if (index < 0 || index >= this.list.size())
            throw new IndexException();
        else
            return this.list.get(index);
    }

    @Override
    public T set(int index, T value) throws IndexException {
        if (index < 0 || index > this.list.size())
            throw new IndexException();
        else
            return this.list.set(index, value);
    }

    @Override
    public String toString() {
        String str = "";
        for (T element : this.list) {
            str += element.toString() + "\n";
        }
        return str;
    }

    @Override
    public int getIndex(T value) {
        return this.list.indexOf(value);
    }

    @Override
    public List<T> getContent() {
        return this.list;
    }

    @Override
    public void setContent(List<T> list) {
        this.list = list;
    }

    @Override
    public MyIList<T> deepCopy() {
        MyIList<T> list = new MyList<>();
        for (T el : this.list) {
            list.append(el);
        }
        return list;
    }
}
