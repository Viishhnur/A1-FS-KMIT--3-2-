/* Singleton Desgin Pattern
1) Make the constructor private so that no one can create object from outside the class
2) Have a private static final instance variable of class type
3) Have a public static getInstance method which returns the reference to that single object 

Eg:- When I want to connect to DB , I should create a connection object , which uses singleton pattern , because I don't want
to connect to DB every time I run a query
 */
class Singleton implements Cloneable {
    private static Singleton obj;

    private Singleton() {
        System.out.println("Constructor called ");
    }

    public static Singleton getInstance() {
        return obj == null ? obj = new Singleton() : obj;
    }

    public void sayHello() {
        System.out.println("Hello bro !!");
    }
    /*
     Cloning can break Singleton.
    To prevent it, override clone() to either throw an exception or return the same instance.
     */
    @Override
    protected Object clone() throws CloneNotSupportedException{

        // If we uncomment this line then a new obj is created 
        // return super.clone(); // here we are calling parent ka clone

         // Option 1: Prevent cloning (if doing this then write throws CloneNotSupportedException in above line)
        // throw new CloneNotSupportedException("Singleton can't be cloned");

        // Option 2: Return same instance
        return getInstance();
    }
}

public class Singleton_Pat {
    public static void main(String[] args) {
        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();

        System.out.println(obj1 == obj2);

        try {
            
            // Singleton obj3 = (Singleton) obj1.clone(); // here a new object is getting created 
            // System.out.println(obj1 == obj3); 
            // TO prevent this we override clonable and return the same instance

            Singleton obj4 = (Singleton) obj1.clone();
            System.out.println(obj1 == obj4);
        } catch (Exception e) {
        }

    }
}
