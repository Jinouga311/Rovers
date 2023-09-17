package org.rovers;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Slf4j
public class FileInputProvider implements InputProvider {
    private final String filePath;

    public FileInputProvider(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<RoverInstructions> getInstructions() throws IOException {
        List<RoverInstructions> rovers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String[] plateauDimensions = reader.readLine().split(" ");
            int maxX = Integer.parseInt(plateauDimensions[0]);
            int maxY = Integer.parseInt(plateauDimensions[1]);

            if (maxX < 0 || maxY < 0) {
                log.error("Plateau dimensions must be non-negative.");
                throw new IllegalArgumentException("Plateau dimensions must be non-negative.");
            }

            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                String[] position = line.split(" ");
                int x = Integer.parseInt(position[0]);
                int y = Integer.parseInt(position[1]);

                if (x < 0 || x > maxX || y < 0 || y > maxY) {
                    log.error("Rover starting position out of bounds.");
                    throw new IllegalArgumentException("Rover starting position out of bounds.");
                }

                Direction direction;
                try {
                    direction = Direction.valueOf(position[2]);
                } catch (IllegalArgumentException e) {
                    log.error("Invalid starting direction. Must be one of: N, E, S, W.");
                    throw new IllegalArgumentException("Invalid starting direction. Must be one of: N, E, S, W.");
                }

                String instructions = reader.readLine();
                if (instructions == null || instructions.isBlank()) {
                    log.error("Instructions cannot be empty or null.");
                    throw new IllegalArgumentException("Instructions cannot be empty or null.");
                }

                rovers.add(new RoverInstructions(x, y, direction, instructions, maxX, maxY));
                log.info("Rover with coordinates (" + x + "," + y + ") and direction " + direction + " added with instructions: " + instructions);
            }
        } catch (IOException e) {
            log.error("An IO exception occurred while reading the file: " + e.getMessage());
            throw e;
        }
        return rovers;
    }
}
