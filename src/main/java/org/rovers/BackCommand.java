package org.rovers;

public class BackCommand implements Command {
    @Override
    public void execute(Rover rover) {
        rover.moveBack();
    }
}