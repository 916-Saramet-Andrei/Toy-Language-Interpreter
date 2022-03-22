package Controller;

import Exceptions.*;
import Model.ProgramState.PrgState;
import Repository.MyIRepository;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class Controller {
    MyIRepository repository;
    ExecutorService executor;
    GarbageCollector garbageCollector;

    public Controller(MyIRepository repository) {
        this.repository = repository;
        this.garbageCollector = new GarbageCollector();
    }

    public void oneStepForAll(List<PrgState> prgList) throws DivisionByZeroException, EmptyStackException, IdentifierException, KeyException, TypeException, EmptyRepositoryException, FileException, InvalidHeapAddressException, ThreadingExecutionException {
        prgList.forEach(program -> {
            this.repository.logPrgStateExec(program);
        });


        List<Callable<PrgState>> callList = prgList.stream().map((PrgState p) -> (Callable<PrgState>) (() -> {
            return p.oneStep();
        })).collect(Collectors.toList());

        List<PrgState> newPrgList = null;
        try {
            newPrgList = this.executor.invokeAll(callList).stream().map(future -> {
                try {
                    return future.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new ThreadingExecutionException(e);
                }
            }).filter(p -> p != null).collect(Collectors.toList());
        } catch (InterruptedException ie) {
            throw new ThreadingExecutionException(ie);
        }
        prgList.addAll(newPrgList);
        prgList.forEach(program -> this.repository.logPrgStateExec(program));
        this.repository.setPrgList(prgList);
    }

    public void oneStepForAllPrograms() {
        this.executor = Executors.newFixedThreadPool(4);

        List<PrgState> prgList = this.removeCompletedPrg(this.repository.getPrgList());

        this.oneStepForAll(prgList);
        prgList.stream().forEach(program -> program.getHeap().setContent(this.garbageCollector.safeGarbageCollector(this.repository.getPrgList(), program.getHeap())));

        this.executor.shutdown();
    }

    public boolean repositoryIsCompleted() {
        return this.repository.getPrgList().stream().allMatch(program -> !program.isNotCompleted());
    }

    public void cleanRepository() {
        this.repository.setPrgList(this.removeCompletedPrg(this.repository.getPrgList()));
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream().filter(program -> program.isNotCompleted()).collect(Collectors.toList());
    }

    public List<PrgState> getPrgList() {
        return this.repository.getPrgList();
    }

    @Override
    public String toString() {
        return this.repository.toString();
    }
}
