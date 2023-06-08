package src2;

import java.util.Objects;

public class MultiCount implements Comparable<MultiCount> {

    public int id;



    public int count;

    public MultiCount(int id, int count) {
        this.id = id;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        MultiCount that = (MultiCount) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(MultiCount multiCount) {
        if (id < multiCount.id) {
            return -1;
        } else if (id == multiCount.id) {
            return 0;
        } else {
            return 1;
        }
    }
}

/*这段代码是一个 Java 类，包含了一个构造函数和三个方法。在构造函数中，
传入两个参数 id 和 count，并将其分别赋值给类中的两个实例变量。在该类中，
还重写了 Object 类的 equals、hashCode 和 Comparable 接口的 compareTo 方法。
其中，equals 方法比较两个 MultiCount 对象的 id 是否相等，hashCode 方法返回对象的 id 的哈希值，compareTo 方法根据对象的 id 进行比较，
返回一个整数，表示该对象与传入对象的大小关系。
 */
/*记录异常类 然后利用multicount 给exception们

 */
