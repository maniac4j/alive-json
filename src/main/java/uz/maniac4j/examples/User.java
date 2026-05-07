package uz.maniac4j.examples;

/**
 * Domain interface representing a User.
 * This belongs to your business logic, completely unaware of JSON.
 *
 * @since 1.0
 */
public interface User {

    /**
     * User's name.
     *
     * @return Name
     */
    String name();

    /**
     * User's age.
     *
     * @return Age
     */
    int age();
}
