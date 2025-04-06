/*
Cliff Shaw is working on the singly linked list.
He is given a list of boxes arranged as singly linked list,
where each box is printed a positive number on it.

Your task is to help Mr Cliff to find the given list is equivalent to 
the reverse of it or not. If yes, print "true", otherwise print "false"

Input Format:
-------------
Line-1: space separated integers, boxes as list.

Output Format:
--------------
Print a boolean a value.

Sample Input-1:
---------------
3 6 2 6 3

Sample Output-1:
----------------
true


Sample Input-2:
---------------
3 6 2 3 6

Sample Output-2:
----------------
false
 */

import java.util.*;

class Node {
	int data;
	Node next;

	public Node(int data) {
		this.data = data;
		this.next = null;
	}
}

class Solution {
	// Write if any supporting methods here

	private Node getMiddle(Node head) {
		Node slow = head;
		Node fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		return slow;
	}

	public boolean isPalindrome(Node head) {
		if (head == null || head.next == null)
			return true;

		// Step 1: Find the middle
		Node middle = getMiddle(head);

		// Step 2: Reverse second half
		Node prev = null;
		Node curr = middle;
		while (curr != null) {
			Node t = curr.next;
			curr.next = prev;
			prev = curr;
			curr = t;
		}

		// Step 3: Compare both halves
		Node first = head;
		Node second = prev; // head of reversed second half
		while (second != null) {
			if (first.data != second.data)
				return false;
			first = first.next;
			second = second.next;
		}

		return true;
	}

	Node reverseLinkedlist(Node head) {

		Node curr = head;
		Node prev = null;
		Node next;

		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;

	}

}

public class PalindromeList {
	public Node head = null;
	public Node tail = null;

	public void addNode(int data) {
		Node newNode = new Node(data);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PalindromeList list = new PalindromeList();
		String list2[] = sc.nextLine().split(" ");
		for (int i = 0; i < list2.length; i++)
			list.addNode(Integer.parseInt(list2[i]));
		Solution sl = new Solution();
		System.out.println(sl.isPalindrome(list.head));
	}
}