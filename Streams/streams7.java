import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.*;

class test1 {
  public static void main(String args[]){
    List<String> list1 = Arrays.asList();
    List<String> list2 = Arrays.asList("apple");
    List<String> list3 = Arrays.asList("orange", "mango");

    Stream<List<String>> fruits =  Stream.of(list1, list2, list3);  
    fruits.flatMap(l -> l.stream())
      .forEach(System.out::println);
  }
}

class test2 {
  public static void main(String args[]){
    List<String> list = Arrays.asList("hello", "world", "java");
    Stream<String> fromList =  list.stream();    
    fromList
      .sorted()
      .forEach(System.out::println);
    System.out.println(list);
    list.stream()
      .sorted(Comparator.reverseOrder())
      .forEach(System.out::println);
  }
}
