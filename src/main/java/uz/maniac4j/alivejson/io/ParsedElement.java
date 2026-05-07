package uz.maniac4j.alivejson.io;

import uz.maniac4j.alivejson.Json;

/**
 * Parses an element from the cursor.
 *
 * @since 1.0
 */
final class ParsedElement implements Token {

    /**
     * Cursor.
     */
    private final Input cursor;

    /**
     * Constructor.
     *
     * @param pointer Cursor
     */
    ParsedElement(final Input pointer) {
        this.cursor = pointer;
    }

    @Override
    public Json value() {
        this.cursor.skip();
        if (!this.cursor.hasNext()) {
            return new ParsedNull(this.cursor).value();
        }
        final char current = this.cursor.current();
        if (current == '{') {
            return new ParsedObject(this.cursor).value();
        }
        if (current == '[') {
            return new ParsedArray(this.cursor).value();
        }
        if (current == '"') {
            return new ParsedString(this.cursor).value();
        }
        if (current == 't' || current == 'f') {
            return new ParsedBool(this.cursor).value();
        }
        if (current == 'n') {
            return new ParsedNull(this.cursor).value();
        }
        return new ParsedNumber(this.cursor).value();
    }
}
