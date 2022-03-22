package Model.Statements;

import Exceptions.*;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.ADTs.MyIStack;
import Model.Expressions.Exp;
import Model.ProgramState.PrgState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

public class WhileStmt implements IStmt {
    Exp exp;
    IStmt stmt;

    public WhileStmt(Exp exp, IStmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof WhileStmt))
            return false;
        WhileStmt whileStmt = (WhileStmt) o;
        return this.exp.equals(whileStmt.exp) && this.stmt.equals(whileStmt.stmt);
    }

    @Override
    public String toString() {
        return "(WHILE (" + this.exp.toString() + ") " + this.stmt.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, IdentifierException, KeyException, TypeException, FileException, InvalidHeapAddressException {
        MyIStack<IStmt> stk = state.getStk();
        MyIHeap<Value> heap = state.getHeap();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        Value cond = this.exp.eval(symTable, heap);
        if (cond.getType().equals(new BoolType())) {
            if (cond.equals(new BoolValue(true))) {
                stk.push(this);
                stk.push(this.stmt);
            }
        } else {
            throw new TypeException("the type of the expression is not bool");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckException {
        Type type;
        type = this.exp.typecheck(typeEnv);
        if (type.equals(new BoolType())) {
            this.stmt.typecheck(typeEnv.deepCopy());
            return typeEnv;
        } else
            throw new TypeCheckException("the condition of the WHILE statement is not of type boolean");
    }

    @Override
    public WhileStmt deepCopy() {
        return new WhileStmt(this.exp.deepCopy(), this.stmt.deepCopy());
    }
}
