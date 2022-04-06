package com.example.rmi;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 700);
        stage.setTitle("Tsoro Yematatu");
        stage.setScene(scene);
        stage.setResizable(false);
        System.out.println("starting game!");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}