package org.example.oop;

public class InitializerBlock {}
// in short the initializer blocks are as named, for initializing. Static version, although not showcased here as it works pretty much the same, has more use cases than instance
// if interested google instance initializer block vs constructor.
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

