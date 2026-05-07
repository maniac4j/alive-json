package uz.maniac4j.alivejson.types;

import uz.maniac4j.alivejson.Json;

/**
 * JSON null representation.
 *
 * @since 1.0
 */
public final class JsonNull implements Json {

    /**
     * Constructor.
     */
    public JsonNull() {
    }

    @Override
    public String text() {
        return "null";
    }

    @Override
    public Json value(final String key) {
        return this;
    }

    @Override
    public Json value(final int index) {
        return this;
    }
}
