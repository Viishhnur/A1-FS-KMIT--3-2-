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

	public synchronized int getValue() {
		return value;
	}

	public synchronized void setValue(int value) {
		this.value = value;
	}

	public static synchronized Singleton getInstance() {
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

	public static void main(String []args) 
	{
		Singleton s1 = Singleton.getInstance2();    
		System.out.println("s1 value is " + s1.getValue());	
		s1.setValue(30);

		Singleton s2 = Singleton.getInstance2();	
		System.out.println("s1 value is " + s1.getValue());	
		System.out.println("s2 value is " + s2.getValue());	
	}
}

// http://www.ibm.com/developerworks/ibm/library/it-haggar_bytecode/#4
// javap -c Singleton > singleton2.bc