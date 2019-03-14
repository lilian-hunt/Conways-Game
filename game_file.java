import java.util.Scanner;

public class Life {
	
	static final char DEAD_SYMBOL = '.';
	static final char LIFE_SYMBOL = 'O';
	
	int width;
	int height;
	boolean[][] lifeField;
	
	public Life(int width, int height) {
		this.width = width;
		this.height = height;
		// initialise all cells as dead
		this.lifeField = new boolean[height][width];
	}
	public int check(int i,int j){
		int living = 0;
		try {
			if (this.lifeField[i+1][j]== true){
				living ++;
			}
		}
		catch (ArrayIndexOutOfBoundsException e ){}
		try {
			if (this.lifeField[i-1][j]== true){
				living ++;
			}
		}
		catch (ArrayIndexOutOfBoundsException e){}
		try {
			if (this.lifeField[i][j+1]== true){
				living ++;
			}
		}
		catch (ArrayIndexOutOfBoundsException e ){}
		try {
			if (this.lifeField[i][j-1]== true){
				living ++;
			}
		}
		catch (ArrayIndexOutOfBoundsException e ){}
		try {
			if (this.lifeField[i-1][j-1]== true){
				living ++;
			}
		}
		catch (ArrayIndexOutOfBoundsException e ){}
		try {
			if (this.lifeField[i+1][j-1]== true){
				living ++;
			}
		}
		catch (ArrayIndexOutOfBoundsException e ){}
		try {
			if (this.lifeField[i-1][j+1]== true){
				living ++;
			}
		}
		catch (ArrayIndexOutOfBoundsException e ){}

		try {
			if (this.lifeField[i+1][j+1]== true){
				living ++;
			}
		}
		catch (ArrayIndexOutOfBoundsException e ){}
		return living;
	}
	public void nextGeneration() {
		boolean[][] nextGen = new boolean[this.height][this.width];
		// TODO: calculate next generation
		for (int i = 0; i<this.lifeField.length; i++){
			for (int j = 0; j<this.lifeField[i].length;j++){
				int living = check(i,j);
				boolean current = this.lifeField[i][j];
				if (current == true && living <2){
					nextGen[i][j] = false;
				}
				else if (current == true && (living ==2||living ==3)){
					nextGen[i][j] = true;
				}
				else if (current == true && living >3){
					nextGen[i][j] = false;
				}
				else if (current == false && living ==3){
					nextGen[i][j] = true;
				}
				else {
					nextGen[i][j] = this.lifeField[i][j];
				}
			}
		}	
		this.lifeField = nextGen;
	}
	
	
	public static void main(String[] args) {
		
		Scanner stdin = new Scanner(System.in);
		/**
		 * First line of input is width height generations
		 * No error handling is required cause I'm lazy
		 **/
		String[] params = stdin.nextLine().split(" ");
		int width = Integer.parseInt(params[0]);
		int height = Integer.parseInt(params[1]);
		int generations = Integer.parseInt(params[2]);
		
		Life game = new Life(width, height);
		for (int i = 0; i < height; ++i) {
			game.setRow(i, stdin.nextLine());
		}
		stdin.close();
		for (int i = 0; i < generations; ++i) {
			game.nextGeneration();
			System.out.println(game.toString());
		}
	}
	
	public void setRow(int rowNum, String rowState) {
		for (int i = 0; i < this.width; ++i) {
			boolean isAlive;
			switch (rowState.charAt(i)) {
				case DEAD_SYMBOL:
					isAlive = false;
					break;
				case LIFE_SYMBOL:
					isAlive = true;
					break;
				default:
					throw new IllegalArgumentException("Unrecognised symbol");
			}
			this.lifeField[rowNum][i] = isAlive;
		}
	}
	
	public String toString() {
		StringBuilder out = new StringBuilder((this.width + 1) * this.height);
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				if (this.lifeField[i][j]){ // is alive
					out.append(LIFE_SYMBOL);
				}
				else{
					out.append(DEAD_SYMBOL);
				}
			}
			out.append('\n');	
		}
		return out.toString();
	}
}
