package uz.maniac4j.alivejson.io;

import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.types.JsonBool;

/**
 * Parses a JSON boolean from the cursor.
 *
 * @since 1.0
 */
final class ParsedBool implements Token {

    /**
     * Cursor.
     */
    private final Input cursor;

    /**
     * Constructor.
     *
     * @param pointer Cursor
     */
    ParsedBool(final Input pointer) {
        this.cursor = pointer;
    }

    @Override
    public Json value() {
        if (this.cursor.startsWith("true")) {
            this.cursor.advance(4);
            return new JsonBool(true);
        }
        this.cursor.advance(5);
        return new JsonBool(false);
    }
}
