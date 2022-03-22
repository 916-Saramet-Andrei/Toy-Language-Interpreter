package Model.Statements;

import Exceptions.*;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Expressions.Exp;
import Model.ProgramState.PrgState;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

public class NewHeapStmt implements IStmt {
    String id;
    Exp exp;

    public NewHeapStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof NewHeapStmt))
            return false;
        NewHeapStmt newHeapStmt = (NewHeapStmt) o;
        return this.id.equals(newHeapStmt.id) && this.exp.equals(newHeapStmt.exp);
    }

    @Override
    public String toString() {
        return "new(" + this.id + ", " + this.exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, IdentifierException, KeyException, TypeException, FileException, InvalidHeapAddressException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Value> heap = state.getHeap();
        if (symTable.isDefined(this.id)) {
            Value valId = symTable.lookup(this.id);
            if (valId.getType() instanceof RefType) {
                RefValue refValue = (RefValue) valId;
                Value val = this.exp.eval(symTable, heap);
                if (refValue.getReferencedType().equals(val.getType())) {
                    int key;
                    key = heap.insert(val);
                    symTable.update(this.id, new RefValue(key, val.getType()));
                } else {
                    throw new TypeException("referenced type " + refValue.getType() + " and value type " + val.getType() + " do not match");
                }
            } else {
                throw new TypeException("declared variable " + this.id + " is not of reference type");
            }
        } else {
            throw new IdentifierException("the variable " + this.id + " has not been declared before");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckException {
        Type typeVar, typeExp;
        try {
            typeVar = typeEnv.lookup(this.id);
        } catch (KeyException ke) {
            throw new TypeCheckException("assignment statement typcheck: the variable has not been declared before");
        }
        typeExp = this.exp.typecheck(typeEnv);
        if (typeVar.equals(new RefType(typeExp)))
            return typeEnv;
        else
            throw new TypeCheckException("new heap typecheck: right hand side and left hand side have different types");
    }

    @Override
    public IStmt deepCopy() {
        return new NewHeapStmt(this.id, this.exp.deepCopy());
    }
}
