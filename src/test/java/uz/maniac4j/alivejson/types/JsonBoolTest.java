package uz.maniac4j.alivejson.types;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link JsonBool}.
 *
 * @since 1.0
 */
public final class JsonBoolTest {

    @Test
    public void returnsTrueTextualValue() {
        MatcherAssert.assertThat(
            "JsonBool failed to return 'true' as string",
            new JsonBool(true).text(),
            Matchers.equalTo("true")
        );
    }

    @Test
    public void returnsFalseTextualValue() {
        MatcherAssert.assertThat(
            "JsonBool failed to return 'false' as string",
            new JsonBool(false).text(),
            Matchers.equalTo("false")
        );
    }

    @Test
    public void returnsNullObjectForAnyKey() {
        MatcherAssert.assertThat(
            "JsonBool failed to return JsonNull when a child is requested by key",
            new JsonBool(true).value("key"),
            Matchers.instanceOf(JsonNull.class)
        );
    }

    @Test
    public void returnsNullObjectForAnyIndex() {
        MatcherAssert.assertThat(
            "JsonBool failed to return JsonNull when a child is requested by index",
            new JsonBool(false).value(0),
            Matchers.instanceOf(JsonNull.class)
        );
    }
}
