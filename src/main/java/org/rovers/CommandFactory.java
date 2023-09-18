// CommandFactory.java
package org.rovers;

import java.util.Map;

public class CommandFactory {

    public static Map<Character, Command> createCommandMap() {
        return Map.of(
                'M', new MoveCommand(),
                'L', new LeftCommand(),
                'R', new RightCommand()
        );
    }
}
