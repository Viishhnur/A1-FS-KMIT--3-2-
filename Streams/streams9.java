import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.*;

// print first two names alphabetically that are 5 or more characters long
class test1 {
  public static void main(String args[]){
    List<String> list = Arrays.asList("India", "Australia", "USA", "Singapore", "UK", "Indian Ocean", "Russia");
    List<String> filtered = new ArrayList<>();
    for(String s:list)
      if (s.length() > 4) filtered.add(s);

    Collections.sort(filtered);
    for(int i = 0; i < filtered.size() && i < 2; i++)
      System.out.println(filtered.get(i));
  }
}

// using streams API
class test2 {
  public static void main(String args[]){
    List<String> list = Arrays.asList("India", "Australia", "USA", "Singapore", "UK");
    list.stream().filter(n -> n.length() > 4)
      .sorted()
      .limit(2)
      .forEach(System.out::println);
  }
}

class test3 {
  public static void main(String args[]){
    Stream.generate(() -> "India")
      .filter(n -> n.length() > 4)
	  .peek(System.out::println)
      .sorted()
      .limit(2)
      .forEach(System.out::println);
  }
}

class test4 {
  public static void main(String args[]){
    Stream.generate(() -> "India")
      .filter(n -> n.length() > 4)
	  .peek(System.out::println)
      .limit(2)
      .sorted()
      .forEach(System.out::println);
  }
}


class test5 {
  public static void main(String args[]){
    Stream.generate(() -> "USA")
      .filter(n -> n.length() > 4)
	  .peek(System.out::println)
      .limit(2)
      .sorted()
      .forEach(System.out::println);
  }
}