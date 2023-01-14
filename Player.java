
import java.util.*;
public class Player extends Entity{
    Scanner input = new Scanner(System.in);
    private int position;
    private int balance;
    private int jailStatus;
    private int cryptoBot;
    private int playerNum;
    //Has name and entity-value from entity abstract class

    /* Pre: Requires nothing
     * Post: Returns nothing
     * Desc: Constructor for a player
     */
    public Player(){
        super(); //Name
        //ISSUE: Needs inventory of stuff

        position = 23; //Starting point for all
        balance = 1500;
        jailStatus = 0;
        cryptoBot = 0;
        playerNum = 0;
    }

    /* Pre: Requires new positions
     * Post: Returns nothing
     * Desc: Sets the new position
     */
    public void setPosition(int newPosition){
        position = newPosition;
    }

    /* Pre: Requires nothing
     * Post: Returns position value
     * Desc: Sets the new position
     */
    public int getPosition(){
        return position;
    }

    /* Pre: Requires the change in balance
     * Post: Returns nothing
     * Desc: Modifies the balance
     */
    public void modifyBalance(int modifiedBalance){
        balance += modifiedBalance;
    }

    /* Pre: Requires nothing
     * Post: Returns balance value
     * Desc: Sets the new balance
     */
    public int getBalance(){
        return balance;
    }

    /* Pre: Requires new status of jail
     * Post: Returns nothing
     * Desc: Changes the jail status (0: not in jail, 1; 1 day in jail, etc)
     */
    public void setJail(int newJailStatus){
        jailStatus = newJailStatus;
    }

    /* Pre: Requires nothing
     * Post: Returns integer
     * Desc: Returns if player is in jail
     */
    public int getJail(){
        return jailStatus;
    }

        /* Pre: Requires nothing
     * Post: Returns nothing
     * Desc: Adds a cryptobot for the user (chance card)
     */
    public void addCryptoBot(){
        cryptoBot ++;
    }

    /* Pre: Requires nothing
     * Post: Returns integer
     * Desc: Returns number of cryptobots user has
     */
    public int getCryptoBot(){
        return cryptoBot;
    }

    /* Pre: Requires nothing
     * Post: Returns nothing
     * Desc: Sets the user's player number
     */
    public void setPlayerNum(int newPlayerNum){
        playerNum = newPlayerNum;
    }

    /* Pre: Requires nothing
     * Post: Returns integer
     * Desc: Returns user's player num
     */
    public int getPlayerNum(){
        return playerNum;
    }


}
