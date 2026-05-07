package uz.maniac4j.alivejson.io;

import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.types.JsonNull;

/**
 * Parses a JSON null from the cursor.
 *
 * @since 1.0
 */
final class ParsedNull implements Token {

    /**
     * Cursor.
     */
    private final Input cursor;

    /**
     * Constructor.
     *
     * @param pointer Cursor
     */
    ParsedNull(final Input pointer) {
        this.cursor = pointer;
    }

    @Override
    public Json value() {
        if (this.cursor.startsWith("null")) {
            this.cursor.advance(4);
        }
        return new JsonNull();
    }
}
