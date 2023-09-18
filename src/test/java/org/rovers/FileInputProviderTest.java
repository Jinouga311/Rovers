package org.rovers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileInputProviderTest {

    private static final String TEST_DATA_DIR = "test_data";

    @BeforeAll
    static void setUp() {
        new File(TEST_DATA_DIR).mkdirs();
    }

    @AfterAll
    static void tearDown() {
        new File(TEST_DATA_DIR).delete();
    }

    private static File createTestFile(String fileName, String content) throws IOException {
        File file = new File(TEST_DATA_DIR, fileName);
        Files.write(file.toPath(), content.getBytes());
        return file;
    }

    @Test
    void testValidInput() throws IOException {
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
        Files.delete(file.toPath());
    }

    @Test
    void testInvalidPlateauDimensions() {
        assertThrows(IllegalArgumentException.class, () -> {
            File file = createTestFile("invalid_plateau.txt", "-5 5\n1 2 N\nLMLMLMLMM\n");
            FileInputProvider provider = new FileInputProvider(file.getAbsolutePath());
            provider.getInstructions();
            Files.delete(file.toPath());
        });
    }

    @Test
    void testInvalidStartingPosition() {
        assertThrows(IllegalArgumentException.class, () -> {
            File file = createTestFile("invalid_start_position.txt", "5 5\n6 2 N\nLMLMLMLMM\n");
            FileInputProvider provider = new FileInputProvider(file.getAbsolutePath());
            provider.getInstructions();
            Files.delete(file.toPath());
        });
    }

    @Test
    void testInvalidStartingDirection() {
        assertThrows(IllegalArgumentException.class, () -> {
            File file = createTestFile("invalid_start_direction.txt", "5 5\n1 2 X\nLMLMLMLMM\n");
            FileInputProvider provider = new FileInputProvider(file.getAbsolutePath());
            provider.getInstructions();
            Files.delete(file.toPath());
        });
    }

    @Test
    void testMissingInstructions() {
        assertThrows(IllegalArgumentException.class, () -> {
            File file = createTestFile("missing_instructions.txt", "5 5\n1 2 N\n");
            FileInputProvider provider = new FileInputProvider(file.getAbsolutePath());
            provider.getInstructions();
            Files.delete(file.toPath());
        });
    }
}
