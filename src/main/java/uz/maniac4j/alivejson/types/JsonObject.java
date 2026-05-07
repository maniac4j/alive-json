package uz.maniac4j.alivejson.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import uz.maniac4j.alivejson.Json;

/**
 * JSON object representation.
 *
 * @since 1.0
 */
public final class JsonObject implements Json {

    /**
     * Map of elements.
     */
    private final Map<String, Json> map;

    /**
     * Constructor.
     *
     * @param elements Map of elements
     */
    public JsonObject(final Map<String, Json> elements) {
        this.map = elements;
    }

    @Override
    public String text() {
        if (this.map.isEmpty()) {
            return "{}";
        }
        final List<String> parts = new ArrayList<>(this.map.size());
        for (final Map.Entry<String, Json> entry : this.map.entrySet()) {
            parts.add("\"" + entry.getKey() + "\":" + entry.getValue().text());
        }
        return "{" + String.join(",", parts) + "}";
    }

    @Override
    public Json value(final String key) {
        return this.map.getOrDefault(key, new JsonNull());
    }

    @Override
    public Json value(final int index) {
        return new JsonNull();
    }
}
