/*
A Builder builds a wall in a strange way.
The wall has to built with a width of W.
And there are N building-blocks are available with some width and height.

The wall is built as follows:
	- Pick the first few buiding-blocks and place them in the available 
	width of the wall.
	- Once no more building block in the order can't be kept in the available 
	width of the wall, place a concrete rack on the highest building-block 
	among the row of building blocks.
	- Construct the rest of the wall by repeating above two steps until
	all the blocks used.

Your task is to find the minimum possible height of the wall built 
with width W, after using all N building blocks.


Input Format:
-------------
Line-1: Two space separaed integers, N and W.
Next N lines: Two space separaed integers, width and height of the brick.

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
7 4
1 1
2 3
2 3
1 1
1 1
1 1
1 2

Sample Output-1:
----------------
6

Refer Hint for explanation

Sample Input-2:
---------------
7 5
1 2
2 2
2 3
2 3
1 4
3 4
4 2

Sample Output-2:
----------------
11


Sample Input-3:
---------------
5 3
1 1
2 2
1 2
2 3
3 2

Sample Output-3:
----------------
7
 */
public class BookCase_Shelves {

}
