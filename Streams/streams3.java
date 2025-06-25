import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/*
Terminal operations produces a non-stream, result such as primitive value, 
a collection or no value at all. Terminal operations are typically preceded 
by intermediate operations which return another Stream which allows operations
to be connected in a form of a query.

Reduction operations, in general, are terminal operations which combine stream 
elements and return a summary result either as:

A single value by using reduce() and it's special cases: min(), max(), count(),
sum(), average().
Or 
a collection by using collect() or toArray() methods. These methods are 
categorized as mutable reduction because it collects desired result into a 
mutable object such as a Collection.
*/

class test1 {
  public static void main(String[] args) {
    Stream<String> s = Stream.of("kmit", "ngit");
    System.out.println(s.count());     

    s = Stream.of("Subbu", "Satyam", "Raj");
    Optional<String> min = s.min((s1, s2) -> s1.length() - s2.length());
    min.ifPresent(System.out::println);

    s = Stream.of("Subbu", "Satyam", "Raj");
    Optional<String> max = s.max((s1, s2) -> s1.length() - s2.length());
    max.ifPresent(System.out::println);
  }    
} 

class test2 {
  public static void main(String[] args) {
    Stream<String> s = Stream.of("Subbu", "Satyam", "Raj");

    Stream<String> infinite = Stream.generate(() -> "Kmit");
    s.findAny().ifPresent(System.out::println);

    infinite.findAny().ifPresent(System.out::println);

    Stream<Integer> output = Stream.iterate(1, n -> n * 3);
    output.limit(5).forEach(System.out::println);

    Stream<Integer> si = Stream.of(1, 2, 3);
    si.limit(5).forEach(System.out::println);

  /*  Stream ss = Stream.of("a", "b");
    for(String obj:ss)
      System.out.println(obj); */
  }    
} 