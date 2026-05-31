package uz.maniac4j.alivejson.io;

import uz.maniac4j.alivejson.Json;

/**
 * Parses a JSON string into a Json object.
 * Acts as the entry point for decentralized parsing.
 *
 * @since 1.0
 */
final class ParsedOrigin {

    /**
     * Source input.
     */
    private final Input input;

    /**
     * Primary constructor.
     *
     * @param src Source input
     */
    ParsedOrigin(final Input src) {
        this.input = src;
    }

    /**
     * Secondary constructor.
     *
     * @param text Source text
     */
    ParsedOrigin(final String text) {
        this(new Cursor(text));
    }

    /**
     * Parses the input into a Json object.
     *
     * @return Json object
     */
    Json json() {
        return new ParsedElement(this.input).value();
    }
}
