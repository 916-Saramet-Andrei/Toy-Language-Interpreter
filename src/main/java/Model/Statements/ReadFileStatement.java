package Model.Statements;

import Exceptions.*;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Expressions.Exp;
import Model.ProgramState.PrgState;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStmt {
    Exp exp;
    String id;

    public ReadFileStatement(Exp exp, String id) {
        this.exp = exp;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ReadFileStatement))
            return false;
        ReadFileStatement readFileStatement = (ReadFileStatement) o;
        return this.exp.equals(readFileStatement.exp) && this.id.equals(readFileStatement.id);
    }

    @Override
    public String toString() {
        return "readFile(" + this.exp.toString() + ", " + this.id + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, IdentifierException, KeyException, TypeException, FileException, InvalidHeapAddressException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Value> heap = state.getHeap();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        if (symTable.isDefined(this.id)) {
            Value var = symTable.lookup(this.id);
            if (var.getType().equals(new IntType())) {
                Value file = this.exp.eval(symTable, heap);
                if (file.getType().equals(new StringType())) {
                    StringValue fileString = (StringValue) file;
                    if (fileTable.isDefined(fileString)) {
                        BufferedReader fileReader = fileTable.lookup(fileString);
                        try {
                            String line = fileReader.readLine();
                            if (line == null) {
                                symTable.update(this.id, new IntValue(0));
                            } else {
                                symTable.update(this.id, new IntValue(Integer.parseInt(line)));
                            }
                        } catch (IOException ioe) {
                            throw new FileException("An error occurred when reading from the file ", ioe);
                        }
                    } else {
                        throw new IdentifierException("the file name " + fileString + " has not been opened");
                    }
                } else {
                    throw new TypeException("the file name type is not a string");
                }
            } else {
                throw new TypeException("the type of the variable " + this.id + " is not integer");
            }
        } else {
            throw new IdentifierException("the given variable " + this.id + " has not been declared");
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
        if (typeExp.equals(new StringType()))
            if (typeVar.equals(new IntType()))
                return typeEnv;
            else
                throw new TypeCheckException("read file statement: the type of the variable is not integer");
        else
            throw new TypeCheckException("read file statement: the file name is not a string");
    }

    @Override
    public ReadFileStatement deepCopy() {
        return new ReadFileStatement(this.exp.deepCopy(), this.id);
    }
}
