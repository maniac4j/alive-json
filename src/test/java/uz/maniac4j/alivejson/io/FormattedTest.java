package uz.maniac4j.alivejson.io;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import uz.maniac4j.alivejson.types.JsonString;

/**
 * Test case for {@link Formatted}.
 *
 * @since 1.0
 */
public final class FormattedTest {

    @Test
    public void delegatesTextualRepresentation() {
        MatcherAssert.assertThat(
            "Formatted failed to delegate textual representation to the origin",
            new Formatted(new JsonString("hello")).text(),
            Matchers.equalTo("\"hello\"")
        );
    }

    @Test
    public void delegatesKeyLookup() {
        MatcherAssert.assertThat(
            "Formatted failed to delegate key lookup to the origin",
            new Formatted(new JsonString("hello")).value("key").text(),
            Matchers.equalTo("null")
        );
    }

    @Test
    public void delegatesIndexLookup() {
        MatcherAssert.assertThat(
            "Formatted failed to delegate index lookup to the origin",
            new Formatted(new JsonString("hello")).value(0).text(),
            Matchers.equalTo("null")
        );
    }
}
