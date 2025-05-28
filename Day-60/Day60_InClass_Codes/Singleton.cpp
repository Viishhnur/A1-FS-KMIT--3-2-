#include <iostream>
using namespace std;

class singleton 
{	
  private:
    int value;
    singleton();
    static singleton* _instance;
	public:
		int getValue();
		void setValue(int val); 
		static singleton* getInstance();
    static void DestroyInstance();
  protected:
    // To disallow copy constructor, assignment operator and destructor 
    // to be called from outside the class
    singleton(const singleton&) {};   
    singleton& operator=(const singleton&){}; 
    ~singleton(){}; 
};

singleton* singleton :: _instance = NULL;

singleton :: singleton() {
	value = 20;
}

int singleton :: getValue() {
	return value;
}

void singleton :: setValue(int val) {
	value = val;
}

singleton * singleton :: getInstance() {
	if (_instance == NULL)  {
		cout << "if block" << endl;
		_instance = new singleton();
	}
	else {
		cout << "else block" << endl;			
	}
	return _instance;
}

void singleton::DestroyInstance() {
  delete _instance;
  _instance = NULL;
}

int main() {
	singleton *s1 = singleton::getInstance();
	s1->setValue(40);
	cout << "Value 1 " << s1->getValue() << endl;
	
	singleton *s2 = singleton::getInstance();
	cout << "Value 2 " << s2->getValue() << endl;

	s2->setValue(50);
	cout << "Value 1 " << s1->getValue() << endl;
	cout << "Value 2 " << s2->getValue() << endl;

  singleton::DestroyInstance();   

	// singleton *s3 = singleton::getInstance();
  cout << "Value 1 " << s1->getValue() << endl;
  cout << "Value 2 " << s2->getValue() << endl;

//   cout << "Value 3 " << s3->getValue() << endl; 

  // *s1 = *s2;
  // singleton s4(*s1);
  return 0;
}