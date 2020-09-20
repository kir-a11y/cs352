import java.io.*;
import java.net.*;

public class PartialHTTP1Server{
	public static void main(String argv[]) throws Exception{
		String clientSentence;
		String capitalizedSentence;

		ServerSocket welcomeSocket = new ServerSocket(8888);

		while(true){

			Socket connectionSocket = welcomeSocket.accept();

			BufferedReader InFromClient = new BufferedReader(
				new InputStreamReader(connectionSocket.getInputStream()));

			DataOutputStream outToClient = 
				new DataOutputStream(connectionSocket.getOutputStream());

				clientSentence = InFromClient.readLine();

				capitalizedSentence = clientSentence.toUpperCase() + '\n';

				outToClient.writeBytes(capitalizedSentence);
		}

	}
}