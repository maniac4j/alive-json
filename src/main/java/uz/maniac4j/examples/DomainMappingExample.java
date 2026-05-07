package uz.maniac4j.examples;

import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.io.Parsed;

/**
 * Example demonstrating how to map JSON to Domain objects
 * without Reflection, Setters, or DTOs.
 *
 * Example of usage:
 * DomainMappingExample.main(new String[]{});
 *
 * @since 1.0
 */
public final class DomainMappingExample {

    /**
     * Constructor.
     */
    private DomainMappingExample() {
    }

    /**
     * Main method.
     *
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        final Json source = new Parsed("{\"name\":\"Ali\", \"age\":25}");
        final User user = new JsonUser(source);
        System.out.println("User name: " + user.name());
        System.out.println("User age: " + user.age());
    }
}
