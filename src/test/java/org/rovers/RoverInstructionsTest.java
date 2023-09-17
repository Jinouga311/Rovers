package org.rovers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoverInstructionsTest {

    private RoverInstructions roverInstructions;

    @BeforeEach
    void setUp() {
        roverInstructions = new RoverInstructions(1, 2, Direction.N, "LMLMLMLMM", 5, 5);
    }

    @Test
    void getX() {
        assertEquals(1, roverInstructions.getX());
    }

    @Test
    void getY() {
        assertEquals(2, roverInstructions.getY());
    }

    @Test
    void getDirection() {
        assertEquals(Direction.N, roverInstructions.getDirection());
    }

    @Test
    void getInstructions() {
        assertEquals("LMLMLMLMM", roverInstructions.getInstructions());
    }

    @Test
    void getMaxX() {
        assertEquals(5, roverInstructions.getMaxX());
    }

    @Test
    void getMaxY() {
        assertEquals(5, roverInstructions.getMaxY());
    }
}
