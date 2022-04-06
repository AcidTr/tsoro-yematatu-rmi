package com.example.rmi;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;


public class ServerRMI extends UnicastRemoteObject implements IServer {

    private ArrayList<IClient> clients;

    int numberOfClientsConnected = 0;

    ServerRMI() throws RemoteException {
//
        clients = new ArrayList<>();
        System.out.println("Servidor criado!");
    }

    @Override
    public void receiveInitialData(IClient client) throws RemoteException {
        try {


        clients.add(client);
        numberOfClientsConnected += 1;
        if (numberOfClientsConnected == 2) {
            Random random = new Random();

            int firstToStart = random.nextBoolean() ? 0 : 1;

            clients.get(0).receiveConfig(firstToStart == 0);

            clients.get(1).receiveConfig(firstToStart == 1);

            System.out.println("Connections Done");

        }
        } catch (RemoteException e) {

        }

    }

    @Override
    public void receiveMessageFromClient(String message, IClient client) throws RemoteException {
        System.out.println("Message received from client: " + message);

        clients.get(1 - clients.indexOf(client)).receiveTextMessage(message);

    }

    @Override
    public void receiveTextMessageFromClient(String message, IClient client) throws RemoteException {
        System.out.println("Message received from client: " + message);

        clients.get(1 - clients.indexOf(client)).receiveTextMessage(message);
    }

    @Override
    public void receivePlaceMessageFromClient(String selectedCircle, IClient client) throws RemoteException {
        System.out.println("Place message received from client");

        clients.get(1 - clients.indexOf(client)).receivePlacePiece(selectedCircle);
    }

    @Override
    public void receiveMovementMessageFromClient(String previousCircleId, String nextCircleId, IClient client) throws RemoteException {
        clients.get(1 - clients.indexOf(client)).receiveMovementMessage(previousCircleId, nextCircleId);
    }

    @Override
    public void receiveGiveUpMessageFromClient(IClient client) throws RemoteException {
        clients.get(1 - clients.indexOf(client)).receiveGiveUpMessage();
    }

    @Override
    public void receiveDrawMessageFromClient(String type, IClient client) throws RemoteException {
        clients.get(1 - clients.indexOf(client)).receiveDrawMessage(type);
    }

    @Override
    public void receiveWinMessageFromClient(IClient client) throws RemoteException {
        clients.get(1 - clients.indexOf(client)).receiveWinMessage();
    }

}
