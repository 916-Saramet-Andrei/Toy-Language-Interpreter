package Interpreter;

import Controller.Controller;
import Model.ProgramState.PrgState;
import Model.Statements.IStmt;
import Model.Values.Value;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ProgramExecutionController implements Initializable {
    private Controller controller;

    @FXML
    private Label noPrograms;

    @FXML
    private TableView<Map.Entry<Integer, Value>> heapTableView;

    @FXML
    private TableColumn<Map.Entry<Integer, Value>, String> heapTableAddress;

    @FXML
    private TableColumn<Map.Entry<Integer, Value>, String> heapTableValue;

    @FXML
    private ListView<Value> outListView;

    @FXML
    private ListView<String> fileTableView;

    @FXML
    private ListView<Integer> programIDListView;

    @FXML
    private TableView<Map.Entry<String, Value>> symbolsTableView;

    @FXML
    private TableColumn<Map.Entry<String, Value>, String> symbolsTableIdentifier;

    @FXML
    private TableColumn<Map.Entry<String, Value>, String> symbolsTableValue;

    @FXML
    private ListView<IStmt> executionStackView;

    @FXML
    private TableView<Map.Entry<Integer, Integer>> latchTableView;

    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, String> latchTableLocation;

    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, String> latchTableValue;

    @FXML
    private Button runOneStepButton;

    public ProgramExecutionController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.startUp();
    }

    @FXML
    public void handleListViewOnMouseClicked(MouseEvent event) {
        this.setSymTable();
        this.setExecutionStack();
    }

    @FXML
    public void handleRunOneStepButtonAction(ActionEvent event) {
        this.controller.oneStepForAllPrograms();
        this.update();
        if (this.controller.repositoryIsCompleted()) {
            this.runOneStepButton.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Toy Language - Current program finished");
            alert.setHeaderText(null);
            alert.setContentText("Program successfully finished!");
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(true);
            alert.showAndWait();
        }
    }

    @FXML
    public void handleCloseExecutionButtonAction(ActionEvent event) {
        this.controller.cleanRepository();
        ((Stage) this.fileTableView.getScene().getWindow()).close();
    }

    public void startUp() {
        this.setNoPrograms();
        this.setPrgStateList();
        this.programIDListView.getSelectionModel().selectFirst();
        this.setSymTable();
        this.setExecutionStack();
        this.setOutList();
        this.setHeapTable();
        this.setFileTable();
        this.setLatchTable();
    }

    public void update() {
        this.setNoPrograms();
        this.setPrgStateList();
        if (this.programIDListView.getSelectionModel().getSelectedItem() == null) {
            this.programIDListView.getSelectionModel().selectFirst();
        }
        this.setSymTable();
        this.setExecutionStack();
        this.setOutList();
        this.setHeapTable();
        this.setFileTable();
        this.setLatchTable();
    }

    private void setNoPrograms() {
        this.noPrograms.setText(Integer.toString(this.controller.getPrgList().size()));
    }

    private void setPrgStateList() {
        ObservableList<Integer> programIDList = FXCollections.observableArrayList();
        for (PrgState prgState : this.controller.getPrgList()) {
            programIDList.add(prgState.getID());
        }
        this.programIDListView.setItems(programIDList);
    }

    private void setSymTable() {
        ObservableList<Map.Entry<String, Value>> symbolsTable = FXCollections.observableArrayList();
        this.symbolsTableIdentifier.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getKey()));
        this.symbolsTableValue.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getValue().toString()));
        PrgState programState = null;
        for (PrgState prgState : this.controller.getPrgList()) {
            if (prgState.getID() == programIDListView.getSelectionModel().getSelectedItem()) {
                programState = prgState;
                break;
            }
        }
        if (programState != null) {
            symbolsTable.addAll(programState.getSymTable().getContent().entrySet());
            this.symbolsTableView.setItems(symbolsTable);
            this.symbolsTableView.refresh();
        }
    }

    private void setExecutionStack() {
        ObservableList<IStmt> executionStack = FXCollections.observableArrayList();
        PrgState programState = null;
        for (PrgState prgState : this.controller.getPrgList()) {
            if (prgState.getID() == programIDListView.getSelectionModel().getSelectedItem()) {
                programState = prgState;
                break;
            }
        }
        if (programState != null) {
            ListIterator<IStmt> stackIterator = programState.getStk().getContent().listIterator(programState.getStk().getContent().size());
            while (stackIterator.hasPrevious()) {
                executionStack.add(stackIterator.previous());
            }
            this.executionStackView.setItems(executionStack);
            this.executionStackView.refresh();
        }
    }

    private void setOutList() {
        ObservableList<Value> outList = FXCollections.observableArrayList();
        outList.addAll(this.controller.getPrgList().get(0).getOut().getContent());
        this.outListView.setItems(outList);
    }

    private void setHeapTable() {
        ObservableList<Map.Entry<Integer, Value>> heapTable = FXCollections.observableArrayList();
        this.heapTableAddress.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getKey().toString()));
        this.heapTableValue.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getValue().toString()));
        heapTable.addAll(this.controller.getPrgList().get(0).getHeap().getContent().entrySet());
        this.heapTableView.setItems(heapTable);
    }

    private void setLatchTable() {
        ObservableList<Map.Entry<Integer, Integer>> latchTable = FXCollections.observableArrayList();
        this.latchTableLocation.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getKey().toString()));
        this.latchTableValue.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getValue().toString()));
        latchTable.addAll(this.controller.getPrgList().get(0).getLatchTable().getContent().entrySet());
        this.latchTableView.setItems(latchTable);
        this.latchTableView.refresh();
    }

    private void setFileTable() {
        ObservableList<String> fileTable = FXCollections.observableArrayList();
        fileTable.addAll(this.controller.getPrgList().get(0).getFileTable().getContent().keySet().stream().map(fileName -> fileName.toString()).collect(Collectors.toList()));
        this.fileTableView.setItems(fileTable);
    }
}
