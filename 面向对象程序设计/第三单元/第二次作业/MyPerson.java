package src2;

import com.oocourse.spec2.main.Message;
import com.oocourse.spec2.main.Person;

import java.util.Arrays;
import java.util.List;

public class MyPerson implements Person {


    public int id;

    public String name;

    public int age;

    public Person[] acquaintance;

    public  int[] value;

    public int socialValue;

    public Message[] messages;
    public boolean visited;

    public MyPerson(int id,String name,int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.acquaintance = new Person[0];
        this.value = new int[0];
        this.visited = false;
        this.socialValue = 0;
        this.messages = new Message[0];
    }


    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public boolean isLinked(Person person) {
        for (int i = 0; i < acquaintance.length; i++) {//exists int i; 0 <= i && i < acquaintance.length;
            if (acquaintance[i].getId() == person.getId()) {//acquaintance[i].getId() == person.getId())
                return true;//ensures 基本这判定return true
            }
        }
        if (person.getId() == id) { return true; }//person.getId() == id
        return false;
    }

    @Override
    public int queryValue(Person person) {
        for (int i = 0; i < acquaintance.length; i++) {
            if (acquaintance[i].getId() == person.getId()) {// acquaintance[i].getId() == person.getId()
                return value[i];//使得该元素的 getId() 方法返回值等于 person.getId() 方法返回值
            }//这段我们可以看到 几段都是姨姨 除了有一个多了\result == value[i] 所以才有这段代码的实现
        }
        return 0;
    }

    @Override
    public int compareTo(Person p2) {
        return name.compareTo(p2.getName());
    }

    @Override
    public void addSocialValue(int num) {
        this.socialValue+=num;
    }

    @Override
    public int getSocialValue() {
        return socialValue;
    }

    @Override
    public List<Message> getMessages() {
        return Arrays.asList(messages);
    }


    /*@ public normal_behavior
  @ assignable \nothing;
  @ ensures (\forall int i; 0 <= i && i < messages.length && i <= 4;
  @           \result.contains(messages[i]) && \result.get(i) == messages[i]);
  @ ensures \result.size() == ((messages.length < 5)? messages.length: 5);
  @*/
    @Override
    public List<Message> getReceivedMessages() {
        int min = messages.length < 5 ? messages.length : 5;//\result.size() == ((messages.length < 5)? messages.length: 5);
        return Arrays.asList(Arrays.copyOfRange(messages, 0, min));
    }

    public void setAcquaintance(Person[] acquaintance) {
        this.acquaintance = acquaintance;
    }

    /*@ also
     @ public normal_behavior
     @ requires obj != null && obj instanceof Person;
     @ assignable \nothing;
     @ ensures \result == (((Person) obj).getId() == id);
     @ also
     @ public normal_behavior
     @ requires obj == null || !(obj instanceof Person);
     @ assignable \nothing;
     @ ensures \result == false;
     @*/
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            if (((Person) obj).getId() == id) {
                return true;//自动return true
            }
        }
        return false;

    }
}
