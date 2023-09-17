package org.rovers;

public class RightCommand implements Command {
    @Override
    public void execute(Rover rover) {
        rover.turnRight();
    }
}