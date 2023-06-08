package src2;

import com.oocourse.spec2.exceptions.RelationNotFoundException;

import java.util.TreeSet;

public class MyRelationNotFoundException extends RelationNotFoundException {


    public int id1;

    public int id2;

    public static int count = 0;//该类有一个静态成员变量 count，记录该异常类的实例数量

    public static TreeSet<MultiCount> multiCountSet = new TreeSet<>();;

    public MyRelationNotFoundException(int id1,int id2) {//该类有一个构造函数，接受两个参数 id1 和 id2，在创建异常类实例时，会更新 count 和 multiCountSet
        this.id1 = id1;
        this.id2 = id2;
    }

    @Override
    public void print() {
        count++;
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

        multiCount = new MultiCount(id2,1);//MultiCount 实现了 Comparable 接口，用于在 TreeSet 中排序。compareTo 方法比较了两个 MultiCount 对象的 id 属性。
        // equals 方法用于判断两个 MultiCount 对象是否相等，只比较 id 属性。
        if (multiCountSet.contains(multiCount)) {
            for (MultiCount multiCount1 : multiCountSet) {
                if (multiCount1.equals(multiCount)) {
                    multiCount1.count += multiCount.count;
                }
            }
        } else {
            multiCountSet.add(multiCount);
        }
        int count1 = 0;
        int count2 = 0;
        if (multiCountSet.contains(new MultiCount(id1, 1))) {
            count1 = multiCountSet.ceiling(new MultiCount(id1, 1)).count;
        }
        if (multiCountSet.contains(new MultiCount(id2, 1))) {
            count2 = multiCountSet.ceiling(new MultiCount(id2, 1)).count;
        }
        if (id1 <= id2) {
            System.out.println("rnf-" + count + ", " + id1 + "-" + count1 + ", " + id2 + "-" + count2);
        } else {
            System.out.println("rnf-" + count + ", " + id2 + "-" + count2 + ", " + id1 + "-" + count1);
        }

    }
}
//在该方法中，首先通过 multiCountSet 获取实体 ID 在数据集中出现的次数。然后根据 ID 的大小关系，打印异常信息。
//因为是两个id触发异常的次数 所以就分成两次的multicount来算，一个id1 一个id2， 最后如果multicount 里面有
//id1 的存在 就存在count1 里面
