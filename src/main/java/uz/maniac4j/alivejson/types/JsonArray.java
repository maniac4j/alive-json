package uz.maniac4j.alivejson.types;

import java.util.ArrayList;
import java.util.List;
import uz.maniac4j.alivejson.Json;

/**
 * JSON array representation.
 *
 * @since 1.0
 */
public final class JsonArray implements Json {

    /**
     * List of elements.
     */
    private final List<Json> list;

    /**
     * Constructor.
     *
     * @param elements List of elements
     */
    public JsonArray(final List<Json> elements) {
        this.list = elements;
    }

    @Override
    public String text() {
        if (this.list.isEmpty()) {
            return "[]";
        }
        final List<String> parts = new ArrayList<>(this.list.size());
        for (final Json item : this.list) {
            parts.add(item.text());
        }
        return "[" + String.join(",", parts) + "]";
    }

    @Override
    public Json value(final String key) {
        return new JsonNull();
    }

    @Override
    public Json value(final int index) {
        return index >= 0 && index < this.list.size() ? this.list.get(index) : new JsonNull();
    }
}
