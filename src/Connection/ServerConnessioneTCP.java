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
	
	
	int port = 2000;
	//oggetto ServerSocket necessario per accettare richieste dal client
	ServerSocket sSocket = null;
	//oggetto da usare per realizzare la connessione TCP
	Socket connection;

	DataOutputStream out;
	DataInputStream stream;
	DateFormat dateFormat;
	String dataoggi;

	public static void main(String[] args) throws IOException {
		ServerConnessioneTCP s=new ServerConnessioneTCP();
		
		s.attendi();
		s.comunica();
		s.chiudi();
	}

	public void attendi() throws IOException {
		try {
			sSocket = new ServerSocket(port);
			System.out.println("In attesa di connessioni!");
			//si è stabilita la connessione
			connection = sSocket.accept();
			System.out.println("Connessione stabilita!");
			System.out.println("Socket server: " + connection.getLocalSocketAddress());
			System.out.println("Socket client: " + connection.getRemoteSocketAddress());
		} catch (IOException e) {
			System.err.println("Errore di I/O!");
		}

	}

	public void comunica() throws IOException {
		System.out.print("Il Client dice: ");

		stream = new DataInputStream(connection.getInputStream());
		System.out.println(stream.readUTF());
				//stream.close();

		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		out = new DataOutputStream(connection.getOutputStream());
		dataoggi = dateFormat.format(date);
		out.writeUTF(dataoggi);
		out.flush();
		//out.close();
	}

	public void chiudi() throws IOException {
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
