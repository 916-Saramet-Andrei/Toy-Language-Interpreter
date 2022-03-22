package Model.ProgramState;

import Exceptions.*;
import Model.ADTs.*;
import Model.Statements.IStmt;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;

public class PrgState {
    static int currentID = 0;

    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIHeap<Value> heap;
    MyIList<Value> out;
    MyIDictionary<StringValue, BufferedReader> fileTable;
    MyILatchTable latchTable;
    IStmt originalProgram;
    int ID;

    public static synchronized int getNewID() {
        currentID++;
        return currentID;
    }

    public PrgState(MyIStack<IStmt> exeStack, MyIDictionary<String, Value> symTable, MyIHeap<Value> heap, MyIList<Value> out, MyIDictionary<StringValue, BufferedReader> fileTable, MyILatchTable latchTable, IStmt prg) {
        this.ID = PrgState.getNewID();
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.heap = heap;
        this.out = out;
        this.fileTable = fileTable;
        this.latchTable = latchTable;
        this.originalProgram = prg.deepCopy();
        this.exeStack.push(prg);
    }

    @Override
    public String toString() {
        return "The program state is the following:\nID: " + this.ID + "\nThe execution stack:\n" + this.exeStack.toString() + "\nThe symbols table:\n" + this.symTable.toString() + "\nThe heap is:\n" + this.heap.toString() + "\nThe output:\n" + this.out.toString() + "\nThe file table is:\n" + this.fileTable.keysToString();
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public MyIStack<IStmt> getStk() {
        return this.exeStack;
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return this.symTable;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    public MyIHeap<Value> getHeap() {
        return this.heap;
    }

    public void setHeap(MyIHeap<Value> heap) {
        this.heap = heap;
    }

    public MyIList<Value> getOut() {
        return this.out;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return this.fileTable;
    }

    public void setFileTable(MyIDictionary<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public MyILatchTable getLatchTable() {
        return this.latchTable;
    }

    public void setLatchTable(MyILatchTable latchTable) {
        this.latchTable = latchTable;
    }

    public IStmt getOriginalProgram() {
        return this.originalProgram;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    public boolean isNotCompleted() {
        return !this.exeStack.isEmpty();
    }

    public PrgState oneStep() throws DivisionByZeroException, EmptyStackException, IdentifierException, KeyException, TypeException, FileException, InvalidHeapAddressException {
        if (!exeStack.isEmpty()) {
            IStmt crtStmt = exeStack.pop();
            return crtStmt.execute(this);
        } else {
            throw new EmptyStackException();
        }
    }
}
