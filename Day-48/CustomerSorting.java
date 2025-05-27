/* Create a Java application that reads customer records 
and performs multiple types of sorting operations 
based on different criteria using both Comparable and Comparator interfaces.

Description:
------------
You are given the task to manage and analyze customer billing data for a retail company. 

Each customer has:
1. A unique Customer ID (integer)
2. A Customer Name (String)
3. A Customer Bill Amount (double)

You need to:
    -> Create a Customer class to represent each customer with fields, constructors, and access methods.
    -> Implement natural ordering (Comparable) to sort customers by ID.
    -> Implement various custom sort logics using Comparator to sort:
        * By Name, then Bill
        * By ID, then Bill
        * By Bill descending, then Name ascending
        * By Bill ascending, then Name ascending

The customer data should be read from console input for 5 customers. 
Each sorting operation should output the sorted list to the console.

Input Format:
-------------
5 lines of input, each containing: <customer_id> <customer_name> <customer_bill>

Output Format:
--------------
After reading the input, the program should display the customer records sorted by each of the following criteria:

Sample Input-1:
---------------
1 Ramesh 9800.00                                                                
2 Suresh 8700.50                                                                
3 Naresh 7500.25                                                                
4 Mahesh 6400.75                                                                
5 Dinesh 5300.00

Sample Output-1:
---------------
Sorted by ID:                                                                   
{id=1, name='Ramesh', bill=9800.0}                                              
{id=2, name='Suresh', bill=8700.5}                                              
{id=3, name='Naresh', bill=7500.25}                                             
{id=4, name='Mahesh', bill=6400.75}                                             
{id=5, name='Dinesh', bill=5300.0}                                              
                                                                                
Sorted by Name, then Bill:                                                      
{id=5, name='Dinesh', bill=5300.0}                                              
{id=4, name='Mahesh', bill=6400.75}                                             
{id=3, name='Naresh', bill=7500.25}                                             
{id=1, name='Ramesh', bill=9800.0}                                              
{id=2, name='Suresh', bill=8700.5}                                              
                                                                                
Sorted by ID, then Bill:                                                        
{id=1, name='Ramesh', bill=9800.0}                                              
{id=2, name='Suresh', bill=8700.5}                                              
{id=3, name='Naresh', bill=7500.25}
{id=4, name='Mahesh', bill=6400.75}                                             
{id=5, name='Dinesh', bill=5300.0}                                              
                                                                                
Sorted by Bill (Ascending), then Name (Ascending):                              
{id=5, name='Dinesh', bill=5300.0}                                              
{id=4, name='Mahesh', bill=6400.75}                                             
{id=3, name='Naresh', bill=7500.25}                                             
{id=2, name='Suresh', bill=8700.5}                                              
{id=1, name='Ramesh', bill=9800.0}                                              
                                                                                
Sorted by Bill (Descending), then Name (Ascending):                             
{id=1, name='Ramesh', bill=9800.0}                                              
{id=2, name='Suresh', bill=8700.5}                                              
{id=3, name='Naresh', bill=7500.25}                                             
{id=4, name='Mahesh', bill=6400.75}                                             
{id=5, name='Dinesh', bill=5300.0}  


Constraints:
------------
-> The Customer ID is a positive integer.
-> Customer names can be duplicates.
-> Bill amount is a floating-point number and may be equal for multiple customers.
-> There will always be 5 customers per run.

Technical Requirements:
------------------------
-> Use Comparable for natural sorting by ID.
-> Use Comparator for all other custom sortings.
-> Use Collections.sort() or List.sort() as appropriate.
-> Avoid external libraries; stick to core Java features.




*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
class Customer implements Comparable<Customer> {
    private final int id;
    private final String name;
    private final double bill;

    public Customer(int id, String name, double bill) {
        this.id = id;
        this.name = name;
        this.bill = bill;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getBill() { return bill; }
    
    
    @Override
    public int compareTo(Customer customer) {
        //Write your code here to perform default sort by ID
        return this.id - customer.id; // this is default sorting by ID 
    }

    @Override
    public String toString() {
        return "{" + "id=" + id + ", name='" + name + '\'' + ", bill=" + bill + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return id == customer.id; // to customer objects are equal if there ids are equal
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

public class CustomerSorting {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            List<Customer> customers = new ArrayList<>();

            //System.out.println("Enter 5 customer details (id name bill):");
            for (int i = 0; i < 5; i++) {
                int id = sc.nextInt();
                String name = sc.next();
                double bill = sc.nextDouble();
                customers.add(new Customer(id, name, bill));
            }

            // 1. Sort by ID (Comparable)
            List<Customer> byId = new ArrayList<>(customers);
            //WRITE YOUR CODE HERE
            Collections.sort(byId); // no need to comparator , comparable will take care
            System.out.println("\nSorted by ID:");
            for (Customer c : byId) {
            	System.out.println(c);
            }

            // 2. Sort by Name, then Bill (Comparator)
            List<Customer> byNameBill = new ArrayList<>(customers);
            //WRITE YOUR CODE HERE
            Collections.sort(byNameBill,(Customer c1,Customer c2)->{
               
                // first sort by name
                int nameVal = c1.getName().compareTo(c2.getName());
                
                if(nameVal == 0){
                    // It means names are same
                    // Now sort by bill
                    return Double.compare(c1.getBill(), c2.getBill());
                }
                
                return nameVal;
            });
            System.out.println("\nSorted by Name, then Bill:");
            for (Customer c : byNameBill) {
            	System.out.println(c);
            }


            // 3. Sort by ID, then Bill
            List<Customer> byIdBill = new ArrayList<>(customers);
            //WRITE YOUR CODE HERE
            // Already default sorting happens by id now lets check if ids are same sort by bill
            Collections.sort(byIdBill,(c1,c2)->{
                if(c1.getId() == c2.getId()){
                   return Double.compare(c1.getBill(), c2.getBill());
                } 
               
                return c1.getId() - c2.getId();
            });
            
            
            System.out.println("\nSorted by ID, then Bill:");
            for (Customer c : byIdBill) {
            	System.out.println(c);
            }
            
            // 4. Sort by Bill ascending, then Name ascending
            List<Customer> byBillAscName = new ArrayList<>(customers);
            //WRITE YOUR CODE HERE
            Collections.sort(byBillAscName,(c1,c2)->{
                if(c1.getBill() == c2.getBill()){
                    return c1.getName().compareTo(c2.getName()); // sort by name if bill is same
                } 
                
                return Double.compare(c1.getBill(), c2.getBill());
            });
            
            System.out.println("\nSorted by Bill (Ascending), then Name (Ascending):");
            for (Customer c : byBillAscName) {
            	System.out.println(c);
            }
            
            // 5. Sort by Bill descending, then Name ascending
            List<Customer> byBillDescName = new ArrayList<>(customers);
            //WRITE YOUR CODE HERE
            Collections.sort(byBillDescName,new BillDescendingNameAscendingComparator()); // here i wrote a seperate class for comparating implementation
            System.out.println("\nSorted by Bill (Descending), then Name (Ascending):");
            for (Customer c : byBillDescName) {
            	System.out.println(c);
            }
        }
    }
}

class BillDescendingNameAscendingComparator implements Comparator<Customer>{ // implement the comparator interface
    
    @Override
    public int compare(Customer c1,Customer c2){
        if(c1.getBill() == c2.getBill()){
            return c1.getName().compareTo(c2.getName());
        }
        
        return Double.compare(c2.getBill(), c1.getBill()); // bill descending
    }
}
