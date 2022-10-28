module com.paintapp.paintapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.paintapp.paintapp to javafx.fxml;
    exports com.paintapp.paintapp;
}