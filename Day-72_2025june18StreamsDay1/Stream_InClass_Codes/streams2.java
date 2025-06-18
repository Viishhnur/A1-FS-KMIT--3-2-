import java.util.stream.*;
import java.util.*;

class test1 {
  public static void main(String args[]){
    Arrays.asList("a1", "a2", "a3")
    .stream()
    .findFirst()
    .ifPresent(System.out::println);  
  }	
}



class test2 {
  public static void main(String args[]){
  IntStream.range(1, 5)
  .forEach(System.out::println);

  Arrays.stream(new int[] {1, 2, 3})
    .map(n -> 2 * n + 1) //3, 5, 7
    .average()
    .ifPresent(System.out::println);  
  }
}


class test3 {
  public static void main(String args[]){
    Stream.of("a1", "a2", "a3")
      .map(s -> s.substring(1)) //1, 2, 3
      .mapToInt(Integer::parseInt)
      .max()
      .ifPresent(System.out::println); 
  }
}



class test4 {
  public static void main(String args[]){
    Stream<Double> randoms = Stream.generate(Math::random);
    randoms.limit(5).forEach(System.out::println);  
    System.out.println("After random generation");
    Stream<Integer> oddNumbers = Stream.iterate(1, n -> n +2);
    oddNumbers.limit(5).forEach(System.out::println);  

    System.out.println(oddNumbers);
  }
}
