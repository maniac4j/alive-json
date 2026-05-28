# AliveJson

AliveJson is a strict, object-oriented JSON parsing and manipulation library for Java, built entirely on the principles of [Elegant Objects](https://www.elegantobjects.org/).

## Why AliveJson?

Most traditional JSON libraries rely heavily on DTOs, `null` references, getters/setters, and heavy central "Parser" classes. AliveJson takes a different approach. We treat JSON not as raw data structures to be manipulated, but as living, immutable objects.

## Why Not Jackson or Gson?

If you are building high-performance infrastructure where every nanosecond counts, **use Jackson**. But if you are building business-critical applications where **maintainability** and **correctness** are paramount, AliveJson is the better choice.

| Feature | Jackson / Gson | AliveJson |
| :--- | :--- | :--- |
| **Philosophy** | Data-oriented (DTOs) | Object-oriented (Living Objects) |
| **Null Safety** | Returns `null` (NPE risk) | **Zero Nulls** (Returns `JsonNull`) |
| **Immutability** | Usually mutable POJOs | **Strictly Immutable** |
| **Reflection** | Heavily used (slow/risky) | **Zero Reflection** |
| **Maintainability** | High coupling with DTOs | Low coupling via Interfaces |
| Thread Safety | Depends on your POJOs | **Thread-safe by design** |

### The "Zero NPE" Guarantee

Traditional libraries return `null` when a key is missing or a path is invalid. This leads to defensive `if (x != null)` checks everywhere. AliveJson returns a `JsonNull` object that implements the `Json` interface, allowing you to keep calling methods safely.

## Quick Start

### Installation (Maven)
```xml
<dependency>
    <groupId>com.github.maniac4j</groupId>
    <artifactId>alive-json</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Basic Usage
```java
final Json source = new Parsed("{\"name\":\"AliveJson\"}");
final String name = source.value("name").text(); // "AliveJson"
```
