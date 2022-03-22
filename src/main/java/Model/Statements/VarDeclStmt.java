package Model.Statements;

import Exceptions.IdentifierException;
import Exceptions.KeyException;
import Exceptions.TypeCheckException;
import Model.ADTs.MyIDictionary;
import Model.ProgramState.PrgState;
import Model.Types.Type;
import Model.Values.Value;

public class VarDeclStmt implements IStmt {
    String id;
    Type type;

    public VarDeclStmt(String id, Type type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof VarDeclStmt))
            return false;
        VarDeclStmt varDeclStmt = (VarDeclStmt) o;
        return this.id.equals(varDeclStmt.id) && this.type.equals(varDeclStmt.type);
    }

    @Override
    public String toString() {
        return type.toString() + " " + id;
    }

    @Override
    public PrgState execute(PrgState state) throws IdentifierException, KeyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if (!symTable.isDefined(id)) {
            symTable.insert(id, this.type.defaultValue());
        } else {
            throw new IdentifierException("the declared variable " + this.id + " has been declared before");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckException {
        typeEnv.insert(this.id, this.type);
        return typeEnv;
    }

    @Override
    public VarDeclStmt deepCopy() {
        return new VarDeclStmt(this.id, this.type.deepCopy());
    }
}
