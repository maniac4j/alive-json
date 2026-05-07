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
     * Source text.
     */
    private final String source;

    /**
     * Constructor.
     *
     * @param text Source text
     */
    ParsedOrigin(final String text) {
        this.source = text;
    }

    /**
     * Parses the source into a Json object.
     *
     * @return Json object
     */
    Json json() {
        return new ParsedElement(new Cursor(this.source)).value();
    }
}
