# SOLID Principles in Java

The **SOLID** principles are five core design principles that help developers write better object-oriented code. These principles promote cleaner, more understandable, and maintainable software architecture.

---

## 🧱 S - Single Responsibility Principle (SRP)

### Theory:

A class should have **only one reason to change**, meaning it should only have one job or responsibility.

When a class has more than one responsibility, those responsibilities become coupled. A change to one responsibility could impair or inhibit the class's ability to meet the others.

### ❌ Bad Code:

```java
class Invoice {
    public void calculateTotal() {
        // calculate total
    }

    public void printInvoice() {
        // print invoice
    }

    public void saveToFile() {
        // save to file
    }
}
```

> This class has multiple responsibilities: calculation, printing, and persistence.

### ✅ Good Code:

```java
class Invoice {
    public void calculateTotal() {
        // calculate total
    }
}

class InvoicePrinter {
    public void print(Invoice invoice) {
        // print logic
    }
}

class InvoicePersistence {
    public void saveToFile(Invoice invoice) {
        // save logic
    }
}
```

---

## 🔄 O - Open/Closed Principle (OCP)

### Theory:

### **Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification.**

You should be able to extend a class's behavior without modifying its existing code.

### ❌ Bad Code:

```java
class AreaCalculator {
    public double calculate(Object shape) {
        if (shape instanceof Circle) {
            Circle c = (Circle) shape;
            return Math.PI * c.radius * c.radius;
        } else if (shape instanceof Square) {
            Square s = (Square) shape;
            return s.side * s.side;
        }
        return 0;
    }
}
```

> Every time we add a new shape, we must modify this class.

### ✅ Good Code:

```java
interface Shape {
    double area();
}

class Circle implements Shape {
    double radius = 5;
    public double area() {
        return Math.PI * radius * radius;
    }
}

class Square implements Shape {
    double side = 4;
    public double area() {
        return side * side;
    }
}

class AreaCalculator {
    public double totalArea(List<Shape> shapes) {
        return shapes.stream().mapToDouble(Shape::area).sum();
    }
}
```

---

## 🔁 L - Liskov Substitution Principle (LSP)

### Theory:

**Objects of a superclass should be replaceable with objects of its subclasses without affecting the correctness of the program.**

Subclasses must behave in a way that does not violate the expectations of the superclass.

### ❌ Bad Code:

```java
class Bird {
    public void fly() {
        System.out.println("Flying");
    }
}

class Ostrich extends Bird {
    public void fly() {
        throw new UnsupportedOperationException("Ostriches can't fly");
    }
}
```

> Violates LSP since not all birds can fly. Clients expecting `Bird` to fly will break with `Ostrich`.

### ✅ Good Code:

```java
class Bird {
    public void layEggs() {
        System.out.println("Laying eggs");
    }
}

interface FlyingBird {
    void fly();
}

class Sparrow extends Bird implements FlyingBird {
    public void fly() {
        System.out.println("Sparrow flying");
    }
}

class Ostrich extends Bird {
    // doesn't implement FlyingBird
}
```

---

## 🔗 I - Interface Segregation Principle (ISP)

### Theory:

**Clients should not be forced to depend upon interfaces they do not use.**

Break large interfaces into smaller, more specific ones.

### ❌ Bad Code:

```java
interface Machine {
    void print();
    void scan();
    void fax();
}

class OldPrinter implements Machine {
    public void print() {
        // ok
    }
    public void scan() {
        throw new UnsupportedOperationException();
    }
    public void fax() {
        throw new UnsupportedOperationException();
    }
}
```

> The `OldPrinter` doesn't need `scan()` or `fax()`.

### ✅ Good Code:

```java
interface Printer {
    void print();
}

interface Scanner {
    void scan();
}

class SimplePrinter implements Printer {
    public void print() {
        System.out.println("Printing...");
    }
}

class MultiFunctionPrinter implements Printer, Scanner {
    public void print() {
        System.out.println("Printing...");
    }
    public void scan() {
        System.out.println("Scanning...");
    }
}
```

---

## 🛠 D - Dependency Inversion Principle (DIP)

### Theory:

**High-level modules should not depend on low-level modules. Both should depend on abstractions.**

**Abstractions should not depend on details. Details should depend on abstractions.**

### ❌ Bad Code:

```java
class WiredKeyboard {
    public void type() {
        System.out.println("Typing...");
    }
}

class Computer {
    private WiredKeyboard keyboard = new WiredKeyboard();

    public void typeSomething() {
        keyboard.type();
    }
}
```

> Tightly coupled. Can't easily switch keyboards.

### ✅ Good Code:

```java
interface Keyboard {
    void type();
}

class WiredKeyboard implements Keyboard {
    public void type() {
        System.out.println("Typing with Wired Keyboard");
    }
}

class BluetoothKeyboard implements Keyboard {
    public void type() {
        System.out.println("Typing with Bluetooth Keyboard");
    }
}

class Computer {
    private Keyboard keyboard;

    public Computer(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public void typeSomething() {
        keyboard.type();
    }
}
```

---

##  Summary Table

| Principle                     | Description                                          |
| ----------------------------- | ---------------------------------------------------- |
| **S** - Single Responsibility | One class = One reason to change                     |
| **O** - Open/Closed           | Open to extend, closed to modify                     |
| **L** - Liskov Substitution   | Subtypes should not break base type behavior         |
| **I** - Interface Segregation | Avoid fat interfaces                                 |
| **D** - Dependency Inversion  | Depend on abstractions, not concrete implementations |

---

