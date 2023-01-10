
import java.util.*;
public class Player extends Entity{
    Scanner input = new Scanner(System.in);
    private int position;
    //Has name and entity-value from entity abstract class

    /* Pre: Requires nothing
     * Post: Returns nothing
     * Desc: Constructor for a player
     */
    public Player(){
        super(); //Name
        //ISSUE: Needs inventory of stuff

        position = 23; //Starting point for all
        

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


}
