package uz.maniac4j.alivejson.types;

import java.util.Arrays;
import java.util.Collections;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link JsonArray}.
 *
 * @since 1.0
 */
public final class JsonArrayTest {

    @Test
    public void returnsEmptyArrayTextualValue() {
        MatcherAssert.assertThat(
            "JsonArray failed to return '[]' string representation",
            new JsonArray(Collections.emptyList()).text(),
            Matchers.equalTo("[]")
        );
    }

    @Test
    public void returnsChildElementByValidIndex() {
        MatcherAssert.assertThat(
            "JsonArray failed to return the correct child by index",
            new JsonArray(Arrays.asList(new JsonString("zero"), new JsonString("one"))).value(1).text(),
            Matchers.equalTo("\"one\"")
        );
    }

    @Test
    public void returnsNullObjectForOutOfBoundsIndex() {
        MatcherAssert.assertThat(
            "JsonArray failed to return JsonNull for an out-of-bounds index",
            new JsonArray(Collections.emptyList()).value(0),
            Matchers.instanceOf(JsonNull.class)
        );
    }

    @Test
    public void returnsNullObjectForAnyKey() {
        MatcherAssert.assertThat(
            "JsonArray failed to return JsonNull when a child is requested by key",
            new JsonArray(Collections.emptyList()).value("key"),
            Matchers.instanceOf(JsonNull.class)
        );
    }
}
