import java.util.*;

public class EmpComparator implements Comparator {
  	public int compare(Object obj1, Object obj2) {
    	Employee emp1 = (Employee)obj1;
    	Employee emp2 = (Employee)obj2;
    	int nameComp = emp1.getName().compareTo(emp2.getName());
    	return ((nameComp == 0) ? 
      	emp1.getDepartment().compareTo(emp2.getDepartment()) : nameComp);
  	}
}