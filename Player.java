
import java.util.*;

public class Player extends Entity {
    Scanner input = new Scanner(System.in);
    private int position;
    private int balance;
    private int jailStatus;
    private int cryptoBot;
    private int playerNum;
    private int[] ownedSpace = new int[17];

    // CURRENT: each player has an array of 17 spaces total (prefilled with -1)
    // will display only non -1, letterpos + company name
    // when owned, will look to write as early as possible (but skip the non -1)

    // Has name and entity-value from entity abstract class

    /*
     * Pre: Requires nothing
     * Post: Returns nothing
     * Desc: Constructor for a player
     */
    public Player() {
        super(); // Name
        // ISSUE: Needs inventory of stuff

        position = 23; // Starting point for all
        balance = 1500;
        jailStatus = 0;
        cryptoBot = 0;
        playerNum = 0;

        // Presets all spaces to -1 (means non existent)
        for (int i = 0; i < ownedSpace.length; i++) {
            ownedSpace[i] = -1;
        }
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



}