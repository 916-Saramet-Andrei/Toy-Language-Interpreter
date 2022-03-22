package Model.Statements;

import Exceptions.*;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Expressions.Exp;
import Model.ProgramState.PrgState;
import Model.Types.Type;
import Model.Values.Value;

public class AssignStmt implements IStmt {
    String id;
    Exp exp;

    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof AssignStmt))
            return false;
        AssignStmt assignStmt = (AssignStmt) o;
        return this.id.equals(assignStmt.id) && this.exp.equals(assignStmt.exp);
    }

    @Override
    public String toString() {
        return id + " = " + exp.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, IdentifierException, KeyException, TypeException, InvalidHeapAddressException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Value> heap = state.getHeap();
        if (symTable.isDefined(id)) {
            Value val = exp.eval(symTable, heap);
            Type typeId = symTable.lookup(id).getType();
            if (val.getType().equals(typeId)) {
                symTable.update(id, val);
            } else {
                throw new TypeException("declared type of variable " + this.id + " and type of the assigned expression do not match");
            }
        } else {
            throw new IdentifierException("the used variable " + this.id + " was not declared before");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckException {
        Type typeVar, typeExp;
        try {
            typeVar = typeEnv.lookup(this.id);
        } catch (KeyException ke) {
            throw new TypeCheckException("assignment statement typcheck: the variable has not been declared before");
        }
        typeExp = this.exp.typecheck(typeEnv);
        if (typeVar.equals(typeExp))
            return typeEnv;
        else
            throw new TypeCheckException("assignment typecheck: right hand side and left hand side have different types");
    }

    @Override
    public AssignStmt deepCopy() {
        return new AssignStmt(this.id, this.exp.deepCopy());
    }
}
