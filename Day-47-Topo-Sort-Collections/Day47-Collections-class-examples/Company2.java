import java.util.*;

public class Company2 {
  	public static void main (String args[]) {
    	Employee emps[] = {
			new Employee("Software", "Ravi"),
			new Employee("Software", "Ajay"),
			new Employee("Sales", "Bharath"),
			new Employee("HR", "Subbu"),
			new Employee("Marketing", "Ravi"),
			new Employee("Finance", "Ajay"),
			new Employee("Finance", "Manohar"),
			new Employee("Finance", "Vinod"),
    	};
    	Set set = new TreeSet(Arrays.asList(emps));
    	Set setrev = new TreeSet(Collections.reverseOrder());
    	setrev.addAll(Arrays.asList(emps)); 

    	Set setcust = new TreeSet(new EmpComparator());
    	setcust.addAll(Arrays.asList(emps)); 
    	System.out.println(set);
		System.out.println();
		System.out.println(setrev);
		System.out.println();
		System.out.println(setcust);
  	}
}
