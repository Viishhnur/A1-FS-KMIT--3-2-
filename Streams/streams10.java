import java.util.stream.*;
import java.util.*;

class test1 {
  public static void main(String args[]){
    Stream<Integer> infinite = Stream.iterate(1, x -> x + 1);
    infinite.limit(5)
      .filter(x -> x % 2 == 1)
      .forEach(System.out::println);
  }
}



class test2 {
  public static void main(String args[]){
    Stream<Integer> infinite = Stream.iterate(1, x -> x + 1);
    infinite.limit(5)
      .peek(System.out::println)
      .filter(x -> x % 2 == 1)
      .forEach(System.out::println);
  }
}



class test3 {
  public static void main(String args[]){
    Stream<Integer> infinite = Stream.iterate(1, x -> x + 1);
    infinite.filter(x -> x % 2 == 1)
      .limit(5)
      .forEach(System.out::println);
  }
}




class test4 {
  public static void main(String args[]){
    Stream<Integer> infinite = Stream.iterate(1, x -> x + 1);
    infinite.filter(x -> x % 2 == 1)
      .peek(System.out::println)
      .limit(5)
      .forEach(System.out::println);
  }
}