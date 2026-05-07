package uz.maniac4j.examples;

import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.io.Parsed;

/**
 * Simple usage example demonstrating basic JSON parsing and value extraction.
 *
 * Example of usage:
 * SimpleExample.main(new String[]{});
 *
 * @since 1.0
 */
public final class SimpleExample {

    /**
     * Constructor.
     */
    private SimpleExample() {
    }

    /**
     * Main method.
     *
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        final Json json = new Parsed("{\"name\":\"AliveJson\",\"version\":1}");
        System.out.println(json.value("name").text());
        System.out.println(json.value("version").text());
    }
}
