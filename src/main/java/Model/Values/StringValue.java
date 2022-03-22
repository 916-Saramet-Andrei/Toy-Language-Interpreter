package Model.Values;

import Model.Types.StringType;
import Model.Types.Type;

public class StringValue implements Value {
    String val;

    public StringValue(String val) {
        this.val = val;
    }

    public String getVal() {
        return this.val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof StringValue))
            return false;
        StringValue stringValue = (StringValue) o;
        return this.val.equals(stringValue.val);
    }

    @Override
    public String toString() {
        return "\"" + this.val + "\"";
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public Value deepCopy() {
        return new StringValue(this.val);
    }
}
