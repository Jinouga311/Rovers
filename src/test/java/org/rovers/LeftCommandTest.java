package org.rovers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeftCommandTest {

    private Rover rover;

    @BeforeEach
    void setUp() {
        rover = new Rover(0, 0, Direction.N, 5, 5);
    }

    @Test
    void execute() {
        new LeftCommand().execute(rover);
        assertEquals(Direction.W, rover.getDirection());
    }
}
