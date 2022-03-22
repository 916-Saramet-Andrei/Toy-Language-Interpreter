package Model.Values;

import Model.Types.IntType;
import Model.Types.Type;

public class IntValue implements Value {
    int val;

    public IntValue(int val) {
        this.val = val;
    }

    public int getVal() {
        return this.val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof IntValue))
            return false;
        IntValue intValue = (IntValue) o;
        return this.val == intValue.val;
    }

    @Override
    public String toString() {
        return "" + this.val;
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public IntValue deepCopy() {
        return new IntValue(this.val);
    }
}
