package uz.maniac4j.alivejson.io;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import uz.maniac4j.alivejson.Json;

/**
 * Test case for {@link ParsedStream}.
 *
 * @since 1.1
 */
public final class ParsedStreamTest {

    @Test
    public void parsesFromInputStream() {
        final String json = "{\"id\":101,\"name\":\"EO\"}";
        final Json parsed = new ParsedStream(
            new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8))
        );
        MatcherAssert.assertThat(
            "ParsedStream failed to extract value from InputStream",
            parsed.value("id").text(),
            Matchers.equalTo("101")
        );
    }

    @Test
    public void cachesParsedResult() {
        final String json = "{\"version\":1}";
        final Json parsed = new ParsedStream(
            new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8))
        );
        parsed.value("version");
        MatcherAssert.assertThat(
            "ParsedStream failed to return cached value on second access",
            parsed.value("version").text(),
            Matchers.equalTo("1")
        );
    }

    @Test
    public void handlesComplexObject() {
        final String json = "{\"user\":{\"active\":true}}";
        final Json parsed = new ParsedStream(
            new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8))
        );
        MatcherAssert.assertThat(
            "ParsedStream failed to parse nested object",
            parsed.value("user").value("active").text(),
            Matchers.equalTo("true")
        );
    }
}
