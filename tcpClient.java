import java.io.*;
import java.net.*;

public class tcpClient{
	public static void main(String args[]) throws Exception{

		String sentence;
		String modifiedSentence;
		int portnum = Integer.parseInt(args[0]);
		BufferedReader inFromUser = new BufferedReader(
			new InputStreamReader(System.in));
		Socket clientSocket = new Socket("localhost", portnum);
		clientSocket.setSoTimeout(10000); //timeout
		System.out.println("Connected, so timeout == 10000.");
		DataOutputStream outToServer = new DataOutputStream(
			clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(
			new InputStreamReader(clientSocket.getInputStream()));

		while(true){
			System.out.println("What would you like to do? (all commands ignores letter cases.)");
			System.out.println("Avaliable command: \"get\"(\"g\"),\"post\"(\"p\"), \"head\"(\"h\")");
			System.out.println("If you want to quit, type \"quit\" or \"q\"");
			sentence = inFromUser.readLine();
			sentence = sentence.toLowerCase();
			if (sentence.equals("quit") || sentence.equals("q")) {
				break;
			}else if (sentence.equals("get") || sentence.equals("g")) {
				System.out.println("Please indicate path you want to access:");
				sentence = inFromUser.readLine();
				outToServer.writeBytes("GET " + sentence + '\n');
			}else if (sentence.equals("post") || sentence.equals("p")) {
				System.out.println("Please indicate path you want to access:");
				sentence = inFromUser.readLine();
				outToServer.writeBytes("POST " + sentence + '\n');
			}else if (sentence.equals("head") || sentence.equals("h")) {
				System.out.println("Please indicate path you want to access:");
				sentence = inFromUser.readLine();
				outToServer.writeBytes("HEAD " + sentence + '\n');
			}else {
				System.out.println("Error: wrong mode, try again.");
			}

			modifiedSentence = inFromServer.readLine();
		System.out.println("FROM SEVER: "+ modifiedSentence);
		}

		clientSocket.close();

	}
}