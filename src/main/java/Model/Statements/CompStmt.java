package Model.Statements;

import Exceptions.TypeCheckException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.ProgramState.PrgState;
import Model.Types.Type;

public class CompStmt implements IStmt {
    IStmt first;
    IStmt second;

    public CompStmt(IStmt first, IStmt second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CompStmt))
            return false;
        CompStmt compStmt = (CompStmt) o;
        return this.first.equals(compStmt.first) && this.second.equals(compStmt.second);
    }

    @Override
    public String toString() {
        return this.first.toString() + "; " + this.second.toString();
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIStack<IStmt> stk = state.getStk();
        stk.push(this.second);
        stk.push(this.first);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckException {
        return this.second.typecheck(this.first.typecheck(typeEnv));
    }

    @Override
    public CompStmt deepCopy() {
        return new CompStmt(this.first.deepCopy(), this.second.deepCopy());
    }
}
