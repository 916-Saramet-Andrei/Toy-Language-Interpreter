package Repository;

import Exceptions.FileException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIList;
import Model.ADTs.MyIStack;
import Model.ADTs.MyList;
import Model.ProgramState.PrgState;
import Model.Statements.IStmt;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.*;
import java.util.List;

public class MyRepository implements MyIRepository {
    MyIList<PrgState> states;
    String logFilePath;
    IStmt originalProgram;

    public MyRepository(PrgState state, String logFilePath) {
        this.states = new MyList<>();
        this.originalProgram = state.getOriginalProgram().deepCopy();
        this.states.append(state);
        this.logFilePath = logFilePath;
    }

    @Override
    public List<PrgState> getPrgList() {
        return this.states.getContent();
    }

    @Override
    public void setPrgList(List<PrgState> prgList) {
        this.states.setContent(prgList);
    }

    @Override
    public void logPrgStateExec(PrgState prgState) throws FileException {
        try (PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)))) {
            int ID = prgState.getID();
            MyIStack<IStmt> stk = prgState.getStk();
            MyIDictionary<String, Value> symTable = prgState.getSymTable();
            MyIList<Value> out = prgState.getOut();
            MyIDictionary<StringValue, BufferedReader> fileTable = prgState.getFileTable();
            logFile.println("ID: " + ID);
            logFile.println("ExeStack:");
            logFile.print(stk.toString());
            logFile.println("SymTable:");
            logFile.print(symTable.toString());
            logFile.println("Out:");
            logFile.print(out.toString());
            logFile.println("FileTable:");
            logFile.print(fileTable.keysToString());
        } catch (IOException ioe) {
            throw new FileException("An error occurred when appending to the file ", ioe);
        }
    }

    @Override
    public String toString() {
        return this.originalProgram.toString();
    }
}
