import java.util.*;
class Test implements Comparable{
	int val;
	Test(int value){
		val = value;
	}
	public int compareTo(Object o){
		return 0;
	}
	public String toString(){
		return ""+val;
	}
	public static void main(String[] args){
		Test t[] = new Test[5];
		for(int i = 0; i<5; i++){
			t[i] = new Test(i*7);
		}
		System.out.println("Before Sorting :" + Arrays.toString(t));
		Arrays.sort(t);
		System.out.println("After Sorting :" + Arrays.toString(t));
	}
}