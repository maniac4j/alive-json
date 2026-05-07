package uz.maniac4j.alivejson;

/**
 * Core interface for representing a JSON entity.
 * Represents any JSON element avoiding nulls and mutability.
 *
 * @since 1.0
 */
public interface Json {

    /**
     * Textual representation of this JSON element.
     *
     * @return Text representation
     */
    String text();

    /**
     * Child JSON element by its key.
     *
     * @param key Lookup key
     * @return Child element
     */
    Json value(String key);

    /**
     * Child JSON element by its index.
     *
     * @param index Lookup index
     * @return Child element
     */
    Json value(int index);

}
