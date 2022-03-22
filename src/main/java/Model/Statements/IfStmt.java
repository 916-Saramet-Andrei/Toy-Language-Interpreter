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

public class IfStmt implements IStmt {
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp exp, IStmt thenS, IStmt elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof IfStmt))
            return false;
        IfStmt ifStmt = (IfStmt) o;
        return this.exp.equals(ifStmt.exp) && this.thenS.equals(ifStmt.thenS) && this.elseS.equals(ifStmt.elseS);
    }

    @Override
    public String toString() {
        return "(IF (" + this.exp.toString() + ") THEN (" + this.thenS.toString() + ") ELSE (" + this.elseS.toString() + "))";
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, IdentifierException, KeyException, TypeException, InvalidHeapAddressException {
        MyIStack<IStmt> stk = state.getStk();
        MyIHeap<Value> heap = state.getHeap();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        Value cond = this.exp.eval(symTable, heap);
        if (cond.getType().equals(new BoolType())) {
            if (cond.equals(new BoolValue(true))) {
                stk.push(thenS);
            } else {
                stk.push(elseS);
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
            this.thenS.typecheck(typeEnv.deepCopy());
            this.elseS.typecheck(typeEnv.deepCopy());
            return typeEnv;
        } else
            throw new TypeCheckException("the condition of the IF statement is not of type boolean");
    }

    @Override
    public IfStmt deepCopy() {
        return new IfStmt(this.exp.deepCopy(), this.thenS.deepCopy(), this.elseS.deepCopy());
    }
}
