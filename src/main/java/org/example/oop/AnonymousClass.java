package org.example.oop;

public class AnonymousClass {
    private int x = 5;
    public void doSomething() {
        System.out.println("Initial value of x: " + x);

        new Incrementable() {
            void incrementXAndSay() {
                x += 1;
                System.out.println("Incremented X!");
            }
        }.incrementXAndSay(); // this is an anonymous class, it could also be initialized to a variable of its interface's object type such as
                                // Incrementable a = new Incrementable() {code here}.methodInvocation();
                                // but the issue here is that the method's return type will need to match the variable.
                                // a void method for example cannot be called when initializing as it does not have a type.
                                // also worth mentioning that if the method is not immediately called you cannot call it again, given that
                                // this is an anonymous class that technically either implements or extends the object its made with, an example of this is below

        Incrementable a = new Incrementable() {
            public void doSomething() {
                System.out.println("Saying hello!!");
            }
        };

        // a.doSomething(); will not compile. there is no way to access the doSomething() method anymore, under the hood it looks similar to below
        // class *NoName* implements Incrementable {
        //      doSomething() {
        //          System.out.println("Saying hello!!);
        //      }
        // }
        // the implements DOES work with classes, in which case it simply becomes extends.

        System.out.println("Incremented value of x: " + x);
    }

    public static void main(String[] args) {
        AnonymousClass ano = new AnonymousClass();
        ano.doSomething();
    }
}

interface Incrementable {

}