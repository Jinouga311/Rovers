package org.rovers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RightCommandTest {

    private Rover rover;

    @BeforeEach
    void setUp() {
        rover = new Rover(0, 0, Direction.N, 5, 5);
    }

    @Test
    void execute() {
        new RightCommand().execute(rover);
        assertEquals(Direction.E, rover.getDirection());
    }
}
