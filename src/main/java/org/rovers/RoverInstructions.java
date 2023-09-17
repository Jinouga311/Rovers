package org.rovers;

import lombok.Getter;

@Getter
public class RoverInstructions {
    private final int x;
    private final int y;
    private final Direction direction;
    private final String instructions;
    private final int maxX;
    private final int maxY;

    public RoverInstructions(int x, int y, Direction direction, String instructions, int maxX, int maxY) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.instructions = instructions;
        this.maxX = maxX;
        this.maxY = maxY;
    }
}
