package org.example.oop;

public class FluencyAndMethodChaining {
    private String stringVariable;

    public FluencyAndMethodChaining() {

    }

    public FluencyAndMethodChaining printStringVariable() {
        System.out.println(stringVariable);

        return this;
    }

    public FluencyAndMethodChaining setStringVariable(String value) {
        this.stringVariable = value;

        return this;
    }

    public static void main(String[] args) {
        FluencyAndMethodChaining object = new FluencyAndMethodChaining().setStringVariable("hi").printStringVariable().setStringVariable("hello!").printStringVariable();
        // this is used in builder pattern where you have a builder class building an object, allowing you to provide values for final objects
        // and keep returning the object until .build() used which returns the actual object and not the builder. it is also called fluency(?)

        // in summary the main point is that as long as you return the correct object/data type that contains the access to the call you want to make, you can continue chaining.
        // chaining would usually end if, maybe among other things, either one of below reasons happen:
        // 1- reached intended result.
        // 2- next intended method call is not in the scope of the last return type.
        // 3- void is returned.
    }
}
