package Model.Statements;

import Exceptions.*;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Expressions.Exp;
import Model.ProgramState.PrgState;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFileStmt implements IStmt {
    Exp exp;

    public CloseFileStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CloseFileStmt))
            return false;
        CloseFileStmt closeFileStmt = (CloseFileStmt) o;
        return this.exp.equals(closeFileStmt.exp);
    }

    @Override
    public String toString() {
        return "closeRFile(" + this.exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, IdentifierException, KeyException, TypeException, FileException, InvalidHeapAddressException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Value> heap = state.getHeap();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        Value file = this.exp.eval(symTable, heap);
        if (file.getType().equals(new StringType())) {
            StringValue fileString = (StringValue) file;
            if (fileTable.isDefined(fileString)) {
                BufferedReader fileReader = fileTable.lookup(fileString);
                try {
                    fileReader.close();
                } catch (IOException ioe) {
                    throw new FileException("An error occurred when closing the file ", ioe);
                }
                fileTable.remove(fileString);
            } else {
                throw new IdentifierException("the file name " + fileString.getVal() + " was not opened");
            }
        } else {
            throw new TypeException("The filename type is not a string");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckException {
        Type type = this.exp.typecheck(typeEnv);
        if (type.equals(new StringType()))
            return typeEnv;
        else
            throw new TypeCheckException("close file statement: the file name is not a string");
    }

    @Override
    public CloseFileStmt deepCopy() {
        return new CloseFileStmt(this.exp.deepCopy());
    }
}
