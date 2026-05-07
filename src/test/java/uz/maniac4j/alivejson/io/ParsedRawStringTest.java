package uz.maniac4j.alivejson.io;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link ParsedRawString}.
 *
 * @since 1.0
 */
public final class ParsedRawStringTest {

    @Test
    public void parsesRawStringCorrectly() {
        MatcherAssert.assertThat(
            "ParsedRawString failed to extract raw string without quotes",
            new ParsedRawString(new Cursor("\"hello\"")).value(),
            Matchers.equalTo("hello")
        );
    }
}
