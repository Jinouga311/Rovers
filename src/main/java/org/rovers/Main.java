package org.rovers;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Slf4j
public class Main {
    public static void main(String[] args) {

        if (args.length == 0) {
            log.error("Please provide the input file path.");
            return;
        }
        try {
            InputProvider inputProvider = new FileInputProvider(args[0]);
            List<RoverInstructions> roverInstructionsList = inputProvider.getInstructions();
            for (RoverInstructions roverInstructions : roverInstructionsList) {
                Rover rover = new Rover(roverInstructions.getX(), roverInstructions.getY(), roverInstructions.getDirection(), roverInstructions.getMaxX(), roverInstructions.getMaxY());
                rover.executeCommands(roverInstructions.getInstructions());
                log.info("RESULTAT : (" + rover.getX() +", " + (rover.getY()) + ", " + rover.getDirection() + ") ✅");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
