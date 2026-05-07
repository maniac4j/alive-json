package uz.maniac4j.alivejson.types;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link JsonString}.
 *
 * @since 1.0
 */
public final class JsonStringTest {

    @Test
    public void returnsItsTextualValue() {
        MatcherAssert.assertThat(
            "JsonString failed to return its exact text value",
            new JsonString("hello").text(),
            Matchers.equalTo("\"hello\"")
        );
    }

    @Test
    public void returnsNullObjectForAnyKey() {
        MatcherAssert.assertThat(
            "JsonString failed to return JsonNull when a child is requested by key",
            new JsonString("hello").value("key"),
            Matchers.instanceOf(JsonNull.class)
        );
    }

    @Test
    public void returnsNullObjectForAnyIndex() {
        MatcherAssert.assertThat(
            "JsonString failed to return JsonNull when a child is requested by index",
            new JsonString("hello").value(0),
            Matchers.instanceOf(JsonNull.class)
        );
    }
}
