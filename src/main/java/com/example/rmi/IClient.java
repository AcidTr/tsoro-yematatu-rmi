package com.example.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {

    void receiveTextMessage(String message) throws RemoteException;

    void receiveConfig(Boolean isFirst) throws RemoteException;

    void receivePlacePiece(String selectedCircle) throws RemoteException;

    void receiveMovementMessage(String previousCircleId, String nextCircleId) throws RemoteException;

    void receiveGiveUpMessage() throws RemoteException;

    void receiveDrawMessage(String type) throws RemoteException;

    void receiveWinMessage() throws RemoteException;
}
