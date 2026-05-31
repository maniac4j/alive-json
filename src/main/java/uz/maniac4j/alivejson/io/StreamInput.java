package uz.maniac4j.alivejson.io;

import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;

/**
 * Input source reading from a Reader.
 * This class provides a sequential view of a character stream.
 *
 * @since 1.1
 */
final class StreamInput implements Input {

    /**
     * Source reader.
     */
    private final Reader source;

    /**
     * Lookahead buffer.
     * -2: empty, -1: EOF, others: char value.
     */
    private final int[] buffer;

    /**
     * Constructor.
     *
     * @param reader Source reader
     */
    StreamInput(final Reader reader) {
        this.source = reader;
        this.buffer = new int[]{-2};
    }

    @Override
    public void skip() {
        while (this.hasNext() && Character.isWhitespace(this.current())) {
            this.advance();
        }
    }

    @Override
    public char current() {
        return (char) this.look();
    }

    @Override
    public boolean match(final char expected) {
        final boolean matched = this.hasNext() && this.current() == expected;
        if (matched) {
            this.advance();
        }
        return matched;
    }

    @Override
    public void advance() {
        this.look();
        this.buffer[0] = -2;
    }

    @Override
    public boolean hasNext() {
        return this.look() != -1;
    }

    /**
     * Peek at the next character.
     *
     * @return Next character or -1 for EOF
     */
    private int look() {
        if (this.buffer[0] == -2) {
            try {
                this.buffer[0] = this.source.read();
            } catch (final IOException ex) {
                throw new UncheckedIOException(ex);
            }
        }
        return this.buffer[0];
    }
}
