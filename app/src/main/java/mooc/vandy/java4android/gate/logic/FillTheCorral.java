package mooc.vandy.java4android.gate.logic;

import java.io.PrintStream;
import java.util.Random;

import mooc.vandy.java4android.gate.ui.OutputInterface;

/**
 * This executable Android app will use your Gate.java class.  We have
 * supplied you with the code necessary to execute as an app.  You
 * must fill in the logic.
 */
public class FillTheCorral {
    private OutputInterface mOut;

    // set initial number of snails out to pasture to 5
    private int outToPasture = 5;

    FillTheCorral(OutputInterface out) { mOut = out; }

    public void setCorralGates(Gate[] corral, Random random) {

        mOut.println("Initial gate setup:");
        // get random gate positions until at least one is allowing entry
        while (true) {
            // randomly set the gates to IN, OUT or 0
            for (Gate g : corral) { g.setSwing(random.nextInt(3) - 1); }
            // if at least 1 gate is open IN, it is a valid setup, else try another random setup
            if (anyCorralAvailable(corral)) { break; }
        }
        printLines(corral);
    }
    /** Helper method to print the status of all gates in the corral
     */
    private void printLines(Gate[] corral) {

        String s = "";
        for (int i = 0; i < corral.length; i++) {
            s = "Gate ";
            if (corral[i].getSwingDirection() == Gate.IN) { s = s + i + ": " + corral[i].toString() + " and swings to enter the pen only."; }
            else { s = s + i + ": " + corral[i].toString() + " and swings to exit the pen only."; }
            mOut.println(s);
        }
    }

    /** Method to determine whether at least one gate allows entry to the corral
     *
     */

    public boolean anyCorralAvailable(Gate[] corral) {

        // if any of the gates has swing direction = 1, it is a valid setup
        for (Gate g : corral) { if (g.getSwingDirection() == 1) return true; }

        return false;
    }

    /** Method to run a simulation of the corral, until all snails are penned
     *
     */
    public void corralSnails(Gate[] corral, Random random) {

        // runs the simulation

        int attempts = 0;
        do {
            // get a random number of snails to move and a random gate
            int move =  random.nextInt(outToPasture) + 1;
            int chosenGate = random.nextInt(corral.length);
            Gate selectedGate = corral[chosenGate];
            // print the possible move and the gate
            printLine(move, selectedGate, chosenGate);
            // calculate the new number of snails out to pasture and print
            outToPasture -= selectedGate.thru(move);
            printLine(outToPasture);
            // keep track of the number of simulations
            attempts++;

        } while (outToPasture != 0);            // stop when all snails are penned
        mOut.println("It took " + attempts + " attempts to coral all of the snails.");
    }

    /** Helper method to print the line of a proposed move
     *
     */
    private void printLine(int move, Gate selectedGate, int gateNumber) {
        String descriptor;
        if (selectedGate.isLocked()) { descriptor = "locked"; }
        else if (selectedGate.getSwingDirection() == Gate.IN) { descriptor = "entry"; }
        else { descriptor = "exit"; }
        mOut.println(move + " snails are trying to move through " +
                descriptor + " coral " + gateNumber + ".");
    }

    /** Helper method to print the current pasture status
     *
     */
    private void printLine(int outToPasture) {
        mOut.println("There are currently " + outToPasture + " snails still in the pasture.");
    }


}
