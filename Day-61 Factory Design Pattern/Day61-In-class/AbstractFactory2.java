interface Widget{
    // create button 
    void createButton();
}

class LinuxButton implements Widget{
    @Override
    public void createButton(){
        System.out.println("Linux button got created");
    }
}

class MacButton implements Widget{
    @Override
    public void createButton(){
        System.out.println("Mac button got created");
    }
}

// Now another interface for shapes
interface Shape{
    void draw();
}

class Circle implements Shape{
    @Override
    public void draw(){
        System.out.println("Circle draw called");
    }
}

class Hexagon implements Shape{
    @Override
    public void draw(){
        System.out.println("Hexagon draw called");
    }
}

// Now create a factory for button
interface ButtonFactory{
    Widget createButton();
}

interface ShapeFactory{
    Shape createShape();
}

// Now a abstract factory
interface AbstractFactory{
    // tthis is responsible for crating actual stuff
}

