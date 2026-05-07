package uz.maniac4j.alivejson.io;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import uz.maniac4j.alivejson.types.JsonNumber;

/**
 * Test case for {@link ParsedNumber}.
 *
 * @since 1.0
 */
public final class ParsedNumberTest {

    @Test
    public void parsesInteger() {
        MatcherAssert.assertThat(
            "ParsedNumber failed to parse an integer",
            new ParsedNumber(new Cursor("123")).value(),
            Matchers.instanceOf(JsonNumber.class)
        );
    }

    @Test
    public void parsesDouble() {
        MatcherAssert.assertThat(
            "ParsedNumber failed to parse a double",
            new ParsedNumber(new Cursor("123.45")).value().text(),
            Matchers.equalTo("123.45")
        );
    }
}
