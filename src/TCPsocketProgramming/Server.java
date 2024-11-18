package TCPsocketProgramming;
import java.io.*;
import java.net.*;



public class Server {
    public static void main(String[] args) {
        int clientCount = 0; // To track the number of connected clients
        final int MAX_CLIENTS = 5;
        try (ServerSocket serverSocket = new ServerSocket(6000)) {
            System.out.println("Server started on port 5000");

            while (clientCount < MAX_CLIENTS) {
                Socket clientSocket = serverSocket.accept();
                clientCount++;
                System.out.println("Client " + clientCount + " connected.");
                Thread clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            }
            System.out.println("Maximum client limit reached. No longer accepting connections.");
        } catch (IOException e) {
            e.printStackTrace();
        }}}




