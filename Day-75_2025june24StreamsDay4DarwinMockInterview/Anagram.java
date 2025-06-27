/*anagram */
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Anagram{
	public static void main(String[] args) {
		String s1 = "RaceCar";
		String s2 = "CarRace";
		
		String S1 = Stream.of(s1.split(""))
						 .map(String::toUpperCase)
						 .sorted()
						 .collect(Collectors.joining());
		
		String S2 = Stream.of(s2.split(""))
						 .map(String::toUpperCase)
						 .sorted()
						 .collect(Collectors.joining());
						 
		System.out.println(S1.equals(S2));

	}

	
	static boolean anagram(String s1,String s2){
		int xor1=s1.toLowerCase().chars().reduce(0, (a,b)->a^b);
		int xor2=s2.toLowerCase().chars().reduce(0, (a,b)->a^b);
		return xor1==xor2;
	}
}
