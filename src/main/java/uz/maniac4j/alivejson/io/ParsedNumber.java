package uz.maniac4j.alivejson.io;

import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.types.JsonNumber;

/**
 * Parses a JSON number from the cursor.
 *
 * @since 1.0
 */
final class ParsedNumber implements Token {

    /**
     * Cursor.
     */
    private final Input cursor;

    /**
     * Constructor.
     *
     * @param pointer Cursor
     */
    ParsedNumber(final Input pointer) {
        this.cursor = pointer;
    }

    @Override
    public Json value() {
        final int start = this.cursor.position();
        while (this.cursor.hasNext()) {
            final char current = this.cursor.current();
            if (Character.isDigit(current) || current == '.' || current == '-' || current == '+' || current == 'e' || current == 'E') {
                this.cursor.advance();
            } else {
                break;
            }
        }
        final String num = this.cursor.substring(start);
        if (num.contains(".")) {
            return new JsonNumber(Double.parseDouble(num));
        }
        return new JsonNumber(Long.parseLong(num));
    }
}
