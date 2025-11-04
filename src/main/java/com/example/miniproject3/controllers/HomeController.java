package com.example.miniproject3.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {
 //It controls the behavior of the Home view (FXML file) — handling user actions like button clicks.
    @FXML
    private Button personsMngBtn; //This button (personsMngBtn) is likely the one the user clicks to
    // open the Person Management window.

    @FXML //links this method to the button’s onAction attribute in the FXML file.
    void openPersonsMng(ActionEvent event) throws Exception{ //This is a method that gets called when
        // the user clicks the personsMngBtn button.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().
                getResource("/com/example/miniproject3/views/persons-view.fxml")); //Creates an FXMLLoader that
         // loads another FXML file, that file describes the layout for the "person
        //management" window
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage() ;
        stage.setTitle("Person Management");
        stage.setScene(scene);
        stage.show();
    }

}