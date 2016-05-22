package mooc.vandy.java4android.gate.logic;

public class Gate {
    public static final int OUT = -1;
    public static final int IN = 1;

    private int swing = 0;
    private boolean locked = true;


    public boolean setSwing(int direction) {
        if (Math.abs(direction)==1) {
            locked = false;
            swing = direction;
            return true;
        }
        return false;
    }

    public boolean open(int direction) {
        return setSwing(direction);
    }

    public void close() {
        locked = true;
        swing = 0;
    }

    public boolean isLocked() {
        return locked;
    }

    public  int getSwingDirection() {
        return swing;
    }

    public int thru(int count) {
        return count * swing;
    }

    public String toString() {
        if (locked) {
            return "This gate is locked";
        }
        return "This gate is open";
    }

}
