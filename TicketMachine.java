package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TicketMachine extends Remote {

    public boolean bookTickets(int nb) throws RemoteException, InterruptedException;
    public void addNewTickets(int nb) throws RemoteException;

    }
