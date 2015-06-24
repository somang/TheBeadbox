package beadbox;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String argv[]) throws Exception {        
        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            try (Socket clientSocket = new Socket("141.117.145.189", 6789)) {
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                
                sentence = inFromUser.readLine();
                outToServer.writeBytes(sentence + '\n');
                modifiedSentence = inFromServer.readLine();
                System.out.println(modifiedSentence);
            }
        }
    }
}
