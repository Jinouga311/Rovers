package org.rovers;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.logging.Logger;

@Getter
@Setter
@ToString
@Slf4j
public class Rover {
    private int x;
    private int y;
    private Direction direction;
    private final int maxX;
    private final int maxY;
    private final Map<Character, Command> commandMap;

    public Rover(int x, int y, Direction direction, int maxX, int maxY) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.maxX = maxX;
        this.maxY = maxY;
        this.commandMap = CommandFactory.createCommandMap();
    }

    public void executeCommands(String instructions) {
        for (char instruction : instructions.toCharArray()) {
            Command command = commandMap.get(instruction);
            log.info("Current instruction: " + instruction);
            log.info("Command object found: " + command);

            if (command != null) {
                command.execute(this);
            } else {
                log.error("Invalid instruction: " + instruction);
                throw new IllegalArgumentException("Invalid instruction: " + instruction);
            }
        }
    }

    public void move() {
        int newX = x + direction.getDx();
        int newY = y + direction.getDy();
        log.info(x + " + " + direction.getDx());
        log.info(y + " + " + direction.getDy());
        log.info("Trying to move to: (" + newX + ", " + newY + ")");

        if (newX >= 0 && newX <= maxX && newY >= 0 && newY <= maxY) {
            x = newX;
            y = newY;
            log.info("Moved successfully to: (" + x + ", " + y + ")");
        } else {
            log.error("Movement out of bounds: " + newX + "," + newY);
            throw new IllegalArgumentException("Movement out of bounds: " + newX + "," + newY);
        }
    }

    public void turnLeft() {
        direction = direction.left();
        log.info("Turned left. Now facing: " + direction);
    }

    public void turnRight() {
        direction = direction.right();
        log.info("Turned right. Now facing: " + direction);
    }
}
