import java.util.*;

public class Employee implements Comparable {
   String department, name;
  
  	public Employee(String department, String name) {
    	this.department = department;
    	this.name = name;
  	}

  	public String getDepartment() {
    	return department;
  	}

  	public String getName() {
    	return name;
  	}

  	public String toString() {
    	return "[dept=" + department + ",name=" + name + "]";
  	}

  	public int compareTo(Object obj) {
    	Employee emp = (Employee)obj;
    	int deptComp = department.compareTo(emp.getDepartment());
    	return ((deptComp == 0) ? name.compareTo(emp.getName()) : deptComp);
  	}

  	public boolean equals(Object obj) {
  		if (!(obj instanceof Employee)) {
      		return false;
    	}
    	Employee emp = (Employee)obj;
    	return department.equals(emp.getDepartment()) &&
    		name.equals(emp.getName());
  	}

  	public int hashCode() {
    	return department.hashCode() + name.hashCode();
  	}
}
