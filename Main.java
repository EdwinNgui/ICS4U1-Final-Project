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
        // Setup
        // Type "chcp 65001" if the characters appear as question marks

        Scanner input = new Scanner(System.in);
        Random rand = new Random();
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

        // Chance Cards
        Queue chanceArr = new Queue(8); // Array with 8 values
        int[] chanceArrFill = { 0, 1, 2, 3, 4, 5, 6, 7 };

        // Shuffles order using random in preparation for queue usage
        for (int i = 0; i < chanceArrFill.length; i++) {
            // swap two numbers (i marker and random)
            int pick = rand.nextInt(8); // Picks random point to swap with
            int temp = chanceArrFill[pick]; // Temp variable holds random number from array
            chanceArrFill[pick] = chanceArrFill[i];
            chanceArrFill[i] = temp;
        }
        for (int i = 0; i < chanceArrFill.length; i++) {
            chanceArr.enqueue(chanceArrFill[i]);
        }

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

        // Properties (String newName, String newLetterPos, int newPosition, int
        // newVirusType, boolean
        // newOwnedStatus, int newBuyValue, int newSellValue , int newRentValue)
        // If the buyValue is 0, it is not a purchasable property

        board[0][0].setInfo("", "⏭", 0, 0, 0, 0, 0, 0);
        board[0][1].setInfo("Mesla", "🅚", 1, 3, 0, 180, 90, 90);
        board[0][2].setInfo("Macrotough", "🅛", 2, 3, 0, 190, 95, 95);
        board[0][3].setInfo("", "✦", 3, 0, 0, 0, 0, 0);
        board[0][4].setInfo("Waterfox", "🅜", 4, 3, 0, 200, 100, 100);
        board[0][5].setInfo("NoFlix", "🅝", 5, 3, 0, 210, 105, 110);
        board[0][6].setInfo("", "◈", 6, 0, 0, 0, 0, 0);

        board[1][0].setInfo("Samysung", "🅙", 7, 2, 0, 120, 60, 70);
        board[1][1].setInfo("Joogle", "🅞", 8, 4, 0, 280, 140, 120);

        board[2][0].setInfo("Bisco", "🅘", 9, 2, 0, 110, 55, 60);
        board[2][1].setInfo("Pear", "🅟", 10, 4, 0, 290, 145, 140);

        board[3][0].setInfo("", "✦", 11, 0, 0, 0, 0, 0);
        board[3][1].setInfo("", "✦", 12, 0, 0, 0, 0, 0);

        board[4][0].setInfo("Hoom", "🅗", 13, 2, 0, 100, 50, 40);
        board[4][1].setInfo("River of Amazon", "🅠", 14, 5, 0, 340, 170, 180);

        board[5][0].setInfo("Bazer", "🅖", 15, 2, 0, 90, 45, 30);
        board[5][1].setInfo("Armbook", "🅡", 16, 5, 0, 350, 175, 200);

        board[6][0].setInfo("Byzen", "🅕", 17, 2, 0, 80, 45, 25);
        board[6][1].setInfo("Crintel", "🅔", 18, 1, 0, 70, 35, 20);
        board[6][2].setInfo("Pacer", "🅓", 19, 1, 0, 60, 30, 15);
        board[6][3].setInfo("", "✦", 20, 0, 0, 0, 0, 0);
        board[6][4].setInfo("Esus", "🅒", 21, 1, 0, 60, 30, 15);
        board[6][5].setInfo("Bogitek", "🅑", 22, 1, 0, 30, 15, 5);
        board[6][6].setInfo("", "▶", 23, 0, 0, 0, 0, 0);

        // Legend
        // alphabet is property
        // chance to 20 [6][3], 11[3][0] , 3[0][3], 12[3][1] ✦
        // ◈ in jail [0][6]
        // ⏭ go to jail [0][0]

        // 0 sends you to jail at 6
        clear();
        System.out.println("Welcome to the 🅜onopoly!"); // Uses special character as early indicator that UTF-8 is or
                                                         // is not working
        System.out.println("When you see <continue> enter anything to continue");
        pause();
        clear();
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
        System.out.println("We live in a world where everyone has the potential to be a hacker; for good and bad.");
        System.out
                .println("Or perhaps you won't do anything? Yet we continue to live in a world where we need money...");
        pause();
        clear();
        System.out.println(
                "Each square may contain a purchasable scam website (generates income), chance card, or action space.");
        System.out.println(
                "The game ends when all players are out of money or the fixed number of turns finish and the game ends on its own.");
        System.out.println("\t\"Actions which harm society will be watched. Good deeds will be seen.\"");
        System.out.println("\t  < You will make choices. Your choices will impact your end result >");
        pause();
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
            System.out.print("Enter name of Player 1: ");
            stringAns = input.nextLine();
            p1.setName(stringAns);
            p1.setPlayerNum(1);
            System.out.print("Enter name of Player 2: ");
            stringAns = input.nextLine();
            p2.setName(stringAns);
            p2.setPlayerNum(2);
            if (numOfPlayers >= 3) {
                System.out.print("Enter name of Player 3: ");
                stringAns = input.nextLine();
                p3.setName(stringAns);
                p3.setPlayerNum(3);
                if (numOfPlayers >= 4) {
                    System.out.print("Enter name of Player 4: ");
                    stringAns = input.nextLine();
                    p4.setName(stringAns);
                    p4.setPlayerNum(4);
                }
            }
        }

        // } else if (ans == 2) { // Load game (use file reading)

        // }

        // For loop runs for n turns per number of players
        playerTurn = p1; // Starts first move
        clear();
        // Allows player to use the turn-based cycle of the game

        // ISSUE put this back to 10; it was for testing earlier
        for (int k = 0; k < 1; k++) { // 15 rounds
            // Allow the turns to continue and move through
            if (numOfPlayers >= 2) {
                displayBoard(board, p1);
                playTurn(p1, board, p2, p3, p4, chanceArr);
                // Sort requires player's array and array size
                p1.sortOwnedSpace(p1.getOwnedSpace(), p1.getOwnedSpace().length);
                displayBoard(board, p2);
                playTurn(p2, board, p1, p3, p4, chanceArr);
                p2.sortOwnedSpace(p2.getOwnedSpace(), p2.getOwnedSpace().length);
                if (numOfPlayers >= 3) {
                    displayBoard(board, p3);
                    playTurn(p3, board, p1, p2, p4, chanceArr);
                    p3.sortOwnedSpace(p3.getOwnedSpace(), p3.getOwnedSpace().length);
                    if (numOfPlayers >= 4) {
                        displayBoard(board, p4);
                        playTurn(p4, board, p1, p2, p3, chanceArr);
                        p4.sortOwnedSpace(p4.getOwnedSpace(), p4.getOwnedSpace().length);
                    }
                }
            }
        }

        // ISSUE: if player is forfeited, must skip turn
        // ISSUE: if all players except one has forfeited; game ends

        // Game Ending...
        clear();
        // CURRENT

        System.out.println("____________________________________________________");
        System.out.println("The winners...");

        // ISSUE: use sleep function to make it fun to watch

        // Sells all properties
        if (numOfPlayers >= 2) {
            sellAll(board, p1);
            sellAll(board, p2);
            if (numOfPlayers >= 3) {
                sellAll(board, p3);
                if (numOfPlayers >= 4) {
                    sellAll(board, p4);
                }
            }
        }

        //Left-Right, is loser to winner
        Player [] moneyStandings = new Player[numOfPlayers];
        Player [] ethicalStandings = new Player[numOfPlayers];

        //Fills both arrays with the players
        if (numOfPlayers >= 2) {
            moneyStandings[0] = p1;
            moneyStandings[1] = p2;
            ethicalStandings[0] = p1;
            ethicalStandings[1] = p2;
            if (numOfPlayers >= 3) {
                moneyStandings[2] = p3;
                ethicalStandings[2] = p3;
                if (numOfPlayers >= 4) {
                    moneyStandings[3] = p4;
                    ethicalStandings[3] = p4;
                }
            }
        }

        //Sorts money array
        num = moneyStandings.length;
        for (int i = 0; i < (num - 1); i ++){ //Anchors at start
            for (int j = 0; j < (num - i - 1); j ++){ //The running sort
                if (moneyStandings[j].getBalance() > moneyStandings[j + 1].getBalance()){ //If left player has greater balance, swap
                    Player temp = moneyStandings[j];
                    moneyStandings[j] = moneyStandings[j + 1];
                    moneyStandings[j + 1] = temp;
                }
            }
        }

        //Sorts ethical array
        num = ethicalStandings.length;
        for (int i = 0; i < (num - 1); i ++){ //Anchors at start
            for (int j = 0; j < (num - i - 1); j ++){ //The running sort
                if (ethicalStandings[j].getEthicalRating() < ethicalStandings[j + 1].getEthicalRating()){ //If left player has greater balance, swap
                    Player temp = ethicalStandings[j];
                    ethicalStandings[j] = ethicalStandings[j + 1];
                    ethicalStandings[j + 1] = temp;
                }
            }
        }
        
        //Prints results
        clear();
        System.out.println("\t\t\t\t   < Results >");
        System.out.println("\tMost Money: " + moneyStandings[0].getName() + " ($" + moneyStandings[0].getBalance() +")"+ "\t\t|\t Highest Ethical Standing: " + ethicalStandings[0].getName()  + " (" + ethicalStandings[0].getEthicalRating() +")");
        if (numOfPlayers >= 2) {
            System.out.println("\t(2nd) " + moneyStandings[1].getName() + " ($" + moneyStandings[1].getBalance() +")" + "\t\t|\t (2nd) " + ethicalStandings[1].getName()  + " (" + ethicalStandings[1].getEthicalRating() +")");
            if (numOfPlayers >= 3) {
                System.out.println("\t(3rd) " + moneyStandings[2].getName() + " ($" + moneyStandings[2].getBalance() +")" + "\t\t|\t (3rd) " + ethicalStandings[2].getName()  + " (" + ethicalStandings[2].getEthicalRating() +")");
                if (numOfPlayers >= 4) {
                    System.out.println("\t(4th) " + moneyStandings[3].getName() + " ($" + moneyStandings[3].getBalance() +")" + "\t\t|\t (4th) " + ethicalStandings[3].getName()  + " (" + ethicalStandings[3].getEthicalRating() +")");
                }
            }
        }        
    }

    /*
     * Pre: Requires player to move, the up-to-date board, number of spaces to move
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
            } else if (player.getPosition() == 6) { // Corner positions which need manual moving across board
                player.setPosition(player.getPosition() + 2); // Increased by one
            } else if (player.getPosition() == 16) {
                player.setPosition(player.getPosition() + 7);
                // When players hit 23 (right here) they should recieve +200
                player.modifyBalance(200);
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
        } else if (menuNum == 4) {
            System.out.println("(1) Roll Dice");
            System.out.println("(2) Pay $300 to get out");
        } else if (menuNum == 5) {
            System.out.println("(1) Execute a cloud-based cyberattack on a bank for money");
            System.out.println("(2) Leave an anonymous note about their vulnerabilities");
        } else if (menuNum == 6) {
            System.out.println("(1) Sell the stolen data on the black market");
            System.out.println("(2) Delete all copies of it from your system and forget about it");
        } else if (menuNum == 7) {
            System.out.println("(1) Infect tons of computers for cryptobots to mine for money!");
            System.out.println("(2) Don't do it for now");
        } else if (menuNum == 8) {
            System.out.println("(1) Purchase");
            System.out.println("(2) Pass");
        } else if (menuNum == 9) {
            System.out.println("(1) Sell Website");
            System.out.println("(2) Pass");
        } else if (menuNum == 10) {
            System.out.println("(1) View your inventory");
            System.out.println("(2) Search for website ownership");
            System.out.println("(3) Pass");
        } else if (menuNum == 11) {
            System.out.println("(#) Search who owns a website");
            System.out.println("(0) Exit");
        }

        // If invalid input previously
        System.out.print("Input corresponding number here");
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

    /*
     * Pre: Requires player on the turn, 2D array of the map board, the other
     * players, and the chance array
     * Post: Returns nothing to main
     * Desc: Takes the player at hand and allows them to start their turn
     */
    public static void playTurn(Player player, boardSpace[][] board, Player playerA, Player playerB, Player playerC,
            Queue chanceArr) {
        int num = rollDice();
        boolean invalidInput;
        int numAns;
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        System.out.println(player.getName() + " is at: " + player.getPosition());

        // Jail (blackmail) check
        if (player.getJail() >= 1) { // If they are in jail...

            // Decision to roll or pay to get out of jail
            invalidInput = false;
            do {
                displayMenu(4, invalidInput);
                numAns = input.nextInt();
                input.nextLine();
                if (numAns != 1 || numAns != 2) {
                    invalidInput = true;
                }
            } while (numAns != 1 && numAns != 2);

            if (numAns == 1) { // Roll dice
                // Dice rolling must equate to the perfect number.
                int randNum = rand.nextInt(5) + 1; // 1-6
                num = rollDice();
                if (randNum == num) { // If same
                    System.out.println(
                            "Dice matched! You were able to contact customer support and get your account back.");
                    player.setJail(0); // Jail is set to 0
                } else { // Did not succeed in dice roll
                    System.out.println("Bummer. Everything you did, and you still can't get your account.");
                    player.setJail(player.getJail() + 1); // Adds a turn spent in jail
                }
            } else if (numAns == 2) { // Pay to get out of jail
                // Checks if player can afford the $300
                if (player.getBalance() >= 300) {
                    player.modifyBalance(-300); // Deducts 300
                    System.out.println(
                            "After paying them back, they gave your login back to you. Better change your password now!");
                    player.setJail(0); // Jail is set to 0
                } else {
                    System.out.println("You don't have enough funds to pay them! Wait why are they robbing you then?");
                    player.setJail(player.getJail() + 1); // Adds a turn spent in jail
                }
            }

            if (player.getJail() == 3) { // If they have waited their two turns and should be released next turn
                System.out.println(
                        "You have waited two turns and the hackers must have gotten bored. You will recieve your login next turn.");
                player.setJail(0); // Adds a turn spent in jail
            }
        } else {

            System.out.println(" > Roll Dice");
            pause();
            clear();
            movePlayer(player, board, num);
            displayBoard(board, player);

            // Find corresponding board space (by searching thru)
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (player.getPosition() == board[i][j].getPosition()) {
                        System.out.println(player.getName() + " rolled a " + num + " and is now at: "
                                + board[i][j].getLetterPos());
                    }
                }
            }

            if (player.getPosition() == 0) { // Send to jail (location 6)
                System.out.println("Uh Oh! You clicked on a suspicious email link and have become victim to phishing!");
                System.out.println("You have now been blackmailed for two turns.");
                player.setPosition(6); // Moves to jail space of 0
                player.setJail(player.getJail() + 1); // Adds one day to jail
            } else if (player.getPosition() == 3 || player.getPosition() == 11 || player.getPosition() == 12
                    || player.getPosition() == 20) { // If player lands on a chance space (11, 12, 20, 3), then pull a
                                                     // chance card
                System.out.println("Chance Space!");
                System.out.println("\nYou pull a chance card and you got...");
                pause();
                clear();
                System.out.println("____________________________________________________________________\n");

                // Pulls number from front, uses it for switch, then dequeues array before
                // enqueue the num to the back
                num = chanceArr.front();
                // System.out.println(chanceArr.toString());//USE THIS FOR TESTING QUEUE
                // validity

                // Pull a random chance card of 12 (obj) - name (entity), money effect, or
                // player location effect (jail, collect 200)
                switch (num) {
                    case 0:
                        System.out.println("\t\t\t< Chance Card 1 >");
                        System.out
                                .println("You found a cute rainbow USB on the ground and plugged it into your laptop.");
                        System.out.println(
                                "UH OH!!! All your login information has been compromised through a virus (don't do this again).");
                        System.out.println("Now they're blackmailing you for it back");
                        player.setPosition(6);
                        player.setJail(player.getJail() + 1); // Adds one day to jail
                        break;
                    case 1:
                        System.out.println("\t\t\t< Chance Card 2 >");
                        System.out.println("You just bought downloadable RAM off the internet!! What a deal!");
                        System.out.println("Wait nevermind. You just put your credit card into a scam. ");
                        System.out.println("[You lost $200]");
                        player.modifyBalance(-200);
                        break;
                    case 2:
                        System.out.println("\t\t\t< Chance Card 3 >");
                        System.out.println(
                                "You have learned how to execute a cloud-based cyberattack, what do you do?");

                        invalidInput = false;
                        do {
                            displayMenu(5, invalidInput);
                            numAns = input.nextInt();
                            input.nextLine();
                            if (numAns != 1 || numAns != 2) {
                                invalidInput = true;
                            }
                        } while (numAns != 1 && numAns != 2);

                        if (numAns == 1) { // Hack for money
                            player.modifyBalance(400);
                            System.out.println("You got $300 dollars from the attack! A lot less than you expected...");
                            player.modifyEthicalRating(-30);
                        } else if (numAns == 2) { // Not hack for money, boosts ethic standing
                            player.modifyEthicalRating(80);
                            System.out.println("You left the note...You feel good about yourself");
                        }
                        break;
                    case 3:
                        System.out.println("\t\t\t< Chance Card 4 >");
                        System.out.println(
                                "Your data got leaked in a data leak from an online store you love shopping at!");
                        System.out.println(
                                "Looks like you're a victim of \"Credential Reuse\" and a hacker realized you re-use passwords!");
                        System.out.println("You lost $150 (use different and secure passwords!)");
                        player.modifyBalance(-150);
                        break;
                    case 4:
                        System.out.println("\t\t\t< Chance Card 5 >");
                        System.out.println(
                                "When testing out your hacking skills for fun, you end up with stolen data after a long session...");

                        invalidInput = false;
                        do {
                            displayMenu(6, invalidInput);
                            numAns = input.nextInt();
                            input.nextLine();
                            if (numAns != 1 || numAns != 2) {
                                invalidInput = true;
                            }
                        } while (numAns != 1 && numAns != 2);

                        if (numAns == 1) { // Hack for money
                            player.modifyBalance(400);
                            System.out.println("You got $400 dollars for the stolen data!");
                            player.modifyEthicalRating(-40);
                        } else if (numAns == 2) { // Not hack for money, boosts ethic standing
                            player.modifyEthicalRating(85);
                            System.out.println("You deleted the copies...CLICK! Maybe that was the right move?");
                        }

                        break;
                    case 5:
                        System.out.println("\t\t\t< Chance Card 6 >");
                        System.out.println(
                                "You learned how to remotely infect other computers with malware and make them mine crypto!");
                        System.out.println("You also learn it is illegal. What should we do now?");

                        invalidInput = false;
                        do {
                            displayMenu(7, invalidInput);
                            numAns = input.nextInt();
                            input.nextLine();
                            if (numAns != 1 || numAns != 2) {
                                invalidInput = true;
                            }
                        } while (numAns != 1 && numAns != 2);

                        if (numAns == 1) { // Hack for money
                            player.modifyBalance(400);
                            System.out.println("You have a crypto machine making you $50 a turn! (stackable)");
                            player.modifyEthicalRating(-50);
                        } else if (numAns == 2) { // Not hack for money, boosts ethic standing
                            player.modifyEthicalRating(110);
                            System.out.println(
                                    "You chose not to do that. Good on you, you're you'll find another way to find money.");
                        }

                        break;
                    case 6:
                        System.out.println("\t\t\t< Chance Card 7 >");
                        System.out.println("You visited a funny looking link someone random sent you.");
                        System.out.println(
                                "Ooops. Looks like the website link started with \"HTTP://\" instead of \"HTTPS://\"");
                        System.out.println(
                                "There was no secure socket layer, and your cookies (login information) have been\nused for fradulent purchases.");
                        System.out.println("[You lost $200]");
                        player.modifyBalance(-200);
                        break;
                    case 7:
                        System.out.println("\t\t\t< Chance Card 8 >");
                        System.out.println(
                                "You connected to \"Pearson Airport's Free Wifi\" and notice you data has been compromised, almost....");
                        System.out.println(
                                "Good thing you had NordVPN and got two free months on a one year subscription");
                        System.out.println("using code \"IHOPEYOUENJOYEDTHISCODE\"!");
                        break;
                }
                pause();
                chanceArr.dequeue();
                chanceArr.enqueue(num);
            } else { // Normal Properties
                if (player.getPosition() != 6 && player.getPosition() != 23) { // Lands on the jail but not in jail AND
                                                                               // not on collect 200
                    System.out.print("\n\t\t\t< Scam Website");
                    // Find corresponding board space (by searching thru)
                    for (int i = 0; i < board.length; i++) {
                        for (int j = 0; j < board[i].length; j++) {
                            if (player.getPosition() == board[i][j].getPosition()
                                    && board[i][j].getName().equals("") == false) {

                                // Checks if someone owns it
                                if (player.getPlayerNum() == board[i][j].getOwnedStatus()) { // The player owns it
                                    System.out.println(" seems to be yours >");
                                } else if (board[i][j].getOwnedStatus() != 0) { // Owned (0 means no owner)
                                    System.out.println(" has stolen your money >");
                                } else { // Not owned
                                    System.out.println(" available for purchase >");
                                }

                                // Displays the website details
                                System.out.println("\"Company\" Name: " + board[i][j].getName());
                                System.out.print("Virus Type: ");
                                if (board[i][j].getVirusType() == 1) { // File-Infecting Virus
                                    System.out.println("File-Infecting Virus");
                                    System.out.println(
                                            " > Attaches itself to executable programs (.exe) present on your website and overwrites host files");
                                } else if (board[i][j].getVirusType() == 2) { // Web Scripting Virus
                                    System.out.println("Web Scripting Virus");
                                    System.out.println(
                                            " > Disguises itself as images, links, and other media to trick users into downloading malicious files");
                                } else if (board[i][j].getVirusType() == 3) { // Ransomware
                                    System.out.println("Ransomware");
                                    System.out.println(" > Blocks computer access of the user until money is paid");
                                } else if (board[i][j].getVirusType() == 4) { // Resident Virus
                                    System.out.println("Resident Virus");
                                    System.out.println(
                                            " > Stores itself onto computer’s memory and interupts operating system to cause program coruption");
                                } else { // Metamorphic Virus
                                    System.out.println("Metamorphic Virus");
                                    System.out.println(
                                            " > Repeatedly rewrites its appearance and code with each iteration whilst developing itself to reduce detectibility");
                                }
                                if (board[i][j].getOwnedStatus() == 0) { // If not owned, will display purchasing info
                                    System.out.println("   ○ Buy Cost: $" + board[i][j].getBuyValue());
                                    System.out.println("   ○ Sell Price: $" + board[i][j].getSellValue());
                                    System.out.println("   ○ Income from Scams: $" + board[i][j].getRentValue());
                                }

                                // Action Menu
                                if (board[i][j].getOwnedStatus() != 0) { // Owned
                                    // If player owns the property themself
                                    if (player.getPlayerNum() == board[i][j].getOwnedStatus()) {
                                        invalidInput = false;
                                        do {
                                            displayMenu(9, invalidInput);
                                            numAns = input.nextInt();
                                            input.nextLine();
                                            if (numAns != 1 || numAns != 2) {
                                                invalidInput = true;
                                            }
                                        } while (numAns != 1 && numAns != 2);

                                        if (numAns == 1) { // sell website
                                            System.out.println(
                                                    board[i][j].getName() + " has been sold. You have regained $"
                                                            + board[i][j].getSellValue());
                                            player.modifyBalance(board[i][j].getSellValue()); // Awards money to person
                                            player.removeOwnedSpace(board[i][j].getPosition()); // The player will lose
                                                                                                // the marker from
                                                                                                // inventory
                                            board[i][j].setOwnedStatus(0);
                                        } else { // option 2: pass
                                            System.out.println("The website has not been sold and remains yours.");
                                        }

                                    } else { // If player does not own property themself
                                        System.out.println("$" + board[i][j].getRentValue()
                                                + " has been stolen from visiting the malicious site.");
                                        player.modifyBalance(-1 * board[i][j].getRentValue());

                                        // Finds who it belongs to and awards the money
                                        if (playerA.getPlayerNum() == board[i][j].getOwnedStatus()) {
                                            playerA.modifyBalance(board[i][j].getRentValue());
                                            System.out.println(playerA.getName() + " has been awarded $"
                                                    + board[i][j].getRentValue() + ".");
                                        } else if (playerB.getPlayerNum() == board[i][j].getOwnedStatus()) {
                                            playerB.modifyBalance(board[i][j].getRentValue());
                                            System.out.println(playerB.getName() + " has been awarded $"
                                                    + board[i][j].getRentValue() + ".");
                                        } else if (playerC.getPlayerNum() == board[i][j].getOwnedStatus()) {
                                            playerC.modifyBalance(board[i][j].getRentValue());
                                            System.out.println(playerC.getName() + " has been awarded $"
                                                    + board[i][j].getRentValue() + ".");
                                        }
                                        board[i][j].setOwnedStatus(player.getPlayerNum());
                                    }

                                } else { // Not owned

                                    invalidInput = false;
                                    do {
                                        displayMenu(8, invalidInput);
                                        numAns = input.nextInt();
                                        input.nextLine();
                                        if (numAns != 1 || numAns != 2) {
                                            invalidInput = true;
                                        }
                                    } while (numAns != 1 && numAns != 2);

                                    if (numAns == 1 && player.getBalance() >= board[i][j].getBuyValue()) { // Want to
                                                                                                           // buy
                                                                                                           // and can
                                                                                                           // afford
                                        System.out.println(
                                                "You have sucessfully bought and now run " + board[i][j].getName()
                                                        + ".");
                                        player.modifyBalance(-1 * board[i][j].getBuyValue()); // Deducts money
                                        board[i][j].setOwnedStatus(player.getPlayerNum()); // Sets property ownership to
                                                                                           // player
                                        player.addOwnedSpace(board[i][j].getPosition()); // Gives player the owned
                                                                                         // status
                                        player.modifyEthicalRating(-30);
                                        //current
                                    } else { // Pass (clicking option 2, or clicking 1 but not being able to afford)
                                        if (player.getBalance() >= board[i][j].getBuyValue() == false) {
                                            System.out.print("You were unable to afford it and passed on the offer ");
                                        } else {
                                            System.out.println("You passed on the offer");
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (player.getPosition() == 6) {
                    System.out.println("\n\t\t\t< You visited the jail, say \"Hi\" to your friends! >\n");
                } else { // Collect 200 on 23
                    System.out.println("\n\t\t\t< You received $200 from your day job. >");
                }
            }
        }

        player.modifyBalance(player.getCryptoBot() * 50); // $50 per cryptobot user has

        System.out.println("Balance: $" + player.getBalance());
        System.out.println("_______________________________________");
        invalidInput = false;
        pause();
        clear();

        do {
            displayBoard(board, player);
            System.out.println(player.getName() + "'s Post-Roll Actions Menu");
            displayMenu(10, invalidInput);
            numAns = input.nextInt();
            input.nextLine();
            if (numAns != 1 && numAns != 2 && numAns != 3) {
                invalidInput = true;
            }

            if (numAns == 1) { // See own inventory
                clear();
                displayBoard(board, player);
                System.out.println(player.getName() + "'s Owned Websites");
                int[] tempArr = player.getOwnedSpace();
                for (int k = 0; k < tempArr.length; k++) {
                    if (tempArr[k] != -1) {

                        for (int i = 0; i < board.length; i++) {
                            for (int j = 0; j < board[i].length; j++) {
                                if (tempArr[k] == board[i][j].getPosition()) {
                                    System.out.println(board[i][j].getLetterPos() + " : " + board[i][j].getName());
                                    // j = board[i].length; //Ends this for loop to return
                                }
                            }
                        }

                    }
                }
                pause();
                clear();
            } else if (numAns == 2) { // Search for a specific property
                clear();

                invalidInput = false;
                do {

                    displayBoard(board, player);
                    System.out.println("\n\t\t\t< Domain Directory >");
                    System.out.println("\t(1) " + board[6][5].getLetterPos() + " : " + board[6][5].getName()
                            + "\t|\t(10) " + board[0][1].getLetterPos() + " : " + board[0][1].getName());
                    System.out.println("\t(2) " + board[6][4].getLetterPos() + " : " + board[6][4].getName()
                            + "\t\t|\t(11) " + board[0][2].getLetterPos() + " : " + board[0][2].getName());
                    System.out.println("\t(3) " + board[6][2].getLetterPos() + " : " + board[6][2].getName()
                            + "\t\t|\t(12) " + board[0][4].getLetterPos() + " : " + board[0][4].getName());
                    System.out.println("\t(4) " + board[6][1].getLetterPos() + " : " + board[6][1].getName()
                            + "\t|\t(13) " + board[0][5].getLetterPos() + " : " + board[0][5].getName());
                    System.out.println("\t(5) " + board[6][0].getLetterPos() + " : " + board[6][0].getName()
                            + "\t\t|\t(14) " + board[1][1].getLetterPos() + " : " + board[1][1].getName());
                    System.out.println("\t(6) " + board[5][0].getLetterPos() + " : " + board[5][0].getName()
                            + "\t\t|\t(15) " + board[2][1].getLetterPos() + " : " + board[2][1].getName());
                    System.out.println("\t(7) " + board[4][0].getLetterPos() + " : " + board[4][0].getName()
                            + "\t\t|\t(16) " + board[4][1].getLetterPos() + " : " + board[4][1].getName());
                    System.out.println("\t(8) " + board[2][0].getLetterPos() + " : " + board[2][0].getName()
                            + "\t\t|\t(17) " + board[5][1].getLetterPos() + " : " + board[5][1].getName());
                    System.out.println("\t(9) " + board[1][0].getLetterPos() + " : " + board[1][0].getName());

                    displayMenu(11, invalidInput);
                    numAns = input.nextInt();
                    input.nextLine();
                    if (numAns < 0 || numAns > 18) { // Checks if it's invalid number
                        invalidInput = true;
                    } else if (numAns != 0) { // Valid num and not -1; search players

                        // Reassigns numbering locations to match; since the legend and the board spaces
                        // have inconsistent numberings (since board used irregular array and this
                        // counts by alphabet)
                        switch (numAns) {
                            case 1:
                                numAns = 22;
                                break;
                            case 2:
                                numAns = 21;
                                break;
                            case 3:
                                numAns = 19;
                                break;
                            case 4:
                                numAns = 18;
                                break;
                            case 5:
                                numAns = 17;
                                break;
                            case 6:
                                numAns = 15;
                                break;
                            case 7:
                                numAns = 13;
                                break;
                            case 8:
                                numAns = 9;
                                break;
                            case 9:
                                numAns = 7;
                                break;
                            case 10:
                                numAns = 1;
                                break;
                            case 11:
                                numAns = 2;
                                break;
                            case 12:
                                numAns = 4;
                                break;
                            case 13:
                                numAns = 5;
                                break;
                            case 14:
                                numAns = 8;
                                break;
                            case 15:
                                numAns = 10;
                                break;
                            case 16:
                                numAns = 14;
                                break;
                            case 17:
                                numAns = 16;
                                break;
                        }

                        // Searches all players
                        // Searches for numAns, starts search at 0
                        if (player.searchOwnedSpace(numAns, 0)) {
                            ; // With self
                            System.out.println("\t> " + player.getName() + " is the owner of this domain <");
                        } else if (playerA.searchOwnedSpace(numAns, 0)) {
                            System.out.println("\t> " + playerA.getName() + " is the owner of this domain <");
                        } else if (playerB.searchOwnedSpace(numAns, 0)) {
                            System.out.println("\t> " + playerB.getName() + " is the owner of this domain <");
                        } else if (playerC.searchOwnedSpace(numAns, 0)) {
                            System.out.println("\t> " + playerC.getName() + " is the owner of this domain <");
                        } else {
                            System.out.println("\t> No one owns this domain as of right now <");
                        }
                        pause();
                        clear();

                    }

                } while (numAns != 0);
                clear();
            }

        } while (numAns != 3);

        // ISSUE: Broke check; if broke must give menu to sell or forfeit

        clear();
    }

    /*
     * Pre: Requires the board array and the player (to take location)
     * Post: Returns nothing to main
     * Desc: Displays the board with player location
     */
    public static void displayBoard(boardSpace[][] board, Player player) {
        String[] legend = { "_______________________________________", "|\t\tLegend                 |",
                "| {Letter} - Scam Website for Income   |", "| ⏭ - Get Blackmailed                  |",
                "| ◈ - Locked out of Login (Jail)       |", "| ▶ - Start: Collect $200              |",
                "| # - Your Player                      |" };

        // Position viewer for all spaces
        for (int i = 0; i < board.length; i++) {
            // System.out.print("\t");
            for (int j = 0; j < board[i].length; j++) {
                if (player.getPosition() == board[i][j].getPosition()) {
                    System.out.print("# ");
                } else {
                    System.out.print(board[i][j].getLetterPos() + " ");
                }
                // If on left side in middle
                if ((board[i][j].getPosition() >= 7 && board[i][j].getPosition() <= 15)
                        && ((board[i][j].getPosition() % 2) == 1)) {
                    System.out.print("\t        ");
                }
            }
            System.out.println("\t" + legend[i]);
        }
        System.out.println("____________________________________________________________________");
    }

    /*
     * Pre: Requires the board array and the player (to take location)
     * Post: Returns nothing to main
     * Desc: Sells all properties of the player, used in the ending
     */
    public static void sellAll(boardSpace[][] board, Player player){
        int[] tempArr = player.getOwnedSpace();
            //Cycles thru all indexes of player's owned spaces
            for (int k = 0; k < tempArr.length; k ++){
                if (tempArr[k] != -1){ //Real property and not a blank space (-1)
                    //Cycles thru board
                    for (int i = 0; i < board.length; i++) {
                        for (int j = 0; j < board[i].length; j++) {
                            //If the owned number is same as board position
                            if (tempArr[k] == board[i][j].getPosition()){
                                //Award the money to player and sell
                                player.modifyBalance(board[i][j].getSellValue());
                            }
                        }
                    }
                }
            }
    }
}
