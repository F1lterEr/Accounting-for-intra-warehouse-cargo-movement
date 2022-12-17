module com.example.warehousecheck {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.warehousecheck to javafx.fxml;
    exports com.example.warehousecheck;
}