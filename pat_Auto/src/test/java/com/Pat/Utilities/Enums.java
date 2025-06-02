package com.Pat.Utilities;

/*An enum is a special "class" that represents a group of constants (unchangeable variables, like final variables).

To create an enum, use the enum keyword (instead of class or interface), and separate the constants
        with a comma. Note that they should be in uppercase letters:*/

 class Main {
    enum Level {
        LOW,
        MEDIUM,
        HIGH
    }

    public static void main(String[] args) {
        /*Level myVar = Level.MEDIUM;
        System.out.println(myVar);*/

        Level myVar = Level.MEDIUM;
        System.out.println(myVar);
    }
}



/*

public enum TrafficSignal {
    RED("Stop"),
    GREEN("Go"),
    YELLOW("Slow down");

    private final String action;

    TrafficSignal(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
*/
/*public static void main(String[] args) {
    TrafficSignal signal = TrafficSignal.RED;
    System.out.println("The red light means: " + signal.getAction());
    //Output: The red light means: Stop
    }*//*


*/
