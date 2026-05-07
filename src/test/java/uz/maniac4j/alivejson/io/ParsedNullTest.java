package uz.maniac4j.alivejson.io;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import uz.maniac4j.alivejson.types.JsonNull;

/**
 * Test case for {@link ParsedNull}.
 *
 * @since 1.0
 */
public final class ParsedNullTest {

    @Test
    public void parsesNull() {
        MatcherAssert.assertThat(
            "ParsedNull failed to parse 'null'",
            new ParsedNull(new Cursor("null")).value(),
            Matchers.instanceOf(JsonNull.class)
        );
    }
}
