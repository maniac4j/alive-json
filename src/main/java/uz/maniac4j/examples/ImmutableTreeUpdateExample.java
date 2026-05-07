package uz.maniac4j.examples;

import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.build.With;
import uz.maniac4j.alivejson.io.Formatted;
import uz.maniac4j.alivejson.io.Parsed;
import uz.maniac4j.alivejson.types.JsonString;

/**
 * Ultra complex example demonstrating immutable tree updates on deeply nested JSON.
 * Shows the functional approach to updating nested states.
 *
 * Example of usage:
 * ImmutableTreeUpdateExample.main(new String[]{});
 *
 * @since 1.0
 */
public final class ImmutableTreeUpdateExample {

    /**
     * Constructor.
     */
    private ImmutableTreeUpdateExample() {
    }

    /**
     * Main method.
     *
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        final Json origin = new Parsed("{\"user\":{\"profile\":{\"theme\":\"dark\"}}}");
        final Json profile = origin.value("user").value("profile");
        final Json updatedProfile = new With(profile, "theme", new JsonString("light"));
        final Json user = origin.value("user");
        final Json updatedUser = new With(user, "profile", updatedProfile);
        final Json updatedOrigin = new With(origin, "user", updatedUser);
        System.out.println(new Formatted(updatedOrigin).text());
    }
}
