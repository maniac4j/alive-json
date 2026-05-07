package uz.maniac4j.alivejson.types;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import uz.maniac4j.alivejson.Json;

/**
 * Test case for {@link JsonObject}.
 *
 * @since 1.0
 */
public final class JsonObjectTest {

    @Test
    public void returnsEmptyObjectTextualValue() {
        MatcherAssert.assertThat(
            "JsonObject failed to return '{}' string representation",
            new JsonObject(Collections.emptyMap()).text(),
            Matchers.equalTo("{}")
        );
    }

    @Test
    public void returnsChildElementByExistingKey() {
        MatcherAssert.assertThat(
            "JsonObject failed to return the correct child by key",
            new JsonObject(Collections.singletonMap("test", new JsonString("value"))).value("test").text(),
            Matchers.equalTo("\"value\"")
        );
    }

    @Test
    public void returnsNullObjectForMissingKey() {
        MatcherAssert.assertThat(
            "JsonObject failed to return JsonNull for a non-existent key",
            new JsonObject(Collections.emptyMap()).value("missing"),
            Matchers.instanceOf(JsonNull.class)
        );
    }

    @Test
    public void returnsNullObjectForAnyIndex() {
        MatcherAssert.assertThat(
            "JsonObject failed to return JsonNull when a child is requested by index",
            new JsonObject(Collections.emptyMap()).value(0),
            Matchers.instanceOf(JsonNull.class)
        );
    }
}
