#include <iostream>
#include <string>

typedef int Cable; 

/* Adaptee (source) interface */
class EuropeanSocketInterface
{
  public:
    virtual int voltage() = 0;
    virtual Cable live() = 0;
    virtual Cable neutral() = 0; 
    virtual Cable earth() = 0;
};

/* Adaptee */
class Socket : public EuropeanSocketInterface
{
  public:
  int voltage() { return 230; }
  Cable live() { return 1; }
  Cable neutral() { return -1; }
  Cable earth() { return 0; }
};

/* Target interface */
class USASocketInterface
{
  public:
    virtual int voltage() = 0;
    virtual Cable live() = 0;
    virtual Cable neutral() = 0;
};

/* The Adapter */
class Adapter : public USASocketInterface
{
  EuropeanSocketInterface* socket;

  public:
    void plugIn(EuropeanSocketInterface* outlet)
    {
      socket = outlet;
    }

    int voltage() { return 110; }
    Cable live() { return socket->live(); }
    Cable neutral() { return socket->neutral(); }
};

/* Client */
class ElectricKettle
{
  USASocketInterface* power;

  public:
    void plugIn(USASocketInterface* supply)
    {
      power = supply;
    }

    void boil()
    {
      if (power->voltage() > 110)
      {
        std::cout << "Kettle is on fire!" << std::endl;
        return;
      }

      if (power->live() == 1 && power->neutral() == -1)
      {
        std::cout << "Coffee time!" << std::endl;
      }
    }
};

int main()
{
  Socket* socket = new Socket;
  Adapter* adapter = new Adapter;
  ElectricKettle* kettle = new ElectricKettle;

  /* Pluging in */
  adapter->plugIn(socket);
  kettle->plugIn(adapter);

  kettle->boil();

  return 0;
}