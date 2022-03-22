package Model.Statements;

import Exceptions.*;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.ADTs.MyIList;
import Model.Expressions.Exp;
import Model.ProgramState.PrgState;
import Model.Types.Type;
import Model.Values.Value;

public class PrintStmt implements IStmt {
    Exp exp;

    public PrintStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PrintStmt))
            return false;
        PrintStmt printStmt = (PrintStmt) o;
        return this.exp.equals(printStmt.exp);
    }

    @Override
    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, IdentifierException, KeyException, TypeException, InvalidHeapAddressException {
        MyIList<Value> out = state.getOut();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Value> heap = state.getHeap();
        out.append(this.exp.eval(symTable, heap));
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckException {
        this.exp.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public PrintStmt deepCopy() {
        return new PrintStmt(this.exp.deepCopy());
    }
}
