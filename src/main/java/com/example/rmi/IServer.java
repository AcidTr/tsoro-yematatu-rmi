package com.example.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {

    void receiveInitialData(IClient client) throws RemoteException;

    void receiveMessageFromClient(String message, IClient client) throws RemoteException;

    void receiveTextMessageFromClient(String message, IClient client) throws RemoteException;

    void receivePlaceMessageFromClient(String selectedCircle , IClient client) throws RemoteException;

    void receiveMovementMessageFromClient(String previousCircleId, String nextCircleId , IClient client) throws RemoteException;

    void receiveGiveUpMessageFromClient(IClient client) throws RemoteException;

    void receiveDrawMessageFromClient(String type, IClient client) throws RemoteException;

    void receiveWinMessageFromClient(IClient client) throws RemoteException;
}
