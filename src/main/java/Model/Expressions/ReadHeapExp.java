package Model.Expressions;

import Exceptions.*;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

public class ReadHeapExp implements Exp {
    Exp exp;

    public ReadHeapExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ReadHeapExp))
            return false;
        ReadHeapExp readHeapExp = (ReadHeapExp) o;
        return this.exp.equals(readHeapExp.exp);
    }

    @Override
    public String toString() {
        return "rH(" + this.exp.toString() + ")";
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> heap) throws DivisionByZeroException, IdentifierException, KeyException, TypeException, InvalidHeapAddressException {
        Value val = this.exp.eval(tbl, heap);
        if (val.getType() instanceof RefType) {
            RefValue refValue = (RefValue) val;
            if (heap.isDefined(refValue.getAddress())) {
                return heap.lookup(refValue.getAddress());
            } else {
                throw new InvalidHeapAddressException("the address does not belong to the heap");
            }
        } else {
            throw new TypeException("the value is not of reference type");
        }
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckException {
        Type type;
        type = this.exp.typecheck(typeEnv);
        if (type instanceof RefType)
            return ((RefType) type).getInner();
        else
            throw new TypeCheckException("the ReadHeap argument is not a RefType");
    }

    @Override
    public ReadHeapExp deepCopy() {
        return new ReadHeapExp(this.exp.deepCopy());
    }
}
