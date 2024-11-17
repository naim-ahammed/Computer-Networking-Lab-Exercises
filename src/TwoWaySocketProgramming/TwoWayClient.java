package TwoWaySocketProgramming;

import java.io.*;
import java.net.*;

public class TwoWayClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream());
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Connected to the server");
            String clientMessage;
            String serverMessage;
            while (true) {
                System.out.print("Client: ");
                clientMessage = reader.readLine();
                output.writeUTF(clientMessage);
                if (clientMessage.equalsIgnoreCase("stop")) {
                    System.out.println("Stopping the conversation.");
                    break;
                }
                serverMessage = input.readUTF();
                System.out.println("Server: " + serverMessage);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
