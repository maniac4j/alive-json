# AliveJson

AliveJson is a strict, object-oriented JSON parsing and manipulation library for Java, built entirely on the principles of [Elegant Objects](https://www.elegantobjects.org/).

Most traditional JSON libraries rely heavily on DTOs, `null` references, getters/setters, and heavy central "Parser" classes. AliveJson takes a different approach. We treat JSON not as raw data structures to be manipulated, but as living, immutable objects.

## Performance vs. Maintainability

It is important to be upfront: **AliveJson is not designed to be the fastest JSON library.** If your project requires parsing millions of JSON objects per second (e.g., High-Frequency Trading or core infrastructure engines) where every microsecond counts, libraries like `fastjson2` or `Jackson` are much better suited for the job. They use low-level memory hacks, mutable states, and byte-level manipulation to achieve incredible speeds.

AliveJson optimizes for a different metric: **Developer time and code maintainability.** 

If you are writing business-logic-heavy applications where there is no room for errors, where the code will live for years, and where multiple team members will need to read and safely update it—then AliveJson and the "Pure OOP" philosophy can be invaluable. 

- **No `null`s:** The library never returns `null`. You get `JsonNull` objects instead. This eliminates `NullPointerException`s entirely.
- **Strict Immutability:** Every object is `final`. You don't mutate state; you compose decorators. This makes the code naturally thread-safe.
- **Decentralized Logic:** There is no "God Class" like `ObjectMapper`. Logic is distributed across small, readable objects.
- **Extensible Architecture:** The parsing mechanism depends on an `Input` interface, not hardcoded Strings. This Open-Closed Principle ensures the library can easily be extended to parse JSON directly from network sockets or files (`InputStream`) without changing a single line of internal logic.

We sacrifice raw machine speed to gain absolute stability and human readability.

## Examples

Here are a few ways you can use AliveJson in your projects.

### 1. Basic Parsing and Reading

Read a JSON string and extract values safely without worrying about null checks.

```java
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.io.Parsed;

public class BasicReading {
    public static void main(String[] args) {
        // Parsing is lazy; it only happens when you request a value.
        Json source = new Parsed("{\"name\":\"AliveJson\",\"version\":1}");
        
        System.out.println(source.value("name").text()); // "AliveJson"
        
        // Requesting a non-existent key returns a safe JsonNull object, not null!
        System.out.println(source.value("missing").text()); // "null"
    }
}
```

### 2. Building Objects from Scratch

You don't need raw strings to build JSON. You can compose it piece by piece using decorators.

```java
import java.util.Collections;
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.build.With;
import uz.maniac4j.alivejson.build.Without;
import uz.maniac4j.alivejson.io.Formatted;
import uz.maniac4j.alivejson.types.JsonNumber;
import uz.maniac4j.alivejson.types.JsonObject;
import uz.maniac4j.alivejson.types.JsonString;

public class BuildingFromScratch {
    public static void main(String[] args) {
        // Start with an empty object
        Json empty = new JsonObject(Collections.emptyMap());
        
        // Chain decorators to add properties immutably
        Json first = new With(empty, "id", new JsonNumber(101));
        Json second = new With(first, "name", new JsonString("Pure OOP"));
        Json third = new With(second, "status", new JsonString("Active"));
        
        // Remove a property immutably
        Json finalJson = new Without(third, "status");
        
        System.out.println(new Formatted(finalJson).text());
        // {"id":101,"name":"Pure OOP"}
    }
}
```

### 3. Deep Immutable Updates

Updating deeply nested fields is traditionally risky and mutable. AliveJson makes it functional and safe.

```java
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.build.With;
import uz.maniac4j.alivejson.io.Formatted;
import uz.maniac4j.alivejson.io.Parsed;
import uz.maniac4j.alivejson.types.JsonString;

public class DeepUpdate {
    public static void main(String[] args) {
        Json origin = new Parsed("{\"user\":{\"profile\":{\"theme\":\"dark\"}}}");
        
        // 1. Extract the deep element you want to change
        Json profile = origin.value("user").value("profile");
        
        // 2. Wrap it with the new value
        Json updatedProfile = new With(profile, "theme", new JsonString("light"));
        
        // 3. Wrap the parent
        Json updatedUser = new With(origin.value("user"), "profile", updatedProfile);
        
        // 4. Wrap the root
        Json updatedOrigin = new With(origin, "user", updatedUser);
        
        System.out.println(new Formatted(updatedOrigin).text());
        // {"user":{"profile":{"theme":"light"}}}
    }
}
```

### 4. Reading JSON from a File

Reading from a physical file is just as easy. Because parsing is lazy, it securely wraps the string content and parses only what you request.

```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.io.Parsed;

public class FileReading {
    public static void main(String[] args) throws Exception {
        // Read file content into a string
        Path path = Paths.get("data.json");
        String content = Files.readString(path);
        
        // Pass to the lazy parser
        Json fileJson = new Parsed(content);
        
        // Safely extract data
        System.out.println(fileJson.value("settings").value("port").text());
    }
}
```

### 5. Handling Missing Values (No More `if != null`)

AliveJson strictly avoids `null`. If a key is missing, it quietly returns a `JsonNull` object. If you need explicit behavior when values are missing, you can use specialized decorators instead of `if` statements.

**The `Fallback` Decorator:**
Returns a default value if the requested key or index is missing.

```java
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.io.Parsed;
import uz.maniac4j.alivejson.build.Fallback;
import uz.maniac4j.alivejson.types.JsonNumber;

public class FallbackExample {
    public static void main(String[] args) {
        Json json = new Parsed("{\"name\":\"AliveJson\"}");
        
        // Wrap with a fallback. If "age" is missing, return 18.
        Json age = new Fallback(json, new JsonNumber(18)).value("age");
        System.out.println(age.text()); // Outputs: 18
    }
}
```

**The `Strict` Decorator:**
Follows the "Fail Fast" paradigm. If a requested key is missing, it immediately throws an `IllegalArgumentException` instead of returning `JsonNull`.

```java
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.io.Parsed;
import uz.maniac4j.alivejson.build.Strict;

public class StrictExample {
    public static void main(String[] args) {
        Json json = new Parsed("{\"name\":\"AliveJson\"}");
        
        // Fails immediately if the required key is missing
        Json strictJson = new Strict(json);
        Json age = strictJson.value("age"); // Throws IllegalArgumentException!
    }
}
```

### 6. Object Mapping (Deserialization) the Pure OOP Way

Traditional JSON libraries map JSON to plain DTOs using reflection and setters. In AliveJson, your domain objects map themselves using Composition. No reflection, no setters, just pure interfaces.

1. **Define your Domain Interface:**
```java
public interface User {
    String name();
    int age();
}
```

2. **Create a Json Implementation:**
```java
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.build.Fallback;
import uz.maniac4j.alivejson.types.JsonNumber;

public final class JsonUser implements User {
    private final Json origin;

    public JsonUser(final Json json) {
        this.origin = json;
    }

    @Override
    public String name() {
        return this.origin.value("name").text().replace("\"", "");
    }

    @Override
    public int age() {
        Json safeAge = new Fallback(this.origin, new JsonNumber(0)).value("age");
        return Integer.parseInt(safeAge.text());
    }
}
```

3. **Use it in your application:**
```java
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.io.Parsed;

public class DomainMappingExample {
    public static void main(String[] args) {
        Json source = new Parsed("{\"name\":\"Ali\", \"age\":25}");
        User user = new JsonUser(source);
        
        System.out.println("User name: " + user.name());
        System.out.println("User age: " + user.age());
    }
}
```
This guarantees thread safety, memory efficiency (fields are parsed lazily on demand), and completely eliminates the need for reflection!

## Core Abstraction

Everything implements a single interface. Because of this, you can compose decorators infinitely.

```java
public interface Json {
    String text();
    Json value(String key);
    Json value(int index);
}
```

## License

MIT License. See `LICENSE` for more information.
