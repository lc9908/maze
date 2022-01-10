import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class mazeGeneratorExample {

	final int MAX_ROW = 11;
	final int MAX_COL = 11;
	int[][] mazeBoard = new int[MAX_ROW][MAX_COL];
	int start;
	int end;
	
	public mazeGeneratorExample() {
		initial();
		print();
		System.out.println(start+":"+1);
		mazeGenerator(start, 1);
		print();
		mazeSolver(start,1);
		print();
	}
	
	public void mazeGenerator(int i, int j) {
		int x[] = {-2,0,2,0};
		int y[] = {0,2,0,-2};
		mazeBoard[i][j] = 0;
		List<int[]> avaliable_path = new ArrayList<int[]>();
		for(int move = 0; move < x.length; move++) {
			int adj_x = x[move] + i;
			int adj_y = y[move] + j;
			if(valid(adj_x,adj_y) ){
				avaliable_path.add(new int[] {adj_x,adj_y});
			} 
		}
		Collections.shuffle(avaliable_path);
		while(!avaliable_path.isEmpty()) {
			int adj_x = avaliable_path.get(0)[0];
			int adj_y = avaliable_path.get(0)[1];
			if( mazeBoard[adj_x][adj_y] == 8) {
			mazeBoard[(i+adj_x)/2][(j+adj_y)/2] = 0;
			mazeGenerator(adj_x, adj_y);
			}
			
			avaliable_path.remove(0);
		}
	}

	
	
	public void mazeSolver(int r, int c) {
		if(mazeBoard[r][c] != 0) {
			return;
		}
		int x[] = {-1,0,1,0};
		int y[] = {0,1,0,-1};
		mazeBoard[r][c] = 2;
		for(int move = 0; move < x.length; move++) {
			int adj_x = x[move] + r;
			int adj_y = y[move] + c;
		}
		mazeBoard[r][c] = 2;
		for(int move = 0; move < x.length; move++) {
			int adj_x = x[move] + r;
			int adj_y = y[move] + c;
			if(valid(adj_x, adj_y) &&!valid_side(adj_x, adj_y)&& mazeBoard[adj_x][adj_y] == 0) {
				mazeSolver(adj_x, adj_y);
			}
		}
		
		
	}
	
	public boolean valid(int position_x, int position_y) {
		return (position_x > 0 && position_x < MAX_ROW-1 && position_y > 0 && position_y < MAX_COL-1);
	}
	public boolean valid_side(int position_x, int position_y) {
		return (position_x == 0 || position_x == MAX_ROW-1 || position_y== 0 || position_y == MAX_COL-1);
	}
	
	public void initial() {
		for(int i = 0; i< MAX_ROW;i++) {
			for(int j = 0; j < MAX_COL; j++) {
				if(i==0 || i == MAX_ROW-1 || j == 0 || j == MAX_COL-1) {
					mazeBoard[i][j] =1;
				} else {
					mazeBoard[i][j] =8;
				}
			}
		}
		//do {
			//start = (int)(Math.random() * (MAX_ROW-2)-1)+1;
			//end = (int)(Math.random() * (MAX_COL-2)-1)+1;
			start = 6;
			end = 3;
			//System.out.println("["+start+":"+end+"]");
			mazeBoard[start][0] = 0;
	//	}while(start!=0 && start != MAX_ROW-1 && end!=0 && end != MAX_ROW-1);
			mazeBoard[end][MAX_COL-1] = 3;
		
	}
	public void print() {
		for(int i = 0; i< mazeBoard.length;i++) {
			for(int j = 0; j < mazeBoard[0].length; j++) {
				System.out.print(mazeBoard[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new mazeGeneratorExample();
	}

}
