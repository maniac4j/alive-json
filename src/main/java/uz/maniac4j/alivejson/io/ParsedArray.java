package uz.maniac4j.alivejson.io;

import java.util.ArrayList;
import java.util.List;
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.types.JsonArray;

/**
 * Parses a JSON array from the cursor.
 *
 * @since 1.0
 */
final class ParsedArray implements Token {

    /**
     * Cursor.
     */
    private final Input cursor;

    /**
     * Constructor.
     *
     * @param pointer Cursor
     */
    ParsedArray(final Input pointer) {
        this.cursor = pointer;
    }

    @Override
    public Json value() {
        final List<Json> list = new ArrayList<>();
        this.cursor.advance();
        this.cursor.skip();
        if (this.cursor.match(']')) {
            return new JsonArray(list);
        }
        while (this.cursor.hasNext()) {
            list.add(new ParsedElement(this.cursor).value());
            this.cursor.skip();
            if (this.cursor.match(']')) {
                break;
            }
            this.cursor.match(',');
        }
        return new JsonArray(list);
    }
}
