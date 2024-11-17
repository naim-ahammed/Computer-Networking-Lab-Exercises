package SocketProgrammingIdSum;

/* ClientHandler Class Code */
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


class ClientHandler extends Thread {
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    final Socket com_tunnel;
    final DataInputStream dis_tunnel;
    final DataOutputStream dos_tunnel;


    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.com_tunnel = s;
        this.dis_tunnel = dis;
        this.dos_tunnel = dos;
    }


    public void run() {
        while (true) {
            try {
                dos_tunnel.writeUTF("Enter 'Date', 'Time', or your ID. Type 'Exit' to terminate:");
                String received = dis_tunnel.readUTF();


                if (received.equalsIgnoreCase("Exit")) {
                    System.out.println("Client " + this.com_tunnel + " sends exit.");
                    System.out.println("Closing the connection.");
                    this.com_tunnel.close();
                    break;
                }


                String toreturn;
                Date date = new Date();


                if (received.equalsIgnoreCase("Date")) {
                    toreturn = fordate.format(date);
                } else if (received.equalsIgnoreCase("Time")) {
                    toreturn = fortime.format(date);
                } else {
                    // Calculate the sum of digits for the input ID
                    try {
                        int id = Integer.parseInt(received);
                        int sum = 0;
                        while (id > 0) {
                            sum += id % 10;
                            id /= 10;
                        }
                        toreturn = "Sum of digits: " + sum;
                    } catch (NumberFormatException e) {
                        toreturn = "Invalid input. Please enter 'Date', 'Time', or a valid numeric ID.";
                    }
                }


                dos_tunnel.writeUTF(toreturn);
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        try {
            this.dos_tunnel.close();
            this.dis_tunnel.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
