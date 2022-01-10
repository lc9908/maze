import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MazeGenerator extends JFrame implements ActionListener{
	
	//Header component
	JPanel header = new JPanel();
	JLabel width_label = new JLabel("width");
	JLabel length_label = new JLabel("length:");
	JTextField width_field = new JTextField(5);
	JTextField length_field = new JTextField(5);
	JButton submit = new JButton("Start");
	
	//Body component
	JPanel body = new JPanel();
	JPanel maze[][];
	
	//MAZE COMPONENT
	int start;
	int end;
	int MAX_ROW;
	int MAX_COL;
	public MazeGenerator() {
		//header
		add(header, BorderLayout.PAGE_START);
		header.add(width_label);
		header.add(width_field);
		header.add(length_label);
		header.add(length_field);
		header.add(submit);
		//body
		add(body, BorderLayout.CENTER);
		
		//Listener
		submit.addActionListener(this);
		
		setTitle("Maze Generator");
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Assuming that the user will enter a number in the field
		MAX_ROW = Integer.parseInt(width_field.getText());
		MAX_COL= Integer.parseInt(length_field.getText());
		body.removeAll();
		body.revalidate();
		body.repaint();
		body.setLayout(new GridLayout(MAX_ROW,MAX_COL));
		maze = new JPanel[MAX_ROW][MAX_COL];
		
		setup(maze);
		mazeGenerator(start, 1);
		maze[end-1][MAX_COL-2].setBackground(Color.GRAY);
		maze[end][MAX_COL-2].setBackground(Color.WHITE);
		maze[end+1][MAX_COL-2].setBackground(Color.GRAY);
		

	}
	
	public void mazeGenerator(int i, int j) {
		int x[] = {-2,0,2,0};
		int y[] = {0,2,0,-2};
		maze[i][j].setBackground(Color.WHITE);
		List<int[]> avaliable_path = new ArrayList<int[]>();
		for(int move = 0; move < x.length; move++) {
			int adj_x = x[move] + i;
			int adj_y = y[move] + j;
			if(valid(adj_x,adj_y)){
				avaliable_path.add(new int[] {adj_x,adj_y});
			} else if(valid_side(adj_x,adj_y)) {
				int dice=(int)(Math.random()* 2);
				if(dice == 1)
					avaliable_path.add(new int[] {adj_x,adj_y});
			}
		}
		Collections.shuffle(avaliable_path);
		while(!avaliable_path.isEmpty()) {
			int adj_x = avaliable_path.get(0)[0];
			int adj_y = avaliable_path.get(0)[1];
			if(valid(adj_x,adj_y) && maze[adj_x][adj_y].getBackground()== Color.GRAY) {
			maze[(i+adj_x)/2][(j+adj_y)/2].setBackground(Color.WHITE);
			mazeGenerator(adj_x, adj_y);
		}else if(valid_side(adj_x,adj_y)){
			maze[(i+adj_x)/2][(j+adj_y)/2].setBackground(Color.WHITE);
		}
			
			avaliable_path.remove(0);
		}
	}
	public boolean valid(int position_x, int position_y) {
		return (position_x > 0 && position_x < MAX_ROW-1 && position_y > 0 && position_y < MAX_COL-1);
	}
	public boolean valid_side(int position_x, int position_y) {
		return (position_x == 0 || position_x == MAX_ROW-1 || position_y== 0 || position_y == MAX_COL-1);
	}
	
	public void setup(JPanel[][] maze) {
		int MAX_ROW = maze.length;
		int MAX_COL = maze[0].length;
		for(int i = 0; i < MAX_ROW; i++) {
			for(int j = 0; j < MAX_COL; j++) {
				maze[i][j] = new JPanel();
				if(i == 0 || i == MAX_ROW-1 || j==0 || j==MAX_COL-1)
					maze[i][j].setBackground(Color.BLACK);
				else
					maze[i][j].setBackground(Color.GRAY);
				body.add(maze[i][j]);
				System.out.println(maze[i][j].getBackground() == Color.WHITE);
			}
		}
		start = (int)(Math.random() * (MAX_ROW-1)-1)+1;
		end = (int)(Math.random() * (MAX_COL-1)-1)+1;
		maze[start][0].setBackground(Color.GREEN);
		maze[end][MAX_COL-1].setBackground(Color.RED);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MazeGenerator();
	}


}
