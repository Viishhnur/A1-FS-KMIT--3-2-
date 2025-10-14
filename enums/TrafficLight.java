public enum TrafficLight {

    RED("Stop"){
        @Override
        public boolean canGo(){return false;}
    }, YELLOW("Wait"){
        @Override
        public boolean canGo(){return false;}
    }, GREEN("Go"){
        @Override
        public boolean canGo(){return true;}

        // public int getSomething(){return 1;}  // we can any other method associated also
    };

    // public static final TrafficLight BLUE = new TrafficLight("BLUE",3);
    private final String action;

    private TrafficLight(String action) { // enum constructor is private by default , no need to explicitly mention that
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    // canGo abstract method , which should be implemented by each constant 
    public abstract boolean canGo();
}

/*
 * Java converts the above code into below one while compiling
 * 
 * public final class TrafficLight extends java.lang.Enum<TrafficLight> {
 * public static final TrafficLight RED = new TrafficLight("RED", 0);
 * public static final TrafficLight YELLOW = new TrafficLight("YELLOW", 1);
 * public static final TrafficLight GREEN = new TrafficLight("GREEN", 2);
 * 
 * private TrafficLight(String name, int ordinal) {
 * super(name, ordinal);
 * }
 * 
 * public static TrafficLight[] values() { ... }
 * public static TrafficLight valueOf(String name) { ... }
 * }
 * 
 * So under the hood:
 * 
 * Every enum extends java.lang.Enum.
 * 
 * It’s final, meaning it cannot be subclassed.
 * 
 * The compiler automatically adds values() and valueOf() methods.
 * 
 * Why Java designed it this way
 * 
 * To prevent misuse — enum constants should be compile-time known.
 * 
 * To enforce type safety — you can’t accidentally create new constants at
 * runtime.
 * 
 * To simplify syntax — just use enum and the compiler handles all boilerplate.
 */
