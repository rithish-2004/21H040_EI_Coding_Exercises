package Exercise1_Design_Patterns.Behavioural.Command_Pattern;

public class DoorLockCommand implements Command {
    private boolean locked;
    public static boolean currentLockState = false; // Default to UNLOCKED

    public DoorLockCommand(boolean locked) {
        this.locked = locked;
        currentLockState = locked;
    }

    public boolean isLocked() {
        return currentLockState;
    }

    @Override
    public void execute() {
        if (locked) {
            System.out.println("Door is LOCKED");
        } else {
            System.out.println("Door is UNLOCKED");
        }
    }
}
