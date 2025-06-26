/*
 * You are given details of n students. 
Each student record contains:
	Student ID: an integer
	Name: a string
	Marks in five fixed subjects: Math, Science, English, History, Computer (each an integer 0–100)

Write a Java program that:
	1.Reads the n student records from standard input.
	2.For each student, computes:
		Total marks (sum of all five subjects)
		Average marks (to two decimal places)
	3.Determines the top 5 students by average marks (highest first).
	4. Prints a summary table of all students (ID, Name, Total, Average), sorted by student ID ascending.
	5. Prints the list of Top 5 students (ID, Name, Average).
	6. Identify and print any student scoring below 35 in any subject (“at risk”).

Input Format
-------------
n
id₁
name₁
math₁ science₁ english₁ history₁ computer₁
id₂
name₂
math₂ science₂ english₂ history₂ computer₂
...
idₙ
nameₙ
mathₙ scienceₙ englishₙ historyₙ computerₙ

where n: number of students

For each student i:
	idᵢ (integer)
	nameᵢ (single-line string)
	Five integers: marks in Math, Science, English, History, Computer

Output Format:
---------------
All students:
ID   Name           Total  Average
101  Alice Smith    450    90.00
102  Bob Johnson    382    76.40
...

Top 5 students:
1) 101 Alice Smith    90.00
2) 107 Carol Davis    88.20
…

At-risk students: 
105 (Eve Lee), 110 (Frank Wu)

Sample Input:
-------------
5
101
Alice Smith
95 90 92 88 94
102
Bob Johnson
60 58 62 70 65
103
Carol Davis
85 82 80 78 84
104
Dave Lee
30 40 35 20 25
105
Eve Kim
50 55 60 65 70

Sample Output:
--------------
All students:
ID   Name                Total  Average
101  Alice Smith        459     91.80
102  Bob Johnson        315     63.00
103  Carol Davis        409     81.80
104  Dave Lee           150     30.00
105  Eve Kim            300     60.00

Top 5 students:
1) 101  Alice Smith      91.80
2) 103  Carol Davis      81.80
3) 102  Bob Johnson      63.00
4) 105  Eve Kim          60.00
5) 104  Dave Lee         30.00

At-risk students: 
104 (Dave Lee)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class StudentAnalysis {
    static class Student {
        int id;
        String name;
        int[] marks; // [Math, Science, English, History, Computer]

        Student(int id, String name, int[] marks) {
            this.id = id;
            this.name = name;
            this.marks = marks;
        }
        // Write your code here

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int[] getMarks() {
            return marks;
        }

        @Override
        public String toString() {
            return ("Id: " + this.id + " Name: " + this.name + " Marks: " + Arrays.toString(this.marks));
        }

        
    }

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = Integer.parseInt(in.nextLine().trim());
            List<Student> students = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                int id = Integer.parseInt(in.nextLine().trim());
                String name = in.nextLine().trim();
                int[] marks = Arrays.stream(in.nextLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                students.add(new Student(id, name, marks));
            }

            // 1. Summary table sorted by ID
            System.out.println("All students:");
            System.out.println("ID   Name                Total  Average");
            // WRITE YOUR CODE HERE
            Stream<Student>ss=students.stream()
                .sorted((a,b)->Integer.compare(a.getId(), b.getId()));
                ss.forEach(s -> {
                    int total = Arrays.stream(s.getMarks()).sum();
                    double avg = total/s.getMarks().length;
                    // Adjust spacing for alignment
                    System.out.printf("%-4d %-18s %-6d %.2f%n", s.getId(), s.getName(), total, avg);
                });
            // 2. Top 5 by average
            System.out.println("\nTop 5 students:");
            // WRITE YOUR CODE HERE
            ss.sorted
            // 3. At-risk students
            System.out.print("\nAt-risk students: \n");
            // WRITE YOUR CODE HERE
        }
        catch(Exception e){
            System.out.println(e.getStackTrace());
        }
        finally{
            System.out.println("EXITING.");
        }
    }
}