package uz.maniac4j.alivejson.build;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.types.JsonString;
import uz.maniac4j.alivejson.types.JsonObject;
import uz.maniac4j.alivejson.types.JsonNull;
import java.util.HashMap;
import java.util.Map;

/**
 * Test case for {@link Without}.
 *
 * @since 1.0
 */
public final class WithoutTest {

    @Test
    public void delegatesTextualRepresentation() {
        MatcherAssert.assertThat(
            "Without failed to delegate textual representation to the origin",
            new Without(new JsonObject(java.util.Collections.singletonMap("test", new JsonString("value"))), "key").text(),
            Matchers.equalTo("{\"test\":\"value\"}")
        );
    }

    @Test
    public void returnsNullObjectForRemovedKey() {
        MatcherAssert.assertThat(
            "Without failed to return JsonNull for the removed key",
            new Without(new JsonObject(java.util.Collections.singletonMap("keyToRemove", new JsonString("value"))), "keyToRemove").value("keyToRemove"),
            Matchers.instanceOf(JsonNull.class)
        );
    }

    @Test
    public void delegatesUnmatchedKeyLookup() {
        MatcherAssert.assertThat(
            "Without failed to delegate unmatched key lookup to the origin",
            new Without(new JsonObject(java.util.Collections.singletonMap("keptKey", new JsonString("keptValue"))), "keyToRemove").value("keptKey").text(),
            Matchers.equalTo("\"keptValue\"")
        );
    }

    @Test
    public void delegatesIndexLookup() {
        MatcherAssert.assertThat(
            "Without failed to delegate index lookup to the origin",
            new Without(new JsonObject(java.util.Collections.emptyMap()), "keyToRemove").value(0).text(),
            Matchers.equalTo("null")
        );
    }
}
