package OneWaySocketProgramming;

/* Client Side Code */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


public class ClientOneWay {
    public static void main(String[] args) throws IOException{
        Socket s = new Socket ("localhost", 5000);
        System.out.println("Client Connected at server Handshaking port " + s.getPort());


        System.out.println("Client’s communcation port " + s.getLocalPort());
        System.out.println("Client is Connected");
        System.out.println("Enter the messages that you want to send and send \" stop\" to close the connection:");




        DataOutputStream output = new DataOutputStream(s.getOutputStream());
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));


        String str = "";
        while(!str.equals("stop")){
            str = read.readLine();
            output.writeUTF(str);
        }


        output.close();
        read.close();
        s.close();
    }
}

