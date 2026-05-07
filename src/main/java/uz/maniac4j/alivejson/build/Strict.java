package uz.maniac4j.alivejson.build;

import uz.maniac4j.alivejson.Json;

/**
 * JSON decorator that fails fast if a key or index is not found.
 * Throws IllegalArgumentException instead of returning JsonNull.
 *
 * @since 1.0
 */
public final class Strict implements Json {

    /**
     * Original JSON.
     */
    private final Json origin;

    /**
     * Constructor.
     *
     * @param json Original JSON
     */
    public Strict(final Json json) {
        this.origin = json;
    }

    @Override
    public String text() {
        return this.origin.text();
    }

    @Override
    public Json value(final String key) {
        final Json val = this.origin.value(key);
        if ("null".equals(val.text())) {
            throw new IllegalArgumentException("Strict constraint failed: Key not found.");
        }
        return new Strict(val);
    }

    @Override
    public Json value(final int index) {
        final Json val = this.origin.value(index);
        if ("null".equals(val.text())) {
            throw new IllegalArgumentException("Strict constraint failed: Index out of bounds.");
        }
        return new Strict(val);
    }
}
