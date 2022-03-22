package Model.Types;

import Model.Values.BoolValue;
import Model.Values.Value;

public class BoolType implements Type {

    public BoolType() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        return o instanceof BoolType;
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }

    @Override
    public BoolType deepCopy() {
        return new BoolType();
    }
}
