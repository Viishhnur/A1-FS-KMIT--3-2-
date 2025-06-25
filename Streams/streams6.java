import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.*;

class test1 {
  public static void main(String args[]){
    List<String> list = Arrays.asList("a1", "a2", "b1", "c2", "c1");

    Stream<String> fromList =  list.stream();    
    fromList
      .filter(s -> s.startsWith("c"))      
      .forEach(System.out::println);
  }
}

class test2 {
  public static void main(String args[]){
    Stream.of("d2", "d3", "d2", "d3", "d2")
      .distinct().forEach(System.out::println);
  }
}







class test3 {
  public static void main(String args[]){
    Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);
    oddNumbers.limit(5).forEach(System.out::println);  

    System.out.println();
    oddNumbers = Stream.iterate(1, n -> n + 2);
    oddNumbers.skip(2).limit(3).forEach(System.out::println); 
  }
}

class test4 {
  public static void main(String args[]){
    IntStream.range(1, 3).forEach(System.out::println);
    System.out.println();

    Arrays.stream(new int[] {3, 4, 5})
      .map(n -> n * n)
      .forEach(System.out::println);  

    System.out.println();
    Stream<String> s = Stream.of("kmit", "Keshav", "Institute");
    s.map(String::length).forEach(System.out::println);

    System.out.println();
    s = Stream.of("OCA", "OCJP", "OCP");
    s.map(x -> x.length()).forEach(System.out::println);
  }
}

