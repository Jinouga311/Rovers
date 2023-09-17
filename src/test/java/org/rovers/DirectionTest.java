package org.rovers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void left() {
        assertEquals(Direction.W, Direction.N.left());
        assertEquals(Direction.N, Direction.E.left());
        assertEquals(Direction.E, Direction.S.left());
        assertEquals(Direction.S, Direction.W.left());
    }

    @Test
    void right() {
        assertEquals(Direction.E, Direction.N.right());
        assertEquals(Direction.S, Direction.E.right());
        assertEquals(Direction.W, Direction.S.right());
        assertEquals(Direction.N, Direction.W.right());
    }
}
