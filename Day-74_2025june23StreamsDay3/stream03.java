
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class stream03 {
    public static void main(String[] args) {
        List<String>l=Arrays.asList("one","two");
        Stream<String>sl=l.stream();
        l.add("three");
        l.remove(1);
        System.out.println(l);
        // String s=sl.collect(Collectors.joining(","));
        // System.out.println(s);
    }
}
