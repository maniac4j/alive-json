# Usage Guide

This guide explains how to use AliveJson effectively in your Java projects.

## Parsing JSON

The entry point for parsing strings is the `Parsed` class. Parsing is **lazy**, meaning the actual processing happens only when you access a value.

```java
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.io.Parsed;

final Json json = new Parsed("{\"id\": 1, \"name\": \"EO\"}");
```

## Parsing from InputStream

You can also parse JSON directly from an `InputStream`. This is more memory-efficient for large files or network streams.

```java
import java.io.InputStream;
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.io.ParsedStream;

final InputStream stream = ...;
final Json json = new ParsedStream(stream);
```

## Accessing Values

You can access object fields by key or array elements by index. If a value is missing, AliveJson returns a `JsonNull` object instead of `null`.

```java
final String name = json.value("name").text(); // Returns "EO"
final Json missing = json.value("unknown"); // Returns JsonNull object
final String text = missing.text(); // Returns "null" (Safe!)
```

## Decorators (Building & Modifying)

AliveJson uses the Decorator pattern to "modify" JSON objects. Since they are immutable, a decorator creates a new view of the data.

### With
Adds or updates a property.

```java
import uz.maniac4j.alivejson.build.With;
import uz.maniac4j.alivejson.types.JsonString;

final Json updated = new With(json, "version", new JsonString("1.0"));
```

### Without
Removes a property.

```java
import uz.maniac4j.alivejson.build.Without;

final Json simplified = new Without(json, "id");
```

### Formatted
Prints JSON with proper formatting (optional, as `text()` usually returns raw string).

```java
import uz.maniac4j.alivejson.io.Formatted;

System.out.println(new Formatted(json).text());
```

## Error Handling & Defaults

### Fallback
Returns a default value if the requested key or index is missing.

```java
import uz.maniac4j.alivejson.build.Fallback;
import uz.maniac4j.alivejson.types.JsonNumber;

// Returns 18 if "age" is missing
final Json age = new Fallback(json, new JsonNumber(18)).value("age");
```

### Strict
Follows the "Fail Fast" paradigm. Throws `IllegalArgumentException` if a key is missing.

```java
import uz.maniac4j.alivejson.build.Strict;

final Json age = new Strict(json).value("age"); // Throws exception if "age" missing
```
