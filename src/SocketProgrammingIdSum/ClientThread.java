package SocketProgrammingIdSum;

/* Client Side Code */
import java.io.*;
import java.net.*;
import java.util.*;

public class ClientThread {
    public static void main(String args[]) throws IOException {
        try {
            Socket clientsocket = new Socket("localhost", 8080);
            System.out.println("Connected to server Handshaking port " + clientsocket.getPort());
            System.out.println("Client is connecting at Communication Port " + clientsocket.getLocalPort());
            System.out.println("Client is Connected");


            Scanner scn = new Scanner(System.in);
            DataOutputStream dos = new DataOutputStream(clientsocket.getOutputStream());
            DataInputStream dis = new DataInputStream(clientsocket.getInputStream());


            while (true) {
                String inLine = dis.readUTF();
                System.out.println(inLine);
                String outLine = scn.nextLine();
                dos.writeUTF(outLine);


                if (outLine.equalsIgnoreCase("Exit")) {
                    System.out.println("Closing the connection " + clientsocket);
                    clientsocket.close();
                    System.out.println("Connection Closed");
                    break;
                }


                String received = dis.readUTF();
                System.out.println(received);
            }


            dos.close();
            dis.close();
            clientsocket.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
