package com.oocourse.spec1.exceptions;

import java.util.TreeSet;

public class MyPersonIdNotFoundException extends PersonIdNotFoundException {

    public int id;

    public static int count = 0;

    public static TreeSet<MultiCount> multiCountSet = new TreeSet<>();//这行代码声明了一个类型为 TreeSet<MultiCount> 的静态变量 multiCountSet，
    // 它将用于跟踪每个人员 ID 被搜索但未被找到的次数。TreeSet 用于以排序顺序维护计数

    public MyPersonIdNotFoundException(int id) {

        count++;
        this.id = id;
        MultiCount multiCount = new MultiCount(id,1);
        if (multiCountSet.contains(multiCount)) {//这行代码检查 multiCountSet 是否已经包含了一个具有相同 id 的 MultiCount 实例。
            for (MultiCount multiCount1 : multiCountSet) {//如果 multiCountSet 已经包含了一个具有相同 id 的 MultiCount 实例，则该循环将其计数增加新的 MultiCount 实例的计数。
                if (multiCount1.equals(multiCount)) {
                    multiCount1.count += multiCount.count;
                }
            }
        } else {//如果 multiCountSet 不包含具有相同 id 的 MultiCount 实例，则将新的 MultiCount 实例添加到集合中
            multiCountSet.add(multiCount);
        }
    }


    @Override
    public void print() {
        System.out.println("pinf-" + count + ", " + id + "-" + multiCountSet.ceiling(new MultiCount(id,1)).count);
    }
}
