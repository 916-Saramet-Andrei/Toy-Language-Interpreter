package Model.ADTs;

import Exceptions.EmptyStackException;

import java.util.Stack;

public interface MyIStack<T> {
    void push(T value);

    T top() throws EmptyStackException;

    T pop() throws EmptyStackException;

    boolean isEmpty();

    MyIStack<T> deepCopy();

    Stack<T> getContent();
}
