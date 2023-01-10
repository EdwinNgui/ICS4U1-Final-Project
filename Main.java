/* Name: Edwin Ngui
 * Course: ICS4U1
 * Instructor: E, Katsman
 * Date Started: Jan/9/2023
 * Date Due: Jan/23/2023
 * Desc: _____________
 */

import java.util.*;

 public class Main {

	public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean invalidInput = false;
        int ans;
        int num = 0;
        //7 by 7 board, 2 layers, the top (1) is the player movement, the bottom (2) is where the properties are held
        boardSpace [][] board = new boardSpace [7][];
        //Makes 7x7 hollow
        board [0] = new boardSpace [7];
        board [1] = new boardSpace [2];
        board [2] = new boardSpace [2];
        board [3] = new boardSpace [2];
        board [4] = new boardSpace [2];
        board [5] = new boardSpace [2];
        board [6] = new boardSpace [7];

        //Initializes each space
        for (int i = 0; i < board.length; i ++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = new boardSpace();
            }
        }

        //Position set for all spaces
        for (int i = 0; i < board.length; i ++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j].setPosition(num);
                num ++;
            }
        }

        int numOfPlayers;


        System.out.println("Welcome to the Monopoly!");
        System.out.println("When you see <continue> enter anything to continue");
        pause();
        //Displays new or load game
        do {
            displayMenu(1, invalidInput);
			ans = input.nextInt();
			input.nextLine();
			if (ans != 1 || ans != 2) {
				invalidInput = true;
			}
		} while (ans != 1 && ans != 2);

        //New or load game
        if (ans == 1){ //New game: Setup variables intialized here
            clear();
            //Sets number of players
            System.out.println("How many players will be playing today?");
            System.out.println("Input here: ");
            numOfPlayers = input.nextInt();



        //Position viewer for all spaces
        for (int i = 0; i < board.length; i ++){
            for (int j = 0; j < board[i].length; j++){
                System.out.print(board[i][j].getPosition() + " ");
            }
            System.out.println("");
        }

            //ISSUE: Sets the properties to their respective locations
            
        }else if (ans == 2){ //Load game (use file reading)

        }

//ISSUE: only 1 player atm m
Player p1 = new Player();

        System.out.println("going to move player 1, 5 spaces along the board. ");
        System.out.println("they were at p1: " + p1.getPosition());
        num = 5;
       movePlayer(p1, board, num);


    }

    /* Pre: Requires player to move, the current board, number of spaces to move
     * Post: Returns the player with their updated position
     * Desc: Moves the player along the board clockwise
     */
    public static void movePlayer(Player player, boardSpace [][]board, int moveSpaces){
        //Go for the number of spaces the player should move
        for (int i = 0; i < moveSpaces; i ++){
            if(player.getPosition() >= 0 && player.getPosition() <= 5){ //Position 0-5 (6 is a corner)
                player.setPosition(player.getPosition() + 1); //Increased by one
            }else if(player.getPosition() >= 0 && player.getPosition() <= 5){ //Position 0-5 (6 is a corner)
                player.setPosition(player.getPosition() + 1); //Increased by one
            }else{

            }
        }

    }


    /* Pre: Requires the menu number and if there's an invalid input
	 * Post: Returns nothing to main
	 * Desc: Displays the menu based on which is called and if there is an invalid previous input
	 */
	public static void displayMenu(int menuNum, boolean invalidInput) {
		if (menuNum == 1) {
			System.out.println("\t...Main Menu...");
			System.out.println("(1) New Game");
			System.out.println("(2) Load Game");
		}else if (menuNum == 2) { //Yes-No Prompt
			System.out.println("(1) Yes");
			System.out.println("(2) No");
		}

		// If invalid input previously
		System.out.print("Input number here");
		if (invalidInput) {
			System.out.print(" (previous input invalid): ");
		} else {
			System.out.print(": ");
		}

	}

    	/* Pre: Requires no variables
	 * Post: Returns nothing to main
	 * Desc: Prints to console many empty lines to clear the screen
	 */
	public static void clear() {
		// Clear 40 lines
		for (int i = 0; i < 40; i++) {
			System.out.println("\n");
		}

	}

	/* Pre: Requires no variables
	 * Post: Returns nothing to main
	 * Desc: Pauses the program function to allow the user to choose when to continue (usually after a read block)
	 */
	public static void pause() {
		Scanner input = new Scanner(System.in);
		System.out.print("<continue>");
		input.nextLine();
	}


}



