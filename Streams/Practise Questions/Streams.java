import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;

// Given a list of integers, separate odd and even numbers?
class test1 
{
    public static void main(String[] args) 
    {
        List<Integer> listOfIntegers = Arrays.asList(71, 18, 42, 21, 67, 32, 95, 14, 56, 87);
         
        Map<Boolean, List<Integer>> oddEvenNumbersMap = 
                listOfIntegers.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));
         
        Set<Entry<Boolean, List<Integer>>> entrySet = oddEvenNumbersMap.entrySet();
		System.out.println(entrySet);
         
        for (Entry<Boolean, List<Integer>> entry : entrySet) 
        {
            System.out.println("--------------");
             
            if (entry.getKey())
            {
                System.out.println("Even Numbers");
            }
            else
            {
                System.out.println("Odd Numbers");
            }
             
            System.out.println("--------------");
             
            List<Integer> list = entry.getValue();
             
            for (int i : list)
            {
                System.out.println(i);
            }
        }
    }
}

// Remove duplicate elements from a list
class test2 
{
    public static void main(String[] args) 
    {
        List<String> listOfStrings = Arrays.asList("Java", "Python", "C#", "Java", "Kotlin", "Python");
         
        List<String> uniqueStrngs = listOfStrings.stream().distinct().collect(Collectors.toList());
         
        System.out.println(uniqueStrngs);
    }
}

// To find frequency of each character in a string 
class test3 
{
    public static void main(String[] args) 
    {
        String inputString = "Keshav Memorial Institute of Techonology";
         
        Map<Character, Long> charCountMap = 
                    inputString.chars()
                                .mapToObj(c -> (char) c)
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
         
        System.out.println(charCountMap);
    }
}

// To find frequency of each element in an array or a list
class test4 
{
    public static void main(String[] args) 
    {
        List<String> stationeryList = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Stapler", "Note Book", "Pencil");
         
        Map<String, Long> stationeryCountMap = 
                stationeryList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
         
        System.out.println(stationeryCountMap);
    }
}

// To sort the given list of decimals in reverse order
class test5 
{
    public static void main(String[] args) 
    {
        List<Double> decimalList = Arrays.asList(12.45, 23.58, 17.13, 42.89, 33.78, 71.85, 56.98, 21.12);
         
        decimalList.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
    }
}

// Given a list of strings, join the strings with '[' as prefix, ']' as suffix and ',' as delimiter?

class test6 
{
    public static void main(String[] args) 
    {
        List<String> listOfStrings = Arrays.asList("Facebook", "Twitter", "YouTube", "WhatsApp", "LinkedIn");
         
        String joinedString = listOfStrings.stream().collect(Collectors.joining(", ", "[", "]"));
         
        System.out.println(joinedString);
    }
}

// Given a list of integers, find maximum and minimum of those numbers?
class test7 
{
    public static void main(String[] args) 
    {
        List<Integer> listOfIntegers = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
         
        int max = listOfIntegers.stream().max(Comparator.naturalOrder()).get();
         
        System.out.println("Maximum Element : "+max);
         
        int min = listOfIntegers.stream().min(Comparator.naturalOrder()).get();
         
        System.out.println("Minimum Element : "+min);
    }
}

// How do you merge two unsorted arrays into single sorted array using Java 8 streams?

 class test8 
{
    public static void main(String[] args) 
    {
        int[] a = new int[] {4, 2, 7, 1};
         
        int[] b = new int[] {8, 3, 9, 5};
         
        int[] c = IntStream.concat(Arrays.stream(a), Arrays.stream(b)).sorted().toArray();
         
        System.out.println(Arrays.toString(c));
    }
}

// Program to check if two strings are anagrams or not?

class test9 
{
    public static void main(String[] args) 
    {
        String s1 = "RaceCar";
        String s2 = "CarRace";
         
        s1 = Stream.of(s1.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining());
         
        s2 = Stream.of(s2.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining());
         
        if (s1.equals(s2)) 
        {
            System.out.println("Two strings are anagrams");
        }
        else
        {
            System.out.println("Two strings are not anagrams");
        }
    }
}

// How do you find common elements between two arrays?
class test10 
{
    public static void main(String[] args) 
    {
        List<Integer> list1 = Arrays.asList(71, 21, 34, 89, 56, 28);
         
        List<Integer> list2 = Arrays.asList(12, 56, 17, 21, 94, 34);
         
        list1.stream().filter(list2::contains).forEach(System.out::println);
    }
}

// Reverse each word of a string?

class test11 
{
    public static void main(String[] args) 
    {
        String str = "Java Concept Of The Day";
         
        String reversedStr = Arrays.stream(str.split(" "))
                                    .map(word -> new StringBuffer(word).reverse())
                                    .collect(Collectors.joining(" "));
         
        System.out.println(reversedStr);
    }
}

// Palindrome program 
class test12 
{
    public static void main(String[] args) 
    {
        String str = "ROTATOR";
         
        boolean isItPalindrome = IntStream.range(0, str.length()/2).
                noneMatch(i -> str.charAt(i) != str.charAt(str.length() - i -1));
          
        if (isItPalindrome)
        {
            System.out.println(str+" is a palindrome");
        }
        else
        {
            System.out.println(str+" is not a palindrome");
        }
    }
}

// How do you extract duplicate elements from an array?

class test13 
{
    public static void main(String[] args) 
    {
        List<Integer> listOfIntegers = Arrays.asList(111, 222, 333, 111, 555, 333, 777, 222);
         
        Set<Integer> uniqueElements = new HashSet<>();
         
        Set<Integer> duplicateElements = listOfIntegers.stream().filter(i -> ! uniqueElements.add(i)).collect(Collectors.toSet());
         
        System.out.println(duplicateElements);
    }
}

// To generate Fibonacci series
class test14 
{
    public static void main(String[] args) 
    {
        Stream.iterate(new int[] {0, 1}, f -> new int[] {f[1], f[0]+f[1]})
                .limit(10)
                .map(f -> f[0])
                .forEach(i -> System.out.print(i+" "));
    }
}

// Find the age of a person in years if the birthday has given?

class test15 
{
    public static void main(String[] args) 
    {
        LocalDate birthDay = LocalDate.of(2022, 01, 01);
        LocalDate today = LocalDate.now();
         
        System.out.println(ChronoUnit.YEARS.between(birthDay, today));
    }
}