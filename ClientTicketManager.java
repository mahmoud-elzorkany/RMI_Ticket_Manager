package com.company;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ClientTicketManager {

    public static void main(String args[]) {

        try {
            System.out.print("Installing security manager...");
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            System.out.println("done.");

            System.out.print("Getting the registry...");
            Registry registry = LocateRegistry.getRegistry();
            System.out.println("done.");

            System.out.print("Getting the stub...");
            TicketMachine ticketMachine = (TicketMachine) registry.lookup("addi");
            System.out.println("done.");

            System.out.print("Inovking the remote method...");
            ticketMachine.addNewTickets(6);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
