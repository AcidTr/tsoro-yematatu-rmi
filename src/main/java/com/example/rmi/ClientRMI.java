package com.example.rmi;

import javafx.application.Platform;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;


public class ClientRMI extends UnicastRemoteObject implements IClient {

    IServer serverRMI;
    ApplicationController applicationController;

    ClientRMI(String hostname, int port, ApplicationController appController) throws RemoteException {
        super();
        try {
            this.applicationController = appController;

            IServer Server = (IServer) Naming.lookup("rmi://" + hostname + ":" + port + "/Server");

            this.serverRMI = Server;

            System.out.println("Object Located!");

            Server.receiveInitialData(this);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.err.println("Error");
        }
    }

    public void sendTextMessage(String message) {
        try {
            this.serverRMI.receiveTextMessageFromClient(message, this);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPlaceMessage(String circleId) {
        try {
            this.serverRMI.receivePlaceMessageFromClient(circleId, this);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMovementMessage(String previousCircleId, String nextCircleId) {
        try {
            this.serverRMI.receiveMovementMessageFromClient(previousCircleId, nextCircleId, this);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendGiveUpMessage() {
        try {
            this.serverRMI.receiveGiveUpMessageFromClient(this);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendDrawMessage(String type) {
        try {
            this.serverRMI.receiveDrawMessageFromClient(type, this);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendWinMessage() {
        try {
            this.serverRMI.receiveWinMessageFromClient(this);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receiveConfig(Boolean isFirst) throws RemoteException {
        System.out.println("Config type");
        System.out.println(isFirst);

        applicationController.isTurn = isFirst;
        applicationController.setTurnLabelText(isFirst);
    }

    @Override
    public void receivePlacePiece(String selectedCircle){
        System.out.println("Place type");

        CirclePiece currentCircle = applicationController.circlePiecesMap.get(selectedCircle);
        applicationController.setCircleValue(false, currentCircle);

    }

    @Override
    public void receiveTextMessage(String message) throws RemoteException {
        System.out.println("Text type");
        System.out.println(message);

        applicationController.addLabel(message, applicationController.vBoxMessages);
    }

    @Override
    public void receiveMovementMessage(String previousCircleId, String nextCircleId) {
        System.out.println("Movement type");

        CirclePiece previousCircle = applicationController.circlePiecesMap.get(previousCircleId);
        CirclePiece nextCircle = applicationController.circlePiecesMap.get(nextCircleId);

        previousCircle.clearCell();

        nextCircle.setUserOwner(false);

        applicationController.isTurn = true;

        applicationController.setTurnLabelText(true);

    }

    @Override
    public void receiveGiveUpMessage() throws RemoteException {
        System.out.println("GiveUp type");
        System.out.println("Opponent gave up. You're the winner!");

        Platform.runLater(() -> {
            applicationController.turnLabel.setText("Opponent gave up!");

            applicationController.askDrawButton.setDisable(true);
            applicationController.askGiveUpButton.setDisable(true);
        });
        applicationController.isTurn = false;
    }

    @Override
    public void receiveDrawMessage(String type) throws RemoteException {
        System.out.println("Draw type");

        if(type.equals("ask")) {
            System.out.println("user is asking to draw game");
            Platform.runLater(() -> {
                applicationController.drawLabel.setText("user is asking to draw");
                applicationController.drawLabel.setOpacity(1);
                applicationController.drawButton.setOpacity(1);
                applicationController.cancelDrawButton.setOpacity(1);

                applicationController.drawButton.setDisable(false);
                applicationController.cancelDrawButton.setDisable(false);
            });
        }else {
            System.out.println("draw game");
            applicationController.isTurn = false;

            Platform.runLater(() -> {
                applicationController.turnLabel.setText("draw game!");

                applicationController.askDrawButton.setDisable(true);
                applicationController.askGiveUpButton.setDisable(true);
            });
        }


    }

    @Override
    public void receiveWinMessage() throws RemoteException {
        System.out.println("Opponent is the winner!");

        applicationController.isTurn = false;

        Platform.runLater(() -> {
            applicationController.turnLabel.setText("You lose!");

            applicationController.askDrawButton.setDisable(true);
            applicationController.askGiveUpButton.setDisable(true);
        });

    }


}
