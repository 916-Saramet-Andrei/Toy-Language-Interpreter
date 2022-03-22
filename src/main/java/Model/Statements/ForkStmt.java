package Model.Statements;

import Exceptions.*;
import Model.ADTs.*;
import Model.ProgramState.PrgState;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;

public class ForkStmt implements IStmt {
    IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ForkStmt))
            return false;
        ForkStmt forkStmt = (ForkStmt) o;
        return this.stmt.equals(forkStmt.stmt);
    }

    @Override
    public String toString() {
        return "fork(" + this.stmt.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, IdentifierException, KeyException, TypeException, FileException, InvalidHeapAddressException {
        MyIStack<IStmt> newStk = new MyStack<>();
        MyIDictionary<String, Value> newSymTable = state.getSymTable().deepCopy();
        MyIHeap<Value> newHeap = state.getHeap();
        MyIDictionary<StringValue, BufferedReader> newFileTable = state.getFileTable();
        MyIList<Value> newOut = state.getOut();
        MyILatchTable latchTable = state.getLatchTable();
        return new PrgState(newStk, newSymTable, newHeap, newOut, newFileTable, latchTable, this.stmt);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckException {
        this.stmt.typecheck(typeEnv.deepCopy());
        return typeEnv;
    }

    @Override
    public ForkStmt deepCopy() {
        return new ForkStmt(this.stmt.deepCopy());
    }
}
