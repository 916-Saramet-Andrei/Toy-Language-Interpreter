package Model.ADTs;

import Exceptions.IndexException;

import java.util.List;

public interface MyIList<T> {
    int size();

    boolean isEmpty();

    void append(T value);

    void add(int index, T value) throws IndexException;

    T remove(int index) throws IndexException;

    T get(int index) throws IndexException;

    T set(int index, T value) throws IndexException;

    int getIndex(T value);

    List<T> getContent();

    void setContent(List<T> list);

    MyIList<T> deepCopy();
}
