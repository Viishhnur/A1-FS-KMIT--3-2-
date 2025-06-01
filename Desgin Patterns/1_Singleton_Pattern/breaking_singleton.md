# ☠️ Breaking Singleton Pattern in Java

The Singleton Design Pattern ensures that only **one instance** of a class is created and provides a **global point of access** to it.

However, Singleton can be **broken** using:

- 🔁 Cloning
- 🧾 Serialization

This document explains both issues and how to **prevent** them.

---

## 🔁 1. Breaking Singleton via Cloning

### ❌ Problem

If a Singleton class implements `Cloneable`, calling `.clone()` can create a **new object**, breaking the singleton constraint.

### 🚫 Bad Example: Cloning Breaks Singleton

```java
public class Singleton implements Cloneable {
    private static Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // creates a new instance!
    }
}

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = (Singleton) s1.clone();

        System.out.println(s1 == s2); // false -> Broken!
    }
}
```

### ✅ Fix: Prevent Cloning

Option 1: Return the same instance

```java
@Override
protected Object clone() throws CloneNotSupportedException {
    return getInstance(); // return existing instance
}
```

Option 2: Disable cloning completely

```java
@Override
protected Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException("Cloning not allowed for singleton");
}
```

---

## 🧾 2. Breaking Singleton via Serialization

### ❌ Problem

Serialization followed by deserialization creates a **new object**, violating the singleton rule.

### 🚫 Bad Example: Serialization Breaks Singleton

```java
import java.io.*;

public class Singleton implements Serializable {
    private static final Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        Singleton s1 = Singleton.getInstance();

        // Serialize to file
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
        oos.writeObject(s1);
        oos.close();

        // Deserialize from file
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("singleton.ser"));
        Singleton s2 = (Singleton) ois.readObject();
        ois.close();

        System.out.println(s1 == s2); // false -> Broken!
    }
}
```

### ✅ Fix: Use `readResolve()` to Return Existing Instance

```java
private Object readResolve() throws ObjectStreamException {
    return getInstance(); // ensures same instance after deserialization
}
```

This prevents the JVM from returning a new object upon deserialization.

---

## ✅ Summary Table

| Method         | Problem                                                    | Fix                                                          |
|----------------|------------------------------------------------------------|---------------------------------------------------------------|
| Cloning        | `.clone()` creates a new instance                          | Override `clone()` to return the singleton or throw exception |
| Serialization  | Deserialization creates a new instance                     | Implement `readResolve()` to return the singleton             |

---

## 🧵 Conclusion

Although Singleton is a widely used pattern, it can be **broken unintentionally** using features like cloning and serialization. Always include guards like `readResolve()` and override `clone()` to preserve the singleton contract in multi-feature Java applications.

What is readResolve?

readResolve is a special method in Java used during deserialization of an object. When an object is deserialized, Java creates a new instance from the byte stream. This can break the Singleton pattern because you might end up with multiple instances.

To prevent this, the Singleton class can implement a readResolve method that returns the existing singleton instance instead of the newly created one during deserialization.
How readResolve works

private Object readResolve() throws ObjectStreamException {
    return getInstance(); // Return the one true Singleton instance
}

    When Java deserializes an object, if a readResolve method is present, it calls this method after deserialization.

    The object returned by readResolve replaces the deserialized object.

    So, even if the JVM creates a new instance during deserialization, readResolve makes sure the singleton instance is returned instead.

Why is readResolve important in Singleton?

Without readResolve, deserializing a Singleton can create multiple instances breaking the Singleton guarantee.