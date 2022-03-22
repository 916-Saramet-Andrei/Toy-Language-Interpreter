package Model.Statements;

import Exceptions.TypeCheckException;
import Model.ADTs.MyIDictionary;
import Model.ProgramState.PrgState;
import Model.Types.Type;

public class NopStmt implements IStmt {

    public NopStmt() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        return o instanceof NopStmt;
    }

    @Override
    public String toString() {
        return "no operation";
    }

    @Override
    public PrgState execute(PrgState state) {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckException {
        return typeEnv;
    }

    @Override
    public NopStmt deepCopy() {
        return new NopStmt();
    }
}
