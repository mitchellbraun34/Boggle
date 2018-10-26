package Boggle;

import java.awt.Point;
import java.util.Arrays;
import java.util.Random;

public class Board {
	public String[][] Board;
	private Boolean[][] cubesUsed; 
	protected Point lastPoint;
	public Board() {
		Board = new String[4][4];
		Random generator = new Random();
		cubesUsed = new Boolean[4][4];
		for(int a = 0; a<4;a++)
		{
			for(int b = 0; b<4; b++)
			{
				cubesUsed[a][b] = false;
			}
		}
		for(int i = 0; i<4; i++)
		{
			for(int j = 0; j<4; j++)
			{	
				Boolean test = false;
				do {
					int roll = generator.nextInt(16);
					String letter = LetterCubes.rollLetterCube(roll);
					if(cubesUsed[i][j] == false)
					{
						cubesUsed[i][j] = true;
						Board[i][j] = letter;
						test = true;
					}
				}while(!test);
			}
		}
	}
	public String getLetter(int row, int colum)
	{
		return Board[row][colum];
	}
	
	
	public void printBoard() {
		for(int i = 0; i<4; i++)
		{
			for(int j = 0; j<4; j++)
			{
				System.out.printf(" %2s ", Board[i][j]);
			}
			System.out.println();
		}
	}
	public Boolean nearbyPiece(Point a, Point b)
	{
		
		if(a == null || b == null)
		{
			return true;
		}
		if(a.getX() == b.getX() - 1)
		{
			if(a.getY() == b.getY() - 1)
			{
				return true;
			}
			else if(a.getY() == b.getY() + 1)
			{
				return true;
			}
			else if(a.getY() == b.getY())
			{
				return true;
			}
		}
		else if(a.getX() == b.getX() + 1)
		{
			if(a.getY() == b.getY() - 1)
			{
				return true;
			}
			else if(a.getY() == b.getY() +1)
			{
				return true;
			}
			else if(a.getY() == b.getY())
			{
				return true;
			}
		}
		
		else if(a.getX() == b.getX())
		{
			if(a.getY() == b.getY() - 1)
			{
				return true;
			}
			else if(a.getY() == b.getY() + 1)
			{
				return true;
			}
		}
		
		return false;
		
		
	}
	
	

}
