package org.drools.benchmarks.paper;

public class MainRunner {

    public static void main(String[] args) {
        EG3 eg1a = new EG3();
        eg1a.setupKieBase();
        eg1a.setup();
        eg1a.test();

    }
}
