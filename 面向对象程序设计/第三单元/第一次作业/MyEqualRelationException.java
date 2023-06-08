package com.oocourse.spec1.exceptions;

import com.oocourse.spec1.exceptions.MyEqualRelationException;

import java.util.TreeSet;

public class MyEqualRelationException extends EqualRelationException {

    public int id1;

    public int id2;

    public static TreeSet<MultiCount> multiCountSet = new TreeSet<>();;

    public static int count = 0;

    public MyEqualRelationException(int id1, int id2) {
        count++;
        this.id1 = id1;
        this.id2 = id2;
        MultiCount multiCount = new MultiCount(id1,1);
        if (multiCountSet.contains(multiCount)) {
            for (MultiCount multiCount1 : multiCountSet) {
                if (multiCount1.equals(multiCount)) {
                    multiCount1.count += multiCount.count;
                }
            }
        } else {
            multiCountSet.add(multiCount);
        }

        if (id1 != id2) {//当 id1 和 id2 不相等时，表示有两个不同的元素参与了相等关系，这时需要分别记录它们的出现次数
            /// 首先，创建一个 MultiCount 对象 multiCount，记录元素 id2 的出现次数为 1。
            // 然后，判断 multiCountSet 集合中是否已经存在元素 id2 的出现次数的记录。如果存在，就在集合中找到对应的 MultiCount 对象 multiCount1，
            // 将其记录的出现次数 count 加上 multiCount 的出现次数 1。如果不存在，就将 multiCount 添加到 multiCountSet 集合中。
            multiCount = new MultiCount(id2,1);
            if (multiCountSet.contains(multiCount)) {
                for (MultiCount multiCount1 : multiCountSet) {
                    if (multiCount1.equals(multiCount)) {
                        multiCount1.count += multiCount.count;
                    }
                }
            } else {
                multiCountSet.add(multiCount);
            }
        }
    }

    @Override
    public void print() {
        int count1 = 0;
        int count2 = 0;
        if (multiCountSet.contains(new MultiCount(id1, 1))) {
            count1 = multiCountSet.ceiling(new MultiCount(id1, 1)).count;
        }
        if (multiCountSet.contains(new MultiCount(id2, 1))) {
            count2 = multiCountSet.ceiling(new MultiCount(id2, 1)).count;
        }
        if (id1 <= id2) {
            System.out.println("er-" + count + ", " + id1 + "-" + count1 + ", " + id2 + "-" + count2);
        } else {
            System.out.println("er-" + count + ", " + id2 + "-" + count2 + ", " + id1 + "-" + count1);
        }
    }
}
