package uz.maniac4j.alivejson.io;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import uz.maniac4j.alivejson.types.JsonString;

/**
 * Test case for {@link ParsedString}.
 *
 * @since 1.0
 */
public final class ParsedStringTest {

    @Test
    public void parsesSimpleString() {
        MatcherAssert.assertThat(
            "ParsedString failed to parse a simple string value",
            new ParsedString(new Cursor("\"hello\"")).value(),
            Matchers.instanceOf(JsonString.class)
        );
    }

    @Test
    public void parsesStringWithEscapes() {
        MatcherAssert.assertThat(
            "ParsedString failed to parse a string with escape characters",
            new ParsedString(new Cursor("\"he\\\"llo\"")).value().text(),
            Matchers.equalTo("\"he\\\"llo\"")
        );
    }
}
