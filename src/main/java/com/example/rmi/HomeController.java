package com.example.rmi;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class HomeController {

    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
    private TextField hostnameInput;

    @FXML
    private TextField portInput;

    @FXML
    private Button confirmButton;

    public static void main(String[] args) {
        System.out.println("Home Controller");
    }


    public void startGame(ActionEvent event) throws IOException {

        String hostname = hostnameInput.getText();

        Integer port = Integer.parseInt(portInput.getText());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        root = loader.load();

        ApplicationController appController = loader.getController();
        appController.initializeClientRMI(hostname, port);

        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }


}
