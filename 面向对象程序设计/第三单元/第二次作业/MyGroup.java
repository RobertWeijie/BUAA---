package src2;

import com.oocourse.spec2.main.Group;
import com.oocourse.spec2.main.Person;

public class MyGroup implements Group {
    public int id;
    public Person[] people;

    public MyGroup (int id) {
        this.id = id;
        this.people = new Person[0];
    }
    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null || !(obj instanceof Group)) return false;
        return id==((Group) obj).getId();
    }

    @Override
    public void addPerson(Person person) {
        Person[] newPeople = new Person[people.length + 1];
        System.arraycopy(people, 0, newPeople, 0, people.length);
        newPeople[people.length] = person;
        people = newPeople;
    }

    @Override
    public boolean hasPerson(Person person) {
        for(Person p: people) {
            if(p.equals(person)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getValueSum() {
        int sum=0;
        for(Person p1: people) {
            for(Person p2: people) {
                if(p1.isLinked(p2)) {
                    sum+=p1.queryValue(p2);
                }
            }
        }
        return sum;
    }

    @Override
    public int getAgeMean() {
        int sum=0;
        if(people.length == 0) return 0;
        for(Person p: people) {
            sum+=p.getAge();
        }
        return sum/people.length;
    }

    @Override
    public int getAgeVar() {
        int sum=0;
        if(people.length == 0) return 0;
        int mean=getAgeMean();
        for(Person p: people) {
            sum+=(p.getAge()-mean)*(p.getAge()-mean);//((\sum int i; 0 <= i && i < people.length;
                     //(people[i].getAge() - getAgeMean()) * (people[i].getAge() - getAgeMean())
        }
        return sum/people.length;
    }

    /*@ public normal_behavior
      @ requires hasPerson(person) == true;
      @ assignable people[*];
      @ ensures (\forall Person p; hasPerson(p); \old(hasPerson(p)));
      @ ensures \old(people.length) == people.length + 1;
      @ ensures hasPerson(person) == false;
      @*/
    /*
    @ requires：前置条件，描述了调用该方法的条件。在本例中，前置条件为“待删除的Person对象在社交网络中存在”。
@ assignable：可修改状态，描述了该方法中可能被修改的变量或对象。在本例中，可修改状态为“people数组”。
@ ensures：后置条件，描述了该方法完成后的行为。在本例中，后置条件包括两个子句：
第一个子句“(\forall Person p; hasPerson(p); \old(hasPerson(p)))”表示该方法不会影响社交网络中所有其他的Person对象。即，如果调用该方法前一个Person对象p在社交网络中存在，则调用该方法后p仍然存在于社交网络中。
第二个子句“\old(people.length) == people.length + 1”表示该方法会将people数组中的一个元素删除，即数组长度会减1。
第三个子句“hasPerson(person) == false”表示该方法会将待删除的Person对象从社交网络中删除。
     */
    @Override
    public void delPerson(Person person) {
        int index = -1;
        for (int i = 0; i < people.length; i++) {
            if (people[i] == person) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            Person[] newPeople = new Person[people.length - 1];
            System.arraycopy(people, 0, newPeople, 0, index);
            System.arraycopy(people, index + 1, newPeople, index, people.length - index - 1);
            people = newPeople;
        }
    }
    /*该方法遍历people数组，查找待删除的Person对象，使用 == 运算符判断两个对象是否相等。如果找到了待删除的Person对象，使用System.arraycopy方法从数组中删除该对象，
    并将删除后的新数组赋值给people数组。具体来说，创建一个新的Person数组newPeople，
    长度为原数组people长度减1。然后，使用System.arraycopy方法将原数组中删除待删除对象后的前半部分拷贝到新数组中，
    再将原数组中删除待删除对象后的后半部分拷贝到新数组中。最后，将新数组赋值给people数组。
     */

    @Override
    public int getSize() {
        return people.length;// \result == people.length;
    }
}
