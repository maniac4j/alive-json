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
        if (this.cursor.match('t')) {
            this.cursor.match('r');
            this.cursor.match('u');
            this.cursor.match('e');
            return new JsonBool(true);
        }
        this.cursor.match('f');
        this.cursor.match('a');
        this.cursor.match('l');
        this.cursor.match('s');
        this.cursor.match('e');
        return new JsonBool(false);
    }
}
