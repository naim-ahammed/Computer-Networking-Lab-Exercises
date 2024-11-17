package OneWaySocketProgramming;

/* Server Side Code */
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;


public class ServerOneWay {
    public static void main(String[] args) throws IOException{
        ServerSocket ss = new ServerSocket (5000);
        System.out.println("Server is connected at port no: " + ss.getLocalPort());


        System.out.println("Server is connecting\n");
        System.out.println("Waiting for the client\n");
        Socket s = ss.accept();
        System.out.println("Client request is accepted at port no: " + s.getPort());


        System.out.println("Server’s Communication Port: "+s.getLocalPort());
        DataInputStream input = new DataInputStream(s.getInputStream());
        String str = "";


        while(!str.equals("stop")){
            str = input.readUTF();
            System.out.println("Client Says: "+str );
        }
        s.close();
        input.close();
    }


}
