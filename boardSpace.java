public class boardSpace extends Entity {
    // Board space includes the movement number and property
    private int buyValue;
    private int sellValue;
    private int position;
    private String letterPos;
    private int rentValue;
    private boolean ownedStatus;

    /*
     * Pre: Requires nothing
     * Post: Returns nothing
     * Desc: Costructor for a board space
     */
    public boardSpace() {
        super(); // Name
        // Entity value will be movement position number
        buyValue = 0;
        sellValue = 0;
        position = 0;
        letterPos = "";
        rentValue = 0;
        ownedStatus = false;
    }

    /*
     * Pre: Requires all variables for all attributes a property has
     * Post: Returns nothing
     * Desc: Sets info which requires all information to be preset
     */
    public void setInfo(String newName, String newLetterPos, int newPosition, boolean newOwnedStatus, int newBuyValue, int newSellValue ,
            int newRentValue) {
        name = newName;
        // Entity value will be movement position number
        buyValue = newBuyValue;
        sellValue = newSellValue;
        position = newPosition;
        letterPos = newLetterPos;
        rentValue = newRentValue;
        ownedStatus = newOwnedStatus;
    }

    // Set/get name is already made in the entity

    /*
     * Pre: Requires new buy value
     * Post: Returns nothing
     * Desc: Sets the new buy value
     */
    public void setBuyValue(int newBuyValue) {
        buyValue = newBuyValue;
    }

    /*
     * Pre: Requires nothing
     * Post: Returns the buy value value
     * Desc: Sets the the new buy value
     */
    public int getBuyValue() {
        return buyValue;
    }

    /*
     * Pre: Requires new sell value
     * Post: Returns nothing
     * Desc: Sets the new sell value
     */
    public void setSellValue(int newSellValue) {
        sellValue = newSellValue;
    }

    /*
     * Pre: Requires nothing
     * Post: Returns the sell value value
     * Desc: Sets the the new sell value
     */
    public int getSellValue() {
        return sellValue;
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
     * Pre: Requires string
     * Post: Returns nothing
     * Desc: Sets the new name for the letter pos
     */
    public void setLetterPos(String newLetterPos) {
        letterPos = newLetterPos;
    }

    /*
     * Pre: Requires nothing
     * Post: Returns String value
     * Desc: Sets the new name of the position
     */
    public String getLetterPos() {
        return letterPos;
    }

    /*
     * Pre: Requires int
     * Post: Returns nothing
     * Desc: Sets the rent value
     */
    public void setRentValue(int newRentValue) {
        rentValue = newRentValue;
    }

    /*
     * Pre: Requires nothing
     * Post: Returns int value
     * Desc: Sets the amount of rent per space
     */
    public int getRentValue() {
        return rentValue;
    }

    /*
     * Pre: Requires boolean
     * Post: Returns nothing
     * Desc: Sets the status of ownership (true is owned, false is not owned)
     */
    public void setOwnedStatus(boolean newOwnedStatus) {
        ownedStatus = newOwnedStatus;
    }

    /*
     * Pre: Requires nothing
     * Post: Returns boolean value
     * Desc: Sets the amount of rent per space
     */
    public boolean getOwnedStatus() {
        return ownedStatus;
    }

    // board space = movement position space and then the head which holds a
    // property
    // potentially turn into nodes, and then have each space in board set to a node

}
