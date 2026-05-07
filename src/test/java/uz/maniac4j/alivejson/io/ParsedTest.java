package uz.maniac4j.alivejson.io;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import uz.maniac4j.alivejson.types.JsonNull;

/**
 * Test case for {@link Parsed}.
 *
 * @since 1.0
 */
public final class ParsedTest {

    @Test
    public void returnsSourceTextualValue() {
        MatcherAssert.assertThat(
            "Parsed failed to return the exact source text",
            new Parsed("{\"test\":1}").text(),
            Matchers.equalTo("{\"test\":1}")
        );
    }

    @Test
    public void parsesJsonObjectAndReturnsChildByKey() {
        MatcherAssert.assertThat(
            "Parsed failed to return child by key from a JSON object string",
            new Parsed("{\"key\":\"value\"}").value("key").text(),
            Matchers.equalTo("\"value\"")
        );
    }

    @Test
    public void parsesJsonArrayAndReturnsChildByIndex() {
        MatcherAssert.assertThat(
            "Parsed failed to return child by index from a JSON array string",
            new Parsed("[\"first\",\"second\"]").value(1).text(),
            Matchers.equalTo("\"second\"")
        );
    }

    @Test
    public void returnsNullObjectForInvalidKey() {
        MatcherAssert.assertThat(
            "Parsed failed to return JsonNull when a missing child is requested by key",
            new Parsed("{\"test\":1}").value("missing"),
            Matchers.instanceOf(JsonNull.class)
        );
    }

    @Test
    public void returnsNullObjectForInvalidIndex() {
        MatcherAssert.assertThat(
            "Parsed failed to return JsonNull when a missing child is requested by index",
            new Parsed("[]").value(0),
            Matchers.instanceOf(JsonNull.class)
        );
    }
}
