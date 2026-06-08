package io.drozda.coding;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

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

    private static Main.FastScanner createScanner(String input) {
        return new Main.FastScanner(
                new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))
        );
    }
}
