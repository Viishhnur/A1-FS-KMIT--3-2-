#include <iostream>
#include <string>
using namespace std;

#define interface struct

// A protection proxy controls access to the original object

interface IAccess {
  virtual bool withdraw(int) = 0;
};

class Person
{
    string Role;
    static string roles[];
    static int next;
  public:
    Person()
    {
      if(next == 4)
        next = 0;
      Role = roles[next++];
    }
    string role()
    {
      return Role;
    }    
};

string Person::roles[] = 
{
  "Manager", "Finance", "Admin", "CFO"
};

int Person::next = 0;

class ActualAccess : public IAccess
{
    int balance;
  public:
    ActualAccess()
    {
      balance = 500;
    }
    bool withdraw(int amount)
    {
      if (amount > balance)
        return false;
      balance -= amount;
      return true;
    }
    int getBalance()
    {
      return balance;
    }
};

class ProxyAccess : public IAccess
{
    Person p;
    ActualAccess object;
  public:
    ProxyAccess(Person &obj)
    {
      p = obj;
    }
    bool withdraw(int amount)
    {
      if (p.role() == "Manager" || p.role() == "Director" || p.role() == "CFO")
        return object.withdraw(amount);
      else
        return false;
    }
    int getBalance()
    {
      return object.getBalance();
    }
};

int main()
{
  Person staff[4];
  IAccess *pc;
  for (int i = 0, amount = 100; i < 4; i++, amount += 100)
  {
    pc = new ProxyAccess(staff[i]);
    if (!pc->withdraw(amount))
      cout << "No access for " << staff[i].role() << '\n';
    else
      cout << amount << " dollars for " << staff[i].role() << '\n';
  }
  return 0;
}