package Model.ADTs;

import Exceptions.EmptyStackException;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    Stack<T> stack;

    public MyStack() {
        this.stack = new Stack<>();
    }

    @Override
    public void push(T value) {
        this.stack.push(value);
    }

    @Override
    public T top() throws EmptyStackException {
        if (!this.stack.isEmpty())
            return this.stack.peek();
        else
            throw new EmptyStackException();
    }

    @Override
    public T pop() throws EmptyStackException {
        if (!this.stack.isEmpty())
            return this.stack.pop();
        else
            throw new EmptyStackException();
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public String toString() {
        String str = "";
        for (T element : this.stack) {
            str = element.toString() + "\n" + str;
        }
        return str;
    }

    @Override
    public MyIStack<T> deepCopy() {
        MyIStack<T> stack = new MyStack<>();
        for (T el : this.stack)
            stack.push(el);
        return stack;
    }

    @Override
    public Stack<T> getContent() {
        return this.stack;
    }
}
