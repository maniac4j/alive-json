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
    public void advancesCorrectly() {
        final Cursor cursor = new Cursor("abc");
        cursor.advance();
        MatcherAssert.assertThat(
            "Cursor failed to advance to next character",
            cursor.current(),
            Matchers.equalTo('b')
        );
    }

    @Test
    public void detectsHasNext() {
        MatcherAssert.assertThat(
            "Cursor failed to detect that it has more characters",
            new Cursor("a").hasNext(),
            Matchers.equalTo(true)
        );
    }
}
