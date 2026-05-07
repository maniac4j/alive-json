package uz.maniac4j.alivejson.types;

import uz.maniac4j.alivejson.Json;

/**
 * JSON boolean representation.
 *
 * @since 1.0
 */
public final class JsonBool implements Json {

    /**
     * Boolean value.
     */
    private final Boolean bool;

    /**
     * Constructor.
     *
     * @param condition Boolean value
     */
    public JsonBool(final Boolean condition) {
        this.bool = condition;
    }

    @Override
    public String text() {
        return this.bool.toString();
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
