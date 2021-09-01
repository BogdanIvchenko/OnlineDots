import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class CleintNetCode implements Runnable{

	
	private Socket sock;
	static PrintWriter pw;
	Scanner sc;
	
	public void run() {
	
		Socket sock = null;
		try {
			
			sock = new Socket("localhost",1234);
		} catch (Exception e) {
			
		}
		System.out.println("Socket created");
	
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(sock.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Client should be writing");
		pw.println(NetDot.myBoard.Player1Name);
		pw.flush();
		
		Scanner sc = null;
		try {
			sc = new Scanner(sock.getInputStream());
		
	
		
			String localString = sc.nextLine();
			System.out.println("Server Recieved Message: "+localString);
			NetDot.myBoard.Player2Name = localString;
			
			
			while (sc.hasNextLine()) {
				
				String PassedData;
				PassedData = sc.nextLine();
				
				
				//Check if it's a Q
				
				if( PassedData.substring(0, 1).contentEquals("Q")) {
					break;
				} else if (PassedData.substring(0, 1).contentEquals("C")) {
					String[] numbers = PassedData.substring(1).split(" ");
					
					int c1 = Integer.parseInt(numbers[0]);
					int c2 = Integer.parseInt(numbers[1]);
					
					NetDot.myBoard.handleMouseClickOnmyPannel(c1,c2);
				}
				
				
			}
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NetDot.myBoard.player1Moves = false;
		NetDot.player1Moves = false;
		
		
		
		
		
		
		
		
		
		sc.close();
		pw.close();
		try {
			sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void send(String s) {
		
		
		
	}

	
}
