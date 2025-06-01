# 🏭 Java Factory Design Pattern

## ✅ What is the Factory Pattern?

The **Factory Pattern** is a creational design pattern that provides an interface for creating objects **without specifying their exact class**. It lets subclasses alter the type of objects that will be created.

---

## 🎯 Why Use the Factory Pattern?

| Benefit                          | Explanation                                                                        |
| -------------------------------- | ---------------------------------------------------------------------------------- |
| Encapsulation of Object Creation | Hides object creation logic from the client.                                       |
| Loose Coupling                   | Reduces dependency on specific implementations.                                    |
| Scalability                      | Easy to introduce new types without changing client code.                          |
| Improves Maintainability         | Follows Open/Closed Principle (OCP) — open for extension, closed for modification. |

---

## 🌍 Real-life Use Cases

1. **UI Toolkit**: Creating different UI elements (Button, Textbox, Dropdown) based on platform.
2. **Payment Gateway**: Selecting PayPal, RazorPay, or Stripe based on user’s choice.
3. **Document Reader**: Opening files like PDF, DOCX, PPT with a common interface.
4. **Vehicle Factory**: Creating different types of vehicles (Car, Bike, Truck) dynamically.

---

## ❌ Bad Example (Tightly Coupled Code)

```java
public class Vehicle {
    public void start() {
        System.out.println("Generic Vehicle Starting...");
    }
}

public class Car extends Vehicle {
    public void start() {
        System.out.println("Car Starting...");
    }
}

public class Bike extends Vehicle {
    public void start() {
        System.out.println("Bike Starting...");
    }
}

public class Main {
    public static void main(String[] args) {
        String type = "car";

        Vehicle v = null;
        if (type.equals("car")) {
            v = new Car();
        } else if (type.equals("bike")) {
            v = new Bike();
        }

        v.start();
    }
}
```

🚫 **Problem**:

- Adding a new vehicle (e.g., `Truck`) requires changing the `main()` logic.
- Violates Open/Closed Principle.
- Not scalable.

---

## ✅ Good Example (Using Factory Pattern)

```java
// 1. Common interface
public interface Vehicle {
    void start();
}

// 2. Concrete implementations
public class Car implements Vehicle {
    public void start() {
        System.out.println("Car Starting...");
    }
}

public class Bike implements Vehicle {
    public void start() {
        System.out.println("Bike Starting...");
    }
}

// 3. Factory class
public class VehicleFactory {
    public static Vehicle getVehicle(String type) {
        if (type == null) return null;
        switch (type.toLowerCase()) {
            case "car": return new Car();
            case "bike": return new Bike();
            default: throw new UnsupportedOperationException("Unknown vehicle type");
        }
    }
}

// 4. Client code
public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = VehicleFactory.getVehicle("car");
        vehicle.start();
    }
}
```

✅ **Advantages**:

- New vehicle types can be added without touching `Main`.
- Cleaner, modular code.
- Follows SOLID principles.

---

## 📌 When to Use Factory Pattern

✅ Use when:

- You have a superclass/interface with multiple subclasses.
- Object creation logic is complex or dependent on conditions.
- You want to abstract and encapsulate the instantiation logic.

❌ Avoid when:

- The class hierarchy is simple and unlikely to change.
- You only need one or two types — no need to over-engineer.

---

## 🔁 Summary

| Topic              | Notes                                                    |
| ------------------ | -------------------------------------------------------- |
| Pattern Type       | Creational                                               |
| Problem Solved     | Hides object creation and encapsulates logic             |
| Key Benefit        | Promotes loose coupling and scalability                  |
| Real-World Example | UI toolkits, payment gateways, document readers          |
| Alternatives       | Builder Pattern, Abstract Factory Pattern (for families) |

---

## 🧠 Pro Tip

If you're creating families of related objects (e.g., UI components for Windows/Mac), consider using the **Abstract Factory Pattern**.

---

## 📚 Final Thoughts

The Factory Pattern helps write flexible and maintainable code. It's particularly useful when the system grows and the number of types increases. It avoids clutter in your client code and respects key OOP principles.
