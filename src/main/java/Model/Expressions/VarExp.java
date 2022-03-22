package Model.Expressions;

import Exceptions.KeyException;
import Exceptions.TypeCheckException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;

public class VarExp implements Exp {
    String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof VarExp))
            return false;
        VarExp varExp = (VarExp) o;
        return this.id.equals(varExp.id);
    }

    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> heap) throws KeyException {
        return tbl.lookup(this.id);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckException {
        return typeEnv.lookup(this.id);
    }

    @Override
    public VarExp deepCopy() {
        return new VarExp(this.id);
    }
}
