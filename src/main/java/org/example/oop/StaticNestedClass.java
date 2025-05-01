package org.example.oop;

public class StaticNestedClass {
    private static int x = 1;

    public static class StaticNestedInner { // can only access static members in the outer class
        private void doSomething() {
            System.out.println("Initial value of x: " + x);
            x++;
            System.out.println("Incremented value of x: " + x);
        }
    }

    public void runInner() {
        StaticNestedInner inner = new StaticNestedInner();
        inner.doSomething();
    }
}