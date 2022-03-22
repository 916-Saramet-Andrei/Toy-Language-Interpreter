module main.homework7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens Interpreter to javafx.fxml;
    exports Interpreter;
}