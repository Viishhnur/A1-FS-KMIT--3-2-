# 🏭 Abstract Factory Design Pattern in Java

## 📌 Definition

The **Abstract Factory Pattern** provides an interface for creating **families of related or dependent objects** without specifying their concrete classes.

It helps to enforce **consistency among objects** in a set (e.g., GUI toolkit elements with a common style).

---

## 🧠 Real-World Intuitive Example

### Cross-Platform OS UI Components

Imagine you are developing an app that should render native UI components (like buttons and checkboxes) differently for **Windows**, **Linux**, and **Mac** operating systems.

You want to:

* Create families of related objects (Button, Checkbox) for each OS.
* Ensure UI consistency (no mixing Mac button with Windows checkbox).
* Avoid conditionals in your core logic.

---

## ✅ Abstract Factory Pattern Structure

```
Client -> OSFactory
           |--> WindowsFactory
           |--> LinuxFactory
           |--> MacFactory

OSFactory --> Button
           --> Checkbox
```

---

## 🔧 Code Example

### 1. Product Interfaces

```java
interface Button {
    void render();
}

interface Checkbox {
    void render();
}
```

### 2. Concrete Products - Windows

```java
class WindowsButton implements Button {
    public void render() {
        System.out.println("Rendering Windows Button");
    }
}

class WindowsCheckbox implements Checkbox {
    public void render() {
        System.out.println("Rendering Windows Checkbox");
    }
}
```

### 3. Concrete Products - Linux

```java
class LinuxButton implements Button {
    public void render() {
        System.out.println("Rendering Linux Button");
    }
}

class LinuxCheckbox implements Checkbox {
    public void render() {
        System.out.println("Rendering Linux Checkbox");
    }
}
```

### 4. Concrete Products - Mac

```java
class MacButton implements Button {
    public void render() {
        System.out.println("Rendering Mac Button");
    }
}

class MacCheckbox implements Checkbox {
    public void render() {
        System.out.println("Rendering Mac Checkbox");
    }
}
```

### 5. Abstract Factory

```java
interface OSFactory {
    Button createButton();
    Checkbox createCheckbox();
}
```

### 6. Concrete Factories

```java
class WindowsFactory implements OSFactory {
    public Button createButton() {
        return new WindowsButton();
    }
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

class LinuxFactory implements OSFactory {
    public Button createButton() {
        return new LinuxButton();
    }
    public Checkbox createCheckbox() {
        return new LinuxCheckbox();
    }
}

class MacFactory implements OSFactory {
    public Button createButton() {
        return new MacButton();
    }
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}
```

### 7. Client Code

```java
class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(OSFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void renderUI() {
        button.render();
        checkbox.render();
    }
}
```

### 8. Main Class

```java
public class Main {
    public static void main(String[] args) {
        OSFactory factory = new LinuxFactory(); // can be MacFactory or WindowsFactory
        Application app = new Application(factory);
        app.renderUI();
    }
}
```

---

## 👍 Benefits

* **Consistency** across product families (e.g., all Linux-styled components).
* Decouples client code from specific implementations.
* Scalable and easy to extend for new OS platforms.

## 👎 When Not to Use

* If only one type of product or minimal variations exist.
* When product types frequently change—each change requires updating all factories.

---

## 🧪 Summary Table

| Concept            | Description                                    |
| ------------------ | ---------------------------------------------- |
| Abstract Factory   | Interface to create families of OS-specific UI |
| Concrete Factory   | Implements creation of OS-specific UI elements |
| Product Interfaces | Button, Checkbox, etc.                         |
| Concrete Products  | WindowsButton, LinuxCheckbox, etc.             |
| Client             | Uses factory, unaware of product details       |

---

## 🧵 Wrap-up

Use the **Abstract Factory Pattern** when:

* You need to create objects that belong to **related families**.
* You want to enforce **consistency** across those families.
* You aim for **extensible**, **maintainable** cross-platform code.

