package Model.Expressions;

import Exceptions.*;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class ArithExp implements Exp {
    Exp e1;
    Exp e2;
    String operator;

    public ArithExp(String operator, Exp e1, Exp e2) {
        this.operator = operator;
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ArithExp))
            return false;
        ArithExp arithExp = (ArithExp) o;
        return this.operator.equals(arithExp.operator) && this.e1.equals(arithExp.e1) && this.e2.equals(arithExp.e2);
    }

    @Override
    public String toString() {
        return this.e1.toString() + " " + this.operator + " " + this.e2.toString();
    }


    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> heap) throws DivisionByZeroException, IdentifierException, KeyException, TypeException, InvalidHeapAddressException {
        Value v1;
        Value v2;
        v1 = this.e1.eval(tbl, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = this.e2.eval(tbl, heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1 = i1.getVal();
                int n2 = i2.getVal();
                switch (this.operator) {
                    case "+": {
                        return new IntValue(n1 + n2);
                    }
                    case "-": {
                        return new IntValue(n1 - n2);
                    }
                    case "*": {
                        return new IntValue(n1 * n2);
                    }
                    default: {
                        if (n2 != 0)
                            return new IntValue(n1 / n2);
                        else
                            throw new DivisionByZeroException();
                    }
                }
            } else {
                throw new TypeException("the second operand is not an integer");
            }
        } else {
            throw new TypeException("the first operand is not an integer");
        }
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckException {
        Type type1, type2;
        type1 = this.e1.typecheck(typeEnv);
        type2 = this.e2.typecheck(typeEnv);
        if (type1.equals(new IntType()))
            if (type2.equals(new IntType()))
                return new IntType();
            else
                throw new TypeCheckException("the second operand is not an integer");
        else
            throw new TypeCheckException("the first operand is not an integer");
    }

    @Override
    public ArithExp deepCopy() {
        return new ArithExp(this.operator, this.e1.deepCopy(), this.e2.deepCopy());
    }
}
