// Here i am using multiple threads  , So I will use synchronized block instead of synchronized method
class Singleton
{
	private int value;

	/* volatile variables are used in Concurrent programming in Java. When we declare
	a variable volatile, every thread reads its value from main memory and doesn't 
	used cached value available in every thread stack. */

	private static volatile Singleton obj;

	private Singleton() {
		value = 10;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public static Singleton getInstance() {
		if(obj == null) {
		  System.out.println("if block");	
		  obj = new Singleton();
		}
		else {
		  System.out.println("else block");	
		}
		return obj;
	}

	public static Singleton getInstance2() {
        /*
         ❓ Why Two null Checks?
            Check	Purpose
            if (obj == null)	First check avoids locking if instance is already created (performance).
            if (obj == null) inside lock	Ensures only one thread initializes the singleton, even if multiple threads 
            reach the first check simultaneously.
            Without the second check inside the synchronized block, multiple instances could be created under high concurrency.
         */
		if(obj == null) {
		  synchronized(Singleton.class) {
			if(obj == null) {
			  System.out.println("if block");	
			  obj = new Singleton();
			}
		  }
		}
		else {
		  System.out.println("else block");	
		}
		return obj;
	}
    public void destructor(){
        obj = null;
    }
	public static void main(String []args) 
	{
		Singleton s1 = Singleton.getInstance2();    
		System.out.println("s1 value is " + s1.getValue());	
		s1.setValue(30);

		Singleton s2 = Singleton.getInstance2();	
		System.out.println("s1 value is " + s1.getValue());	
		System.out.println("s2 value is " + s2.getValue());	

        s1.destructor();
        System.out.println("After destructor " + s1.getValue());
        System.out.println(s1 == s2);

        // Try creating threads and access
        // Thread t1 = new Thread(()->{
        //     Singleton.getInstance2();
        // });

        // Thread t2 = new Thread(()->{
        //     Singleton.getInstance2();
        // });

        // t1.start();
        // t2.start();

        // System.out.println("Main ends");
	}
}

// http://www.ibm.com/developerworks/ibm/library/it-haggar_bytecode/#4
// javap -c Singleton > singleton2.bc