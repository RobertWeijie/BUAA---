package com.oocourse.spec1.exceptions;

import com.oocourse.spec1.main.Person;
import com.oocourse.spec1.main.Person;

public class MyPerson implements Person {

    public int id;

    public String name;

    public int age;

    public Person[] acquaintance;

    public  int[] value;

    public boolean visited;

    public MyPerson(int id,String name,int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.acquaintance = new Person[0];
        this.value = new int[0];
        this.visited = false;
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

    public void setAcquaintance(Person[] acquaintance) {
        this.acquaintance = acquaintance;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            if (((Person) obj).getId() == id) {
                return true;//自动return true
            }
        }
        return false;
    }
}
