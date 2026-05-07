package uz.maniac4j.alivejson.build;

import uz.maniac4j.alivejson.Json;

/**
 * JSON decorator that returns a fallback value if a key or index is missing.
 *
 * @since 1.0
 */
public final class Fallback implements Json {

    /**
     * Original JSON.
     */
    private final Json origin;

    /**
     * Fallback JSON.
     */
    private final Json fallback;

    /**
     * Constructor.
     *
     * @param json Original JSON
     * @param def Fallback value
     */
    public Fallback(final Json json, final Json def) {
        this.origin = json;
        this.fallback = def;
    }

    @Override
    public String text() {
        return this.origin.text();
    }

    @Override
    public Json value(final String key) {
        final Json val = this.origin.value(key);
        if ("null".equals(val.text())) {
            return this.fallback;
        }
        return new Fallback(val, this.fallback);
    }

    @Override
    public Json value(final int index) {
        final Json val = this.origin.value(index);
        if ("null".equals(val.text())) {
            return this.fallback;
        }
        return new Fallback(val, this.fallback);
    }
}
