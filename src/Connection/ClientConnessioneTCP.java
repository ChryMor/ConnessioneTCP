/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

/**
 *
 * @author Christian Moretti
 */
public class ClientConnessioneTCP {
    /**
     * @param args the command line arguments
     */
	
	//oggetto da usare per realizzare la connessione TCP
        Socket connection = null;
        //nome o IP del server
        String serverAddress = "localhost";
        //porta del server in ascolto
        int port = 2000;
        
        DataOutputStream out;
        DataInputStream stream;
		
    public static void main(String[] args) throws IOException{
        
        ClientConnessioneTCP c = new ClientConnessioneTCP();
        
		c.apri();
		c.comunica();
		c.chiudi();
        
    
	}
	public void apri()throws IOException{
		//apertura della connessione al server sulla porta specificata
		try{
            connection = new Socket(serverAddress, port);
            System.out.println("Connessione aperta");
            
			
					
        }
        catch(ConnectException e){
            System.err.println("Server non disponibile!");
        }
        catch(UnknownHostException e1){
            System.err.println("Errore DNS!");
        }

        catch(IOException e2){//
            System.err.println(e2);
            e2.printStackTrace();
        }
	}
	public void comunica() throws IOException{
		out = new DataOutputStream(connection.getOutputStream());
            out.writeUTF("Dammi la data di oggi");
            out.flush();
            //out.close();
            
			System.out.print("Il Server risponde: ");
			
			stream=new DataInputStream(connection.getInputStream());
			System.out.println(stream.readUTF());
            //stream.close();
	}
	
	public void chiudi() throws IOException{
		//chiusura della connnessione
		try {
            if (connection!=null)
                {
					
                    connection.close();
                    System.out.println("Connessione chiusa!");
                }
            }
            catch(IOException e){
                System.err.println("Errore nella chiusura della connessione!");
            }
	}
}