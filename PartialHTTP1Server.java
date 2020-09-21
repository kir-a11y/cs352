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
		String capitalizedSentence;
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
				capitalizedSentence = clientSentence.toUpperCase()+'\n';
				outToClient.writeBytes(capitalizedSentence);
			}
            client.close();
        }catch(Exception e){  
            e.printStackTrace();
        }
	}
}

public class PartialHTTP1Server  {
	public static void main(String argv[]) throws Exception{
		String clientSentence;
		String capitalizedSentence;
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