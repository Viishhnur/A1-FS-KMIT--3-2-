import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.*;

class test1 {
  public static void main(String args[]){
    Stream<String> stream = Stream.of("a2", "c1", "b2", "c3", "e1");
    long count = stream.filter(s -> s.startsWith("c"))
      .peek(System.out::println).count();
    System.out.println(count);

    Stream.of("one", "two", "three", "four")
      .filter(e -> e.length() > 3)
      .peek(e -> System.out.println("Filtered value: " + e))
      .map(String::toUpperCase)
      .peek(e -> System.out.println("Mapped value: " + e))
      .collect(Collectors.toList())
      .forEach(System.out::println);
  }
}

class test2 {
  public static void main(String args[]){
    List<Integer> li = new ArrayList<>();
    li.add(20);

    List<Character> lc = new ArrayList<>();
    lc.add('A');
    lc.add('B');

    Stream<List<?>> stream = Stream.of(li, lc);
    stream.map(List::size).forEach(System.out::println);

    System.out.println();
    StringBuilder sb = new StringBuilder();
    Stream<List<?>> stream2 = Stream.of(li, lc);
    stream2.peek(l -> sb.append(l)).map(List::size)
      .forEach(System.out::println);
    System.out.println(sb);

    System.out.println();
    Stream<List<?>> stream3 = Stream.of(li, lc);
    stream3.peek(l -> l.remove(0)).map(List::size)
      .forEach(System.out::println);
	  
  }
}