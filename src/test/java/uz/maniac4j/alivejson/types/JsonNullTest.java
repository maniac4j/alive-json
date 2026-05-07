package uz.maniac4j.alivejson.types;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link JsonNull}.
 *
 * @since 1.0
 */
public final class JsonNullTest {

    @Test
    public void returnsItsTextualValue() {
        MatcherAssert.assertThat(
            "JsonNull failed to return 'null' as string",
            new JsonNull().text(),
            Matchers.equalTo("null")
        );
    }

    @Test
    public void returnsItselfForAnyKey() {
        MatcherAssert.assertThat(
            "JsonNull failed to return itself when a child is requested by key",
            new JsonNull().value("key"),
            Matchers.instanceOf(JsonNull.class)
        );
    }

    @Test
    public void returnsItselfForAnyIndex() {
        MatcherAssert.assertThat(
            "JsonNull failed to return itself when a child is requested by index",
            new JsonNull().value(0),
            Matchers.instanceOf(JsonNull.class)
        );
    }
}
