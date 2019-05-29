package net.ukr.andy777;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 Lesson11
 3. Напишите программу, которая выведет в файл все ссылки, которые содержатся в html документе, 
 который будет прислан в результате запроса на произвольный URL. 
 */

public class Main {
	public static void main(String[] args) {
		File fileIn = new File(".\\src\\net\\ukr\\andy777\\Prog.kiev.ua.html");
		File fileOut = new File(".\\src\\net\\ukr\\andy777\\hrefs.txt");

		try {
			ServerSocket serverSocket = new ServerSocket(8080);
			for (;;) {
				Socket clientSocket = serverSocket.accept();
				InetAdressFind iaf = new InetAdressFind(fileIn, fileOut);
				iaf.findAdressInFile();
				Client client = new Client(clientSocket, iaf.fileContent());
			}
		} catch (IOException e) {
			System.out.println("Error to server Socket Open!!!");
		}
	}
}
