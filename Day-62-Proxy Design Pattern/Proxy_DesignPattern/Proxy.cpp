#include <iostream>
#include <string>
using namespace std;
#define interface struct

interface ICar {
  virtual void DriveCar() = 0;
};

// Real Subject 
class Car : public ICar
{
  public:
    void DriveCar()
    {
      cout << "Car has been driven!";
    }
};

class Driver
{
  public:
    int Age;        

    Driver(int age = 20)
    {
      this->Age = age;
    }
};

//Proxy Subject
class ProxyCar : public ICar
{
  Driver driver;
  ICar *realCar;

  public:
    ProxyCar(Driver driver)
    {
      this->driver = driver;
      realCar = new Car();
    }

    void DriveCar()
    {
      if (driver.Age <= 16)
        cout << "Sorry, the driver is too young to drive." << endl;
      else
        realCar->DriveCar();
    }
};

int main()
{
  ICar *car = new Car(Driver(16));
  car->DriveCar();

  car = new ProxyCar(Driver(25));
  car->DriveCar();
  return 0;
}