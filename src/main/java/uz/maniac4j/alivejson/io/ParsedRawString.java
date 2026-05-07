package uz.maniac4j.alivejson.io;

/**
 * Parses a raw string value without quotes from the cursor.
 *
 * @since 1.0
 */
final class ParsedRawString {

    /**
     * Cursor.
     */
    private final Input cursor;

    /**
     * Constructor.
     *
     * @param pointer Cursor
     */
    ParsedRawString(final Input pointer) {
        this.cursor = pointer;
    }

    /**
     * Evaluates and returns the raw string content.
     *
     * @return String content
     */
    String value() {
        this.cursor.advance();
        final StringBuilder builder = new StringBuilder();
        while (this.cursor.hasNext()) {
            final char current = this.cursor.current();
            if (current == '"') {
                this.cursor.advance();
                break;
            }
            if (current == '\\') {
                this.cursor.advance();
                builder.append(this.cursor.current());
            } else {
                builder.append(current);
            }
            this.cursor.advance();
        }
        return builder.toString();
    }
}
