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
        int [][] board = new int [7][7];


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
        if (ans == 1){ //New game
            //Setup variables intialized here

        }else if (ans == 2){ //Load game (use file reading)

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



