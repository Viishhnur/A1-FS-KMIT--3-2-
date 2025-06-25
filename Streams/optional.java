
import java.util.Optional;

class withoutOptionalDemo {
    public static void main(String[] args)
    {
        String[] words = new String[10];
        
		Optional<String> checkNull = Optional.ofNullable(words[5]);

		String word = words[5].toLowerCase();
		System.out.print(word);
    }
}

public class optional {
    public static void main(String[] args)
    {
        String[] words = new String[10];
		words[5] = "Hello";
        
		Optional<String> checkNull = Optional.ofNullable(words[5]);

		if (checkNull.isPresent()) {
			String word = words[5].toLowerCase();
			System.out.print(word);
		}
		else
			System.out.println("word is null");
    }
}