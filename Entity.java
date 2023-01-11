public abstract class Entity {
    public static String name;

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