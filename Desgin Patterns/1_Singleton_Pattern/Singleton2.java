// In this code snippet we are creating the object through static initilizer block only once
// But this is not suitable , we should use lazy instatializtion(create objects only when needed)
class Singleton
{
	private int value;
	private static final Singleton obj;

	private Singleton() {	}

	public synchronized int getValue() {
		return value;
	}

	public synchronized void setValue(int value) {
		this.value = value;
	}

	static { obj = new Singleton(); }
	{ value = 10; }

	public static Singleton getInstance() {
		return obj;
	}

	public static void main(String []args) 
	{
		Singleton s1 = Singleton.getInstance();  
		System.out.println("s1 value is " + s1.getValue());	
		s1.setValue(30);

		Singleton s2 = Singleton.getInstance();	
		System.out.println("s1 value is " + s1.getValue());	
		System.out.println("s2 value is " + s2.getValue());	
  }
}
