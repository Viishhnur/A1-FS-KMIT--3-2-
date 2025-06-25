// You are given details of n employees in an organization. Each employee record contains:
// 	Employee ID: an integer
// 	Name: a string (single line)
// 	Department: a string (single line)
// 	Salary: a floating-point number

// Write a Java program that:

// 	1.Finds the top-salaried employee in each department.
// 	2.Prints each department name (in ascending lexicographic order) along with all employee names in that department (sorted by name).
// 	3.Sorts the entire employee list by name and prints each record.  --> [treemap will sort by key]

// Use the Java Stream API (groupingBy, maxBy, mapping, sorted, etc.) to perform these operations.

// Input Format:
// -------------
// n
// id₁
// name₁
// department₁
// salary₁
// id₂
// name₂
// department₂
// salary₂
// ...
// idₙ
// nameₙ
// departmentₙ
// salaryₙ
// .
// where, 
// n: number of employees
// For each employee i (1 ≤ i ≤ n):
// 	idᵢ (integer)
// 	nameᵢ (string)
// 	departmentᵢ (string)
// 	salaryᵢ (double)
	
// Output Format:
// --------------
// 1) Top-salaried employee in each department:
//   <Dept₁> => <Name> (<Salary>)
//   <Dept₂> => <Name> (<Salary>)
//   ...
  
// 2) Departments (asc) with their employees:
//   <Dept₁>: NameA, NameB, …
//   <Dept₂>: NameC, NameD, …
//   ...
  
// 3) All employees sorted by name:
//   <id>:<Name>(<Dept>,$<Salary>)
//   ...
  
  
  
// Sample Input:
// -------------
// 10
// 101
// Alice
// HR
// 75000.00
// 102
// Bob
// Engineering
// 98000.00
// 103
// Carol
// HR
// 82000.00
// 104
// Dave
// Engineering
// 120000.00
// 105
// Eve
// Finance
// 90000.00
// 106
// Frank
// Finance
// 88000.00
// 107
// Grace
// Engineering
// 115000.00
// 108
// Heidi
// HR
// 79000.00
// 109
// Ivan
// Engineering
// 105000.00
// 110
// Judy
// Finance
// 95000.00

// Sample Output:
// --------------
// 1) Top-salaried employee in each department:                                                                                                          
//   Engineering => Dave (120000.00)                                                                                                                     
//   Finance => Judy (95000.00)                                                                                                                          
//   HR => Carol (82000.00)                                                                                                                              
                                                                                                                                                      
// 2) Departments (asc) with their employees:                                                                                                            
//   Engineering: Bob, Dave, Grace, Ivan                                                                                                                 
//   Finance: Eve, Frank, Judy                                                                                                                           
//   HR: Alice, Carol, Heidi                                                                                                                             
                                                                                                                                                      
// 3) All employees sorted by name:                                                                                                                      
//   101:Alice(HR,$75000.00)                                                                                                                             
//   102:Bob(Engineering,$98000.00)                                                                                                                      
//   103:Carol(HR,$82000.00)                                                                                                                             
//   104:Dave(Engineering,$120000.00)                                                                                                                    
//   105:Eve(Finance,$90000.00)                                                                                                                          
//   106:Frank(Finance,$88000.00)                                                                                                                        
//   107:Grace(Engineering,$115000.00)                                                                                                                   
//   108:Heidi(HR,$79000.00)                                                                                                                             
//   109:Ivan(Engineering,$105000.00)                                                                                                                    
//   110:Judy(Finance,$95000.00)



import java.util.*;
import java.util.stream.*;

public class FS_74_Employee_Analysis_Streams {
    static class Employee {
        private final int    id;
        private final String name;
        private final String department;
        private final double salary;
        
        //WRITE YOUR CODE HERE
        Employee(int id, String name, String department, double salary){
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }
        
        public int getId(){
            return id;
        }
        public String getName(){
            return name;
        }
        public String getDepartment(){
            return department;
        }
        public double getSalary(){
            return salary;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        //System.out.print("Enter number of employees: ");
        int n = Integer.parseInt(scanner.nextLine().trim());
        
        List<Employee> employees = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            //System.out.printf("Employee %d/%d ID: ", i, n);
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            //System.out.print("  Name: ");
            String name = scanner.nextLine().trim();
            
            //System.out.print("  Department: ");
            String dept = scanner.nextLine().trim();
            
            //System.out.print("  Salary: ");
            double sal = Double.parseDouble(scanner.nextLine().trim());
            
            employees.add(new Employee(id, name, dept, sal));
        }
        
        System.out.println("\n1) Top-salaried employee in each department:");
        //WRITE YOUR CODE HERE
        Map<String, Optional<Employee>> topSalariedByDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))
            ));

        topSalariedByDept.forEach((dept, empOpt) -> {
            empOpt.ifPresent(emp -> 
                System.out.printf("  %s => %s (%.2f)%n", dept, emp.getName(), emp.getSalary())
            );
        });

        
        System.out.println("\n2) Departments (asc) with their employees:");
        //WRITE YOUR CODE HERE
        Map<String, List<String>> employeesByDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.mapping(Employee::getName, Collectors.toList())
            ));
        
        System.out.println("\n3) All employees sorted by name:");
        //WRITE YOUR CODE HERE
        employees.stream()
            .sorted(Comparator.comparing(Employee::getName))
            .forEach(emp -> 
                System.out.printf("  %d:%s(%s,$%.2f)%n", emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary())
            );

        employeesByDept.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach(entry -> {
                String dept = entry.getKey();
                List<String> names = entry.getValue().stream()
                    .sorted()
                    .collect(Collectors.toList());
                System.out.printf("  %s: %s%n", dept, String.join(", ", names));
            });
    }
}
