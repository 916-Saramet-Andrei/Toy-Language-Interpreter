package Model.Types;

import Model.Values.RefValue;
import Model.Values.Value;

public class RefType implements Type {

    Type inner;

    public RefType(Type inner) {
        this.inner = inner;
    }

    public Type getInner() {
        return this.inner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof RefType))
            return false;
        RefType refType = (RefType) o;
        return this.inner.equals(refType.inner);
    }

    @Override
    public String toString() {
        return "ref(" + this.inner.toString() + ")";
    }

    @Override
    public Value defaultValue() {
        return new RefValue(0, this.inner);
    }

    @Override
    public RefType deepCopy() {
        return new RefType(this.inner.deepCopy());
    }
}
