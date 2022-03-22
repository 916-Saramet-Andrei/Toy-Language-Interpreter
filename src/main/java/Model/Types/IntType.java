package Model.Types;

import Model.Values.IntValue;
import Model.Values.Value;

public class IntType implements Type {

    public IntType() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        return o instanceof IntType;
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }

    @Override
    public IntType deepCopy() {
        return new IntType();
    }
}
