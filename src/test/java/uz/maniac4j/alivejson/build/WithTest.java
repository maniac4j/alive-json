package uz.maniac4j.alivejson.build;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import uz.maniac4j.alivejson.types.JsonString;
import uz.maniac4j.alivejson.types.JsonObject;
import java.util.Collections;

/**
 * Test case for {@link With}.
 *
 * @since 1.0
 */
public final class WithTest {

    @Test
    public void delegatesTextualRepresentation() {
        MatcherAssert.assertThat(
            "With failed to delegate textual representation to the origin",
            new With(new JsonObject(Collections.emptyMap()), "test", new JsonString("value")).text(),
            Matchers.equalTo("{\"test\":\"value\"}")
        );
    }

    @Test
    public void returnsInjectedChildByKey() {
        MatcherAssert.assertThat(
            "With failed to return the injected child when requested by key",
            new With(new JsonObject(Collections.emptyMap()), "newKey", new JsonString("newValue")).value("newKey").text(),
            Matchers.equalTo("\"newValue\"")
        );
    }

    @Test
    public void delegatesUnmatchedKeyLookup() {
        MatcherAssert.assertThat(
            "With failed to delegate unmatched key lookup to the origin",
            new With(new JsonObject(Collections.emptyMap()), "newKey", new JsonString("newValue")).value("otherKey").text(),
            Matchers.equalTo("null")
        );
    }

    @Test
    public void delegatesIndexLookup() {
        MatcherAssert.assertThat(
            "With failed to delegate index lookup to the origin",
            new With(new JsonString("hello"), "newKey", new JsonString("newValue")).value(0).text(),
            Matchers.equalTo("null")
        );
    }
}
