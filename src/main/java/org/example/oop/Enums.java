package org.example.oop;

public class Enums {
    enum Level {
        HIGH, // all enum constants are always public static final
        MEDIUM,
        LOW
    } // what is the difference between an enum being put inside or outside a class?

    public static void main(String[] args) {
        Level level = Level.HIGH; // can make enums into variables
        Level2 level2 = Level2.LOW;

        printEnum(level); // printing enum directly shows its value instead of an object reference, so it works slightly different.
        ifBlockEnum(Level.MEDIUM);
        switchExpEnum(Level.LOW);
        iterateEnum();

        String levelString = level.toString(); // for printing .toString() is called automatically but for storing it has to be done manually.
        String levelString2 = level2.toString(); // .toString() is possible even with an enum that actually holds a value inside.
                                                    // but although unlike the above "level", whose HIGH/MED/LOW does not correspond to anything,
                                                    // level2's values correspond to 3/2/1 as integers and some strings, .toString() only shows constant's name as a String and not it's values.


        System.out.println(level2); // printing out level2 and level2.toString() gives the same values, which is not surprising
        System.out.println(levelString2); // but it means that you cannot reach the value of an enum by simply calling the variable.

        System.out.println(level2.getNumber()); // a method that returns the value needs to be implemented
        System.out.println(Level2.MEDIUM.getNumber()); // another way to call the getter, since they are static it's always the same value

        Level3 level3 = Level3.HIGH;

        System.out.println(level3.sayHello()); // can create and implement unique logic per enum constant through abstract methods

        System.out.println(level2.name()); // returns the constant's exact name, but .toString() should be preferred. read docs of this method for detail, this is a method for "specialized situation"
    }

    public static void iterateEnum() {
        int i = 0;
        for (Level level : Level.values()) {
            System.out.println("Iteration " + (++i) + ": " + level);
        }
    }

    public static void switchExpEnum(Level level) { // enum in a parameter, are there any edge cases?
        switch (level) { // once in switch, the requirement of the static call Level.HIGH/Level.MEDIUM/Level.Low disappears. This is a behaviour specific to switch, yet it makes things quite concise.
            case HIGH -> System.out.println("switch high level.");
            case MEDIUM -> System.out.println("switch medium level.");
            case LOW -> System.out.println("switch low level.");
        }
    }

    public static void ifBlockEnum(Level level) {
        if (level.equals(Level.HIGH)) { // both equals and == works exactly the same, are there edge cases to this?
            System.out.println("high level.");
        } else if (level == Level.MEDIUM) {
            System.out.println("medium level.");
        } else if (level == Level.LOW) {
            System.out.println("low level.");
        }
    }

    public static void printEnum(Level level) {
        System.out.println(level);
    }
}

enum Level2 {
    // all enum constants are always public static final
    HIGH (3, "big hello"),
    MEDIUM (2, "medium hello"),
    LOW (1, "small hello");

    // enum variables can be public, can be static and can be not-final unrelated to one another, but having them be private final makes more sense as enum constants are public static final
    private final int number;
    private final String hello;

    // all enum constructors are always private
    Level2(int number, String hello) {
        this.number = number; // this constructor is used by the above enum values when called to self-assign values.
                                // I think of this behaviour to be similar to a mini-class,
                                // each value is using enum's constructor to assign themselves a value,
                                // but they cannot do this without a field variable,
                                // this can be thought as each enum value has a different instance of the field variable inside them

        this.hello = hello; // upon introducing this variable, I had to add a string value as a parameter to each of the enum values
                            // which confirms that they are almost like automated objects using the constructor of the scope that they are in
    }

    public int getNumber() {
        return number;
    }
}

enum Level3 {
    HIGH {
        @Override
        public String sayHello() {
            return "big HELLO";
        }
    },
    MEDIUM {
        @Override
        public String sayHello() {
            return "medium hello";
        }
    },
    LOW {
        @Override
        public String sayHello() {
            return "low hello";
        }
    };

    public abstract String sayHello(); // enums can have abstract methods, which forces each enum constant to have an implementation for it. Truly like a class extending a superclass
}