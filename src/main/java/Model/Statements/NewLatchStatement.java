package Model.Statements;

import Exceptions.*;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.ADTs.MyILatchTable;
import Model.Expressions.Exp;
import Model.ProgramState.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

import java.util.Objects;

public class NewLatchStatement implements IStmt {
    String id;
    Exp exp;

    public NewLatchStatement(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewLatchStatement))
            return false;
        NewLatchStatement newLatchStatement = (NewLatchStatement) o;
        return this.id.equals(newLatchStatement.id) && this.exp.equals(newLatchStatement.exp);
    }

    @Override
    public String toString() {
        return "newLatch(" + this.id + ", " + this.exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, IdentifierException, KeyException, TypeException, FileException, InvalidHeapAddressException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Value> heap = state.getHeap();
        MyILatchTable latchTable = state.getLatchTable();
        Value value = this.exp.eval(symTable, heap);
        if (value.getType().equals(new IntType())) {
            IntValue intValue = (IntValue) value;
            int newAddress = latchTable.insert(intValue.getVal());
            if (symTable.isDefined(this.id)) {
                if (symTable.lookup(this.id).getType().equals(new IntType())) {
                    symTable.update(this.id, new IntValue(newAddress));
                } else {
                    throw new TypeException("the used variable " + this.id + " does not have type integer");
                }
            } else {
                throw new IdentifierException("the used variable " + this.id + " was not declared before");
            }
        } else {
            throw new TypeException("the type of the expression " + this.exp.toString() + " is not integer");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckException {
        Type typeVar, typeExp;
        try {
            typeVar = typeEnv.lookup(this.id);
        } catch (KeyException ke) {
            throw new TypeCheckException("new latch typecheck: the used variable " + this.id + " has not been declared before");
        }
        typeExp = this.exp.typecheck(typeEnv);
        if (typeVar.equals(new IntType())) {
            if (typeExp.equals(new IntType())) {
                return typeEnv;
            } else {
                throw new TypeCheckException("new latch typecheck: the expression " + this.exp.toString() + " does not have type integer");
            }
        } else {
            throw new TypeCheckException("new latch typecheck: the used variable " + this.id + " is not of type integer");
        }
    }

    @Override
    public NewLatchStatement deepCopy() {
        return new NewLatchStatement(this.id, this.exp.deepCopy());
    }
}
