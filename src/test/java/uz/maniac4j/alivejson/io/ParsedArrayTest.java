package uz.maniac4j.alivejson.io;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import uz.maniac4j.alivejson.types.JsonArray;

/**
 * Test case for {@link ParsedArray}.
 *
 * @since 1.0
 */
public final class ParsedArrayTest {

    @Test
    public void parsesEmptyArray() {
        MatcherAssert.assertThat(
            "ParsedArray failed to parse an empty JSON array into JsonArray",
            new ParsedArray(new Cursor("[]")).value(),
            Matchers.instanceOf(JsonArray.class)
        );
    }

    @Test
    public void parsesElementsCorrectly() {
        MatcherAssert.assertThat(
            "ParsedArray failed to parse array elements correctly",
            new ParsedArray(new Cursor("[\"first\",2]")).value().value(0).text(),
            Matchers.equalTo("\"first\"")
        );
    }
}
