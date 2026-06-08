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
         */

        output.write(result.toString().getBytes(StandardCharsets.UTF_8));
    }

    public static final class FastScanner {

        private final InputStream input;
        private final byte[] buffer = new byte[1 << 16];

        private int pointer = 0;
        private int length = 0;

        public FastScanner(InputStream input) {
            this.input = input;
        }

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

        private static boolean isDelimiter(int character) {
            return character <= ' ';
        }

        public String next() throws IOException {
            StringBuilder builder = new StringBuilder();

            int character;

            while ((character = read()) != -1 && isDelimiter(character)) {
            }

            if (character == -1) {
                return null;
            }

            while (character != -1 && !isDelimiter(character)) {
                builder.append((char) character);
                character = read();
            }

            return builder.toString();
        }

        public int nextInt() throws IOException {
            int character;

            while ((character = read()) != -1 && isDelimiter(character)) {
            }

            int sign = 1;

            if (character == '-') {
                sign = -1;
                character = read();
            }

            int result = 0;

            while (character >= '0' && character <= '9') {
                result = result * 10 + (character - '0');
                character = read();
            }

            return result * sign;
        }

        public long nextLong() throws IOException {
            int character;

            while ((character = read()) != -1 && isDelimiter(character)) {
            }

            int sign = 1;

            if (character == '-') {
                sign = -1;
                character = read();
            }

            long result = 0;

            while (character >= '0' && character <= '9') {
                result = result * 10 + (character - '0');
                character = read();
            }

            return result * sign;
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        public String nextStringToEndOfLine() throws IOException {
            StringBuilder builder = new StringBuilder();

            int character = read();

            while (character != -1 && character != '\n' && character != '\r') {
                builder.append((char) character);
                character = read();
            }

            if (character == '\r') {
                int nextCharacter = read();

                if (nextCharacter != '\n' && nextCharacter != -1) {
                    pointer--;
                }
            }

            return builder.toString();
        }

        public String nextFullLine() throws IOException {
            StringBuilder builder = new StringBuilder();

            int character;

            do {
                character = read();
            } while (character == '\n' || character == '\r');

            while (character != -1 && character != '\n' && character != '\r') {
                builder.append((char) character);
                character = read();
            }

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
