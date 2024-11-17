package socketProgrammingThreading;

/* Server Side Code */
import java.io.*;
import java.net.*;

public class ServerThread {
    public static void main(String args[]) throws IOException{
        ServerSocket handshake = new ServerSocket (5000);
        System.out.println("Server connected at " + handshake.getLocalPort());
        System.out.println("Server is connecting\n");
        System.out.println("Wait for the client\n");
        while(true){
            Socket com_socket = handshake.accept();
            System.out.println("A new client is connected "+ com_socket);
            DataOutputStream dos = new DataOutputStream(com_socket.getOutputStream());

            DataInputStream dis = new DataInputStream(com_socket.getInputStream());

            System.out.println("A new thread is assigning");
            Thread new_tunnel = new ClientHandler(com_socket, dis, dos);
            new_tunnel.start();

        }
    }
}
