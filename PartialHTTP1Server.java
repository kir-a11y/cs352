import java.io.*;
import java.net.*;

class ServerThread implements Runnable{
	private String name;
	private Socket client = null;

	ServerThread(String name, Socket client){
		this.name = name;
		this.client = client;
		System.out.println("Created Thread " + name);
	}

	@Override
	public void run(){
		System.out.println("Running Thread: " + name);
		String clientSentence;
		String output;
		try{
			BufferedReader InFromClient = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(this.client.getOutputStream());
            while(true){

				clientSentence = InFromClient.readLine();
				System.out.println("Thread: "+ name + ", from Clilent: " + clientSentence);
				if (clientSentence.equals("QUIT")) {
					outToClient.writeBytes("y\n");
					break;
				}
				String header = InFromClient.readLine();
				System.out.println("header line: "+header);

				//HTTP Protocol Implementation
				int space1 = clientSentence.indexOf(' ');
				int space2 = clientSentence.indexOf(' ', space1+1);
				if (space1 == -1 || space2 == -1||space2 <= clientSentence.length()-8) {
					output = "HTTP/1.0 400 Bad Request\n\n";
				}else{
					String mode = clientSentence.substring(0,space1);
					String src = clientSentence.substring(space1+1, space2);
					String httpver = clientSentence.substring(space2+1);
					if (mode.equals("GET") || mode.equals("POST")) {
						output = "Need Implementations\n\n";
						//Implementations for GET command and POST command

					}else if (mode.equals("HEAD")) {
						output = "Need Implementations\n\n";
						//Implementations for HEAD command


					}else if (mode.equals("DELETE") || mode.equals("PUT") || mode.equals("LINK") || mode.equals("UNLINK")) {
						output = "HTTP/1.0 505 HTTP Version Not Supported\n\n";
					}else{
						output = "HTTP/1.0 400 Bad Request\n\n";
					}
				}

				outToClient.writeBytes(output);
			}
            this.client.close();
        }catch(Exception e){  
            e.printStackTrace();
        }
	}
}

public class PartialHTTP1Server  {
	public static void main(String argv[]) throws Exception{
		int threadnum = 0;

		ServerSocket welcomeSocket = new ServerSocket(8888,3);//port number == 8888, listen: 50

		while(true){
			Socket connectionSocket = welcomeSocket.accept();
			Thread t = new Thread(new ServerThread("n"+threadnum, connectionSocket));
			t.start();
			threadnum++;
		}


	}
}