package uz.maniac4j;

import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.build.Fallback;
import uz.maniac4j.alivejson.build.Strict;
import uz.maniac4j.alivejson.build.With;
import uz.maniac4j.alivejson.build.Without;
import uz.maniac4j.alivejson.io.Formatted;
import uz.maniac4j.alivejson.io.Parsed;
import uz.maniac4j.alivejson.types.JsonNumber;
import uz.maniac4j.alivejson.types.JsonObject;
import uz.maniac4j.alivejson.types.JsonString;

import java.util.Collections;

/**
 * Main entry point.
 *
 * @since 1.0
 */
public final class Main {

    /**
     * Constructor.
     */
    private Main() {
    }

    /**
     * Main method.
     *
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        Json json = new Parsed("{\"name\":\"AliveJson\"}");

        // Fails immediately if the required key is missing
        Json strictJson = new Strict(json);
        Json age = strictJson.value("age");
    }
}
