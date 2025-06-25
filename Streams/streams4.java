import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class Employee {
  public int id;
  public String name;
  public int sal;
  public Employee(int id,String name,int sal  ){
    this.id = id;
    this.name = name;
    this.sal = sal;
  }
} 

class test1 {
  public static void main(String[] args) {
    List<Employee> list = new ArrayList<>();
    list.add(new Employee(1, "Ravi", 2000));
    list.add(new Employee(2, "Kavita", 3000));
    list.add(new Employee(3, "Bharath", 4000));
    list.add(new Employee(4, "Ashish", 5000));

    Predicate<Employee> p1 = e -> e.id < 10 && e.name.startsWith("A");
    Predicate<Employee> p2 = e -> e.sal < 10000;

    boolean b1 = list.stream().allMatch(p1);
    System.out.println(b1);
    boolean b2 = list.stream().allMatch(p2);
    System.out.println(b2);

    boolean b3 = list.stream().anyMatch(p1);
    System.out.println(b3);
    boolean b4 = list.stream().anyMatch(p2);
    System.out.println(b4);

    boolean b5 = list.stream().noneMatch(p1);
    System.out.println(b5);     
  }    
} 