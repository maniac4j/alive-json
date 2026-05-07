package uz.maniac4j.alivejson.io;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link ParsedBool}.
 *
 * @since 1.0
 */
public final class ParsedBoolTest {

    @Test
    public void parsesTrue() {
        MatcherAssert.assertThat(
            "ParsedBool failed to parse 'true'",
            new ParsedBool(new Cursor("true")).value().text(),
            Matchers.equalTo("true")
        );
    }

    @Test
    public void parsesFalse() {
        MatcherAssert.assertThat(
            "ParsedBool failed to parse 'false'",
            new ParsedBool(new Cursor("false")).value().text(),
            Matchers.equalTo("false")
        );
    }
}
