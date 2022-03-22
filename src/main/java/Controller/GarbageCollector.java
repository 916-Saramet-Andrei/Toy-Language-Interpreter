package Controller;

import Model.ADTs.MyIHeap;
import Model.ProgramState.PrgState;
import Model.Values.RefValue;
import Model.Values.Value;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GarbageCollector {
    public List<Integer> getAddrFromSymTables(List<Collection<Value>> symTablesValues) {
        return symTablesValues.stream()
                .flatMap(symTable -> symTable.stream())
                .filter(value -> value instanceof RefValue)
                .map(value -> ((RefValue) value).getAddress())
                .collect(Collectors.toList());
    }


    public Map<Integer, Value> safeGarbageCollector(List<PrgState> prgStates, MyIHeap<Value> heap) {
        List<Collection<Value>> symTablesValues = prgStates.stream()
                .map(prgState -> prgState.getSymTable().getContent().values())
                .collect(Collectors.toList());
        List<Integer> addressesList = this.getAddrFromSymTables(symTablesValues);
        List<Integer> partialHeapAddressesList = null;

        while (true) {
            partialHeapAddressesList = heap.getContent().entrySet().stream()
                    .filter(entry -> entry.getValue() instanceof RefValue)
                    .filter(entry -> addressesList.contains(entry.getKey()))
                    .map(entry -> ((RefValue) entry.getValue()).getAddress())
                    .filter(address -> !addressesList.contains(address))
                    .collect(Collectors.toList());
            if (partialHeapAddressesList.isEmpty())
                break;
            addressesList.addAll(partialHeapAddressesList);
        }
        return heap.getContent().entrySet().stream()
                .filter(address -> addressesList.contains(address.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
