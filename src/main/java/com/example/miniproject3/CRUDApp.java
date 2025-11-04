package com.example.miniproject3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CRUDApp extends Application {
    //This class starts your JavaFX app, loads the home-view.fxml layout, creates a 650×500 window titled
    // “Company Management System,” and shows it to the user.
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/com/example/miniproject3/views/home-view.fxml"));
        //This loads your home-view.fxml file — the layout for your main window.
        Scene scene = new Scene(fxmlLoader.load(), 650, 500);
        stage.setTitle("Company Management System");
        stage.setScene(scene);
        stage.setResizable(false);//Window resizing: Prevents the user from resizing the window by dragging its edges.
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

//@todo
/* Step 1: change the file name into the thing your targeting.
   Step 2: make sure the linkage are correct.
   Step 3: make a login page.
   okay i will rewrite the whole code tomo like dr maroun's and adjust using chat
   yes exactly
   thank you charbel im gonna sleep im too tired for this tomo in my break i will deal with it
   deal bey
   byeee
   il help
   may u send the db lecture ??
   u want me to send u today's lecture?
   yes can't wait for maroun
   yea sure yala give me a minute
   tomo
   not today?
   go rest im not working rn
   i'll send it now anyways it needs 2 mins ahsan ma ensa
   k gn
   gn
 */