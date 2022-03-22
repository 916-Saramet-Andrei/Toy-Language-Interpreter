package Model.Statements;

import Exceptions.*;
import Model.ADTs.MyIDictionary;
import Model.ProgramState.PrgState;
import Model.Types.Type;

public interface IStmt {
    PrgState execute(PrgState state) throws DivisionByZeroException, IdentifierException, KeyException, TypeException, FileException, InvalidHeapAddressException;

    MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckException;

    IStmt deepCopy();
}
