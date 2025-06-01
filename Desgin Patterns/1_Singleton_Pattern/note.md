
# 🧵 Java Singleton Pattern — Double-Checked Locking Explained

## 📌 What is Double-Checked Locking?

Double-Checked Locking is a design pattern used in **multithreaded** environments to reduce the overhead of acquiring a lock by first checking the condition **without synchronization**, and only synchronizing if the condition might still be true.

This pattern is often used when implementing the **Singleton Design Pattern**, where only **one instance** of a class should exist throughout the application.

---

## ✅ Proper Singleton with Double-Checked Locking

```java
public class Singleton {
    private static volatile Singleton obj;

    private Singleton() {
        // private constructor to prevent instantiation
    }

    public static Singleton getInstance2() {
        if (obj == null) { // First check - no locking
            synchronized (Singleton.class) { // Class-level lock
                if (obj == null) { // Second check - inside lock
                    System.out.println("if block");
                    obj = new Singleton();
                }
            }
        } else {
            System.out.println("else block");
        }
        return obj;
    }
}
```

---

## ❓ Why Two `null` Checks?

| Check                        | Purpose                                                                 |
|-----------------------------|-------------------------------------------------------------------------|
| `if (obj == null)`          | First check avoids locking if instance is already created (performance). |
| `if (obj == null)` inside lock | Ensures only one thread initializes the singleton, even if multiple threads reach the first check simultaneously. |

⚠️ Without the second check inside the synchronized block, **multiple instances** could be created under high concurrency.

---

## 🔐 Class-Level Locking vs. Object-Level Locking

### 🔷 Class-Level Locking

```java
synchronized (Singleton.class)
```

- Locks on the **Class object**, not on an instance.
- Used when **synchronizing static members or methods**.
- Applies to all threads accessing static members of the class.

📍 In Singleton pattern, this is **essential** because we don’t have an instance before it is created.

---

### 🔶 Object-Level Locking

```java
synchronized (this)
```

or

```java
public synchronized void someMethod() { }
```

- Locks on the **current instance** of the object.
- Used to **synchronize access to instance-level fields**.
- Each object has its own lock.

📍 This doesn’t help in Singleton initialization since the instance does not exist yet.

---

## ⚠️ Important: Use `volatile` Keyword

```java
private static volatile Singleton obj;
```

- Prevents **instruction reordering** issues during object creation.
- Ensures that when a thread writes to `obj`, the updated value is immediately visible to other threads.

---

## ❌ Bad Singleton Example (Not Thread Safe)

```java
public class Singleton {
    private static Singleton obj;

    private Singleton() {}

    public static Singleton getInstance() {
        if (obj == null) {
            obj = new Singleton(); // Not thread-safe
        }
        return obj;
    }
}
```

### ❌ Problem:
- Multiple threads can enter the `if (obj == null)` block at the same time and create **multiple instances**, breaking the Singleton principle.

---

## ✅ Good Singleton Example (Thread Safe & Efficient)

```java
public class Singleton {
    private static volatile Singleton obj;

    private Singleton() {}

    public static Singleton getInstance() {
        if (obj == null) {
            synchronized (Singleton.class) {
                if (obj == null) {
                    obj = new Singleton();
                }
            }
        }
        return obj;
    }
}
```

- Uses `volatile` to ensure visibility.
- Uses **double-checked locking** to reduce synchronization overhead.
- Only locks when needed.

---

## 📚 Summary

| Aspect                    | Explanation                                                            |
|---------------------------|------------------------------------------------------------------------|
| Double-Checked Locking    | Ensures lazy initialization with thread safety.                        |
| First null check          | Avoids unnecessary synchronization.                                    |
| Second null check         | Ensures only one instance is created.                                  |
| `synchronized (Class.class)` | Class-level locking for static members.                             |
| `volatile` keyword        | Prevents memory consistency issues.                                    |