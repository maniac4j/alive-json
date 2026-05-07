package uz.maniac4j.alivejson.io;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import uz.maniac4j.alivejson.types.JsonObject;

/**
 * Test case for {@link ParsedObject}.
 *
 * @since 1.0
 */
public final class ParsedObjectTest {

    @Test
    public void parsesEmptyObject() {
        MatcherAssert.assertThat(
            "ParsedObject failed to parse an empty JSON object into JsonObject",
            new ParsedObject(new Cursor("{}")).value(),
            Matchers.instanceOf(JsonObject.class)
        );
    }

    @Test
    public void parsesNestedObject() {
        MatcherAssert.assertThat(
            "ParsedObject failed to parse nested properties correctly",
            new ParsedObject(new Cursor("{\"key\":{\"inner\":1}}")).value().value("key").text(),
            Matchers.equalTo("{\"inner\":1}")
        );
    }
}
