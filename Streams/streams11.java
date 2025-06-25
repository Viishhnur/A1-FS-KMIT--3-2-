import java.util.stream.*;
import java.util.*;

class test1 {
  public static void main(String args[]){
    Stream.of("d2", "a2", "b1", "b3", "c")
      .map(s -> {
          System.out.println("map: " + s);
          return s.toUpperCase();
      })
      .filter(s -> {
          System.out.println("filter: " + s);
          return s.startsWith("A");
      })
      .forEach(s -> System.out.println("forEach: " + s));  }	
}

class test2 {
  public static void main(String args[]){
    Stream.of("d2", "a2", "b1", "b3", "c")
      .filter(s -> {
          System.out.println("filter: " + s);
          return s.startsWith("a");
      })
      .map(s -> {
          System.out.println("map: " + s);
          return s.toUpperCase();
      })
      .forEach(s -> System.out.println("forEach: " + s));
  }
}

class test3 {
  public static void main(String args[]){
    Stream.of("d2", "a2", "b1", "b3", "a3")
      .filter(s -> {
          System.out.println("filter: " + s);
          return s.startsWith("a");
      })
      .sorted((s1, s2) -> {
          System.out.printf("sort: %s; %s\n", s1, s2);
          return s1.compareTo(s2);
      })
      .map(s -> {
          System.out.println("map: " + s);
          return s.toUpperCase();
      })
      .forEach(s -> System.out.println("forEach: " + s));  }
}

class test4 {
	public static void main(String args[]){
		List<String> l = new ArrayList(Arrays.asList("one", "two"));
		Stream<String> sl = l.stream();
		l.add("three");
		l.remove(1);
		String s = sl.collect(Collectors.joining(",")); 
		System.out.println(s);
	}
}