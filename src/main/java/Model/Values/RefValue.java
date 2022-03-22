package Model.Values;

import Model.Types.RefType;
import Model.Types.Type;

public class RefValue implements Value {
    int address;
    Type locationType;

    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return this.address;
    }

    public Type getReferencedType() {
        return this.locationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof RefValue))
            return false;
        RefValue refValue = (RefValue) o;
        return this.address == refValue.address && this.locationType.equals(refValue.locationType);
    }

    @Override
    public String toString() {
        return "(" + this.address + ", " + this.locationType.toString() + ")";
    }

    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    @Override
    public Value deepCopy() {
        return new RefValue(this.address, this.locationType.deepCopy());
    }
}
