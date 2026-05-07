package uz.maniac4j.alivejson.build;

import java.util.Map;
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.types.JsonObject;

/**
 * JSON with an added key-value pair.
 *
 * @since 1.0
 */
public final class With implements Json {

    /**
     * Original JSON.
     */
    private final Json origin;

    /**
     * Key to add.
     */
    private final String key;

    /**
     * Value to add.
     */
    private final Json val;

    /**
     * Constructor.
     *
     * @param json Original JSON
     * @param name Key to add
     * @param value Value to add
     */
    public With(final Json json, final String name, final Json value) {
        this.origin = json;
        this.key = name;
        this.val = value;
    }

    @Override
    public String text() {
        final Map<String, Json> map = new ParsedMap(this.origin.text()).value();
        map.put(this.key, this.val);
        return new JsonObject(map).text();
    }

    @Override
    public Json value(final String k) {
        return this.key.equals(k) ? this.val : this.origin.value(k);
    }

    @Override
    public Json value(final int index) {
        return this.origin.value(index);
    }
}
