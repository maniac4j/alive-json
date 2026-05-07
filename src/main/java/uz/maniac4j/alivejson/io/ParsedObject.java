package uz.maniac4j.alivejson.io;

import java.util.HashMap;
import java.util.Map;
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.types.JsonObject;

/**
 * Parses a JSON object from the cursor.
 *
 * @since 1.0
 */
final class ParsedObject implements Token {

    /**
     * Cursor.
     */
    private final Input cursor;

    /**
     * Constructor.
     *
     * @param pointer Cursor
     */
    ParsedObject(final Input pointer) {
        this.cursor = pointer;
    }

    @Override
    public Json value() {
        final Map<String, Json> map = new HashMap<>();
        this.cursor.advance();
        this.cursor.skip();
        if (this.cursor.match('}')) {
            return new JsonObject(map);
        }
        while (this.cursor.hasNext()) {
            this.cursor.skip();
            final String key = new ParsedRawString(this.cursor).value();
            this.cursor.skip();
            this.cursor.match(':');
            final Json element = new ParsedElement(this.cursor).value();
            map.put(key, element);
            this.cursor.skip();
            if (this.cursor.match('}')) {
                break;
            }
            this.cursor.match(',');
        }
        return new JsonObject(map);
    }
}
