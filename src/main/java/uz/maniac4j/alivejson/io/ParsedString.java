package uz.maniac4j.alivejson.io;

import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.types.JsonString;

/**
 * Parses a JSON string from the cursor.
 *
 * @since 1.0
 */
final class ParsedString implements Token {

    /**
     * Cursor.
     */
    private final Input cursor;

    /**
     * Constructor.
     *
     * @param pointer Cursor
     */
    ParsedString(final Input pointer) {
        this.cursor = pointer;
    }

    @Override
    public Json value() {
        return new JsonString(new ParsedRawString(this.cursor).value());
    }
}
