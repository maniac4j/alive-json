# Architecture

AliveJson is built with a focus on **Pure Object-Oriented Programming** and follows the principles of [Elegant Objects](https://www.elegantobjects.org/).

## Core Principles

### 1. No Nulls
We believe `null` is an industry disaster. In AliveJson, no method ever returns `null`. Instead, we use the `JsonNull` object which implements the `Json` interface.

### 2. Immutability
All classes in AliveJson are `final` and all objects are immutable. This makes the code thread-safe by default and prevents hidden side effects.

### 3. No Static Methods
Static methods are just procedures in disguise. AliveJson uses only constructors and instance methods. Logic is encapsulated within objects.

### 4. Interfaces First
All public functionality is defined in interfaces (like `Json`, `Input`). Users interact with abstractions, not implementations.

## Design Patterns

### Decorators
We rely heavily on the Decorator pattern to add functionality. For example, `Formatted` is a decorator that adds formatting logic to an existing `Json` object without changing its internal structure.

### Composition over Inheritance
Inheritance is avoided. We use object composition to build complex behaviors from simple, focused objects.
