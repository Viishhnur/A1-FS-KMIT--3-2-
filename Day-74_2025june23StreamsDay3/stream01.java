
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class stream01 {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("India", "Australia", "USA", "Singapore", "UK", "Indian Ocean", "Russia");
        Stream<String> s = list.stream();
        s.filter(x -> x.length() > 4).sorted().limit(2).forEach(System.out::println);
    }
}
