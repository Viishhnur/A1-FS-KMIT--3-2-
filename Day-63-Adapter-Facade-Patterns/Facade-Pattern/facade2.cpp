#include <iostream>
#include <cstring>
using namespace std;
#define interface struct

interface Shape
{
    virtual void draw() = 0;
};

interface Factory
{
    virtual Shape *getShape(char *shapeType) = 0;
};

class Square : public Shape
{
public:
    void draw()
    {
        cout << "Inside Square::draw() method." << endl;
    }
};

class Circle : public Shape
{
public:
    void draw()
    {
        cout << "Inside Circle::draw() method." << endl;
    }
};

class Rectangle : public Shape
{
public:
    void draw()
    {
        cout << "Inside Rectangle::draw() method." << endl;
    }
};

class Hexagon : public Shape
{
public:
    void draw()
    {
        cout << "Inside Hexagon::draw() method." << endl;
    }
};

class ShapeFactory : public Factory
{
public:
    Shape *getShape(char *shapeType)
    {
        if (shapeType == NULL)
        {
            return NULL;
        }
        if (strcasecmp(shapeType, "CIRCLE") == 0)
        {
            return new Circle();
        }
        else if (strcasecmp(shapeType, "SQUARE") == 0)
        {
            return new Square();
        }
        else if (strcasecmp(shapeType, "RECTANGLE") == 0)
        {
            return new Rectangle();
        }
        else if (strcasecmp(shapeType, "HEXAGON") == 0)
        {
            return new Hexagon();
        }
        return NULL;
    }
};

class ShapeMaker
{
private:
    Factory *shapeFactory;
    Shape *circle;
    Shape *rectangle;
    Shape *square;
    Shape *hexagon;

    void customDraw(Shape* shape,char * shapeType){
        if(shape == nullptr){
            shape = shapeFactory->getShape(shapeType);
        }
        shape->draw();
    }

public:
    ShapeMaker()
    {
        shapeFactory = new ShapeFactory();
        circle = nullptr;
        rectangle = nullptr;
        square = nullptr;
        hexagon = nullptr;
    }

    void drawCircle()
    {
        customDraw(circle,"circle");
    }

    void drawRectangle()
    {
        customDraw(rectangle,"rectangle");
    }

    void drawSquare()
    {
        customDraw(square,"square");
    }

    void drawHexagon()
    {
        customDraw(hexagon,"hexagon");
    }
};

int main()
{
    ShapeMaker shapeMaker;

    shapeMaker.drawCircle();
    shapeMaker.drawRectangle();
    shapeMaker.drawSquare();
    shapeMaker.drawHexagon();
    return 0;
}