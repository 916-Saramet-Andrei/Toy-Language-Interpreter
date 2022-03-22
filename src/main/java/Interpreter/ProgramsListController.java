package Interpreter;

import Controller.Controller;
import Exceptions.TypeCheckException;
import Model.ADTs.*;
import Model.Expressions.*;
import Model.ProgramState.PrgState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.MyIRepository;
import Repository.MyRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProgramsListController implements Initializable {
    @FXML
    private ListView<Controller> programsListView;

    @FXML
    private Button runButton;

    @FXML
    private Button closeButton;

    private final ObservableList<Controller> programsList;

    public ProgramsListController() {
        this.programsList = FXCollections.observableArrayList();
    }

    void setUp() {
        String file1;
        file1 = "log1.txt";
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        try {
            ex1.typecheck(new MyDictionary<>());
            MyIStack<IStmt> stk1 = new MyStack<>();
            MyIDictionary<String, Value> symTable1 = new MyDictionary<>();
            MyIHeap<Value> heap1 = new MyHeap<>();
            MyIList<Value> out1 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> fileTable1 = new MyDictionary<>();
            MyILatchTable latchTable1 = new MyLatchTable();
            PrgState state1 = new PrgState(stk1, symTable1, heap1, out1, fileTable1, latchTable1, ex1);
            MyIRepository repository1 = new MyRepository(state1, file1);
            Controller controller1 = new Controller(repository1);
            this.programsList.add(controller1);
        } catch (TypeCheckException tce) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toy Language - TypeCheck Exception");
            alert.setHeaderText(null);
            alert.setContentText("Example 1 - " + tce.toString());
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(true);
            alert.showAndWait();
        }
        String file2;
        file2 = "log2.txt";
        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp("+", new ValueExp(new IntValue(2)), new ArithExp("*", new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(new AssignStmt("b", new ArithExp("+", new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        try {
            ex2.typecheck(new MyDictionary<>());
            MyIStack<IStmt> stk2 = new MyStack<>();
            MyIDictionary<String, Value> symTable2 = new MyDictionary<>();
            MyIHeap<Value> heap2 = new MyHeap<>();
            MyIList<Value> out2 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> fileTable2 = new MyDictionary<>();
            MyILatchTable latchTable2 = new MyLatchTable();
            PrgState state2 = new PrgState(stk2, symTable2, heap2, out2, fileTable2, latchTable2, ex2);
            MyIRepository repository2 = new MyRepository(state2, file2);
            Controller controller2 = new Controller(repository2);
            this.programsList.add(controller2);
        } catch (TypeCheckException tce) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toy Language - TypeCheck Exception");
            alert.setHeaderText(null);
            alert.setContentText("Example 2 - " + tce.toString());
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(true);
            alert.showAndWait();
        }
        String file3;
        file3 = "log3.txt";
        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        try {
            ex3.typecheck(new MyDictionary<>());
            MyIStack<IStmt> stk3 = new MyStack<>();
            MyIDictionary<String, Value> symTable3 = new MyDictionary<>();
            MyIHeap<Value> heap3 = new MyHeap<>();
            MyIList<Value> out3 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> fileTable3 = new MyDictionary<>();
            MyILatchTable latchTable3 = new MyLatchTable();
            PrgState state3 = new PrgState(stk3, symTable3, heap3, out3, fileTable3, latchTable3, ex3);
            MyIRepository repository3 = new MyRepository(state3, file3);
            Controller controller3 = new Controller(repository3);
            this.programsList.add(controller3);
        } catch (TypeCheckException tce) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toy Language - TypeCheck Exception");
            alert.setHeaderText(null);
            alert.setContentText("Example 3 - " + tce.toString());
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(true);
            alert.showAndWait();
        }
        String file4;
        file4 = "log4.txt";
        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))), new CompStmt(new OpenFileStmt(new VarExp("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new ReadFileStatement(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(new ReadFileStatement(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseFileStmt(new VarExp("varf"))))))))));
        try {
            ex4.typecheck(new MyDictionary<>());
            MyIStack<IStmt> stk4 = new MyStack<>();
            MyIDictionary<String, Value> symTable4 = new MyDictionary<>();
            MyIHeap<Value> heap4 = new MyHeap<>();
            MyIList<Value> out4 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> fileTable4 = new MyDictionary<>();
            MyILatchTable latchTable4 = new MyLatchTable();
            PrgState state4 = new PrgState(stk4, symTable4, heap4, out4, fileTable4, latchTable4, ex4);
            MyIRepository repository4 = new MyRepository(state4, file4);
            Controller controller4 = new Controller(repository4);
            this.programsList.add(controller4);
        } catch (TypeCheckException tce) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toy Language - TypeCheck Exception");
            alert.setHeaderText(null);
            alert.setContentText("Example 4 - " + tce.toString());
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(true);
            alert.showAndWait();
        }
        String file5;
        file5 = "log5.txt";
        IStmt ex5 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(2))), new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(3))), new CompStmt(new VarDeclStmt("val", new IntType()), new CompStmt(new VarDeclStmt("file", new StringType()), new CompStmt(new IfStmt(new RelExp(">", new VarExp("a"), new VarExp("b")), new AssignStmt("file", new ValueExp(new StringValue("file1.txt"))), new AssignStmt("file", new ValueExp(new StringValue("file2.txt")))), new CompStmt(new OpenFileStmt(new VarExp("file")), new CompStmt(new ReadFileStatement(new VarExp("file"), "val"), new CompStmt(new CloseFileStmt(new VarExp("file")), new PrintStmt(new VarExp("val"))))))))))));
        try {
            ex5.typecheck(new MyDictionary<>());
            MyIStack<IStmt> stk5 = new MyStack<>();
            MyIDictionary<String, Value> symTable5 = new MyDictionary<>();
            MyIHeap<Value> heap5 = new MyHeap<>();
            MyIList<Value> out5 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> fileTable5 = new MyDictionary<>();
            MyILatchTable latchTable5 = new MyLatchTable();
            PrgState state5 = new PrgState(stk5, symTable5, heap5, out5, fileTable5, latchTable5, ex5);
            MyIRepository repository5 = new MyRepository(state5, file5);
            Controller controller5 = new Controller(repository5);
            this.programsList.add(controller5);
        } catch (TypeCheckException tce) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toy Language - TypeCheck Exception");
            alert.setHeaderText(null);
            alert.setContentText("Example 5 - " + tce.toString());
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(true);
            alert.showAndWait();
        }
        String file6;
        file6 = "log6.txt";
        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new NewHeapStmt("a", new VarExp("v")), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));
        try {
            ex6.typecheck(new MyDictionary<>());
            MyIStack<IStmt> stk6 = new MyStack<>();
            MyIDictionary<String, Value> symTable6 = new MyDictionary<>();
            MyIHeap<Value> heap6 = new MyHeap<>();
            MyIList<Value> out6 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> fileTable6 = new MyDictionary<>();
            MyILatchTable latchTable6 = new MyLatchTable();
            PrgState state6 = new PrgState(stk6, symTable6, heap6, out6, fileTable6, latchTable6, ex6);
            MyIRepository repository6 = new MyRepository(state6, file6);
            Controller controller6 = new Controller(repository6);
            this.programsList.add(controller6);
        } catch (TypeCheckException tce) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toy Language - TypeCheck Exception");
            alert.setHeaderText(null);
            alert.setContentText("Example 6 - " + tce.toString());
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(true);
            alert.showAndWait();
        }
        String file7;
        file7 = "log7.txt";
        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new NewHeapStmt("a", new VarExp("v")), new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))), new PrintStmt(new ArithExp("+", new ReadHeapExp(new ReadHeapExp(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));
        try {
            ex7.typecheck(new MyDictionary<>());
            MyIStack<IStmt> stk7 = new MyStack<>();
            MyIDictionary<String, Value> symTable7 = new MyDictionary<>();
            MyIHeap<Value> heap7 = new MyHeap<>();
            MyIList<Value> out7 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> fileTable7 = new MyDictionary<>();
            MyILatchTable latchTable7 = new MyLatchTable();
            PrgState state7 = new PrgState(stk7, symTable7, heap7, out7, fileTable7, latchTable7, ex7);
            MyIRepository repository7 = new MyRepository(state7, file7);
            Controller controller7 = new Controller(repository7);
            this.programsList.add(controller7);
        } catch (TypeCheckException tce) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toy Language - TypeCheck Exception");
            alert.setHeaderText(null);
            alert.setContentText("Example 7 - " + tce.toString());
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(true);
            alert.showAndWait();
        }
        String file8;
        file8 = "log8.txt";
        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))), new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new ArithExp("+", new ReadHeapExp(new VarExp("v")), new ValueExp(new IntValue(5))))))));
        try {
            ex8.typecheck(new MyDictionary<>());
            MyIStack<IStmt> stk8 = new MyStack<>();
            MyIDictionary<String, Value> symTable8 = new MyDictionary<>();
            MyIHeap<Value> heap8 = new MyHeap<>();
            MyIList<Value> out8 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> fileTable8 = new MyDictionary<>();
            MyILatchTable latchTable8 = new MyLatchTable();
            PrgState state8 = new PrgState(stk8, symTable8, heap8, out8, fileTable8, latchTable8, ex8);
            MyIRepository repository8 = new MyRepository(state8, file8);
            Controller controller8 = new Controller(repository8);
            this.programsList.add(controller8);
        } catch (TypeCheckException tce) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toy Language - TypeCheck Exception");
            alert.setHeaderText(null);
            alert.setContentText("Example 8 - " + tce.toString());
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(true);
            alert.showAndWait();
        }
        String file9;
        file9 = "log9.txt";
        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new NewHeapStmt("a", new VarExp("v")), new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))))))));
        try {
            ex9.typecheck(new MyDictionary<>());
            MyIStack<IStmt> stk9 = new MyStack<>();
            MyIDictionary<String, Value> symTable9 = new MyDictionary<>();
            MyIHeap<Value> heap9 = new MyHeap<>();
            MyIList<Value> out9 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> fileTable9 = new MyDictionary<>();
            MyILatchTable latchTable9 = new MyLatchTable();
            PrgState state9 = new PrgState(stk9, symTable9, heap9, out9, fileTable9, latchTable9, ex9);
            MyIRepository repository9 = new MyRepository(state9, file9);
            Controller controller9 = new Controller(repository9);
            this.programsList.add(controller9);
        } catch (TypeCheckException tce) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toy Language - TypeCheck Exception");
            alert.setHeaderText(null);
            alert.setContentText("Example 9 - " + tce.toString());
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(true);
            alert.showAndWait();
        }
        String file10;
        file10 = "log10.txt";
        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))), new CompStmt(new WhileStmt(new RelExp(">", new VarExp("v"), new ValueExp(new IntValue(0))), new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp("-", new VarExp("v"), new ValueExp(new IntValue(1)))))), new PrintStmt(new VarExp("v")))));
        try {
            ex10.typecheck(new MyDictionary<>());
            MyIStack<IStmt> stk10 = new MyStack<>();
            MyIDictionary<String, Value> symTable10 = new MyDictionary<>();
            MyIHeap<Value> heap10 = new MyHeap<>();
            MyIList<Value> out10 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> fileTable10 = new MyDictionary<>();
            MyILatchTable latchTable10 = new MyLatchTable();
            PrgState state10 = new PrgState(stk10, symTable10, heap10, out10, fileTable10, latchTable10, ex10);
            MyIRepository repository10 = new MyRepository(state10, file10);
            Controller controller10 = new Controller(repository10);
            this.programsList.add(controller10);
        } catch (TypeCheckException tce) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toy Language - TypeCheck Exception");
            alert.setHeaderText(null);
            alert.setContentText("Example 10 - " + tce.toString());
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(true);
            alert.showAndWait();
        }
        String file11;
        file11 = "log11.txt";
        IStmt ex11 = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), new CompStmt(new NewHeapStmt("a", new ValueExp(new IntValue(55))), new CompStmt(new VarDeclStmt("b", new RefType(new RefType(new IntType()))), new CompStmt(new VarDeclStmt("c", new RefType(new RefType(new RefType(new IntType())))), new CompStmt(new NewHeapStmt("b", new VarExp("a")), new CompStmt(new NewHeapStmt("c", new VarExp("b")), new CompStmt(new NewHeapStmt("a", new ValueExp(new IntValue(22))), new CompStmt(new NewHeapStmt("b", new VarExp("a")), new PrintStmt(new ReadHeapExp(new ReadHeapExp(new ReadHeapExp(new VarExp("c")))))))))))));
        try {
            ex11.typecheck(new MyDictionary<>());
            MyIStack<IStmt> stk11 = new MyStack<>();
            MyIDictionary<String, Value> symTable11 = new MyDictionary<>();
            MyIHeap<Value> heap11 = new MyHeap<>();
            MyIList<Value> out11 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> fileTable11 = new MyDictionary<>();
            MyILatchTable latchTable11 = new MyLatchTable();
            PrgState state11 = new PrgState(stk11, symTable11, heap11, out11, fileTable11, latchTable11, ex11);
            MyIRepository repository11 = new MyRepository(state11, file11);
            Controller controller11 = new Controller(repository11);
            this.programsList.add(controller11);
        } catch (TypeCheckException tce) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toy Language - TypeCheck Exception");
            alert.setHeaderText(null);
            alert.setContentText("Example 11 - " + tce.toString());
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(true);
            alert.showAndWait();
        }
        String file12;
        file12 = "log12.txt";
        IStmt ex12 = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), new CompStmt(new NewHeapStmt("a", new ValueExp(new IntValue(55))), new CompStmt(new VarDeclStmt("b", new RefType(new RefType(new IntType()))), new CompStmt(new VarDeclStmt("c", new RefType(new RefType(new RefType(new IntType())))), new CompStmt(new NewHeapStmt("b", new VarExp("a")), new CompStmt(new NewHeapStmt("c", new VarExp("b")), new CompStmt(new NewHeapStmt("a", new ValueExp(new IntValue(22))), new CompStmt(new NewHeapStmt("b", new VarExp("a")), new CompStmt(new NewHeapStmt("c", new VarExp("b")), new PrintStmt(new ReadHeapExp(new ReadHeapExp(new ReadHeapExp(new VarExp("c"))))))))))))));
        try {
            ex12.typecheck(new MyDictionary<>());
            MyIStack<IStmt> stk12 = new MyStack<>();
            MyIDictionary<String, Value> symTable12 = new MyDictionary<>();
            MyIHeap<Value> heap12 = new MyHeap<>();
            MyIList<Value> out12 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> fileTable12 = new MyDictionary<>();
            MyILatchTable latchTable12 = new MyLatchTable();
            PrgState state12 = new PrgState(stk12, symTable12, heap12, out12, fileTable12, latchTable12, ex12);
            MyIRepository repository12 = new MyRepository(state12, file12);
            Controller controller12 = new Controller(repository12);
            this.programsList.add(controller12);
        } catch (TypeCheckException tce) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toy Language - TypeCheck Exception");
            alert.setHeaderText(null);
            alert.setContentText("Example 12 - " + tce.toString());
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(true);
            alert.showAndWait();
        }
        String file13;
        file13 = "log13.txt";
        IStmt ex13 = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), new CompStmt(new NewHeapStmt("a", new ValueExp(new IntValue(31))), new CompStmt(new VarDeclStmt("b", new RefType(new RefType(new IntType()))), new CompStmt(new NewHeapStmt("b", new VarExp("a")), new CompStmt(new NewHeapStmt("a", new ValueExp(new IntValue(13))), new NewHeapStmt("b", new VarExp("a")))))));
        try {
            ex13.typecheck(new MyDictionary<>());
            MyIStack<IStmt> stk13 = new MyStack<>();
            MyIDictionary<String, Value> symTable13 = new MyDictionary<>();
            MyIHeap<Value> heap13 = new MyHeap<>();
            MyIList<Value> out13 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> fileTable13 = new MyDictionary<>();
            MyILatchTable latchTable13 = new MyLatchTable();
            PrgState state13 = new PrgState(stk13, symTable13, heap13, out13, fileTable13, latchTable13, ex13);
            MyIRepository repository13 = new MyRepository(state13, file13);
            Controller controller13 = new Controller(repository13);
            this.programsList.add(controller13);
        } catch (TypeCheckException tce) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toy Language - TypeCheck Exception");
            alert.setHeaderText(null);
            alert.setContentText("Example 13 - " + tce.toString());
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(true);
            alert.showAndWait();
        }
        String file14;
        file14 = "log14.txt";
        IStmt ex14 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))), new CompStmt(new NewHeapStmt("a", new ValueExp(new IntValue(22))), new CompStmt(new ForkStmt(new CompStmt(new WriteHeapStmt("a", new ValueExp(new IntValue(33))), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a"))))))), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));
        try {
            ex14.typecheck(new MyDictionary<>());
            MyIStack<IStmt> stk14 = new MyStack<>();
            MyIDictionary<String, Value> symTable14 = new MyDictionary<>();
            MyIHeap<Value> heap14 = new MyHeap<>();
            MyIList<Value> out14 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> fileTable14 = new MyDictionary<>();
            MyILatchTable latchTable14 = new MyLatchTable();
            PrgState state14 = new PrgState(stk14, symTable14, heap14, out14, fileTable14, latchTable14, ex14);
            MyIRepository repository14 = new MyRepository(state14, file14);
            Controller controller14 = new Controller(repository14);
            this.programsList.add(controller14);
        } catch (TypeCheckException tce) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toy Language - TypeCheck Exception");
            alert.setHeaderText(null);
            alert.setContentText("Example 14 - " + tce.toString());
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(true);
            alert.showAndWait();
        }
        String file15;
        file15 = "log15.txt";
        IStmt ex15 = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), new CompStmt(new NewHeapStmt("a", new ValueExp(new IntValue(33))), new CompStmt(new ForkStmt(new CompStmt(new VarDeclStmt("b", new RefType(new IntType())), new CompStmt(new NewHeapStmt("a", new ValueExp(new IntValue(55))), new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("a"))), new NewHeapStmt("b", new ValueExp(new IntValue(33))))))), new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("a"))), new PrintStmt(new ReadHeapExp(new VarExp("a")))))));
        try {
            ex15.typecheck(new MyDictionary<>());
            MyIStack<IStmt> stk15 = new MyStack<>();
            MyIDictionary<String, Value> symTable15 = new MyDictionary<>();
            MyIHeap<Value> heap15 = new MyHeap<>();
            MyIList<Value> out15 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> fileTable15 = new MyDictionary<>();
            MyILatchTable latchTable15 = new MyLatchTable();
            PrgState state15 = new PrgState(stk15, symTable15, heap15, out15, fileTable15, latchTable15, ex15);
            MyIRepository repository15 = new MyRepository(state15, file15);
            Controller controller15 = new Controller(repository15);
            this.programsList.add(controller15);
        } catch (TypeCheckException tce) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toy Language - TypeCheck Exception");
            alert.setHeaderText(null);
            alert.setContentText("Example 15 - " + tce.toString());
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(true);
            alert.showAndWait();
        }
        String file16;
        file16 = "log16.txt";
        IStmt ex16 = new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(14))), new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), new CompStmt(new NewHeapStmt("a", new ValueExp(new IntValue(321))), new CompStmt(new ForkStmt(new CompStmt(new WriteHeapStmt("a", new ValueExp(new IntValue(789))), new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(15))), new PrintStmt(new VarExp("b"))))), new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("a"))), new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(13))), new PrintStmt(new VarExp("b")))))))));
        try {
            ex16.typecheck(new MyDictionary<>());
            MyIStack<IStmt> stk16 = new MyStack<>();
            MyIDictionary<String, Value> symTable16 = new MyDictionary<>();
            MyIHeap<Value> heap16 = new MyHeap<>();
            MyIList<Value> out16 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> fileTable16 = new MyDictionary<>();
            MyILatchTable latchTable16 = new MyLatchTable();
            PrgState state16 = new PrgState(stk16, symTable16, heap16, out16, fileTable16, latchTable16, ex16);
            MyIRepository repository16 = new MyRepository(state16, file16);
            Controller controller16 = new Controller(repository16);
            this.programsList.add(controller16);
        } catch (TypeCheckException tce) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toy Language - TypeCheck Exception");
            alert.setHeaderText(null);
            alert.setContentText("Example 16 - " + tce.toString());
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(true);
            alert.showAndWait();
        }
        String file17;
        file17 = "log17.txt";
        IStmt ex17 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new ForkStmt(new CompStmt(new VarDeclStmt("b", new BoolType()), new VarDeclStmt("c", new IntType()))), new CompStmt(new VarDeclStmt("b", new StringType()), new VarDeclStmt("c", new BoolType()))));
        try {
            ex17.typecheck(new MyDictionary<>());
            MyIStack<IStmt> stk17 = new MyStack<>();
            MyIDictionary<String, Value> symTable17 = new MyDictionary<>();
            MyIHeap<Value> heap17 = new MyHeap<>();
            MyIList<Value> out17 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> fileTable17 = new MyDictionary<>();
            MyILatchTable latchTable17 = new MyLatchTable();
            PrgState state17 = new PrgState(stk17, symTable17, heap17, out17, fileTable17, latchTable17, ex17);
            MyIRepository repository17 = new MyRepository(state17, file17);
            Controller controller17 = new Controller(repository17);
            this.programsList.add(controller17);
        } catch (TypeCheckException tce) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toy Language - TypeCheck Exception");
            alert.setHeaderText(null);
            alert.setContentText("Example 17 - " + tce.toString());
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(true);
            alert.showAndWait();
        }


        String file18;
        file18 = "log18.txt";
        IStmt ex18 = new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())), new CompStmt(new VarDeclStmt("v2", new RefType(new IntType())), new CompStmt(new VarDeclStmt("v3", new RefType(new IntType())), new CompStmt(new VarDeclStmt("cnt", new IntType()), new CompStmt(new NewHeapStmt("v1", new ValueExp(new IntValue(2))), new CompStmt(new NewHeapStmt("v2", new ValueExp(new IntValue(3))), new CompStmt(new NewHeapStmt("v3", new ValueExp(new IntValue(4))), new CompStmt(new NewLatchStatement("cnt", new ReadHeapExp(new VarExp("v2"))), new CompStmt(new ForkStmt(new CompStmt(new WriteHeapStmt("v1", new ArithExp("*", new ReadHeapExp(new VarExp("v1")), new ValueExp(new IntValue(10)))), new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v1"))), new CompStmt(new CountDownStatement("cnt"), new ForkStmt(new CompStmt(new WriteHeapStmt("v2", new ArithExp("*", new ReadHeapExp(new VarExp("v2")), new ValueExp(new IntValue(10)))), new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v2"))), new CompStmt(new CountDownStatement("cnt"), new ForkStmt(new CompStmt(new WriteHeapStmt("v3", new ArithExp("*", new ReadHeapExp(new VarExp("v3")), new ValueExp(new IntValue(10)))), new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v3"))), new CountDownStatement("cnt")))))))))))), new CompStmt(new AwaitStatement("cnt"), new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))), new CompStmt(new CountDownStatement("cnt"), new PrintStmt(new ValueExp(new IntValue(100)))))))))))))));
        try {
            ex18.typecheck(new MyDictionary<>());
            MyIStack<IStmt> stk18 = new MyStack<>();
            MyIDictionary<String, Value> symTable18 = new MyDictionary<>();
            MyIHeap<Value> heap18 = new MyHeap<>();
            MyIList<Value> out18 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> fileTable18 = new MyDictionary<>();
            MyILatchTable latchTable18 = new MyLatchTable();
            PrgState state18 = new PrgState(stk18, symTable18, heap18, out18, fileTable18, latchTable18, ex18);
            MyIRepository repository18 = new MyRepository(state18, file18);
            Controller controller18 = new Controller(repository18);
            this.programsList.add(controller18);
        } catch (TypeCheckException tce) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toy Language - TypeCheck Exception");
            alert.setHeaderText(null);
            alert.setContentText("Example 18 - " + tce.toString());
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(true);
            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setUp();
        this.programsListView.setItems(this.programsList);
        this.programsListView.getSelectionModel().selectFirst();
    }

    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void handleRunButtonAction(ActionEvent event) throws IOException {
        Parent programRoot;
        Stage programStage = new Stage();
        Callback<Class<?>, Object> controllerFactory = type -> {
            if (type == ProgramExecutionController.class) {
                return new ProgramExecutionController(this.programsListView.getSelectionModel().getSelectedItem());
            } else {
                try {
                    return type.newInstance();
                } catch (Exception exception) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Toy Language Interpreter Exception");
                    alert.setHeaderText(null);
                    alert.setContentText("Could not create controller for " + type.getName());
                    Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                    confirm.setDefaultButton(true);
                    alert.showAndWait();
                    return null;
                }
            }
        };

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProgramExecutionStage.fxml"));
            fxmlLoader.setControllerFactory(controllerFactory);
            try {
                programRoot = fxmlLoader.load();
                Scene programScene = new Scene(programRoot);
                programStage.setTitle("Program Running");
                programStage.setScene(programScene);
                programStage.show();
            } catch (LoadException le) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Toy Language - Program already run");
                alert.setHeaderText(null);
                alert.setContentText("The chosen program has already been completely executed");
                Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                confirm.setDefaultButton(true);
                alert.showAndWait();
            }
        } catch (IOException ioe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("JavaFX IOException");
            alert.setHeaderText(null);
            alert.setContentText(ioe.toString());
            Button confirm = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            confirm.setDefaultButton(true);
            alert.showAndWait();
        }
    }


}
