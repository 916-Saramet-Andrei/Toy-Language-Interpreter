package Model.Statements;

import Exceptions.*;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.ADTs.MyILatchTable;
import Model.ADTs.MyIList;
import Model.ProgramState.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;


public class CountDownStatement implements IStmt {
    String id;

    public CountDownStatement(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CountDownStatement))
            return false;
        CountDownStatement countDownStatement = (CountDownStatement) o;
        return this.id.equals(countDownStatement.id);
    }

    @Override
    public String toString() {
        return "countDown(" + this.id + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, IdentifierException, KeyException, TypeException, FileException, InvalidHeapAddressException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyILatchTable latchTable = state.getLatchTable();
        MyIList<Value> out = state.getOut();
        if (symTable.isDefined(this.id)) {
            Value value = symTable.lookup(this.id);
            if (value.getType().equals(new IntType())) {
                IntValue intValue = (IntValue) value;
                int address = intValue.getVal();
                if (latchTable.isDefined(address)) {
                    if (latchTable.lookup(address) > 0) {
                        latchTable.update(address, latchTable.lookup(address) - 1);
                        out.append(new IntValue(state.getID()));
                    } else {
                        out.append(new IntValue(state.getID()));
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
    public CountDownStatement deepCopy() {
        return new CountDownStatement(this.id);
    }
}
