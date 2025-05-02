package org.example.oop;

public class Records {
    public static void main(String[] args) {
        Rectangle rect = new Rectangle(10.5, 12.7);
    }
}

// record classes are always final, meaning they cannot be extended, and they cannot extend other classes. Can implement interfaces.
record RectangleRecordExample (double length, double width) implements Recordable { // record parameters are private AND final, java auto implements getter, toString, equals, hashCode

}

interface Recordable {}

// this class would be the exact equivalent of record Rectangle above
final class RectangleClassExample {
    private final double length;
    private final double width;

    public RectangleClassExample (double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double length() {
        return length;
    }

    public double width() {
        return width;
    }

    // java implementation of equals(), hashCode() which specify
    // that two record objects are equal if they
    // are of the same type and contain equal field values.

    // An implementation of toString() that returns a string
    // representation of all the record class's fields,
    // including their names.
}

record Rectangle (double length, double width) {

    public Rectangle(double width) {
        this(10, width); // this is a canonical constructor
    }

    // this is a compact constructor, this is only possible for java records.
    public Rectangle { // despite not having parameters, the values will be assigned to the fields after this constructor.
        if (length > 100) {
            throw new IllegalArgumentException("This rectangle cannot have a length over 100.");
        }

        // initialization of fields width and length should happen at this stage by java automatically, seeing that there is no further code.
    }

    public void printValuesAndSayHi() { // can have instance methods
        System.out.println("Hello! length: " + length + ", width: " + width);
    }

    public static String returnStringWithValuesAndHi(Rectangle rectangle) { // can also have static methods
        return "Hello! Hello! length: " + rectangle.length() + ", width: " + rectangle.width();
    }
}

// example of records with generics.
record Pair<T extends Number> (T x, T y) {

}

record RectanglePair(double length, double width) {

    public RectanglePair(Pair<Double> corner) { // alternative record constructor that substitutes the values for fields from another object.
        this(corner.x(), corner.y()); // such constructor must call the record's canonical constructor in order to be compiled.
    }

    // such method is implicitly implemented without the println, yet it can be explicitly implemented to possibly change behavior.
    // docs say ensure that the accessors of these record methods (also called components) are of matching characteristics, meaning they are public just like their implicit counterpart.
    // but upon a short test it is easily discoverable that the record components can ONLY be public
    public double length() {
        System.out.println("Length is " + length);
        return length;
    }
}

record StaticRectangle(double length, double width) {
    // static field declaration, cannot be done in header.
    static double rectangleVariable;

    // static initializer
    static {
        rectangleVariable = (1 + Math.pow(3, 7)) / 2;
    }

    // you cannot do instance (non-static) fields like above. for fields, it must be done in headers whereas instance blocks are simply not allowed in records.
    /*
    {
        someVariable = 5;
    }
     */ // this does not compile

}

// nested records
record Rectangle3(double length, double width) {

    record RotationAngle(double angle) { // nested record classes are implicitly static, meaning there is only one instance of this nested record across all outer instances
        public RotationAngle {
            angle = Math.toRadians(angle);
        }
    }

    public Rectangle doSomething(double angle) {
        RotationAngle ra = new RotationAngle(angle);

        //do something with nested class

        return new Rectangle(0, 0);
    }
}

class ExampleClass {
    record ExampleRecord(String name, int number) { }

    public static void main(String[] args) {

        class ExampleInnerClass implements Runnable {

            // Record class member, declared in an inner class, therefore implicitly static
            // records are always static when nested
            ExampleRecord a;

            public ExampleInnerClass(ExampleRecord a) {
                this.a = a;
            }

            public void run() {
                // do something
            }
        }

        // some code using exampleInnerClass
    }
}