package Model.Expressions;

import Exceptions.TypeCheckException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;

public class ValueExp implements Exp {
    Value val;

    public ValueExp(Value val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ValueExp))
            return false;
        ValueExp valueExp = (ValueExp) o;
        return this.val.equals(valueExp.val);
    }

    @Override
    public String toString() {
        return this.val.toString();
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> heap) {
        return this.val;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckException {
        return this.val.getType();
    }

    @Override
    public ValueExp deepCopy() {
        return new ValueExp(this.val.deepCopy());
    }
}
