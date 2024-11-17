package socketProgrammingThreading;

/* ClientHandler Class Code */
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class ClientHandler extends Thread{
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    final Socket com_tunnel;
    final DataInputStream dis_tunnel;
    final DataOutputStream dos_tunnel;
    String received = "";
    String toreturn = "";

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)
    {
        this.com_tunnel = s;
        this.dis_tunnel = dis;
        this.dos_tunnel = dos;
    }
    public void run(){
        while(true){
            try {
                dos_tunnel.writeUTF("What do you want [Date/Time]");
                received = dis_tunnel.readUTF();
                if(received.equals("Exit")){
                    System.out.println("Client " + this.com_tunnel + " sends exits");
                    System.out.println("Closing the connection");
                    this.com_tunnel.close();
                    break;
                }
                Date date = new Date();
                switch (received) {
                    case "Date" :
                        toreturn = fordate.format(date);
                        dos_tunnel.writeUTF(toreturn);
                        break;
                    case "Time" :
                        toreturn = fortime.format(date);
                        dos_tunnel.writeUTF(toreturn);
                        break;
                    default:
                        dos_tunnel.writeUTF("Invalid input");
                        break;
                }
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
