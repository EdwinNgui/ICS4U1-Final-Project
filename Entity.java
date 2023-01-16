/* Name: Edwin Ngui
 * Course: ICS4U1
 * Instructor: E, Katsman
 * Date Started: Jan/9/2023
 * Date Due: Jan/23/2023
 * Desc: Entity class which allows the board space and player object classes to inherit from. Provides the basis for any object in the game
 */

public abstract class Entity {
    public String name;

    /*
     * Pre: Requires nothing
     * Post: Returns nothing
     * Desc: Constructor for basic variables for a value and string name
     */
    public Entity() {
        name = "";
    }

    /*
     * Pre: Requires nothing
     * Post: Returns nothing
     * Desc: Sets the new name
     */
    public void setName(String newName) {
        name = newName;
    }

    /*
     * Pre: Requires nothing
     * Post: Returns string value of name
     * Desc: Returns the name
     */
    public String getName() {
        return name;
    }

}