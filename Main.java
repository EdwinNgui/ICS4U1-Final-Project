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
        String stringAns; // For names
        int num = 0;
        int numOfPlayers;
        Player playerTurn = new Player();
        // Supports up to 4 players,
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();
        Player p4 = new Player();

        // 7 by 7 board, 2 layers, the top (1) is the player movement, the bottom (2) is
        // where the properties are held
        boardSpace[][] board = new boardSpace[7][];
        // Makes 7x7 hollow
        board[0] = new boardSpace[7];
        board[1] = new boardSpace[2];
        board[2] = new boardSpace[2];
        board[3] = new boardSpace[2];
        board[4] = new boardSpace[2];
        board[5] = new boardSpace[2];
        board[6] = new boardSpace[7];

        // Initializes each space there
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new boardSpace();
            }
        }

        // Position set for all spaces
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].setPosition(num);
                num++;
            }
        }

        System.out.println("Welcome to the Monopoly!");
        System.out.println("When you see <continue> enter anything to continue");
        pause();
        // Displays new or load game
        invalidInput = false;
        do {
            displayMenu(1, invalidInput);
            ans = input.nextInt();
            input.nextLine();
            if (ans != 1 || ans != 2) {
                invalidInput = true;
            }
        } while (ans != 1 && ans != 2);

        // New or load (commentedOut)
        // if (ans == 1) { // New game: Setup variables intialized here
        clear();
        // Sets number of players
        invalidInput = false;
        do {
            displayMenu(3, invalidInput);
            numOfPlayers = input.nextInt();
            input.nextLine();
            if (ans != 2 || ans != 3 || ans != 4) {
                invalidInput = true;
            }
        } while (numOfPlayers != 2 && numOfPlayers != 3 && numOfPlayers != 4);

        if (numOfPlayers >= 2) {
            System.out.println("Enter name of Player 1: ");
            stringAns = input.nextLine();
            p1.setName(stringAns);
            System.out.println("Enter name of Player 2: ");
            stringAns = input.nextLine();
            p2.setName(stringAns);
            if (numOfPlayers >= 3) {
                System.out.println("Enter name of Player 3: ");
                stringAns = input.nextLine();
                p3.setName(stringAns);
                if (numOfPlayers >= 4) {
                    System.out.println("Enter name of Player 4: ");
                    stringAns = input.nextLine();
                    p4.setName(stringAns);
                }
            }
        }

        // } else if (ans == 2) { // Load game (use file reading)

        // }

//START of turn
//For loop runs for 5 turns per number of players
playerTurn = p1; //Starts first move
for (int k = 0; k < numOfPlayers*5; k ++){

        // Position viewer for all spaces
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j].getPosition() + " ");
                // If on left side in middle
                if ((board[i][j].getPosition() >= 7 && board[i][j].getPosition() <= 15)
                        && ((board[i][j].getPosition() % 2) == 1)) {
                    System.out.print("\t   ");
                }
            }
            System.out.println("");
        }

        System.out.println(playerTurn.getName() + " is at " + playerTurn.getPosition());
        System.out.println("insert roll dice button.");
        System.out.println(playerTurn.getName() + " is now at " + playerTurn.getPosition());
        pause();

        //ISSUE: moves to next turn just fine but the player turn name never gets replaced or reset

        //Move to next turn
        if (playerTurn == p1){
            playerTurn = p2;
            System.out.println("ab");
        }else if (playerTurn == p2){
            //Checks to move next or reset cycle
            System.out.println("abc");
            if (numOfPlayers > 2){
                playerTurn = p3;
            }else{
                playerTurn = p1;
            }
        }else if (playerTurn == p3){
            System.out.println("dab");
            //Checks to move next or reset cycle
            if (numOfPlayers > 3){
                playerTurn = p4;
            }else{
                playerTurn = p1;
            }
        }else if (playerTurn == p4){
            playerTurn = p1;
            System.out.println("abafsdfgeds");
        }else{
            System.err.println("uh oh issueuas");
        }
        // ISSUE: Sets the properties to their respective locations
    }
//END of turn

    }

    /*
     * Pre: Requires player to move, the current board, number of spaces to move
     * Post: Returns the player with their updated position
     * Desc: Moves the player along the board clockwise
     */
    public static void movePlayer(Player player, boardSpace[][] board, int moveSpaces) {
        // Go for the number of spaces the player should move
        for (int i = 0; i < moveSpaces; i++) {
            if (player.getPosition() >= 0 && player.getPosition() <= 5) { // Position 0-5 (6 is a corner)
                player.setPosition(player.getPosition() + 1); // Increased by one
            } else if (player.getPosition() >= 18 && player.getPosition() <= 23) { // Position 18-23 (moves counter
                                                                                   // clockwise)
                player.setPosition(player.getPosition() - 1);
            } else if (player.getPosition() >= 8 && player.getPosition() <= 15) { // Left or right side
                if (player.getPosition() % 2 == 0) { // if even
                    player.setPosition(player.getPosition() + 2);
                } else { // if odd
                    player.setPosition(player.getPosition() - 2);
                }
            } else if (player.getPosition() == 6) { // Position 6 (move to 8)
                player.setPosition(player.getPosition() + 2); // Increased by one
            } else if (player.getPosition() == 16) {
                player.setPosition(player.getPosition() + 7);
            } else if (player.getPosition() == 17) {
                player.setPosition(player.getPosition() - 2);
            } else if (player.getPosition() == 7) {
                player.setPosition(player.getPosition() - 7);
            }
        }
    }

    // Move player to the new value, then have a search thru all boardSpaces to set
    // player position (coded as nodes?)
    // if player there, saved by knowing that the player is at that spot thru value

    // player position should move to a new value, then it should search thru all
    // board spaces until it finds its match and move there. or
    // or just code the player to have 2 values for position, then shift the values,
    // accordingly

    // writing a number for the physical space, players will imagine it

    /*
     * Pre: Requires the menu number and if there's an invalid input
     * Post: Returns nothing to main
     * Desc: Displays the menu based on which is called and if there is an invalid
     * previous input
     */
    public static void displayMenu(int menuNum, boolean invalidInput) {
        if (menuNum == 1) {
            System.out.println("\t...Main Menu...");
            System.out.println("(1) New Game");
            System.out.println("(2) Load Game");
        } else if (menuNum == 2) { // Yes-No Prompt
            System.out.println("(1) Yes");
            System.out.println("(2) No");
        } else if (menuNum == 3) { // How many players
            System.out.println("How many players will be playing  (2-4)?");
        }

        // If invalid input previously
        System.out.print("Input number here");
        if (invalidInput) {
            System.out.print(" (previous input invalid): ");
        } else {
            System.out.print(": ");
        }

    }

    /*
     * Pre: Requires no variables
     * Post: Returns nothing to main
     * Desc: Prints to console many empty lines to clear the screen
     */
    public static void clear() {
        // Clear 40 lines
        for (int i = 0; i < 40; i++) {
            System.out.println("\n");
        }

    }

    /*
     * Pre: Requires no variables
     * Post: Returns nothing to main
     * Desc: Pauses the program function to allow the user to choose when to
     * continue (usually after a read block)
     */
    public static void pause() {
        Scanner input = new Scanner(System.in);
        System.out.print("<continue>");
        input.nextLine();
    }

    /*
     * Pre: Requires no variables
     * Post: Returns int value to main
     * Desc: Makes random dice roll value
     */
    public static int rollDice() {
        Random rand = new Random();
        int num = rand.nextInt(6) + 1; // 1-6 spaces

        return num;

    }

}
