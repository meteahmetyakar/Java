package com.constructors.main;

import com.constructors.family.Child;
import com.constructors.family.Generation;
import com.constructors.family.Parent;
import com.constructors.person.Person;

public class Main {

    public static void main(String[] args) {

        Parent parent = new Parent("Jane Doe", "01/01/1950", 4);
        Child child = new Child();

        System.out.println("com.constructors.family.Parent: " + parent);
        System.out.println("com.constructors.family.Child: " + child);

        Person joe = new Person("Joe", "01-01-1950");
        System.out.println(joe);

        Person joeCopy = new Person(joe);
        System.out.println(joeCopy);

        Generation g = Generation.BABY_BOOMER;
        Generation g2 = Generation.GEN_X;

    }
}
