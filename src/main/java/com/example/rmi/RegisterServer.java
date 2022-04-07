package com.example.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RegisterServer {

    RegisterServer(String hostname, int port) {
        try {

        LocateRegistry.createRegistry(port);

        ServerRMI obj = new ServerRMI();

        Naming.rebind("rmi://" + hostname + ":" + port + "/Server", obj);

        System.out.println("Servidor Registrado!");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        try {
            String hostname = "localhost";


            if(args.length > 0 && !args[0].isEmpty()){
                hostname = args[0];
                System.out.println(hostname);
            }

            int port = 1099;

            if(args.length > 1 && !args[1].isEmpty()){
                port = Integer.parseInt(args[1]);
                System.out.println(port);
            }

            new RegisterServer(hostname, port);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro");
        }

    }
}
