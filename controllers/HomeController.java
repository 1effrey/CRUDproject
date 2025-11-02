package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeController {

    @FXML
    private Label welcomeLabel;

    private String loggedUser;

    // Called from LoginController after login succeeds
    public void setLoggedUser(String username) {
        loggedUser = username;
        welcomeLabel.setText("Hello, " + capitalize(username) + "!");
    }

    private String capitalize(String name) {
        if (name == null || name.isEmpty()) return "";
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login - Business Manager");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
