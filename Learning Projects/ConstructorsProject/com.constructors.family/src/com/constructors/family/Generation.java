package com.constructors.family;

public enum Generation {
    GEN_Z(2001,2025) {
        @Override
        public String toString() {
            return "com.constructors.family.Generation Z";
        }
    },
    MILLENNIAL(1981,2000),
    GEN_X(1965,1980),
    BABY_BOOMER(1946,1964),
    SILENT_GENERATION(1927,1945),
    GREATEST_GENERATION(1901,1926);

    private int startYear;
    private int endYear;

    Generation(int startYear, int endYear) {
        this.startYear = startYear;
        this.endYear = endYear;
        System.out.println(this + " " + startYear + " - " + endYear);
    }



    /*

    When a class is instantiated, each enum value invokes a constructor.
    If a constructor is not defined, there won't be an issue because a default constructor is created.
    However, if a parameterized constructor exists, then there is a need for a no-args constructor.
    We can also overcome this situation by providing parameters alongside the values.
    Additionally, we can provide additional methods or similar using curly braces {}. (As demonstrated in GEN_Z)

    com.constructors.family.Generation()
    {

    }
    */
}
