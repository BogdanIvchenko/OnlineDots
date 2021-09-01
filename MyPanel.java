import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.*;

public class MyPanel extends JPanel{

	private int inc = 50;
	private static int border = 50;
	private int diameter = 10;
	public String Player1Name = "Server";
	public String Player2Name = "Client";
	public String Player1Initials = "Se";
	public String Player2Initials = "Cl";
	public boolean player1Moves = true;
	public boolean player1Won = false;
	public boolean player2Won = false;
	public int player1Score = 0;
	public int player2Score = 0;
	public static Box boxes[][] = new Box[8][8];
	
	
	public void clearBoxes() {
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				boxes[i][j] = new Box();
			}
		}
		
		System.out.println("clearBoxes exited");
	}
	
	public void handleMouseClickOnmyPannel(int x, int y) {
		
//		System.out.println("Player 1: "+Player1Name+"Player 2"+Player2Name);
//		System.out.println("Player 1: "+Player1Initials+"Player 2"+Player2Initials);
		
		if ((x < border || y < border) || (x > border * 9 || y > border * 9)) {

//			System.out.println("outofbounds");

		} else {
			int boxX = x / border;
			int boxY = y / border;
//			System.out.println("(" + boxX + "," + boxY + ")");

			int xremainder = x % border;
			int yremainder = y % border;

			int left = border - xremainder;
			int right = xremainder;
			int top = border - yremainder;
			int bottom = yremainder;

			try {

				if (right > left && right > top && right > bottom) {
					boxes[boxX - 1][boxY - 1].right = true;
					if (boxX != 8) {
						boxes[boxX][boxY - 1].left = true;
					}

//					System.out.println("Right");
				}
				if (left > right && left > top && left > bottom) {
					boxes[boxX - 1][boxY - 1].left = true;

					if (boxX != 1) {
						boxes[boxX - 2][boxY - 1].right = true;
					}

//					System.out.println("left");
				}
				if (top > left && top > right && top > bottom) {
					boxes[boxX - 1][boxY - 1].top = true;

					if (boxY != 1) {
						boxes[boxX - 1][boxY - 2].bottom = true;
					}

//					System.out.println("top");

				}
				if (bottom > left && bottom > right && bottom > top) {
					boxes[boxX - 1][boxY - 1].bottom = true;

					if (boxY != 8) {
						boxes[boxX - 1][boxY].top = true;
					}

//					System.out.println("bottom");
				}
				
				boolean boxCaptured=false;
				
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						
						if ((boxes[i][j].top && boxes[i][j].bottom && boxes[i][j].right && boxes[i][j].left)
								&& (boxes[i][j].initial.equals(" "))) { // Works one move later thou
							System.out.println("plyr1mv"+player1Moves);
							System.out.println(Player2Initials);
							System.out.println(Player1Initials);
							if (player1Moves) {
								boxes[i][j].initial = Player1Initials;
							} else {
								boxes[i][j].initial = Player2Initials;
							}
							if ( player1Moves) {
								player1Score++;
							} else {
								player2Score++;
							}

							boxCaptured = true;
							repaint();
						}
					}
				}
				if( boxCaptured == false) {
					if ( player1Moves) {
						player1Moves = false;
					} else {
						player1Moves = true;
					}
				}
				 boxCaptured=false;
				
				if (player1Score + player2Score>=8*8 ) {
					if ( player1Moves) {
						player1Won = true;
					} else {
						player1Won = true;
					}
				}
				
			} catch (Exception e1) {
				System.out.println("LigicL Filure");
			}
			repaint();
		}
	}
	

	
	
	
	
	

	
	public void paint (Graphics g) {
		super.paint(g);
		int xbox = 0;
		int ybox = 0;
		for ( int x = border; x<getWidth(); x+= inc) {
			for (int y = border; y<getHeight(); y+=inc) {	
				g.fillOval(x-5, y-5, diameter, diameter);
				
				
				
				if(x<=400 && y<=400) {
					if (boxes[xbox][ybox].top) {
						g.drawLine(x, y, x+inc, y);
					
					}
					if (boxes[xbox][ybox].bottom) {
						g.drawLine(x, y+inc, x+inc, y+inc);
						
					}
					if (boxes[xbox][ybox].right) {
						g.drawLine(x+inc, y, x+inc, y+inc);
						
					}
					if (boxes[xbox][ybox].left) {
						g.drawLine(x, y, x, y+inc);
						
					}
					
					g.drawString(boxes[xbox][ybox].initial+" ", x+20, y+20);
				}	
				
				
				if (ybox !=7) {
					ybox++;
				}
			}
			if (xbox!=7) {
				xbox++;
			}
			ybox=0;
		}
		
		try {
			if (NetDot.player1Moves) {
				
				NetDot.txtpnPlayerMove.setText("Player 1 move");
			} else {
				NetDot.txtpnPlayerMove.setText("Player 2 move");
				NetDot.txtpnPlayerMove.setBounds(442, 37, 204, 23);
//				DotsMainFrame.add(txtpnPlayerMove);
			}
		
		}catch(Exception e) {
			
		}
	}

}
