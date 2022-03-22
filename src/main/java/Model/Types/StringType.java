package Model.Types;

import Model.Values.StringValue;
import Model.Values.Value;

public class StringType implements Type {

    public StringType() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        return o instanceof StringType;
    }

    @Override
    public String toString() {
        return "string";
    }

    @Override
    public Value defaultValue() {
        return new StringValue("");
    }

    @Override
    public StringType deepCopy() {
        return new StringType();
    }
}
