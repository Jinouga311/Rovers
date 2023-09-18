package org.rovers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoverInstructions {
    private final int x;
    private final int y;
    private final Direction direction;
    private final String instructions;
    private final int maxX;
    private final int maxY;


}
