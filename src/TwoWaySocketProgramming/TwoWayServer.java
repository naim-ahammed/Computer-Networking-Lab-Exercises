package TwoWaySocketProgramming;

import java.io.*;
import java.net.*;
public class TwoWayServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is listening on port 5000");
            try (Socket socket = serverSocket.accept();
                 DataInputStream input = new DataInputStream(socket.getInputStream());
                 DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.println("Client connected");
                String clientMessage;
                String serverMessage;
                while (true) {
                    clientMessage = input.readUTF();
                    System.out.println("Client: " + clientMessage);
                    if (clientMessage.equalsIgnoreCase("stop")) {
                        System.out.println("Client has stopped the conversation.");
                        break;
                    }
                    System.out.print("Server: ");
                    serverMessage = reader.readLine();
                    output.writeUTF(serverMessage);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
