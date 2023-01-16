/* Name: Edwin Ngui
 * Course: ICS4U1
 * Instructor: E, Katsman
 * Date Started: Jan/9/2023
 * Date Due: Jan/23/2023
 * Desc: Player object class to create an instance of a player inside the game. Holds numerous pieces of information including position.
 */

import java.util.*;

public class Player extends Entity {
    Scanner input = new Scanner(System.in);
    private int position;
    private int balance;
    private int jailStatus;
    private int cryptoBot;
    private int playerNum;
    private int[] ownedSpace = new int[17];
    private int ethicalRating;
    private boolean inGame;

    // Has name and entity-value from entity abstract class

    /*
     * Pre: Requires nothing
     * Post: Returns nothing
     * Desc: Constructor for a player
     */
    public Player() {
        super(); // Name
        position = 23; // Starting point for all
        balance = 1500;
        jailStatus = 0;
        cryptoBot = 0;
        playerNum = 0;
        ethicalRating = 0;
        inGame = true;

        // Presets all spaces to -1 (means non existent)
        for (int i = 0; i < ownedSpace.length; i++) {
            ownedSpace[i] = -1;
        }
    }

        /*
     * Pre: Requires all variables of user
     * Post: Returns nothing
     * Desc: Constructor for a player with known information
     */
    public Player(String newName, int newPosition, int newBalance, int newJailStatus, int newCryptoBot, int newPlayerNum, int newEthicalRating, boolean newInGame, int [] newOwnedSpace) {
        super(); // Name
        super.setName(newName);
        position = newPosition;
        balance = newBalance;
        jailStatus = newJailStatus;
        cryptoBot = newCryptoBot;
        playerNum = newPlayerNum;
        ethicalRating = newEthicalRating;
        inGame = newInGame;
        ownedSpace = newOwnedSpace;
    }

    /*
     * Pre: Requires new positions
     * Post: Returns nothing
     * Desc: Sets the new position
     */
    public void setPosition(int newPosition) {
        position = newPosition;
    }

    /*
     * Pre: Requires nothing
     * Post: Returns position value
     * Desc: Sets the new position
     */
    public int getPosition() {
        return position;
    }

    /*
     * Pre: Requires the change in balance
     * Post: Returns nothing
     * Desc: Modifies the balance
     */
    public void modifyBalance(int modifiedBalance) {
        balance += modifiedBalance;
    }

    /*
     * Pre: Requires nothing
     * Post: Returns balance value
     * Desc: Sets the new balance
     */
    public int getBalance() {
        return balance;
    }

    /*
     * Pre: Requires new status of jail
     * Post: Returns nothing
     * Desc: Changes the jail status (0: not in jail, 1; 1 day in jail, etc)
     */
    public void setJail(int newJailStatus) {
        jailStatus = newJailStatus;
    }

    /*
     * Pre: Requires nothing
     * Post: Returns integer
     * Desc: Returns if player is in jail
     */
    public int getJail() {
        return jailStatus;
    }

    /*
     * Pre: Requires nothing
     * Post: Returns nothing
     * Desc: Adds a cryptobot for the user (chance card)
     */
    public void addCryptoBot() {
        cryptoBot++;
    }

    /*
     * Pre: Requires nothing
     * Post: Returns integer
     * Desc: Returns number of cryptobots user has
     */
    public int getCryptoBot() {
        return cryptoBot;
    }

    /*
     * Pre: Requires nothing
     * Post: Returns nothing
     * Desc: Sets the user's player number
     */
    public void setPlayerNum(int newPlayerNum) {
        playerNum = newPlayerNum;
    }

    /*
     * Pre: Requires nothing
     * Post: Returns integer
     * Desc: Returns user's player num
     */
    public int getPlayerNum() {
        return playerNum;
    }

    /*
     * Pre: Requires int (of the new space the player has gotten)
     * Post: Returns nothing
     * Desc: Adds to the player's inventory that they own this corresponding space
     */
    public void addOwnedSpace(int n) {
        boolean looking = true;
        int i = 0;

        do {
            if (ownedSpace[i] == -1) { // If empty
                ownedSpace[i] = n;
                looking = false;
            } else {
                i++;
            }
        } while (looking);
    }

    /*
     * Pre: Requires nothing
     * Post: Returns nothing
     * Desc: Sets the user's player number
     */
    public void removeOwnedSpace(int n) {
        boolean looking = true;
        int i = 0;
        do {
            if (ownedSpace[i] == n) { // When it is found, just set to NO SPACE OWNED
                ownedSpace[i] = -1;
                looking = false;
            } // No else condition needed, the check of ownership occurs earlier
        } while (looking);
    }

    /*
     * Pre: Requires int space to look for and the starting point to search (always
     * set to 0 on first call)
     * Post: Returns boolean
     * Desc: Used for searching if it exists with the player or not; Uses RECURSION here
     */
    public boolean searchOwnedSpace(int n, int start) {
        // If it detects it, send true
        if (ownedSpace[start] == n) {
            return true;
        } else {
            if (start == 16) { // If it reaches the end; index 16 is the 17th space bc it starts 0
                return false;
            } else {
                return searchOwnedSpace(n, start + 1);
            }
        }
    }

    /*
     * Pre: Requires nothing
     * Post: Returns int array
     * Desc: Returns the array of spaces the player owns
     */
    public int [] getOwnedSpace() {
        return ownedSpace;
    }


    /*
     * Pre: Requires the array (int) and the array size
     * Post: Returns nothing
     * Desc: Recursively bubble sorts the player's inventory to reduce searching times in the recursive linear search
     */
    public void sortOwnedSpace(int [] arr, int arrSize){
        //Uses player's array; ownedSpace

        //Finished passing sufficiently
        if (arrSize == 1){
            return;
        }

        //Run thru each index and do the swaps needed
        for (int i = 0; i < (arrSize - 1); i ++){
            if (arr[i] > arr[i + 1]){ //If left bigger than right, swap
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }

        //When pass done, go check again
        sortOwnedSpace(arr, arrSize -1);
    }


    /*
     * Pre: Requires integer
     * Post: Returns nothing
     * Desc: Modifies the user's ethical rating
     */
    public void modifyEthicalRating(int newEthicalRating) {
        ethicalRating += newEthicalRating;
    }

    /*
     * Pre: Requires nothing
     * Post: Returns integer
     * Desc: Returns user's ethical rating
     */
    public int getEthicalRating() {
        return ethicalRating;
    }

        /*
     * Pre: Requires the boolean status
     * Post: Returns nothing
     * Desc: Sets if user is in game or not
     */
    public void setInGame(boolean newInGame) {
        inGame = newInGame;
    }

    /*
     * Pre: Requires nothing
     * Post: Returns boolean
     * Desc: Returns if user is in game or not
     */
    public boolean getInGame() {
        return inGame;
    }

    /*
     * Pre: Requires nothing
     * Post: Returns string
     * Desc: Prints out all user information as a string, mainly for the saving
     */
    public String toString(){
        String info = "";
        info += (name + "," + position + "," + jailStatus + "," + cryptoBot + "," + playerNum + "," + ethicalRating + "," + inGame + "/");
        
        for (int i = 0; i < ownedSpace.length; i ++){
            info += (ownedSpace[i] + ",");
        }   

        return info;
    }

}