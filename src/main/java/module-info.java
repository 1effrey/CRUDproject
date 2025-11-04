module com.example.miniproject3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.miniproject3 to javafx.fxml;
    exports com.example.miniproject3;
    exports com.example.miniproject3.controllers;
    exports com.example.miniproject3.models;
    opens com.example.miniproject3.controllers to javafx.fxml;
}