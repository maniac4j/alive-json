package uz.maniac4j.alivejson.build;

import java.util.HashMap;
import java.util.Map;
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.io.Parsed;

/**
 * Parses top-level JSON object string into a map.
 * Used internally by build decorators.
 *
 * @since 1.0
 */
final class ParsedMap {

    /**
     * Source string.
     */
    private final String src;

    /**
     * Constructor.
     *
     * @param text Source JSON string
     */
    ParsedMap(final String text) {
        this.src = text.trim();
    }

    /**
     * Extracts the top level key-value pairs.
     *
     * @return Map of properties
     */
    Map<String, Json> value() {
        final Map<String, Json> map = new HashMap<>();
        if (this.src.length() < 2 || !this.src.startsWith("{")) {
            return map;
        }
        int pos = 1;
        while (pos < this.src.length() - 1) {
            while (pos < this.src.length() && Character.isWhitespace(this.src.charAt(pos))) {
                pos++;
            }
            if (this.src.charAt(pos) == '}') {
                break;
            }
            pos++;
            final int start = pos;
            while (this.src.charAt(pos) != '"') {
                pos++;
            }
            final String key = this.src.substring(start, pos);
            pos++;
            while (this.src.charAt(pos) != ':') {
                pos++;
            }
            pos++;
            while (pos < this.src.length() && Character.isWhitespace(this.src.charAt(pos))) {
                pos++;
            }
            final int valStart = pos;
            int braces = 0;
            int brackets = 0;
            boolean quotes = false;
            while (pos < this.src.length()) {
                final char current = this.src.charAt(pos);
                if (current == '"' && this.src.charAt(pos - 1) != '\\') {
                    quotes = !quotes;
                }
                if (!quotes) {
                    if (current == '{') {
                        braces++;
                    }
                    if (current == '}') {
                        braces--;
                    }
                    if (current == '[') {
                        brackets++;
                    }
                    if (current == ']') {
                        brackets--;
                    }
                    if (braces == 0 && brackets == 0 && (current == ',' || current == '}')) {
                        break;
                    }
                    if (braces < 0) {
                        break;
                    }
                }
                pos++;
            }
            map.put(key, new Parsed(this.src.substring(valStart, pos).trim()));
            if (this.src.charAt(pos) == ',') {
                pos++;
            }
        }
        return map;
    }
}
