package Model.Statements;

import Exceptions.*;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.ADTs.MyILatchTable;
import Model.ProgramState.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

import java.util.Objects;

public class AwaitStatement implements IStmt {
    String id;

    public AwaitStatement(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AwaitStatement))
            return false;
        AwaitStatement awaitStatement = (AwaitStatement) o;
        return this.id.equals(awaitStatement.id);
    }

    @Override
    public String toString() {
        return "await(" + this.id + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, IdentifierException, KeyException, TypeException, FileException, InvalidHeapAddressException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Value> heap = state.getHeap();
        MyILatchTable latchTable = state.getLatchTable();
        if (symTable.isDefined(this.id)) {
            Value value = symTable.lookup(this.id);
            if (value.getType().equals(new IntType())) {
                IntValue intValue = (IntValue) value;
                int address = intValue.getVal();
                if (latchTable.isDefined(address)) {
                    if (latchTable.lookup(address) != 0) {
                        state.getStk().push(this);
                    }
                } else {
                    throw new KeyException("the used variable's value " + this.id + " does not represent a valid entry in the latch table");
                }
            } else {
                throw new TypeException("the used variable " + this.id + " does not have type integer");
            }
        } else {
            throw new IdentifierException("the used variable " + this.id + " was not declared before");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckException {
        Type typeVar;
        try {
            typeVar = typeEnv.lookup(this.id);
        } catch (KeyException ke) {
            throw new TypeCheckException("new latch typecheck: the used variable " + this.id + " has not been declared before");
        }
        if (typeVar.equals(new IntType())) {
            return typeEnv;
        } else {
            throw new TypeCheckException("new latch typecheck: the used variable " + this.id + " is not of type integer");
        }
    }

    @Override
    public AwaitStatement deepCopy() {
        return new AwaitStatement(this.id);
    }
}
