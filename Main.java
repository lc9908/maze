import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;
public class Main extends JFrame {
	
	int SIZE;
	
	JPanel cells[][];
	MazeGenerator2 mg = new MazeGenerator2();
	
	public Main() {
		//Get info from mazeGenerator
		SIZE = mg.getSize();
		cells = new JPanel[SIZE][SIZE];
		initial();
	}
	
	public void showPanel() {
		for(int i = 0; i < SIZE;i++) {
			for(int j = 0; j < SIZE; j++) {
				cells[i][j] = new JPanel();
				if(mg.getCurrentCells(i, j).equals("#")) cells[i][j].setBackground(Color.BLACK);
				else if(mg.getCurrentCells(i, j).equals(">")) cells[i][j].setBackground(Color.GREEN);
				else if(mg.getCurrentCells(i, j).equals("<")) cells[i][j].setBackground(Color.RED);
				else if(mg.getCurrentCells(i, j).equals(" . ")) cells[i][j].setBackground(Color.gray);
				add(cells[i][j]);		
			}
		}
	}
	
	public void findSolution() {
		mg.runMazeSolver();
		showPanel();
	}
	
	public void initial() {
		setTitle("Maze Generator");
		setSize(1000,1000);
		setLayout(new GridLayout(SIZE,SIZE));
		findSolution();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}
	

}
