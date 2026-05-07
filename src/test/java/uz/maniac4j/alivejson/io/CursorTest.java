package uz.maniac4j.alivejson.io;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link Cursor}.
 *
 * @since 1.0
 */
public final class CursorTest {

    @Test
    public void skipsWhitespaceProperly() {
        final Cursor cursor = new Cursor("   a");
        cursor.skip();
        MatcherAssert.assertThat(
            "Cursor failed to skip whitespaces correctly",
            cursor.current(),
            Matchers.equalTo('a')
        );
    }

    @Test
    public void matchesAndAdvancesIfExpectedChar() {
        MatcherAssert.assertThat(
            "Cursor failed to match expected character",
            new Cursor("hello").match('h'),
            Matchers.equalTo(true)
        );
    }

    @Test
    public void failsToMatchIfUnexpectedChar() {
        MatcherAssert.assertThat(
            "Cursor failed to reject unexpected character",
            new Cursor("hello").match('x'),
            Matchers.equalTo(false)
        );
    }

    @Test
    public void advancesByGivenAmount() {
        final Cursor cursor = new Cursor("abcdef");
        cursor.advance(3);
        MatcherAssert.assertThat(
            "Cursor failed to advance by specific amount",
            cursor.current(),
            Matchers.equalTo('d')
        );
    }

    @Test
    public void detectsPrefixCorrectly() {
        MatcherAssert.assertThat(
            "Cursor failed to detect prefix correctly",
            new Cursor("hello world").startsWith("hello"),
            Matchers.equalTo(true)
        );
    }
}
