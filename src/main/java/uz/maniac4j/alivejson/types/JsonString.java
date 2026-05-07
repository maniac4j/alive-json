package uz.maniac4j.alivejson.types;

import uz.maniac4j.alivejson.Json;

/**
 * JSON string representation.
 *
 * @since 1.0
 */
public final class JsonString implements Json {

    /**
     * Text value.
     */
    private final String val;

    /**
     * Constructor.
     *
     * @param text Text value
     */
    public JsonString(final String text) {
        this.val = text;
    }

    @Override
    public String text() {
        return "\"" + this.val.replace("\\", "\\\\").replace("\"", "\\\"").replace("\b", "\\b").replace("\f", "\\f").replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t") + "\"";
    }

    @Override
    public Json value(final String key) {
        return new JsonNull();
    }

    @Override
    public Json value(final int index) {
        return new JsonNull();
    }
}
