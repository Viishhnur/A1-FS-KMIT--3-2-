#include <iostream>
using namespace std;
#include<cstring>
#define interface struct

interface Shape 
{	
  virtual void draw() = 0;
};

class Square : public Shape 
{
  public:
    void draw() {
      cout << "Inside Square::draw() method." << endl;
    }
};

class Circle : public Shape 
{
  public:
    void draw() {
      cout << "Inside Circle::draw() method." << endl;
    }
};

class Rectangle : public Shape 
{
  public:
    void draw() {
      cout << "Inside Rectangle::draw() method." << endl;
    }
};

class Hexagon : public Shape{
  public:
    void draw(){
      cout << "Inside Hexagon::draw() method." << endl;
    }
};


interface Factory 
{
  virtual Shape * getShape(char * shapeType) = 0;
};

class ShapeFactory : public Factory
{
  public:
    Shape * getShape(char * shapeType)
    {   
      if(shapeType == NULL) {
          return NULL;
      }		      
      if(stricmp(shapeType, "CIRCLE") == 0)
      {
        return new Circle();       
      }
      else if(stricmp(shapeType, "SQUARE") == 0)
      {
        return new Square();
      } 
      else if(stricmp(shapeType, "RECTANGLE") == 0)
      {
        return new Rectangle();
      }  
      else if(stricmp(shapeType,"HEXAGON") == 0){
        return new Hexagon();
      }
      return NULL;
    }
};

int main() 
{
  //get shape factory
  Factory * shapeFactory = new ShapeFactory();

  //get an object of Shape Circle
  Shape * shape1 = shapeFactory->getShape("CIRCLE");
  if(shape1 != NULL)
    shape1->draw();

  //get an object of Shape Square 
  Shape * shape2 = shapeFactory->getShape("SQUARE");
  if(shape2 != NULL)
    shape2->draw();

  //get an object of Shape Rectangle 
  Shape * shape3 = shapeFactory->getShape("RECTANGLE");
  if(shape3 != NULL)
    shape3->draw();

  // Now get an object of HEXAGON
  Shape* shape4 = shapeFactory->getShape("HEXAGON");
  if(shape4 != NULL){
    shape4->draw();
  }
}