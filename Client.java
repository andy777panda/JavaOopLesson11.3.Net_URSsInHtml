package net.ukr.andy777;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
	private Socket clientSocket;
	private String answer;
	private Thread t;

	public Client(Socket clientSocket, String answer) {
		super();
		this.clientSocket = clientSocket;
		this.answer = answer;
		t = new Thread(this);
		t.start();
	}

	public void run() {
		try {
			InputStream is = clientSocket.getInputStream();
			OutputStream os = clientSocket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);

			byte[] record1 = new byte[is.available()];
			is.read(record1);
			String response = "HTTP/1.1 200 OK\r\n" + "Server: My_Server\r\n" + "Content-Type:text/html\r\n"+
			"Content-Length: " + "\r\n" + "Connection: close\r\n\r\n";
			pw.print(response + answer);
			pw.flush();
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}
}