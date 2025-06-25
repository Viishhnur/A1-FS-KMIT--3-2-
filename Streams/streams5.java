import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class test1 {
  public static void main(String[] args) {
    String [] array = new String[] {"k", "m", "i", "t"};
    String result = "";
    for(String s:array) result += s;
    System.out.println(result);

    Stream<String> stream = Stream.of("k", "m", "i", "t");
    String word = stream.reduce("", (s,c) -> s + c);
    System.out.println(word);

    Stream<String> stream2 = Stream.of("k", "m", "i", "t");
    word = stream2.reduce("", String::concat);
    System.out.println(word);

    Stream<Integer> streami = Stream.of(2, 3, 4);
    System.out.println(streami.reduce(1, (a, b) -> a * b));
  }    
} 



class test2 {
  public static void main(String[] args) {
    BinaryOperator<Integer> op = (a, b) -> a * b;
    Stream<Integer> empty = Stream.empty();
    Stream<Integer> oneElement = Stream.of(2);
    Stream<Integer> threeElements = Stream.of(2,3,4);

    empty.reduce(op).ifPresent(System.out::println);
    oneElement.reduce(op).ifPresent(System.out::println);
    threeElements.reduce(op).ifPresent(System.out::println);
  }    
} 

class test3 {
  public static void main(String[] args) {
    Stream<String> stream = Stream.of("k", "m", "i", "t");
    StringBuilder word = stream.collect(StringBuilder::new,
      StringBuilder::append, StringBuilder::append);
	System.out.println(word);

    stream = Stream.of("k", "m", "i", "t");
    TreeSet<String> set = stream.collect(TreeSet::new,
      TreeSet::add, TreeSet::addAll);
    System.out.println(set);
  }    
} 

class test4 {
  public static void main(String[] args) {
    Stream<String> stream = Stream.of("n", "g", "i", "t","i", "n");

    TreeSet<String> set = stream.collect(
      Collectors.toCollection(TreeSet::new));
    System.out.println(set);

    stream = Stream.of("n", "g", "i", "t", "i", "n");
    Set<String> set2 = stream.collect(
      Collectors.toSet());
    System.out.println(set2);
  }    
} 