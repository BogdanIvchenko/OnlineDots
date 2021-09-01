import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetDot extends JFrame {

	public static JTextPane txtpnPlayerMove;


	private JPanel contentPane;
	public JTextField textField;
	public JTextField textField_1;
	public boolean isServer = true;
	public static  MyPanel myBoard;
	public static boolean player1Moves = true;
	public static ServerNetCode server;
	public static CleintNetCode client;
//	public DotsMainFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NetDot frame = new NetDot();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NetDot() {
		setTitle("DOTS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 915, 673);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		JRadioButton clientbtn = new JRadioButton("Client");
		clientbtn.setBounds(40,340 , 100, 30);
//		contentPane.
		
		JRadioButton serverbtn = new JRadioButton("Server",true);
		serverbtn.setBounds(40,370 , 100,30);
//		contentPane.add
		
		
		
		ButtonGroup serverselect = new ButtonGroup();
		
//		serverselect.setVisible(true);
		
		contentPane.add(serverbtn);
		contentPane.add(clientbtn);
		contentPane.setLayout(null);
		contentPane.setSize(300, 300);
		contentPane.setVisible(true);
		
		serverselect.add(clientbtn);
		serverselect.add(serverbtn);
		
		
		
		
		
		JButton NetButton = new JButton("Start");
	
		NetButton.setBounds(80,300,140,40 );
		contentPane.add(NetButton);

		
		clientbtn.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NetButton.setText("Connect");
				//Later add the IP field
			}
			
			
		});
		
		serverbtn.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NetButton.setText("Start");
			}
			
			
		});
		
		textField = new JTextField();
		textField.setBounds(170, 203, 88, 23);
		textField.setText("Server");
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(170, 234, 88, 23);
		textField_1.setText("Client");
		textField_1.setEditable(false);
		contentPane.add(textField_1);
		
		JTextPane txtpnPlayerMove = new JTextPane();
		txtpnPlayerMove.setEditable(false);
		txtpnPlayerMove.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnPlayerMove.setText("Player 1 Move");
		txtpnPlayerMove.setBounds(442, 37, 204, 23);
		contentPane.add(txtpnPlayerMove);
		
		
		
		
		JButton btnNewButton_1 = new JButton("New Game");
//		btnNewButton_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				myBoard.Player1Name = textField.getText();
//				myBoard.Player2Name = textField_1.getText();
////				System.out.println("Player 1: "+myBoard.Player1Name+"Player 2"+myBoard.Player2Name);
//				myBoard.Player1Initials = myBoard.Player1Name.length() < 2 ? myBoard.Player1Name : myBoard.Player1Name.substring(0, 2);
//				myBoard.Player2Initials = myBoard.Player2Name.length() < 2 ? myBoard.Player2Name : myBoard.Player2Name.substring(0, 2);
//				myBoard.clearBoxes();
//				myBoard.repaint();
//				myBoard.player1Moves = true;
//				myBoard.player1Score = 0;
//				myBoard.player2Score = 0;
//				txtpnPlayerMove.setText("Player 1 Move 0:0");
//			
//				
//			}
//		});
		btnNewButton_1.setBounds(89, 270, 131, 23);
		contentPane.add(btnNewButton_1);

		JTextPane txtpnPlayerName = new JTextPane();
		txtpnPlayerName.setText("Player 1 Name:");
		txtpnPlayerName.setBounds(41, 203, 109, 23);
		txtpnPlayerName.setEditable(false);
		contentPane.add(txtpnPlayerName);

		JTextPane txtpnPlayerName_2 = new JTextPane();
		txtpnPlayerName_2.setText("Player 2 Name:");
		txtpnPlayerName_2.setBounds(41, 234, 109, 23);
		txtpnPlayerName_2.setEditable(false);
		contentPane.add(txtpnPlayerName_2);

	

		JTextPane txtpnPlayerName_2_1 = new JTextPane();
		txtpnPlayerName_2_1.setText("Please Enter the names for players 1 and 2 before beiginning the game");
		txtpnPlayerName_2_1.setBounds(41, 144, 230, 51);
		txtpnPlayerName_2_1.setEditable(false);
		contentPane.add(txtpnPlayerName_2_1);

		JTextPane txtpnWelcomeToThe = new JTextPane();
		txtpnWelcomeToThe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnWelcomeToThe.setText("Welcome to the game of DOTS");
		txtpnWelcomeToThe.setBounds(41, 87, 230, 23);
		txtpnWelcomeToThe.setEditable(false);
		contentPane.add(txtpnWelcomeToThe);

		NetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("entered click #96");
				System.out.println("isServer = "+ isServer);
				System.out.println(clientbtn.isSelected());
				
				myBoard.Player1Initials = myBoard.Player1Name.length() < 2 ? myBoard.Player1Name : myBoard.Player1Name.substring(0, 2);
				myBoard.Player2Initials = myBoard.Player2Name.length() < 2 ? myBoard.Player2Name : myBoard.Player2Name.substring(0, 2);
				myBoard.clearBoxes();
				myBoard.repaint();
//				myBoard.player1Moves = true;
				myBoard.player1Score = 0;
				myBoard.player2Score = 0;
				System.out.println("259");
				txtpnPlayerMove.setText("Player 1 Move 0:0");
				System.out.println("261");
				
				myBoard.Player1Name = textField.getText();
				myBoard.Player2Name = textField_1.getText();
				
				if(clientbtn.isSelected()) {

					isServer = false;
					player1Moves = false;
				
			        
					System.out.println("sent");
				} else {
					player1Moves = true;
				}
			
				
				if (isServer) {
					server = new ServerNetCode();
					server.run();
					System.out.println("not waiting for run");
				} else {
					client = new CleintNetCode();
					client.run();
				}
				
				
				
			}
		});
		
		


		myBoard = new MyPanel();
		myBoard.clearBoxes();
		myBoard.repaint();
//		myBoard.Player1Name = textField_1.getText();
//		myBoard.Player2Name = textField.getText();
		myBoard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//					System.out.println("(" + e.getX() + "," + e.getY() + ")");

					System.out.println(myBoard.player1Moves);
					
					int x = e.getX();
					int y = e.getY();
					
					myBoard.handleMouseClickOnmyPannel(x, y);

					
					//if player 1 moves: then player 1 moves, else player2 moves
					if(myBoard.player1Moves) {
						txtpnPlayerMove.setText("Player 1 Move "+myBoard.player1Score+":"+myBoard.player2Score);
					} else {
						txtpnPlayerMove.setText("Player 2 Move "+myBoard.player1Score+":"+myBoard.player2Score);
					}
					
					if(myBoard.player1Won) {
						txtpnPlayerMove.setText("Player 1 won "+myBoard.player1Score+":"+myBoard.player2Score);
					}
					if(myBoard.player2Won) {
						txtpnPlayerMove.setText("Player 2 won "+myBoard.player1Score+":"+myBoard.player2Score);
					}
					
					//if player1 won then player1 won
					
					//if player2 won, then player2 won 
					
					
					//Show scores
			
		}});
		myBoard.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		myBoard.setBounds(306, 71, 500, 500);
		contentPane.add(myBoard);

		
	}
}

/*
 * Start game button Check if names are full
 * 
 * Draw the grid
 * 
 * Input:
 * 
 * 
 * 
 * 
 * 
 */
