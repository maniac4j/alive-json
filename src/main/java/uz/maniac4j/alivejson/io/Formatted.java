package uz.maniac4j.alivejson.io;

import uz.maniac4j.alivejson.Json;

/**
 * Formatted JSON decorator.
 *
 * @since 1.0
 */
public final class Formatted implements Json {

    /**
     * Original JSON.
     */
    private final Json origin;

    /**
     * Constructor.
     *
     * @param json Original JSON
     */
    public Formatted(final Json json) {
        this.origin = json;
    }

    @Override
    public String text() {
        return this.origin.text();
    }

    @Override
    public Json value(final String key) {
        return this.origin.value(key);
    }

    @Override
    public Json value(final int index) {
        return this.origin.value(index);
    }
}
