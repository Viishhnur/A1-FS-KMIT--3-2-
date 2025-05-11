import java.util.*;

public class Company {
  	public static void main (String args[]) {
    	Employee emps[] = {
			new Employee("Software", "Ravi"),
			new Employee("Software", "Bharath"),
			new Employee("Software", "Subbu"),
			new Employee("Software", "Kavita"),
			new Employee("Software", "Ajay"),
			new Employee("Software", "Manohar"),
   		};

    	Set set = new TreeSet(Arrays.asList(emps));
    	System.out.println(set);
  	}
}
