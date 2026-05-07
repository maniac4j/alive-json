package uz.maniac4j.alivejson.io;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import uz.maniac4j.alivejson.types.JsonArray;
import uz.maniac4j.alivejson.types.JsonBool;
import uz.maniac4j.alivejson.types.JsonNull;
import uz.maniac4j.alivejson.types.JsonNumber;
import uz.maniac4j.alivejson.types.JsonObject;
import uz.maniac4j.alivejson.types.JsonString;

/**
 * Test case for {@link ParsedOrigin}.
 *
 * @since 1.0
 */
public final class ParsedOriginTest {

    @Test
    public void parsesEmptyObject() {
        MatcherAssert.assertThat(
            "ParsedOrigin failed to parse an empty JSON object into JsonObject",
            new ParsedOrigin("{}").json(),
            Matchers.instanceOf(JsonObject.class)
        );
    }

    @Test
    public void parsesEmptyArray() {
        MatcherAssert.assertThat(
            "ParsedOrigin failed to parse an empty JSON array into JsonArray",
            new ParsedOrigin("[]").json(),
            Matchers.instanceOf(JsonArray.class)
        );
    }

    @Test
    public void parsesStringValue() {
        MatcherAssert.assertThat(
            "ParsedOrigin failed to parse a string value into JsonString",
            new ParsedOrigin("\"hello\"").json(),
            Matchers.instanceOf(JsonString.class)
        );
    }

    @Test
    public void parsesIntegerValue() {
        MatcherAssert.assertThat(
            "ParsedOrigin failed to parse an integer value into JsonNumber",
            new ParsedOrigin("123").json(),
            Matchers.instanceOf(JsonNumber.class)
        );
    }

    @Test
    public void parsesDoubleValue() {
        MatcherAssert.assertThat(
            "ParsedOrigin failed to parse a double value into JsonNumber",
            new ParsedOrigin("12.34").json(),
            Matchers.instanceOf(JsonNumber.class)
        );
    }

    @Test
    public void parsesTrueBoolean() {
        MatcherAssert.assertThat(
            "ParsedOrigin failed to parse 'true' into JsonBool",
            new ParsedOrigin("true").json(),
            Matchers.instanceOf(JsonBool.class)
        );
    }

    @Test
    public void parsesFalseBoolean() {
        MatcherAssert.assertThat(
            "ParsedOrigin failed to parse 'false' into JsonBool",
            new ParsedOrigin("false").json(),
            Matchers.instanceOf(JsonBool.class)
        );
    }

    @Test
    public void parsesNullValue() {
        MatcherAssert.assertThat(
            "ParsedOrigin failed to parse 'null' into JsonNull",
            new ParsedOrigin("null").json(),
            Matchers.instanceOf(JsonNull.class)
        );
    }

    @Test
    public void ignoresWhitespace() {
        MatcherAssert.assertThat(
            "ParsedOrigin failed to ignore surrounding whitespace during parsing",
            new ParsedOrigin(" \n\t { \n \"key\" : \n 1 \n } \t").json(),
            Matchers.instanceOf(JsonObject.class)
        );
    }
}
