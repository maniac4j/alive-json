package uz.maniac4j.alivejson.build;

import java.util.Collections;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import uz.maniac4j.alivejson.types.JsonObject;
import uz.maniac4j.alivejson.types.JsonString;

/**
 * Test case for {@link Strict}.
 *
 * @since 1.0
 */
public final class StrictTest {

    @Test
    public void delegatesTextualRepresentation() {
        MatcherAssert.assertThat(
            "Strict failed to delegate textual representation to the origin",
            new Strict(new JsonObject(Collections.singletonMap("test", new JsonString("value")))).text(),
            Matchers.equalTo("{\"test\":\"value\"}")
        );
    }

    @Test
    public void returnsExistingChildByKey() {
        MatcherAssert.assertThat(
            "Strict failed to return the existing child when requested by key",
            new Strict(new JsonObject(Collections.singletonMap("test", new JsonString("value")))).value("test").text(),
            Matchers.equalTo("\"value\"")
        );
    }

    @Test
    public void throwsExceptionForMissingKey() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new Strict(new JsonObject(Collections.emptyMap())).value("missing")
        );
    }

    @Test
    public void throwsExceptionForMissingIndex() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new Strict(new JsonObject(Collections.emptyMap())).value(0)
        );
    }
}
