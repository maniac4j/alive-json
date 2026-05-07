package uz.maniac4j.alivejson.io;

/**
 * Input source for JSON parsing.
 *
 * @since 1.0
 */
interface Input {

    /**
     * Skips whitespace characters.
     */
    void skip();

    /**
     * Current character.
     *
     * @return Current character
     */
    char current();

    /**
     * Checks if current character matches the given character and advances.
     *
     * @param expected Expected character
     * @return True if matched
     */
    boolean match(char expected);

    /**
     * Advances the input by one.
     */
    void advance();

    /**
     * Advances the input by the given amount.
     *
     * @param amount Amount to advance
     */
    void advance(int amount);

    /**
     * Returns true if input has not reached the end.
     *
     * @return True if has next
     */
    boolean hasNext();

    /**
     * Returns true if source starts with prefix at current position.
     *
     * @param prefix Prefix to check
     * @return True if starts with prefix
     */
    boolean startsWith(String prefix);

    /**
     * Substring from start to current position.
     *
     * @param start Start index
     * @return Substring
     */
    String substring(int start);

    /**
     * Current raw index position.
     *
     * @return Current index
     */
    int position();
}
