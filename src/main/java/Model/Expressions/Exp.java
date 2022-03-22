package Model.Expressions;

import Exceptions.*;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;

public interface Exp {
    Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> heap) throws DivisionByZeroException, IdentifierException, KeyException, TypeException, InvalidHeapAddressException;

    Type typecheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckException;

    Exp deepCopy();
}
