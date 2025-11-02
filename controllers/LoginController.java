package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;

    private Map<String, String> users = new HashMap<>();

    @FXML
    public void initialize() {
        // Team credentials (hardcoded for now)
        users.put("myriam", "1111");
        users.put("jeffrey", "2222");
        users.put("jad", "3333");
        users.put("mark", "4444");
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim().toLowerCase();
        String password = passwordField.getText().trim();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            openHomeView(username);
        } else {
            messageLabel.setText("Invalid username or password.");
        }
    }

    private void openHomeView(String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/HomeView.fxml"));
            Parent root = loader.load();

            // Optionally pass username to HomeController (if you want a welcome message)
            controllers.HomeController homeController = loader.getController();
            homeController.setLoggedUser(username);

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Home - Business Manager");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Error loading Home View.");
        }
    }
}
