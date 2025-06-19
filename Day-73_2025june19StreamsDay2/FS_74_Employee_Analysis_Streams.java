/*
 * You are given details of n employees in an organization. Each employee record contains:
	Employee ID: an integer
	Name: a string (single line)
	Department: a string (single line)
	Salary: a floating-point number

Write a Java program that:

	1.Finds the top-salaried employee in each department.
	2.Prints each department name (in ascending lexicographic order) along with all employee names in that department (sorted by name).
	3.Sorts the entire employee list by name and prints each record.

Use the Java Stream API (groupingBy, maxBy, mapping, sorted, etc.) to perform these operations.

Input Format:
-------------
n
id₁
name₁
department₁
salary₁
id₂
name₂
department₂
salary₂
...
idₙ
nameₙ
departmentₙ
salaryₙ
.
where, 
n: number of employees
For each employee i (1 ≤ i ≤ n):
	idᵢ (integer)
	nameᵢ (string)
	departmentᵢ (string)
	salaryᵢ (double)
	
Output Format:
--------------
1) Top-salaried employee in each department:
  <Dept₁> => <Name> (<Salary>)
  <Dept₂> => <Name> (<Salary>)
  ...
  
2) Departments (asc) with their employees:
  <Dept₁>: NameA, NameB, …
  <Dept₂>: NameC, NameD, …
  ...
  
3) All employees sorted by name:
  <id>:<Name>(<Dept>,$<Salary>)
  ...
  
  
  
Sample Input:
-------------
10
101
Alice
HR
75000.00
102
Bob
Engineering
98000.00
103
Carol
HR
82000.00
104
Dave
Engineering
120000.00
105
Eve
Finance
90000.00
106
Frank
Finance
88000.00
107
Grace
Engineering
115000.00
108
Heidi
HR
79000.00
109
Ivan
Engineering
105000.00
110
Judy
Finance
95000.00

Sample Output:
--------------
1) Top-salaried employee in each department:                                                                                                          
  Engineering => Dave (120000.00)                                                                                                                     
  Finance => Judy (95000.00)                                                                                                                          
  HR => Carol (82000.00)                                                                                                                              
                                                                                                                                                      
2) Departments (asc) with their employees:                                                                                                            
  Engineering: Bob, Dave, Grace, Ivan                                                                                                                 
  Finance: Eve, Frank, Judy                                                                                                                           
  HR: Alice, Carol, Heidi                                                                                                                             
                                                                                                                                                      
3) All employees sorted by name:                                                                                                                      
  101:Alice(HR,$75000.00)                                                                                                                             
  102:Bob(Engineering,$98000.00)                                                                                                                      
  103:Carol(HR,$82000.00)                                                                                                                             
  104:Dave(Engineering,$120000.00)                                                                                                                    
  105:Eve(Finance,$90000.00)                                                                                                                          
  106:Frank(Finance,$88000.00)                                                                                                                        
  107:Grace(Engineering,$115000.00)                                                                                                                   
  108:Heidi(HR,$79000.00)                                                                                                                             
  109:Ivan(Engineering,$105000.00)                                                                                                                    
  110:Judy(Finance,$95000.00)
 */
public class FS_74_Employee_Analysis_Streams {
    
}
