package TCPsocketProgramming;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6000)) {
            System.out.println("Connected to the server.");
            try (
                    DataInputStream dis = new DataInputStream(socket.getInputStream());
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    Scanner scanner = new Scanner(System.in)
            ) {
                while (true) {
                    System.out.println(dis.readUTF()); // Read server message
                    String input = scanner.nextLine();
                    dos.writeUTF(input); // Send input to the server

                    if (input.equalsIgnoreCase("ENDS")) {
                        System.out.println("Disconnected from the server.");
                        break;
                    }

                    String response = dis.readUTF(); // Read server response
                    System.out.println(response);
                }}
        } catch (IOException e) {
            e.printStackTrace();
        }}}