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
     * Returns true if input has not reached the end.
     *
     * @return True if has next
     */
    boolean hasNext();

}
