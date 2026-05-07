package uz.maniac4j.examples;

import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.build.Fallback;
import uz.maniac4j.alivejson.types.JsonNumber;

/**
 * JSON implementation of a User.
 * Acts as a declarative "mapper" without reflection or DTOs.
 *
 * @since 1.0
 */
public final class JsonUser implements User {

    /**
     * Original JSON.
     */
    private final Json origin;

    /**
     * Constructor.
     *
     * @param json JSON source
     */
    public JsonUser(final Json json) {
        this.origin = json;
    }

    @Override
    public String name() {
        return this.origin.value("name").text().replace("\"", "");
    }

    @Override
    public int age() {
        final Json safeAge = new Fallback(this.origin, new JsonNumber(0)).value("age");
        return Integer.parseInt(safeAge.text());
    }
}
