package org.rovers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    private static final String TEST_DATA_DIR = "test_data";

    @BeforeAll
    static void setUp() {
        new File(TEST_DATA_DIR).mkdirs();
    }

    @AfterAll
    static void tearDown() {
        new File(TEST_DATA_DIR).delete();
    }

    @Test
    void testMainWithNoArguments() {
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        Main.main(new String[]{});

        assertEquals("Please provide the input file path.\n", errContent.toString());
    }

    @Test
    void testMainWithValidInputFile() throws IOException {
        String input = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM\n";
        File file = new File(TEST_DATA_DIR, "valid_input.txt");
        Files.write(file.toPath(), input.getBytes());

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{file.getAbsolutePath()});

        assertEquals("1 3 N\n5 1 E\n", outContent.toString());

        Files.delete(file.toPath());
    }

    @Test
    void testMainWithInvalidInputFile() throws IOException {
        String input = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRR\n";
        File file = new File(TEST_DATA_DIR, "invalid_input.txt");
        Files.write(file.toPath(), input.getBytes());

        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        Main.main(new String[]{file.getAbsolutePath()});


        Files.delete(file.toPath());
    }
}
