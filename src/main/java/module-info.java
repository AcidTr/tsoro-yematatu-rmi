module com.example.rmi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;


    opens com.example.rmi to javafx.fxml;
    exports com.example.rmi;
}