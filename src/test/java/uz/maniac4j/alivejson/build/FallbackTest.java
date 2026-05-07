package uz.maniac4j.alivejson.build;

import java.util.Collections;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import uz.maniac4j.alivejson.types.JsonNumber;
import uz.maniac4j.alivejson.types.JsonObject;
import uz.maniac4j.alivejson.types.JsonString;

/**
 * Test case for {@link Fallback}.
 *
 * @since 1.0
 */
public final class FallbackTest {

    @Test
    public void delegatesTextualRepresentation() {
        MatcherAssert.assertThat(
            "Fallback failed to delegate textual representation to the origin",
            new Fallback(new JsonObject(Collections.singletonMap("test", new JsonString("value"))), new JsonNumber(0)).text(),
            Matchers.equalTo("{\"test\":\"value\"}")
        );
    }

    @Test
    public void returnsExistingChildByKey() {
        MatcherAssert.assertThat(
            "Fallback failed to return the existing child when requested by key",
            new Fallback(new JsonObject(Collections.singletonMap("test", new JsonString("value"))), new JsonNumber(0)).value("test").text(),
            Matchers.equalTo("\"value\"")
        );
    }

    @Test
    public void returnsFallbackChildForMissingKey() {
        MatcherAssert.assertThat(
            "Fallback failed to return the fallback value for a missing key",
            new Fallback(new JsonObject(Collections.emptyMap()), new JsonNumber(404)).value("missing").text(),
            Matchers.equalTo("404")
        );
    }

    @Test
    public void returnsFallbackChildForMissingIndex() {
        MatcherAssert.assertThat(
            "Fallback failed to return the fallback value for an out-of-bounds index",
            new Fallback(new JsonObject(Collections.emptyMap()), new JsonNumber(404)).value(0).text(),
            Matchers.equalTo("404")
        );
    }
}
