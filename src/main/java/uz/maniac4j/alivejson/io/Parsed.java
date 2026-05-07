package uz.maniac4j.alivejson.io;

import uz.maniac4j.alivejson.Json;

/**
 * Parsed JSON from a string.
 *
 * @since 1.0
 */
public final class Parsed implements Json {

    /**
     * Source text.
     */
    private final String source;

    /**
     * Constructor.
     *
     * @param text Source text
     */
    public Parsed(final String text) {
        this.source = text;
    }

    @Override
    public String text() {
        return this.source;
    }

    @Override
    public Json value(final String key) {
        return this.origin().value(key);
    }

    @Override
    public Json value(final int index) {
        return this.origin().value(index);
    }

    /**
     * Parses the source text into a Json object lazily.
     *
     * @return Parsed Json
     */
    private Json origin() {
        return new ParsedOrigin(this.source).json();
    }
}
