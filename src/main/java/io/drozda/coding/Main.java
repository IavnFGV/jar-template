package io.drozda.coding;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        Path inputPath = Path.of("input.txt");
        Path outputPath = Path.of("output.txt");

        try (
                InputStream input = Files.newInputStream(inputPath);
                OutputStream output = new BufferedOutputStream(Files.newOutputStream(outputPath))
        ) {
            solve(input, output);
        }
    }

    public static void solve(InputStream input, OutputStream output) throws IOException {
        FastScanner scanner = new FastScanner(input);
        StringBuilder result = new StringBuilder();

        /*
         * Implement solution here.
         *
         * Typical usage:
         * int n = scanner.nextInt();
         * int q = scanner.nextInt();
         * String value = scanner.next();
         *
         * result.append(answer).append('\n');
         */

        output.write(result.toString().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Fast input reader suitable for coding interviews and competitive programming.
     *
     * It avoids java.util.Scanner because Scanner uses regex internally and is usually
     * too slow for large input files.
     */
    public static final class FastScanner {

        private final InputStream input;
        private final byte[] buffer = new byte[1 << 16];

        private int pointer = 0;
        private int length = 0;

        public FastScanner(InputStream input) {
            this.input = input;
        }

        /**
         * Reads the next byte from the internal buffer.
         *
         * The buffer is refilled automatically when all previously loaded bytes
         * have been consumed.
         *
         * @return next character code, or -1 when end of stream is reached
         */
        private int read() throws IOException {
            if (pointer >= length) {
                length = input.read(buffer);
                pointer = 0;

                if (length <= 0) {
                    return -1;
                }
            }

            return buffer[pointer++];
        }

        /**
         * Determines whether a character should be treated as a token separator.
         *
         * Default separators:
         * - space
         * - tab
         * - carriage return (\r)
         * - line feed (\n)
         *
         * To support custom input formats, modify this method only.
         *
         * Examples:
         * return character <= ' ' || character == '|';
         * return character <= ' ' || character == ',';
         * return character <= ' ' || character == ';';
         */
        private static boolean isDelimiter(int character) {
            return character <= ' ';
        }

        /**
         * Reads the next token separated by delimiters.
         *
         * Example input:
         * 10 20 ABC
         *
         * Calls:
         * next() -> "10"
         * next() -> "20"
         * next() -> "ABC"
         *
         * @return next token, or null when EOF is reached before a token starts
         */
        public String next() throws IOException {
            StringBuilder builder = new StringBuilder();

            int character;

            // Skip all delimiters before the token.
            while ((character = read()) != -1 && isDelimiter(character)) {
                // Skip delimiter.
            }

            if (character == -1) {
                return null;
            }

            // Read token characters until the next delimiter or EOF.
            while (character != -1 && !isDelimiter(character)) {
                builder.append((char) character);
                character = read();
            }

            return builder.toString();
        }

        /**
         * Reads the next integer directly from the stream.
         *
         * This method avoids creating a temporary String object and is faster
         * than Integer.parseInt(next()).
         *
         * Supports positive and negative integers.
         */
        public int nextInt() throws IOException {
            int character;

            // Skip leading delimiters.
            while ((character = read()) != -1 && isDelimiter(character)) {
                // Skip delimiter.
            }

            int sign = 1;

            if (character == '-') {
                sign = -1;
                character = read();
            }

            int result = 0;

            // Build integer digit by digit.
            while (character >= '0' && character <= '9') {
                result = result * 10 + (character - '0');
                character = read();
            }

            return result * sign;
        }

        /**
         * Reads the next long directly from the stream.
         *
         * This method avoids creating a temporary String object and is faster
         * than Long.parseLong(next()).
         *
         * Supports positive and negative values.
         */
        public long nextLong() throws IOException {
            int character;

            // Skip leading delimiters.
            while ((character = read()) != -1 && isDelimiter(character)) {
                // Skip delimiter.
            }

            int sign = 1;

            if (character == '-') {
                sign = -1;
                character = read();
            }

            long result = 0;

            // Build long value digit by digit.
            while (character >= '0' && character <= '9') {
                result = result * 10 + (character - '0');
                character = read();
            }

            return result * sign;
        }

        /**
         * Reads the next double.
         *
         * This method uses next() because double values are less common in typical
         * integer-heavy tasks, and this keeps the scanner simple.
         */
        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        /**
         * Reads all remaining characters from the current position until the end
         * of the current line.
         *
         * This is useful when part of the line has already been processed.
         *
         * Example input:
         * 10 some text
         *
         * Calls:
         * nextInt() -> 10
         * nextStringToEndOfLine() -> " some text"
         */
        public String nextStringToEndOfLine() throws IOException {
            StringBuilder builder = new StringBuilder();

            int character = read();

            while (character != -1
                    && character != '\n'
                    && character != '\r') {

                builder.append((char) character);
                character = read();
            }

            // Handle Windows line endings: \r\n.
            if (character == '\r') {
                int nextCharacter = read();

                if (nextCharacter != '\n' && nextCharacter != -1) {
                    pointer--;
                }
            }

            return builder.toString();
        }

        /**
         * Reads the next full non-empty line.
         *
         * Leading line separators are skipped.
         *
         * Example input:
         * 3
         * John Smith
         *
         * Calls:
         * nextInt() -> 3
         * nextFullLine() -> "John Smith"
         */
        public String nextFullLine() throws IOException {
            StringBuilder builder = new StringBuilder();

            int character;

            // Skip empty line separators before reading the actual line.
            do {
                character = read();
            } while (character == '\n' || character == '\r');

            while (character != -1
                    && character != '\n'
                    && character != '\r') {

                builder.append((char) character);
                character = read();
            }

            // Handle Windows line endings: \r\n.
            if (character == '\r') {
                int nextCharacter = read();

                if (nextCharacter != '\n' && nextCharacter != -1) {
                    pointer--;
                }
            }

            return builder.toString();
        }
    }
}
