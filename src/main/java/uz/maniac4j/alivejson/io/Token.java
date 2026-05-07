package uz.maniac4j.alivejson.io;

import uz.maniac4j.alivejson.Json;

/**
 * Represents a parsed token from the JSON string.
 * This is the internal contract for all decentralized parser objects.
 *
 * @since 1.0
 */
interface Token {

    /**
     * Evaluates and returns the parsed element.
     *
     * @return Parsed Json
     */
    Json value();
}
