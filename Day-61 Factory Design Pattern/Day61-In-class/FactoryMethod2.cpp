#include <iostream>
using namespace std;

// Abstract base product
class Widget {
  public:
    virtual void draw() = 0;
};

// Concrete product family 1
class LinuxButton : public Widget {
  public:
    void draw() { cout << "LinuxButton\n"; }
};

// Concrete product family 2
class WindowsButton : public Widget {
  public:
    void draw() { cout << "WindowsButton\n"; }
};

class MacButton : public Widget{
  public:
    void draw() {cout << "Mac button\n";}
};
// factory defines methods to create all related products
class Factory {
  public:
    virtual Widget *create_button() = 0;
};

// Each concrete factory corresponds to one product family. It creates all 
// possible products of one kind.
class LinuxFactory : public Factory {
  public:
    Widget *create_button() {
      return new LinuxButton;
    }
};
class WindowsFactory : public Factory {
  public:
  Widget *create_button() {
    return new WindowsButton;
  }
};

// Now creating MaxFactory to return Max Button
class MacFactory : public Factory{
  public:
    Widget *create_button(){
      return new MacButton;
    }
};

int main() {
  Factory *factory;
  factory = new LinuxFactory;
  Widget *w = factory->create_button();
  w->draw();

  factory = new WindowsFactory;
  w = factory->create_button();
  w->draw();
}
