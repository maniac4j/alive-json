package uz.maniac4j.alivejson.io;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;
import uz.maniac4j.alivejson.Json;

/**
 * JSON parsed from an InputStream.
 * This implementation is lazy and caches the result after first parse.
 *
 * <p>Example usage:</p>
 * <pre>
 * final InputStream stream = ...;
 * final Json json = new ParsedStream(stream);
 * String name = json.value("name").text();
 * </pre>
 *
 * @since 1.1
 */
public final class ParsedStream implements Json {

    /**
     * Source stream.
     */
    private final InputStream source;

    /**
     * Cached parsed JSON.
     */
    private final AtomicReference<Json> cache;

    /**
     * Constructor.
     *
     * @param stream Source stream
     */
    public ParsedStream(final InputStream stream) {
        this.source = stream;
        this.cache = new AtomicReference<>();
    }

    @Override
    public String text() {
        return this.origin().text();
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
     * Parses the stream and caches the result.
     *
     * @return Parsed Json
     */
    private Json origin() {
        if (this.cache.get() == null) {
            try (Reader reader = new InputStreamReader(this.source, StandardCharsets.UTF_8)) {
                this.cache.compareAndSet(null, new ParsedOrigin(new StreamInput(reader)).json());
            } catch (final Exception ex) {
                throw new IllegalStateException(ex);
            }
        }
        return this.cache.get();
    }
}
