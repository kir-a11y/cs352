import java.io.*;
import java.net.*;

public class tcpClient{
	public static void main(String args[]) throws Exception{

		String sentence;
		String modifiedSentence;
		int portnum = Integer.parseInt(args[0]);
		BufferedReader inFromUser = new BufferedReader(
			new InputStreamReader(System.in));
		Socket clientSocket = new Socket("127.0.0.1", portnum);
		clientSocket.setSoTimeout(10000); //timeout
		System.out.println("Connected, so timeout == 10000.");
		DataOutputStream outToServer = new DataOutputStream(
			clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(
			new InputStreamReader(clientSocket.getInputStream()));

		while(true){
			System.out.println("Indicate your http version:");
			String httpver = inFromUser.readLine();
			System.out.println("What would you like to do? (all commands ignores letter cases.)");
			System.out.println("Avaliable command: \"get\"(\"g\"),\"post\"(\"p\"), \"head\"(\"h\")");
			System.out.println("\"delete\"(\"d\"),\"put\"(\"pu\"), \"link\"(\"l\") and \"unlink\"(\"u\")");
			System.out.println("If you want to quit, type \"quit\" or \"q\"");
			sentence = inFromUser.readLine();
			
			String header  = "";
			String output;
			
			sentence = sentence.toLowerCase();
			if (sentence.equals("quit") || sentence.equals("q")) {
				output = "QUIT\n\n";
			}else if (sentence.equals("get") || sentence.equals("g")) {
				System.out.println("Please indicate path you want to access:");
				sentence = inFromUser.readLine();
				output = "GET " + sentence+" "+httpver'\n';
			}else if (sentence.equals("post") || sentence.equals("p")) {
				System.out.println("Please indicate path you want to access:");
				sentence = inFromUser.readLine();
				output = "POST " + sentence+" "+httpver'\n';
			}else if (sentence.equals("head") || sentence.equals("h")) {
				System.out.println("Please indicate path you want to access:");
				sentence = inFromUser.readLine();
				output = "HEAD " + sentence+" "+httpver'\n';
			}else if (sentence.equals("delete") || sentence.equals("d")) {
				System.out.println("Please indicate path you want to access:");
				sentence = inFromUser.readLine();
				output = "DELETE " + sentence+" "+httpver'\n';
			}else if (sentence.equals("put") || sentence.equals("pu")) {
				System.out.println("Please indicate path you want to access:");
				sentence = inFromUser.readLine();
				output = "PUT " + sentence+" "+httpver'\n';
			}else if (sentence.equals("link") || sentence.equals("l")) {
				System.out.println("Please indicate path you want to access:");
				sentence = inFromUser.readLine();
				output = "LINK " + sentence+" "+httpver'\n';
			}else if (sentence.equals("unlink") || sentence.equals("u")) {
				System.out.println("Please indicate path you want to access:");
				sentence = inFromUser.readLine();
				output = "UNLINK " + sentence+" "+httpver'\n';
			}else {
				System.out.println("Error: wrong mode, try again.");
			}
			System.out.println("Include header line? \"yes\"(\"y\") or anything");
			String str  = inFromUser.readLine();
			if (str.equals("yes") || str.equals("y")) {
				System.out.println("type in header line:");
				header = inFromUser.readLine() + '\n';
			}

			outToServer.writeBytes(output+ header);
			System.out.println();
			modifiedSentence = inFromServer.readLine();
			System.out.println("FROM SEVER: "+ modifiedSentence);
			if (modifiedSentence.equals("y")){
				break;
			}
		}

		clientSocket.close();

	}
}