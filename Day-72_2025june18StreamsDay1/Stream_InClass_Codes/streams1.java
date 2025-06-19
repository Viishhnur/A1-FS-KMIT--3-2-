import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.*;

class test1 {
  public static void main(String args[]){
    List<String> list = Arrays.asList("a1", "a2", "b1", "c2", "camel", "c1");

    Stream<String> fromList =  list.stream();    
    fromList
		.filter(s -> s.startsWith("c"))
		.peek(System.out::println)
		.map(String::toUpperCase)
		.peek(System.out::println)
		.sorted()
		.forEach(System.out::println).Collectors.;

    System.out.println(list);
  }
}

class test2 {
  public static void main(String args[]){
    Stream.of("d2", "a2", "b1", "b3", "c")
        .filter(s -> {
            System.out.println("filter: " + s);
            return true;}).forEach(s -> System.out.println("forEach: " + s));
  }
}




class test3 {
  public static void main(String args[]){
    Stream<String> empty = Stream.empty();
    empty.forEach(System.out::println);

    Stream<Integer> singleElement = Stream.of(1);
    singleElement.forEach(System.out::println);

    Stream<Integer> fromArray = Stream.of(2, 3, 4);
    fromArray.forEach(System.out::println);
  }
}

class test4 {
  public static void main(String args[]){
    List<String> list = Arrays.asList("A", "B", "C", "D", "E");
    Stream<String> fromList = list.stream();
    fromList.forEach(System.out::println);

    Stream<String> fromListParallel = list.parallelStream();
    fromListParallel.forEach(System.out::println);
  }
}

