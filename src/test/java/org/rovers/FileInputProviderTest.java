package org.rovers;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileInputProviderTest {

    private static final String TEST_DATA_DIR = "test_data";

    private static void createTestDataDir() {
        new File(TEST_DATA_DIR).mkdirs();
    }

    private static File createTestFile(String fileName, String content) throws IOException {
        File file = new File(TEST_DATA_DIR, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        }
        return file;
    }

    @Test
    void testValidInput() throws IOException {
        createTestDataDir();
        File file = createTestFile("valid_input.txt", "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM\n");
        FileInputProvider provider = new FileInputProvider(file.getAbsolutePath());

        List<RoverInstructions> instructions = provider.getInstructions();

        assertEquals(2, instructions.size());
        assertEquals(1, instructions.get(0).getX());
        assertEquals(2, instructions.get(0).getY());
        assertEquals(Direction.N, instructions.get(0).getDirection());
        assertEquals("LMLMLMLMM", instructions.get(0).getInstructions());
        assertEquals(3, instructions.get(1).getX());
        assertEquals(3, instructions.get(1).getY());
        assertEquals(Direction.E, instructions.get(1).getDirection());
        assertEquals("MMRMMRMRRM", instructions.get(1).getInstructions());
    }

    @Test
    void testInvalidPlateauDimensions() {
        assertThrows(IllegalArgumentException.class, () -> {
            createTestDataDir();
            File file = createTestFile("invalid_plateau.txt", "-5 5\n1 2 N\nLMLMLMLMM\n");
            FileInputProvider provider = new FileInputProvider(file.getAbsolutePath());
            provider.getInstructions();
        });
    }

    @Test
    void testInvalidStartingPosition() {
        assertThrows(IllegalArgumentException.class, () -> {
            createTestDataDir();
            File file = createTestFile("invalid_start_position.txt", "5 5\n6 2 N\nLMLMLMLMM\n");
            FileInputProvider provider = new FileInputProvider(file.getAbsolutePath());
            provider.getInstructions();
        });
    }

    @Test
    void testInvalidStartingDirection() {
        assertThrows(IllegalArgumentException.class, () -> {
            createTestDataDir();
            File file = createTestFile("invalid_start_direction.txt", "5 5\n1 2 X\nLMLMLMLMM\n");
            FileInputProvider provider = new FileInputProvider(file.getAbsolutePath());
            provider.getInstructions();
        });
    }

    @Test
    void testMissingInstructions() {
        assertThrows(IllegalArgumentException.class, () -> {
            createTestDataDir();
            File file = createTestFile("missing_instructions.txt", "5 5\n1 2 N\n");
            FileInputProvider provider = new FileInputProvider(file.getAbsolutePath());
            provider.getInstructions();
        });
    }
}
