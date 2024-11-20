import java.awt.Canvas;
import java.awt.Graphics;

/**
 * 2D array of cells representing a rectangular grid.
 * 
 * @author Chris Mayfield
 * @version 7.1.0
 */
public class GridCanvas extends Canvas {

    /** Cells stored in row-major order. */
    private Cell[][] array;

    /**
     * Constructs a grid of given size.
     * 
     * @param rows number of rows
     * @param cols number of columns
     * @param size pixels per cell
     */
    public GridCanvas(int rows, int cols, int size) {

        // build 2D array of cells
        array = new Cell[rows][cols];
        for (int r = 0; r < rows; r++) {
            int y = r * size;
            for (int c = 0; c < cols; c++) {
                int x = c * size;
                array[r][c] = new Cell(x, y, size);
            }
        }

        // set the canvas size
        setSize(cols * size, rows * size);
    }

    /**
     * Gets the number of rows.
     * 
     * @return number of rows
     */
    public int numRows() {
        return array.length;
    }

    /**
     * Gets the number of columns.
     * 
     * @return number of columns
     */
    public int numCols() {
        return array[0].length;
    }

    /**
     * Gets the cell at index (r, c).
     * 
     * @param r row index
     * @param c column index
     * @return the cell
     */
    public Cell getCell(int r, int c) {
        return array[r][c];
    }

    /**
     * Convenience method that turns on the cell at (r, c).
     * 
     * @param r row index
     * @param c column index
     */
    public void turnOn(int r, int c) {
        array[r][c].turnOn();
    }

    
     /** Returns 1 if the cell at (r, c) exists and is on. Returns 0 if the cell
     * doesn't exist or is off.
     * 
     * @param r row index
     * @param c column index
     * @return 1 or 0*/
    
    public int test(int r, int c) {
        try {
            if (array[r][c].isOn()) {
                return 1;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // cell doesn't exist
        }
        return 0;
    }
    
    /**************Exercise 15.2---------Updated test method-------------****************/

    // Updating the test method....
    
    /**
    * Returns 1 if the cell at (r, c) exists and is on. Returns 0 if the cell
    * doesn't exist or is off. Wraps the coordinates around the grid if they are
    * too low or too high.
    * 
    * @param row  row index
    * @param col column index
    * @return 1 or 0
    */
    public int testWrappedGrid(int row, int col){
        // wrap the row index around the grid
        if(row < 0){
            row = numRows()-1; // wrap to the last row
        }
        else if (row >= numRows()) {
            row = 0; // wrap to the first row
        }
            
        // wrap the column index around the grid
        if(col < 0){
            col = numCols()-1; // wrap to the last column
        }
        else if (col >= numCols()) {
            col = 0; // wrap to the first column
        }
        // Test the wrapped conditions
        try {
            if (array[row][col].isOn()) {
                return 1;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // cell doesn't exist
        }
        return 0;
        
    }
    /**
     * Draws the grid, cell by cell.
     * 
     * @param g graphics context
     */
    public void draw(Graphics g) {
        for (Cell[] row : array) {
            for (Cell cell : row) {
                cell.draw(g);
            }
        }
    }

    /**
     * Paints the grid on the screen.
     * 
     * @param g graphics context
     */
    public void paint(Graphics g) {
        draw(g);
    }

    /**
     * Overriding this method helps the simulation run more smoothly. Normally
     * the Canvas is cleared before painting, but there is no need to clear it
     * since the paint method draws the entire grid.
     * 
     * @param g graphics context
     */
    public void update(Graphics g) {
        draw(g);
    }


/*---------------Exercise 15.1-----------Counting the total number of cells on------- */


    /**
 * Counts the total number of cells that are on.
 * 
 * @return total number of cells that are on
 */


 public int countOn() {
    int count = 0;
    // Use for loop to iterate over the grid
    for (int row = 0; row < array.length; row = row + 1) {
        for (int col = 0; col < array[row].length; col = col+1) {
            if (array[row][col].isOn()) {
                count++; // Increment the count if the cell is on
            }
        }
    }
    return count; // Return the total number of on cells
}

public class Main {
    public static void main(String[] args) {
        // Create a GridCanvas object with 5 rows, 5 columns, and each cell size of 20 pixels
        GridCanvas grid = new GridCanvas(5, 5, 20);
        //GridCanvas grid1 = new GridCanvas(5, 5, 20);
        // Turn on a few cells
        grid.turnOn(0, 0);  // Turn on cell at (0, 0)
        grid.turnOn(1, 1);  // Turn on cell at (1, 1)
        grid.turnOn(2, 2);  // Turn on cell at (2, 2)
        grid.turnOn(3, 3);  // Turn on cell at (3, 3)
        grid.turnOn(4, 4);  // Turn on cell at (4, 4)

        // Use the countOn method to count the number of cells that are "on"
        int onCells = grid.countOn();

        // Print the result
        System.out.println("Number of cells that are on: " + onCells);
    

     
    // Test wrapping behavior
    System.out.println("Test (0,0): " + grid.testWrappedGrid(0, 0)); // Should be 1 (on)
    System.out.println("Test (5,5): " + grid.testWrappedGrid(5, 5)); // Should wrap to (0,0), expect 1
    System.out.println("Test (-1,-1): " + grid.testWrappedGrid(-1, -1)); // Should wrap to (4,4), expect 1
    System.out.println("Test (3,3): " + grid.testWrappedGrid(3, 3)); // Should be 1(on)
    System.out.println("Test (4,4): " + grid.testWrappedGrid(4, 4)); // Should be 1 (on)

        

        
}
}// end-Main


}// end Class
