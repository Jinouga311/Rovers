package org.rovers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveCommandTest {

    private Rover rover;

    @BeforeEach
    void setUp() {
        rover = new Rover(0, 0, Direction.N, 5, 5);
    }

    @Test
    void execute() {
        new MoveCommand().execute(rover);
        assertEquals(0, rover.getX());
        assertEquals(1, rover.getY());
    }
}
