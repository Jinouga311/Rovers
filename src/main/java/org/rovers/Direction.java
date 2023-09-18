package org.rovers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Direction {
    N(0, 1), //North
    E(1, 0), //East
    S(0, -1), //South
    W(-1, 0); //West

    private final int dx;
    private final int dy;

    public Direction left() {
        return values()[(this.ordinal() + 3) % 4];
    }

    public Direction right() {
        return values()[(this.ordinal() + 1) % 4];
    }
}
