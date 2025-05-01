package org.example.oop;

public class NestedClass {
    private int x = 5;

    public class NestedClassInner {
        void doSomethingWithOuterMethod() {
            System.out.println("Initial value of x: " + x);
            ++x;
            System.out.println("Incremented value of x: " + x);
            sayHello();
        }
    }

    public void sayHello() {
        System.out.println("Saying hello from the outer class.");
    }

    public static void main(String[] args) {
        NestedClass outer = new NestedClass();
        NestedClassInner inner = outer.new NestedClassInner(); // initialize nested local class #1

        inner.doSomethingWithOuterMethod(); // can use outer method in inner class
        outer.new NestedClassInner().doSomethingWithOuterMethod(); // the upper line can also be called like this,
                                                                    // although the access to the object used to call the method is lost,
                                                                    // in which case static nested will probably be better

        NestedClassInner inner2 = new NestedClass().new NestedClassInner(); // initialize nested local class #2
        // this is probably not ideal as it doesn't make sense to initialize an inner class outside, as well as losing access to the outer object.
        // the ideal way of using this would probably be using static/instance blocks or constructors to have the inner class already initialized
        // and the necessary method from it invoked.
    }
}