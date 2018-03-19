package com.company;

import java.util.*;
import java.io.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class TicketmachineImpl implements TicketMachine {

    private int TicketsAvailable = 6;

    @Override
    public synchronized boolean  bookTickets(int nb) throws RemoteException, InterruptedException {

        while (nb > TicketsAvailable) {
            System.out.println("Waiting for enough ticket to be available..... ");
            wait();
        }//if

            System.out.println("Booked "+nb+" tickets");
            TicketsAvailable -= nb;
            return true;

    }

    @Override
    public synchronized void addNewTickets(int nb) throws RemoteException {

        System.out.println("Added "+nb+" new tickets");
        TicketsAvailable += nb;
        notifyAll();

    }


    public static void main(String args[]) {

        try {
            System.out.print("Installing security manager...");
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            System.out.println("done.");

            System.out.print("Creating the registry of RMI services...");
            LocateRegistry.createRegistry(1099);
            System.out.println("done.");

            System.out.print("Creating the remotely accessible Ticketmachineimpl (and the stub)...");
            TicketMachine ticketMachine = new TicketmachineImpl();
            TicketMachine stub = (TicketMachine) UnicastRemoteObject.exportObject(ticketMachine, 0);
            System.out.println("done.");

            System.out.println("Registering the stub...");
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("addi", stub);
            System.out.println("done.");
        } catch (Exception re) {
            re.printStackTrace();
        }
    }
}