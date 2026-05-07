package uz.maniac4j.alivejson.build;

import java.util.Map;
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.types.JsonNull;
import uz.maniac4j.alivejson.types.JsonObject;

/**
 * JSON without a specific key.
 *
 * @since 1.0
 */
public final class Without implements Json {

    /**
     * Original JSON.
     */
    private final Json origin;

    /**
     * Key to remove.
     */
    private final String key;

    /**
     * Constructor.
     *
     * @param json Original JSON
     * @param name Key to remove
     */
    public Without(final Json json, final String name) {
        this.origin = json;
        this.key = name;
    }

    @Override
    public String text() {
        final Map<String, Json> map = new ParsedMap(this.origin.text()).value();
        map.remove(this.key);
        return new JsonObject(map).text();
    }

    @Override
    public Json value(final String k) {
        return this.key.equals(k) ? new JsonNull() : this.origin.value(k);
    }

    @Override
    public Json value(final int index) {
        return this.origin.value(index);
    }
}
