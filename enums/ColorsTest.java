import java.util.Scanner;

/*
 In Java, enumerations (enums) are a special type used to define a group of named constants.

Enums help in readability, maintainability, and type safety in programs by assigning meaningful names to integer values.
Mainly useful when we have a small set of possible values for an item like directions, days of week, etc.

 */
public class ColorsTest {
    public static void main(String[] args) {
        String inpColor;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter a color: ");
            inpColor = sc.nextLine();
        }

        Color color = Color.valueOf(inpColor.toUpperCase());

        // this is called as Rule switch
        switch (color) {
            case RED -> System.out.println("You entered RED");
            case YELLOW -> System.out.println("You entered YELLOW");
            case GREEN -> System.out.println("You entered GREEN");

        }

        for (Color c : Color.values()) { // Use EnumType.values() to loop through all enum constants.
            System.out.println(c);
        }
    }
}
