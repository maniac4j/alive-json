package uz.maniac4j.alivejson.types;

import uz.maniac4j.alivejson.Json;

/**
 * JSON number representation.
 *
 * @since 1.0
 */
public final class JsonNumber implements Json {

    /**
     * Number value.
     */
    private final Number num;

    /**
     * Constructor.
     *
     * @param number Number value
     */
    public JsonNumber(final Number number) {
        this.num = number;
    }

    @Override
    public String text() {
        return this.num.toString();
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
