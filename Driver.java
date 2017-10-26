import java.util.*;

/*
This is the classic Battleship game. You need two people to play.
@author John Calderaio
*/

class Driver {
	
	public static void main(String args[]) {
		Battleship b = new Battleship();
		b.setUp();
		b.printBoard();
		
		Scanner scan = new Scanner(System.in);
		
		int hit = 0;
		String s;
		System.out.println();
		
		int tries = 0;
		
		while(hit != 9) {
			
			System.out.print("Input coordinate: ");
			s = scan.nextLine();
			hit += b.attemptHit(s);
			b.printBoard();
			System.out.println();
			++tries;
		}
		System.out.println();
		System.out.println("It took you " + tries + " tries to beat battleship!");
		
	}
}

class Battleship {
	
	private ArrayList<Node> ship1 = new ArrayList<Node>();
	private ArrayList<Node> ship2 = new ArrayList<Node>();
	private ArrayList<Node> ship3 = new ArrayList<Node>();
	
	private char [][]board = new char[7][7];
	
	class Node {
		
		int a;
		int b;
		
		Node(int x, int y) {
			this.a = x;
			this.b = y;
		}
	}
	
	void setUp() {
		
		
		placeShip(getRandom(), getRandom(), ship1);
		placeShip(getRandom(), getRandom(), ship2);
		placeShip(getRandom(), getRandom(), ship3);
		
		
	}
	
	void placeShip(int a, int b, ArrayList<Node> ar) {
		
		int d = (int) (Math.random() * 2);
		
		if(d == 1) {
			while(board[a][b] == 'x' | board[a][b+1] == 'x' | board[a][b+2] == 'x') {
				a = getRandom();
				b = getRandom();
			}
			for(int i = 0; i < 3; ++i) {
				ar.add(new Node(a,b));
				board[a][b++] = 'x';
			}
			
		}
		else {
			
			while(board[a][b] == 'x' | board[a+1][b] == 'x' | board[a+2][b] == 'x') {
				a = getRandom();
				b = getRandom();
			}
			for(int i = 0; i < 3; ++i) {
				ar.add(new Node(a,b));
				board[a++][b] = 'x';
			}
			
			
		}
		
	}
	
	int getRandom() {
		return (int) (Math.random() * 5);
	}
	
	void printBoard() {
		
		char c = 'A';
		
		for(int i = 0; i < 7; ++i, ++c) {
			
			System.out.print(c + "  ");
			
			for(int j = 0; j < 7; ++j) {
				
				if(board[i][j] == 'x') {
					System.out.print("|" + board[i][j]);
				}
				else {
					System.out.print("|" + " ");
				}
				 
			}
			System.out.print("|");
			System.out.println(); 
			
		}
		
		System.out.println(); 
		System.out.print("    ");
		
		for(int i = 1; i <= 7; ++i) {
			System.out.print(i + " ");
		}
	}
	
	int attemptHit(String hit) {
		
		try {
			char c = Character.toLowerCase(hit.charAt(0));
			int b = Character.getNumericValue(hit.charAt(1));
			--b;
			int a = 0;
			
			if(c == 'a') {
				a = 0;
			}
			else if(c == 'b') {
				a = 1;
			}
			else if(c == 'c') {
				a = 2;
			}
			else if(c == 'd') {
				a = 3;
			}
			else if(c == 'e') {
				a = 4;
			}
			else if(c == 'f') {
				a = 5;
			}
			else if(c == 'g') {
				a = 6;
			}
			
			if(board[a][b] == 'x') {
				
				board[a][b] = ' ';
				Node n = new Node(a,b);
				
				for(int i = 0; i < ship1.size(); ++i) {
					if(ship1.get(i).a == a && ship1.get(i).b == b) {
						ship1.remove(i);
						if(ship1.isEmpty()) {
							System.out.println("ship 1 sunk!");
							return 1;
						}
						System.out.println("ship 1 hit!");
						return 1;
					}
				}
				for(int i = 0; i < ship2.size(); ++i) {
					if(ship2.get(i).a == a && ship2.get(i).b == b) {
						ship2.remove(i);
						if(ship2.isEmpty()) {
							System.out.println("ship 2 sunk!");
							return 1;
						}
						System.out.println("ship 2 hit!");
						return 1;
					}
				}
				for(int i = 0; i < ship3.size(); ++i) {
					if(ship3.get(i).a == a && ship3.get(i).b == b) {
						ship3.remove(i);
						if(ship3.isEmpty()) {
							System.out.println("ship 3 sunk!");
							return 1;
						}
						System.out.println("ship 3 hit!");
						return 1;
					}
				}
			}
			else {
				System.out.println("miss!");
				return 0;
			}
		}
		catch(Exception e) {
			System.err.print("There was an uncaught exception of type " + e);
		}
		return 0;
	}

}
