package com.oocourse.spec1.exceptions;

import com.oocourse.spec1.main.*;

public class Main {


    public static void main(String [ ] args)throws Exception {
        Runner runner = new Runner(MyPerson.class, MyNetwork.class);
        runner.run();
    }
}
