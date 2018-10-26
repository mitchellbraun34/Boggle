package Boggle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class GUI extends Board {

	JFrame frame;
	JPanel MainPanel;
	JPanel SecondaryPanel;
	JButton[][] b = new JButton[4][4];
	JButton StartButton;
	JButton Check;
	JButton Randomize;
	static JLabel timer;
	JLabel SCORE;
	Thread thread = new Thread();
	GameTimer GT;
	Word w;
	Dictionary dict;
	ArrayList<String> LETTERS = new ArrayList<String>();
	Boolean ADD = false;
	Score sr;
	ArrayList<Point> listOfPoints;
	ArrayList<String> Words = new ArrayList<String>();
	JLabel error;
	ScoreBoard srb;
	//PrintWriter out = new PrintWriter("BoggleHighScore.txt");
	String[][] mirror;
	public static Boolean play = true;


	
	/*
	 * Constructs the Main Components in the GUI
	 */
	
	public GUI() throws FileNotFoundException {

		mirror = new String[4][4];
		frame = new JFrame("Will's BEST BOGGLE");
		MainPanel = new JPanel();
		SecondaryPanel = new JPanel();
		StartButton = new JButton("Start");
		Check = new JButton("Check");
		Randomize = new JButton("Randomize");
		SCORE = new JLabel("SCORE: 0");
		timer = new JLabel();
		w = new Word(LETTERS);
		dict = new Dictionary("words.txt");
		sr = new Score("");
		srb = new ScoreBoard();
		error = new JLabel("");
		listOfPoints = new ArrayList<Point>();
		/*
		 * Creates the BoggleBoard
		 */
		
		SecondaryPanel.setBounds(100,150,400,400);
		SecondaryPanel.setLayout(new GridLayout(4,4));
		MainPanel.add(SecondaryPanel);
		
		for(int i = 0; i<4; i++)
		{
			for(int j = 0; j<4; j++)
			{
				b[i][j] = new JButton();
				SecondaryPanel.add(b[i][j]);
				b[i][j].setText(Board[i][j]);
				b[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
				b[i][j].setBackground(Color.lightGray);
				b[i][j].setForeground(Color.white);

			}
		}
	}
	
	/*
	 * Puts the Main Components on the JFrame and initializes it
	 */
	
	public void initialize() 
	{
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(630,630);
		
		MainPanel.setLayout(null);
		MainPanel.setForeground(Color.white);
		MainPanel.setBackground(Color.black);
		
		//StartButton
		StartButton.setBounds(450,50,100,40);
		StartButton.setForeground(Color.white);
		StartButton.setBackground(Color.DARK_GRAY);
		MainPanel.add(StartButton);
		
		//Timer
		timer.setBounds(90,50,100,40);
		timer.setForeground(Color.white);
		MainPanel.add(timer);
		
		//Score
		SCORE.setBounds(250,50,100,40);
		SCORE.setForeground(Color.white);
		MainPanel.add(SCORE);
		
		//Check
		Check.setBounds(530,200,70,35);
		Check.setForeground(Color.white);
		Check.setBackground(Color.DARK_GRAY);
		MainPanel.add(Check);
		
		//error
		error.setBounds(200,100,300,15);
		error.setForeground(Color.white);
		MainPanel.add(error);
		
//		//Randomize
//		Randomize.setBounds(15, 100, 100, 35);
//		MainPanel.add(Randomize);
		
		//Adds everything to the frame
		frame.add(MainPanel);
		
		//Creates the Timer
		timer.setText("180");
		GT = new gametimer(20);

	
		MouseListener listener = new Clickers();
		
		Check.addMouseListener(listener);
		Randomize.addMouseListener(listener);
		
		StartButton.addMouseListener(listener);	
		for(int i = 0; i<4; i++)
		{
			for(int j = 0; j<4; j++)
			{
				b[i][j].addMouseListener(listener);
			}
		}
	}
	
	
	
	class gametimer extends GameTimer
	{

		public gametimer(int startTime) {
			super(startTime);
			
			class Graphics implements ActionListener
			{

				@Override
				public void actionPerformed(ActionEvent e) {
					timeRemaining--;
					if(getTimeRemaining()!=-1)
					{
						timer.setText("" + getTimeRemaining());						
					}
					else
					{
						t.stop();
						try {
							srb.getting();
						} catch (FileNotFoundException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						try {
							srb.printing("BoggleHighScore.txt", Boggle.name, sr.getScore());
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						System.out.println("Your score is: " + sr.getScore());
						System.exit(0);
					}
					
				}
				
				
				
			}
			listener = new Graphics();
			super.t = new Timer(DELAY, listener);
			
		}
		
	}
	class Clickers implements MouseListener
	{
		
		@Override
		public void mouseClicked(MouseEvent e) 
		{
				
				GT.start();

				for(int i = 0; i<4;i++)
				{
					for(int j = 0; j<4; j++)
					{
						
						if(b[i][j] == e.getSource() && nearbyPiece(lastPoint, new Point(i,j)) && checkPoint(new Point(i,j)) == true)
						{
							b[i][j].setBackground(Color.darkGray);
							w.addLetter(b[i][j].getText());
							lastPoint = new Point(i,j);
							listOfPoints.add(new Point(i,j));
						}
					}
				}
				if(Randomize == e.getSource())
				{
					ChangePosition();
				}
				if(Check == e.getSource())
				{
					
					for(int i = 0; i<4;i++)
					{
						for(int j = 0; j<4; j++)
						{
							b[i][j].setBackground(Color.lightGray);
							lastPoint = null;
							listOfPoints.clear();
						}
					}
					if(w.length() <=2)
					{
						error.setText("You need to pick a word longer than 2 letters.");
						w = new Word(LETTERS);
					}
					else if(checkWord(w.toString())!=true)
					{
						error.setText("Cannot do the same Word.");
					}
					
					else
					{
						error.setText("");
						try 
						{
							Words.add(w.toString());
							sr.scoreWord(w, dict);
						}
						catch(InvalidWordException e1)
						{
							error.setText("Not a valid word.");
						}
						SCORE.setText("SCORE: " + sr.getScore());
						w = null;
						w = new Word(LETTERS);
					}
					

				}
		}

		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}

		
		
	}
	public Boolean checkPoint(Point a)
	{
			if(listOfPoints.contains(a))
			{
				error.setText("Cannot pick the same letter.");
				return false;
			}
		
		return true;
	}
	
	public Boolean checkWord(String n)
	{
		if(Words.contains(n))
			return false;
		return true;
	}
	public void Mirror()
	{
		for(int i = 0; i<4; i++)
		{
			for(int j = 0; j<4; j++)
			{
				mirror[i][j] = b[i][j].getText();
			}
		}
		for(int i = 0; i<4; i++)
		{
			for(int j = 0; j<4; j++)
			{
				System.out.print(mirror[i][j]);
			}
			System.out.println();
		}
	}
	public void ChangePosition()
	{
		Boolean[][] cubesUsed = new Boolean[4][4];
		Random generatorx = new Random();
		Random generatory = new Random();
		
		for(int i = 0; i<4; i++)
		{
			for(int j = 0; j<4; j++)
			{
				cubesUsed[i][j] = false;
			}
		}
		
		for(int a = 0; a<4; a++)
		{
			for(int c = 0; c<4; c++)
			{
				Boolean test = false;
				do 
				{
					int rollx = generatorx.nextInt(4);
					int rolly = generatory.nextInt(4);
					String letter = mirror[rollx][rolly];
					if(cubesUsed[a][c] == false)
					{
						cubesUsed[a][c] = true;
						b[a][c].setText(letter);
						test = true;
					}
				}while(!test);
			}
		}
	}
}
