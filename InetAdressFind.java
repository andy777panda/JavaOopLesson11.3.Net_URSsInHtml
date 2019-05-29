package net.ukr.andy777;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class InetAdressFind {

	private File fileIn;
	private File fileOut;

	public InetAdressFind(File fileIn, File fileOut) {
		super();
		this.fileIn = fileIn;
		this.fileOut = fileOut;
	}

	public void findAdressInFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileIn));
			String line = "";
			String resStr = "";
			for (; (line = br.readLine()) != null;) {
				if (line.indexOf("<a ") > -1) {
					line = line.substring(line.indexOf("href=\"") + 6);
					String href = line.substring(0, line.indexOf("\""));
					resStr += href + System.getProperty("line.separator");
				}
			}
			// jdk1.7
			// try(PrintWriter pw = new PrintWriter(new File(fileOut.toString())){
			// pw.print(resStr);
			// } catch (FileNotFoundException e) {
			// System.out.println("Error found file" + fileOut);
			// }

			// jdk1.6
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(new File(fileOut.toString()));
				pw.print(resStr);
			} catch (FileNotFoundException e) {
				System.out.println("Error found file" + fileOut);
			} finally {
				pw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String fileContent() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileOut));
			String line = "";
			String resStr = "<html><head><title>URLs in file</title> <meta charset='utf-8'></head><body>";
			for (; (line = br.readLine()) != null;) {
				resStr += "<br><a href=\"" + line + "\">" + line + "</a>";
			}
			resStr += "</body></html>";
			return resStr;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "ERROR";
	}

}
