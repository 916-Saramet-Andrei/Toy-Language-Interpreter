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
import java.io.FileReader;
import java.io.IOException;

public class OpenFileStmt implements IStmt {
    Exp exp;

    public OpenFileStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof OpenFileStmt))
            return false;
        OpenFileStmt openFileStmt = (OpenFileStmt) o;
        return this.exp.equals(openFileStmt.exp);
    }

    @Override
    public String toString() {
        return "openRFile(" + this.exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, IdentifierException, KeyException, TypeException, FileException, InvalidHeapAddressException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Value> heap = state.getHeap();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        Value file = this.exp.eval(symTable, heap);
        if (file.getType().equals(new StringType())) {
            StringValue fileString = (StringValue) file;
            if (!fileTable.isDefined(fileString)) {
                try {
                    BufferedReader fileReader = new BufferedReader(new FileReader(fileString.getVal()));
                    fileTable.insert(fileString, fileReader);
                } catch (IOException ioe) {
                    throw new FileException("An error occurred when opening the file ", ioe);
                }
            } else {
                throw new IdentifierException("the file name " + fileString + " is already opened");
            }
        } else {
            throw new TypeException("the filename type is not a string");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypeCheckException {
        Type type = this.exp.typecheck(typeEnv);
        if (type.equals(new StringType()))
            return typeEnv;
        else
            throw new TypeCheckException("open file statement: the file name is not a string");
    }

    @Override
    public OpenFileStmt deepCopy() {
        return new OpenFileStmt(this.exp.deepCopy());
    }
}
