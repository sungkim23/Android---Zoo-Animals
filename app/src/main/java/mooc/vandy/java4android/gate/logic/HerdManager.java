package mooc.vandy.java4android.gate.logic;

import java.util.Random;

import mooc.vandy.java4android.gate.ui.OutputInterface;
/**
 * This executable Android App will use your Gate class.  We have
 * supplied you will the code necessary to execute as an app.  You
 * must fill in the missing logic below.
 */
public class HerdManager {
    /**
     * Reference to the output.
     */
    private OutputInterface mOut;

    public static final int HERD = 24;
    private int snailsInPen;

    /**
     * Constructor initializes the field.
     */
    public HerdManager(OutputInterface out){
        mOut = out;
    }

    /** Method to set the west gate to enter and the east gate to exit
     *
     */
    public void setGates(Gate west, Gate east) {
        west.open(Gate.IN);
        east.open(Gate.OUT);
    }

    /** Method to simulate herd movements using random numbers
     *
     */
    public void simulateHerd(Gate west, Gate east, Random random) {

        snailsInPen = HERD;
        int gatePicked;

        printLine(snailsInPen);
        mOut.println("");
        // run simulation 10 times
        for (int i = 0; i < 10; i++) {
            // if all snails are in the pen, move some out
            if (snailsInPen == HERD) { moveHerd(east, random, snailsInPen); }
            // if all snails are out of the pen, move some in
            else if (snailsInPen == 0) { moveHerd(west, random, HERD); }
            else {
                // select a gate at random and move some snails, depending on gate direction
                gatePicked = random.nextInt(2);
                if (gatePicked == 0) { moveHerd(west, random, HERD - snailsInPen); }
                else { moveHerd(east, random, snailsInPen); }
            }
            printLine(snailsInPen);
        }
    }


    /** Helper method to print the current herd status
     *
     */
    private void printLine(int numIn) {
        mOut.println("There are currently " + numIn + " snails in the pen and " +
                (HERD - numIn) + " snails in the pasture");
    }


    /** Helper method to move a random number of snails
     */
    private void moveHerd(Gate gate, Random random, int numToMove) {
        int move = random.nextInt(numToMove) + 1;
        snailsInPen += gate.thru(move);
    }
}
