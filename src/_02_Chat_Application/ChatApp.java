package _02_Chat_Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp {
	private String ip;
	private int port;

	Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public ChatApp(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void start(){
		try {

			connection = new Socket(ip, port);

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (connection.isConnected()) {
			try {
				JOptionPane.showMessageDialog(null, is.readObject());
				System.out.println(is.readObject());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void sendClick() {
		try {
			if (os != null) {
			String input=JOptionPane.showInputDialog("What do you want to say to the server?");
				os.writeObject(input);
				os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
