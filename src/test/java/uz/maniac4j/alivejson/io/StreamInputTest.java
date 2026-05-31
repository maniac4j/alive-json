package uz.maniac4j.alivejson.io;

import java.io.StringReader;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link StreamInput}.
 *
 * @since 1.1
 */
public final class StreamInputTest {

    @Test
    public void readsCharacterByCharacter() {
        final StreamInput input = new StreamInput(new StringReader("abc"));
        MatcherAssert.assertThat(
            "StreamInput failed to read first character",
            input.current(),
            Matchers.equalTo('a')
        );
    }

    @Test
    public void advancesToNextCharacter() {
        final StreamInput input = new StreamInput(new StringReader("abc"));
        input.advance();
        MatcherAssert.assertThat(
            "StreamInput failed to advance to second character",
            input.current(),
            Matchers.equalTo('b')
        );
    }

    @Test
    public void matchesExpectedCharacter() {
        final StreamInput input = new StreamInput(new StringReader("hello"));
        MatcherAssert.assertThat(
            "StreamInput failed to match expected character",
            input.match('h'),
            Matchers.equalTo(true)
        );
    }

    @Test
    public void skipsWhitespace() {
        final StreamInput input = new StreamInput(new StringReader("   x"));
        input.skip();
        MatcherAssert.assertThat(
            "StreamInput failed to skip whitespace",
            input.current(),
            Matchers.equalTo('x')
        );
    }

    @Test
    public void detectsEndOfStream() {
        final StreamInput input = new StreamInput(new StringReader("a"));
        input.advance();
        MatcherAssert.assertThat(
            "StreamInput failed to detect end of stream",
            input.hasNext(),
            Matchers.equalTo(false)
        );
    }

    @Test
    public void handlesNonAsciiCharacters() {
        final StreamInput input = new StreamInput(new StringReader("©"));
        MatcherAssert.assertThat(
            "StreamInput failed to handle non-ASCII character",
            input.current(),
            Matchers.equalTo('©')
        );
    }
}
