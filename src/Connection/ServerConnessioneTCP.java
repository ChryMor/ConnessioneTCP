/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author Christian Moretti
 */
public class ServerConnessioneTCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // porta del server maggiore di 1024 
        int port = 2000;
        //oggetto ServerSocket necessario per accettare richieste dal client
        ServerSocket sSocket = null;
        //oggetto da usare per realizzare la connessione TCP
        Socket connection;

        DataOutputStream out;
		DataInputStream stream;
        DateFormat dateFormat;
		String dataoggi;
        while (true) {
            try {
                // il server si mette in ascolto sulla porta voluta
                sSocket = new ServerSocket(port);
                System.out.println("In attesa di connessioni!");
                //si è stabilita la connessione
                connection = sSocket.accept();
                System.out.println("Connessione stabilita!");
                System.out.println("Socket server: " + connection.getLocalSocketAddress());
                System.out.println("Socket client: " + connection.getRemoteSocketAddress());
                
				stream=new DataInputStream(connection.getInputStream());
				System.out.println(stream.readUTF());
				stream.close();
				
				dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
                out = new DataOutputStream(connection.getOutputStream());
				dataoggi=dateFormat.format(date);
                out.writeUTF(dataoggi);
                out.flush();
                //out.close();
            
			} catch (IOException e) {
                System.err.println("Errore di I/O!");
            }
            //chiusura della connessione con il client
            try {
                if (sSocket != null) {
                    sSocket.close();
                }
            } catch (IOException ex) {
                System.err.println("Errore nella chiusura della connessione!");
            }
            System.out.println("Connessione chiusa!");
            
        }
    }
}