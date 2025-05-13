/*
Alice and Bob have their own task lists represented as strings. 
Each character is a task. You need to find all valid ways to create a 
shared task schedule that includes all tasks from both lists, but without 
changing the order of tasks for either person.

Explanation:
-------------
An interleaving of two strings X and Y is a combination of characters where:
•	All characters from both strings are used exactly once.
•	The order of characters from each original string is maintained.
Example:
---------
If X = "AB" and Y = "CD", valid interleaving’s are:
•	"ABCD" (A→B→C→D),
•	"ACBD" (A→C→B→D),
•	"ACDB", "CABD", "CADB", "CDAB"

Input Format:
-------------------
Line-1: two space-separated strings

Output Format:
----------------------
Line-1: List of strings(interleaving’s) that matches order of characters.


Sample Input-1:
---------------
ABC ACB

Sample Output-1:
---------------
[AACBCB, ABCACB, ACBABC, ABACCB, ACABCB, AABCCB, ACABBC, AACBBC, ABACBC, AABCBC]


Sample Input-2:
---------------
12 AB

Sample Output-2:
----------------
[12AB, 1A2B, 1AB2, A12B, A1B2, AB12]

 */
public class Inter_Leaving_String {

}
