import java.util.ArrayList;
import java.util.Collections;

class MazeGenerator2 {
	final int SIZE = 25;
	int start;
	int end;
	String[][] maze = new String[SIZE][SIZE];
	String[][] solution;
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		new MazeGenerator2();
	}
	
	public void initial() {
		for(int i = 0; i < maze.length;i++) {
			for(int j = 0; j < maze[i].length; j++) {
				maze[i][j] = "#";
			}
		}
		do {
			start = (int)(Math.random() * (SIZE-2)-1)+1;
		}while(start%2 == 0);
			end = (int)(Math.random() * (SIZE-2)-1)+1;
			maze[start][0] = ">";
			maze[end][SIZE-1] = "<";
	}
	
	public void printMaze() {
		for(int i = 0; i < maze.length;i++) {
			for(int j = 0; j < maze.length; j++) {
				System.out.print(maze[i][j] + " ");
				if(maze[i][j].equals("_"))
					System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public MazeGenerator2(){
		initial();
		mazeGenerate(start, 1);
		printMaze();
		maze[end][SIZE-2] = "_";
		//runMazeSolver();
	}
	
	public void runMazeSolver() {
		mazeSolver(start,1);
		printMaze();
	}
	
	public void mazeGenerate(int row, int col) {
		int[] move_x = {2,0,-2,0};
		int[] move_y = {0,2,0,-2};
		ArrayList<Point> poss_adj = new ArrayList<>();
		maze[row][col] = "_";
		for(int i = 0; i < move_x.length;i++) {
			int adj_x = row + move_x[i];
			int adj_y = col + move_y[i];
			if(valid(adj_x,adj_y) && maze[adj_x][adj_y].equals("#")) {
				poss_adj.add(new Point(adj_x, adj_y));
			}
		}
		Collections.shuffle(poss_adj);
		while(!poss_adj.isEmpty()) {
			int chosen_x = poss_adj.get(0).getX();
			int chosen_y= poss_adj.get(0).getY();
			if(maze[chosen_x][chosen_y].equals("#"))
				maze[(chosen_x+row)/2][(chosen_y+col)/2] = "_";
			mazeGenerate(chosen_x, chosen_y);
			poss_adj.remove(0);
		}	
	}
	
	public boolean mazeSolver(int x, int y) {
		int[] move_x = {1,0,-1,0};
		int[] move_y = {0,1,0,-1};
		// check if the end is adjacent to maze[x][y]
		maze[x][y] = " . ";
		for(int i = 0; i < move_x.length;i++) {
			int adj_x = move_x[i] + x;
			int adj_y = move_y[i] + y;
			if(maze[adj_x][adj_y].equals("<"))//found end
				return true;			
		}
		//If there is end 
		for(int i = 0; i < move_x.length;i++) {
			int adj_x = move_x[i] + x;
			int adj_y = move_y[i] + y;
			if(maze[adj_x][adj_y].equals("_")) {//found path
				if(mazeSolver(adj_x, adj_y))
					return true;
			}	
		}
		// If I don't find any path
		maze[x][y] = " * ";
		return false;

	}
	
	public String getCurrentCells(int i, int j) {return maze[i][j];}
	
	public int getSize() { return SIZE;}
	
	public boolean valid(int x, int y) {
		return (x > 0 && x < SIZE-1 && y > 0 && y < SIZE-1);
		
		
	}
	
	

}
