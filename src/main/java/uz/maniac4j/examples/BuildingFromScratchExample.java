package uz.maniac4j.examples;

import java.util.Collections;
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.build.With;
import uz.maniac4j.alivejson.build.Without;
import uz.maniac4j.alivejson.io.Formatted;
import uz.maniac4j.alivejson.types.JsonNumber;
import uz.maniac4j.alivejson.types.JsonObject;
import uz.maniac4j.alivejson.types.JsonString;

/**
 * Complex usage example demonstrating building JSON from scratch.
 * Shows how to compose objects immutably using decorators.
 *
 * Example of usage:
 * BuildingFromScratchExample.main(new String[]{});
 *
 * @since 1.0
 */
public final class BuildingFromScratchExample {

    /**
     * Constructor.
     */
    private BuildingFromScratchExample() {
    }

    /**
     * Main method.
     *
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        final Json empty = new JsonObject(Collections.emptyMap());
        final Json first = new With(empty, "id", new JsonNumber(101));
        final Json second = new With(first, "name", new JsonString("Pure OOP"));
        final Json third = new With(second, "status", new JsonString("Active"));
        final Json removed = new Without(third, "status");
        System.out.println(new Formatted(removed).text());
    }
}
