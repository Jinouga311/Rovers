package org.rovers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoverTest {

    private Rover rover;

    @BeforeEach
    void setUp() {
        rover = new Rover(1, 2, Direction.N, 5, 5);
    }

    @Test
    void testGetters() {
        assertEquals(1, rover.getX());
        assertEquals(2, rover.getY());
        assertEquals(Direction.N, rover.getDirection());
        assertEquals(5, rover.getMaxX());
        assertEquals(5, rover.getMaxY());
    }


    @Test
    void testMoveValid() {
        rover.move();
        assertEquals(1, rover.getX());
        assertEquals(3, rover.getY());
    }

    @Test
    void testMoveOutOfBound() {
        rover = new Rover(0, 0, Direction.S, 5, 5);
        Exception exception = assertThrows(IllegalArgumentException.class, rover::move);
        assertEquals("Movement out of bounds: 0,-1", exception.getMessage());
    }

    @Test
    void testTurnLeft() {
        rover.turnLeft();
        assertEquals(Direction.W, rover.getDirection());
    }

    @Test
    void testTurnRight() {
        rover.turnRight();
        assertEquals(Direction.E, rover.getDirection());
    }

    @Test
    void testExecuteCommandsValid() {
        rover.executeCommands("LMLMLMLMM");
        assertEquals(1, rover.getX());
        assertEquals(3, rover.getY());
        assertEquals(Direction.N, rover.getDirection());
    }

    @Test
    void testExecuteCommandsInvalidInstruction() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> rover.executeCommands("LMLMLMLMX"));
        assertEquals("Invalid instruction: X", exception.getMessage());
    }

}
