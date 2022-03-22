package Repository;

import Exceptions.FileException;
import Model.ProgramState.PrgState;

import java.util.List;

public interface MyIRepository {
    List<PrgState> getPrgList();

    void setPrgList(List<PrgState> prgList);

    void logPrgStateExec(PrgState prgState) throws FileException;
}
