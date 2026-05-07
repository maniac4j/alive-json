package uz.maniac4j.alivejson.types;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link JsonNumber}.
 *
 * @since 1.0
 */
public final class JsonNumberTest {

    @Test
    public void returnsItsTextualValue() {
        MatcherAssert.assertThat(
            "JsonNumber failed to return its string representation",
            new JsonNumber(123.45).text(),
            Matchers.equalTo("123.45")
        );
    }

    @Test
    public void returnsNullObjectForAnyKey() {
        MatcherAssert.assertThat(
            "JsonNumber failed to return JsonNull when a child is requested by key",
            new JsonNumber(123).value("key"),
            Matchers.instanceOf(JsonNull.class)
        );
    }

    @Test
    public void returnsNullObjectForAnyIndex() {
        MatcherAssert.assertThat(
            "JsonNumber failed to return JsonNull when a child is requested by index",
            new JsonNumber(123).value(0),
            Matchers.instanceOf(JsonNull.class)
        );
    }
}
