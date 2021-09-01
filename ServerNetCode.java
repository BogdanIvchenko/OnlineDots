import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ServerNetCode implements Runnable{

	

	static PrintWriter pw;
//	Scanner sc;
	boolean firstCom = true;
	


	@Override
	public void run() {
		ServerSocket ss = null;
		
		try {
			ss = new ServerSocket(1234);
			
		}catch (Exception e) {
			System.out.println("Cant create sc server");
			return;
		}
	
		System.out.println("Socket Created");
		
		Socket sock;
		try {
			sock = ss.accept();
		} catch (Exception e2) {
			return;
		}
		System.out.println("Socket connected "+sock);
	
		Scanner sc = null;
		try {
			sc = new Scanner(sock.getInputStream());
		
	
		
			String localString = sc.nextLine();
			System.out.println("Server Recieved Message: "+localString);
			NetDot.myBoard.Player2Name = localString;
			
//			"C 123123  123123"
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
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(sock.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("ServerisWriting");
		pw.println(NetDot.myBoard.Player1Name);
		pw.flush();
		
		System.out.println("P1Name = " + NetDot.myBoard.Player1Name );
		System.out.println("P2Name = " + NetDot.myBoard.Player2Name );
		
		
		
		
		
		
		pw.close();
		sc.close();
		System.out.println("sc is closed");
		try {
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void send(String s) {
		
	}
		
	
}
