package com.oocourse.spec1.exceptions;

import java.util.TreeSet;

public class MyEqualPersonIdException extends EqualPersonIdException {


    public int id;

    public static int count = 0;

    public static TreeSet<MultiCount> multiCountSet = new TreeSet<>();;


    public MyEqualPersonIdException(int id) {
        count++;
        this.id = id;
        MultiCount multiCount = new MultiCount(id,1);
        if (multiCountSet.contains(multiCount)) {
            for (MultiCount multiCount1 : multiCountSet) {
                if (multiCount1.equals(multiCount)) {
                    multiCount1.count += multiCount.count;//统计的是相等的人员 id 的出现次数。
                }
            }
        } else {
            multiCountSet.add(multiCount);
        }
    }

    @Override
    public void print() {
        System.out.println("epi-" + count + ", " + id + "-" + multiCountSet.ceiling(new MultiCount(id,1)).count);
    }
}
