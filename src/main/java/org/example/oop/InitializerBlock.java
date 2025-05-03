package org.example.oop;

public class InitializerBlock {}
// in short the initializer blocks are as named, for initializing. Static version, although not showcased here as it works pretty much the same, has more use cases than instance
// if interested in initializer block vs constructor scroll all the way down.

// initializer blocks are especially useful for initializing final variables especially if the value of the final variable is something more complex than a = 5, yet it's something that is better done in the current class.
// this is because initializer blocks can contain code, meaning you can do a few operations to get to the value of the final variable, something that you would otherwise do in a method but doing so would not compile
// and throw "final variable is not initialized" error

class ForwardRef {
    {
        i = j = 10; // permitted forward reference

        System.out.println(this.i);
        System.out.println(this.j);
        System.out.println(this.k);
    }


    int i;
    int j = 100;
    final int k = 50;
}

class NonStaticForwardRef {
    { // nonstatic forward refs
        nsf1 = 10;
        nsf1 = sf1;
        // int a = 2 * nsf1; // cannot recognize nsf1 yet, read operation before declaration

        var b = nsf1 = 20;
        // int c = nsf1; // cannot be reached, won't compile because read-before-declare
        int c = this.nsf1; // okay if not accessed by simple name
    }

    int nsf1 = nsf2 = 30; // nsf initialized before declared, compiles
    int nsf2; // it's okay for this to be static, just like bottom line. but the above line cannot be static while nsf2 is not static. they have to match if initialized like above line
    static int sf1 = 5;

    {
        int d = 2 * nsf1; // this is okay, nsf1 was declared above
        var e = nsf1 = 50; // assignment is allowed
    }
}

// Here's a breakdown:
//
//    Purpose: Initializer blocks are used for initializing instance variables. They contain code that is executed when an instance of the class is created.
//    This is especially useful for initializing variables that require more complex logic than a simple assignment.
//
//    Difference from Constructors: While constructors have parameters and blocks don't, the key difference lies in when and how they are executed.
//        Constructors are explicitly called when you create an object using new. You can have multiple constructors with different parameters, allowing you to initialize objects in various ways.
//        Initializer blocks, on the other hand, are executed before the constructor. They are executed in the order they appear in the class definition.
//        Importantly, they are executed every time an object is created, regardless of which constructor is used.
//
//    Use Cases: Initializer blocks are useful when you have initialization code that needs to be run for every object, regardless of the constructor used.
//    This can include setting up default values based on calculations or external data.