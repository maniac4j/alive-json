# AliveJson

[![Release](https://jitpack.io/v/maniac4j/alive-json.svg)](https://jitpack.io/p/maniac4j/alive-json)
[![Javadoc](https://img.shields.io/badge/javadoc-reference-blue.svg)](https://jitpack.io/com/github/maniac4j/alive-json/latest/javadoc/)

AliveJson is a strict, object-oriented JSON parsing and manipulation library for Java, built entirely on the principles of [Elegant Objects](https://www.elegantobjects.org/).

## Installation

To use AliveJson in your project, add the JitPack repository to your build file:

### Maven
Add this to your `pom.xml`:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.maniac4j</groupId>
    <artifactId>alive-json</artifactId>
    <version>1.1.0</version>
</dependency>
```

### Gradle
Add it in your `build.gradle` at the end of repositories:
```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.maniac4j:alive-json:1.1.0'
}
```

Most traditional JSON libraries rely heavily on DTOs, `null` references, getters/setters, and heavy central "Parser" classes. AliveJson takes a different approach. We treat JSON not as raw data structures to be manipulated, but as living, immutable objects.

## Why Not Jackson or Gson?

If you are building high-performance infrastructure where every nanosecond counts, **use Jackson**. But if you are building business-critical applications where **maintainability** and **correctness** are paramount, AliveJson is the better choice.

| Feature | Jackson / Gson | AliveJson |
| :--- | :--- | :--- |
| **Philosophy** | Data-oriented (DTOs) | Object-oriented (Living Objects) |
| **Null Safety** | Returns `null` (NPE risk) | **Zero Nulls** (Returns `JsonNull`) |
| **Immutability** | Usually mutable POJOs | **Strictly Immutable** |
| **Reflection** | Heavily used (slow/risky) | **Zero Reflection** |
| Maintainability | High coupling with DTOs | Low coupling via Interfaces |
| **Thread Safety** | Depends on your POJOs | **Thread-safe by design** |

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

AliveJson follows the Pure OOP paradigm. Examples below show how to compose objects without static methods or utility classes.

### 1. Basic Parsing and Reading

Read a JSON string and extract values safely without worrying about null checks.

```java
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.io.Parsed;

// Parsing is lazy; it only happens when you request a value.
final Json source = new Parsed("{\"name\":\"AliveJson\",\"version\":1}");
final String name = source.value("name").text(); // "AliveJson"
// Requesting a non-existent key returns a safe JsonNull object, not null!
final String missing = source.value("missing").text(); // "null"
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

// Start with an empty object and chain decorators
final Json user = new Without(
    new With(
        new With(
            new JsonObject(Collections.emptyMap()),
            "id", new JsonNumber(101)
        ),
        "name", new JsonString("Pure OOP")
    ),
    "status"
);
final String text = new Formatted(user).text();
// {
//   "id": 101,
//   "name": "Pure OOP"
// }
```

### 3. Deep Immutable Updates

Updating deeply nested fields is functional and safe through composition.

```java
import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.build.With;
import uz.maniac4j.alivejson.io.Parsed;
import uz.maniac4j.alivejson.types.JsonString;

final Json origin = new Parsed("{\"user\":{\"profile\":{\"theme\":\"dark\"}}}");
final Json updated = new With(
    origin, 
    "user", 
    new With(
        origin.value("user"), 
        "profile", 
        new With(
            origin.value("user").value("profile"), 
            "theme", 
            new JsonString("light")
        )
    )
);
```

### 4. Handling Missing Values

Avoid `if != null` by using specialized decorators.

**The `Fallback` Decorator:**
```java
import uz.maniac4j.alivejson.build.Fallback;
import uz.maniac4j.alivejson.types.JsonNumber;

// Returns 18 if "age" is missing
final Json age = new Fallback(source, new JsonNumber(18)).value("age");
```

**The `Strict` Decorator:**
```java
import uz.maniac4j.alivejson.build.Strict;

// Throws IllegalArgumentException if "age" is missing
final Json strict = new Strict(source).value("age");
```

### 5. Pure OOP Object Mapping

Map JSON to domain objects using Composition, not Reflection.

```java
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
        return Integer.parseInt(
            new Fallback(this.origin, new JsonNumber(0)).value("age").text()
        );
    }
}
```

## Core Abstraction

Everything implements a single interface, allowing infinite composition.

```java
public interface Json {
    String text();
    Json value(String key);
    Json value(int index);
}
```

## License

MIT License. See `LICENSE` for more information.
