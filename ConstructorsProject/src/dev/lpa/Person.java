package dev.lpa;

public record Person(String name, String dob) {

//    public Person(String name, String dob) {
//        this.name = name;
//        this.dob = dob.replace('-', '/');
//    }

    /*
        Canonical Constructor: It requires all attributes to be present.
        Other constructors can be implemented using this. It's also referred to as the Long constructor.

        Compact Constructor: This can only be used in records. It is a shorter way of using the Canonical constructor.
        Typically, in this constructor, operations such as control and manipulation of the parameters given to the canonical constructor are performed.
        Later, this constructor automatically calls the canonical constructor it creates.

    */

    public Person(Person p) {
        this(p.name, p.dob);
    }

    public Person {
        if (dob == null) throw new IllegalArgumentException("Bad data");
        dob = dob.replace('-', '/');
    }
}
