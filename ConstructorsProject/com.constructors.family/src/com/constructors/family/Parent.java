package com.constructors.family;

public class Parent {

    //this block runs only once when class creating, it called as static initializer
    static {
        System.out.println("com.constructors.family.Parent static initializer: class being constructed");
    }

    private final String name;
    private final String dob;

    protected final int siblings;

    //this block runs every object instantiation, it called as Instance initializer
    {
//        name = "John Doe";
//        dob = "01/01/1900";
        System.out.println("In com.constructors.family.Parent Initializer");
    }

//    public com.constructors.family.Parent() {
//        System.out.println("In com.constructors.family.Parent's No Args Constructor");
//    }

    public Parent(String name, String dob, int siblings) {
        this.name = name;
        this.dob = dob;
        this.siblings = siblings;
        System.out.println("In com.constructors.family.Parent Constructor");
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +", dob='" + dob+ '\'';
    }
}
