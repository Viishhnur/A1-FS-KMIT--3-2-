public class TrafficLightTest {
    public static void main(String[] args) {
        
        System.out.println(TrafficLight.RED.getAction());
        System.out.println(TrafficLight.YELLOW.getAction());
        System.out.println(TrafficLight.GREEN.getAction());


        // Iterate all values
        for(TrafficLight light : TrafficLight.values()){
            System.out.println(light + " -> " + light.canGo());
        }

         // Switch-case test
        TrafficLight signal = TrafficLight.RED;
        switch (signal) {
            case RED -> System.out.println("RED means: " + signal.getAction());
            case YELLOW -> System.out.println("YELLOW means: " + signal.getAction());
            case GREEN ->  System.out.println("GREEN means: " + signal.getAction());
            // If no color matches then you will get IllelegalArgumentException , that no key matches in enum
        }

    }
}
