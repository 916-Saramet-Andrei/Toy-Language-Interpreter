package Model.Expressions;

import Exceptions.*;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

public class LogicExp implements Exp {
    Exp e1;
    Exp e2;
    String operator;

    public LogicExp(String operator, Exp e1, Exp e2) {
        this.operator = operator;
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof LogicExp))
            return false;
        LogicExp logicExp = (LogicExp) o;
        return this.operator.equals(logicExp.operator) && this.e1.equals(logicExp.e1) && this.e2.equals(logicExp.e2);
    }

    @Override
    public String toString() {
        return this.e1.toString() + " " + this.operator + " " + this.e2;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> heap) throws DivisionByZeroException, IdentifierException, KeyException, TypeException, InvalidHeapAddressException {
        Value v1;
        Value v2;
        v1 = this.e1.eval(tbl, heap);
        if (v1.getType().equals(new BoolType())) {
            v2 = this.e2.eval(tbl, heap);
            if (v2.getType().equals(new BoolType())) {
                BoolValue b1 = (BoolValue) v1;
                BoolValue b2 = (BoolValue) v2;
                boolean n1 = b1.getVal();
                boolean n2 = b2.getVal();
                if (this.operator.equals("and")) {
                    return new BoolValue(n1 && n2);
                } else {
                    return new BoolValue(n1 || n2);
                }
            } else {
                throw new TypeException("the second operand is not a boolean");
            }
        } else {
            throw new TypeException("the first operand is not a boolean");
        }
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckException {
        Type type1, type2;
        type1 = this.e1.typecheck(typeEnv);
        type2 = this.e2.typecheck(typeEnv);
        if (type1.equals(new BoolType()))
            if (type2.equals(new BoolType()))
                return new BoolType();
            else
                throw new TypeCheckException("the second operand is not a boolean");
        else
            throw new TypeCheckException("the first operand is not a boolean");
    }

    @Override
    public LogicExp deepCopy() {
        return new LogicExp(this.operator, this.e1.deepCopy(), this.e2.deepCopy());
    }
}
