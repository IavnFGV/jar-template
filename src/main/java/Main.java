import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
         * Example:
         * int n = scanner.nextInt();
         * result.append(n).append('\n');
         */

        output.write(result.toString().getBytes());
    }

 /**
 * Fast input reader suitable for coding interviews and competitive programming.
 */
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

    /**
     * Determines whether a character should be treated as a token separator.
     *
     * Default:
     * - space
     * - tab
     * - carriage return (\r)
     * - line feed (\n)
     */
    private static boolean isDelimiter(int character) {
        return character <= ' ';
    }

    /**
     * Reads the next token.
     *
     * Example:
     * Input:
     *   10 20 ABC
     *
     * Calls:
     *   next() -> "10"
     *   next() -> "20"
     *   next() -> "ABC"
     */
    public String next() throws IOException {
        StringBuilder builder = new StringBuilder();

        int character;

        while ((character = read()) != -1 && isDelimiter(character)) {
            // Skip delimiters.
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

    /**
     * Reads the next integer without creating a String object.
     */
    public int nextInt() throws IOException {
        int character;

        while ((character = read()) != -1 && isDelimiter(character)) {
            // Skip delimiters.
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

    /**
     * Reads the next long without creating a String object.
     */
    public long nextLong() throws IOException {
        int character;

        while ((character = read()) != -1 && isDelimiter(character)) {
            // Skip delimiters.
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

    /**
     * Reads the next double.
     */
    public double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    /**
     * Reads all remaining characters from the current position
     * until the end of the current line.
     *
     * Example:
     * Input:
     *   10 20 ABC
     *
     * After nextInt():
     *   nextStringToEndOfLine() -> " 20 ABC"
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
     * Example:
     * Input:
     *   10
     *   John Smith
     *
     * Calls:
     *   nextInt()      -> 10
     *   nextFullLine() -> "John Smith"
     */
    public String nextFullLine() throws IOException {
        StringBuilder builder = new StringBuilder();

        int character;

        do {
            character = read();
        } while (character == '\n' || character == '\r');

        while (character != -1
                && character != '\n'
                && character != '\r') {

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
