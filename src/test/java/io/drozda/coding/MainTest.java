package io.drozda.coding;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MainTest {

    @Test
    void shouldReadTokensAndNumbers() throws Exception {
        String input = """
                42 7
                hello world
                -15 9999999999
                """;

        Main.FastScanner scanner = createScanner(input);

        assertEquals(42, scanner.nextInt());
        assertEquals(7, scanner.nextInt());
        assertEquals("hello", scanner.next());
        assertEquals("world", scanner.next());
        assertEquals(-15, scanner.nextInt());
        assertEquals(9999999999L, scanner.nextLong());

        assertNull(scanner.next());
    }

    @Test
    void shouldReadNegativeNumbersAndLongValues() throws Exception {
        String input = """
                -10 9223372036854775807 -42
                """;

        Main.FastScanner scanner = createScanner(input);

        assertEquals(-10, scanner.nextInt());
        assertEquals(9223372036854775807L, scanner.nextLong());
        assertEquals(-42, scanner.nextInt());
    }

    @Test
    void shouldReadDoubleValue() throws Exception {
        String input = """
                3.1415
                """;

        Main.FastScanner scanner = createScanner(input);

        assertEquals(3.1415, scanner.nextDouble(), 0.000001);
    }

    @Test
    void shouldReadStringToEndOfLineFromCurrentPosition() throws Exception {
        String input = """
                10 some text after number
                Next line value
                """;

        Main.FastScanner scanner = createScanner(input);

        assertEquals(10, scanner.nextInt());
        assertEquals(" some text after number", scanner.nextStringToEndOfLine());
        assertEquals("Next line value", scanner.nextFullLine());
    }

    @Test
    void shouldReadNextFullLineAfterTokenReading() throws Exception {
        String input = """
                3
                First full line
                Second full line
                Third full line
                """;

        Main.FastScanner scanner = createScanner(input);

        assertEquals(3, scanner.nextInt());
        assertEquals("First full line", scanner.nextFullLine());
        assertEquals("Second full line", scanner.nextFullLine());
        assertEquals("Third full line", scanner.nextFullLine());
    }

    @Test
    void shouldHandleWindowsLineEndings() throws Exception {
        String input = "10\r\nFirst Windows line\r\n20\r\n";

        Main.FastScanner scanner = createScanner(input);

        assertEquals(10, scanner.nextInt());
        assertEquals("First Windows line", scanner.nextFullLine());
        assertEquals(20, scanner.nextInt());
    }

    @Test
    void shouldRunEmptySolveTemplate() throws Exception {
        String input = """
                100
                placeholder input
                """;

        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Main.solve(inputStream, outputStream);

        assertEquals("", outputStream.toString(StandardCharsets.UTF_8));
    }

    private static Main.FastScanner createScanner(String input) {
        return new Main.FastScanner(
                new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))
        );
    }
}
