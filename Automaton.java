

/**
 * Exercise 16.1 The last section of this chapter introduced Automaton as an
    abstract class and rewrote Conway as a subclass of Automaton. Now it's your
 *  turn: rewrite Langton as a subclass of Automaton, removing the code that's
    no longer needed.
 */

 import javax.swing.JFrame;

 /**
  * A cellular automaton consists of a grid of cells and follows a set of rules
  * that update the grid over time.
  */
 public abstract class Automaton {
 
     protected GridCanvas grid;
 
     /**
      * Applies rules to update the grid.
      */
     public abstract void update();
 
     /**
      * The simulation loop.
      * 
      * @param rate frames per second
      */
     private void mainloop(int rate) {
         while (true) {
 
             // update the drawing
             this.update();
             grid.repaint();
 
             // delay the simulation
             try {
                 Thread.sleep(1000 / rate);
             } catch (InterruptedException e) {
                 // do nothing
             }
         }
     }
 
     /**
      * Creates a JFrame and runs the automaton.
      * 
      * @param title the frame title
      * @param rate frames per second
      */
     public void run(String title, int rate) {
         // set up the window frame
         JFrame frame = new JFrame(title);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setResizable(false);
         frame.add(this.grid);
         frame.pack();
         frame.setVisible(true);
         this.mainloop(rate);
     }
 
 }