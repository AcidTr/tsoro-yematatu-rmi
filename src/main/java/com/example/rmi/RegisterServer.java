package com.example.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RegisterServer {

    public static void main(String args[]) {
        try {
//            if (args.length < 2) {
//                throw new Error("Please provide the hostname and port. e.g. java Registrador hostname 1099");
//            }

            String hostname = "localhost";

            int port = 1099;

//            System.setProperty("java.rmi.server.hostname", hostname);

            LocateRegistry.createRegistry(port);

            ServerRMI obj = new ServerRMI();

            Naming.rebind("rmi://" + hostname + ":" + port + "/Server", obj);

            System.out.println("Servidor Registrado!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro");
        }

    }
}
