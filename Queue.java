/* Name: Edwin Ngui
 * Course: ICS4U1
 * Instructor: E, Katsman
 * Date Started: Jan/9/2023
 * Date Due: Jan/23/2023
 * Desc: Queue class which allows for an instance of a queue to occur (first in, first out). Used for the chance cards
 */
public class Queue {

    private int[] data;
    private int back;

    public Queue(int size) {
        data = new int[size];
        back = 0;
    }

    /*
     * Pre: Requires int (of new number)
     * Post: Returns nothing
     * Desc: Adds to the back
     */
    public void enqueue(int n) {
        data[back] = n;
        back++;
    }

    /*
     * Pre: Requires nothing
     * Post: Returns nothing
     * Desc: Shuffle everything down
     */
    public void dequeue() {
        for (int i = 0; i < data.length - 1; i++) {
            data[i] = data[i + 1];
        }
        back--;
    }

    /*
     * Pre: Requires nothing
     * Post: Returns integer
     * Desc: Returns the front number (use this before dequeue)
     */
    public int front() {
		return data[0];
	}

    /*
     * Pre: Requires nothing
     * Post: Returns String
     * Desc: Returns the entire array for viewing (mainly for testing)
     */
    public String toString() {
		String fullArr = "";
		
		for (int i = 0; i < back; i ++) {
			fullArr += (" " + data[i]);
		}
		
		return fullArr;
		
	}

        /*
     * Pre: Requires nothing
     * Post: Returns String
     * Desc: Returns the entire array for viewing (mainly for testing)
     */
    public String CSVtoString() {
		String fullArr = "";
		
		for (int i = 0; i < back; i ++) {
			fullArr += (data[i]+",");
		}
		
		return fullArr;
		
	}

}