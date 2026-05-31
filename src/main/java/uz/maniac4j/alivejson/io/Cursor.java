package uz.maniac4j.alivejson.io;

/**
 * Mutable cursor representing the current position in a string.
 * This is an internal utility to track parsing state across decorators.
 *
 * @since 1.0
 */
final class Cursor implements Input {

    /**
     * Source string.
     */
    private final String source;

    /**
     * Current index array to allow mutation.
     */
    private final int[] index;

    /**
     * Constructor.
     *
     * @param src Source text
     */
    Cursor(final String src) {
        this.source = src;
        this.index = new int[]{0};
    }

    @Override
    public void skip() {
        while (this.index[0] < this.source.length() && Character.isWhitespace(this.source.charAt(this.index[0]))) {
            this.index[0]++;
        }
    }

    @Override
    public char current() {
        return this.source.charAt(this.index[0]);
    }

    @Override
    public boolean match(final char expected) {
        if (!this.hasNext() || this.current() != expected) {
            return false;
        }
        this.advance();
        return true;
    }

    @Override
    public void advance() {
        this.index[0]++;
    }

    @Override
    public boolean hasNext() {
        return this.index[0] < this.source.length();
    }
}
