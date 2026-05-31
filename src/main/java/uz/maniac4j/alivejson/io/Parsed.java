package uz.maniac4j.alivejson.io;

import java.util.concurrent.atomic.AtomicReference;
import uz.maniac4j.alivejson.Json;

/**
 * Parsed JSON from a string.
 * This implementation is lazy and caches the result after first parse.
 *
 * @since 1.0
 */
public final class Parsed implements Json {

    /**
     * Source text.
     */
    private final String source;

    /**
     * Cached parsed JSON.
     */
    private final AtomicReference<Json> cache;

    /**
     * Constructor.
     *
     * @param text Source text
     */
    public Parsed(final String text) {
        this.source = text;
        this.cache = new AtomicReference<>();
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
        if (this.cache.get() == null) {
            this.cache.compareAndSet(null, new ParsedOrigin(this.source).json());
        }
        return this.cache.get();
    }
}
