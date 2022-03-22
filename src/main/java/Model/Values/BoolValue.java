package Model.Values;

import Model.Types.BoolType;
import Model.Types.Type;

public class BoolValue implements Value {
    boolean val;

    public BoolValue(boolean val) {
        this.val = val;
    }

    public boolean getVal() {
        return this.val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof BoolValue))
            return false;
        BoolValue boolValue = (BoolValue) o;
        return this.val == boolValue.val;
    }

    @Override
    public String toString() {
        return "" + this.val;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public BoolValue deepCopy() {
        return new BoolValue(this.val);
    }
}
