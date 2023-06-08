package com.oocourse.spec1.exceptions;

import com.oocourse.spec1.exceptions.*;
import com.oocourse.spec1.main.Person;
import com.oocourse.spec1.main.*;
import com.oocourse.spec1.main.Network;
import com.oocourse.spec1.exceptions.*;
import com.oocourse.spec1.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class MyNetwork implements Network {

    public Person[] people;

    public MyNetwork() {
        people = new Person[0];
    }

    @Override
    public boolean contains(int id) {
        for (int i = 0; i < people.length; i++) {
            if (people[i].getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Person getPerson(int id) {
        if (!contains(id)) { return null; }
        for (int i = 0; i < people.length; i++) {
            if (people[i].getId() == id) {
                return people[i];
            }
        }
        return null;
    }

    @Override
    public void addPerson(Person person) throws MyEqualPersonIdException {
        for (Person p : people) {//requires 那个意思 该前置条件的含义是：people 数组中不存在与 person 相等的元素
            if (p.equals(person)) {//people[i].equals(person));
                throw new MyEqualPersonIdException(person.getId());//signals (EqualPersonIdException e)
                // (\exists int i; 0 <= i && i < people.length people[i].equals(person));
            }
        }
        Person[] newPeople = new Person[people.length + 1];//ensures people.length == \old(people.length) + 1;
        for (int i = 0; i < people.length; i++) {
            newPeople[i] = people[i];
        }
        newPeople[newPeople.length - 1] = person;//ensures (\exists int i; 0 <= i && i < people.length; people[i] == person);
        people = newPeople;
    }

    @Override
    public void addRelation(int id1, int id2, int value) throws MyPersonIdNotFoundException, MyEqualRelationException {
        if (!contains(id1)) {
            throw new MyPersonIdNotFoundException(id1);
        } else if (!contains(id2)) {
            throw new MyPersonIdNotFoundException(id2);
        } else if (getPerson(id1).isLinked(getPerson(id2))) {
            throw new MyEqualRelationException(id1, id2);
        }
        Person[] newAcquaintance = new Person[((MyPerson) getPerson(id1)).acquaintance.length + 1];//ensures people.length == \old(people.length);
        for (int i = 0; i < newAcquaintance.length - 1; i++) {//(\forall int i; 0 <= i && i < \old(people.length);
            newAcquaintance[i] = ((MyPerson) getPerson(id1)).acquaintance[i];//(\exists int j; 0 <= j && j < people.length; people[j] == \old(people[i])));
        }
        newAcquaintance[newAcquaintance.length - 1] = getPerson(id2);//@ ensures getPerson(id1).isLinked(getPerson(id2)) && getPerson(id2).isLinked(getPerson(id1));
        //        ((MyPerson) getPerson(id1)).setAcquaintance(newAcquaintance)
        ((MyPerson) getPerson(id1)).acquaintance = newAcquaintance;

        int[] newValue1 = new int[((MyPerson) getPerson(id1)).value.length + 1];//ensures \old(getPerson(id1).value.length) == getPerson(id1).acquaintance.length - 1

        for (int i = 0; i < newValue1.length - 1; i++) {//\forall int i; 0 <= i && i < \old(getPerson(id1).acquaintance.length);
            newValue1[i] = ((MyPerson) getPerson(id1)).value[i];//not_assigned(getPerson(id1).acquaintance[i],getPerson(id1).value[i]));
        }
        newValue1[newValue1.length - 1] = value;//ensures getPerson(id1).queryValue(getPerson(id2)) == value;
        ((MyPerson) getPerson(id1)).value = newValue1;//ensures getPerson(id2).queryValue(getPerson(id1)) == value;

        Person[] newAcquaintance2 = new Person[((MyPerson) getPerson(id2)).acquaintance.length + 1];
        for (int i = 0; i < newAcquaintance2.length - 1; i++) {//\forall int i; 0 <= i && i < people.length && \old(people[i].getId()) != id1 & @     \old(people[i].getId()) != id2; \not_assigned(people[i]));
            newAcquaintance2[i] = ((MyPerson) getPerson(id2)).acquaintance[i];
        }
        newAcquaintance2[newAcquaintance2.length - 1] = getPerson(id1);//@ ensures getPerson(id1).isLinked(getPerson(id2)) && getPerson(id2).isLinked(getPerson(id1));
        //        ((MyPerson) getPerson(id2)).setAcquaintance(newAcquaintance2);
        ((MyPerson) getPerson(id2)).acquaintance = newAcquaintance2;
        int[] newValue2 = new int[((MyPerson) getPerson(id2)).value.length + 1];

        for (int i = 0; i < newValue2.length - 1; i++) {//(\forall int i; 0 <= i && i < \old(getPerson(id2).acquaintance.length);
            newValue2[i] = ((MyPerson) getPerson(id2)).value[i];//not_assigned(getPerson(id2).acquaintance[i],getPerson(id2).value[i]));
        }
        newValue2[newValue2.length - 1] = value;//getPerson(id1).value.length == getPerson(id1).acquaintance.length;
        ((MyPerson) getPerson(id2)).value = newValue2;//ensures getPerson(id2).value.length == getPerson(id2).acquaintance.length;
    }

    @Override
    public int queryValue(int id1, int id2) throws MyPersonIdNotFoundException, MyRelationNotFoundException {
        if (!contains(id1)) {
            throw new MyPersonIdNotFoundException(id1);
        } else if (!contains(id2)) {
            throw new MyPersonIdNotFoundException(id2);
        } else if (!getPerson(id1).isLinked(getPerson(id2))) {
            throw new MyRelationNotFoundException(id1, id2);
        }
        return getPerson(id1).queryValue(getPerson(id2));//result == getPerson(id1).queryValue(getPerson(id2));
    }

    @Override
    public boolean isCircle(int id1, int id2) throws MyPersonIdNotFoundException {
        if (!contains(id1)) {
            throw new MyPersonIdNotFoundException(id1);
        } else if (!contains(id2)) {
            throw new MyPersonIdNotFoundException(id2);
        }
        if (getPerson(id1).isLinked(getPerson(id2))) {//array[i].isLinked(array[i + 1]) == true));
            return true;
        } else {
            Person p1 = getPerson(id1);
            Person p2 = getPerson(id2);
            HashSet<Person> ids1 = new HashSet<Person>();
            ids1.add(p1);
            return isCircle2(new HashSet<Person>(), ids1, p2);
        }
    }

    public boolean isCircle2(HashSet<Person> persons, HashSet<Person> p1, Person p2) {
        if (p1.isEmpty()) { return false; }
        for (Person id: p1) {
            if (id.isLinked(p2)) {
                return true;
            }
        }
        persons.addAll(p1);
        HashSet acids = new HashSet();
        for (Person p: p1) {
            acids.addAll(Arrays.asList(((MyPerson)p).acquaintance));
        }
        acids.removeAll(persons);
        return isCircle2(persons, acids, p2);
    }

    @Override
    public int queryBlockSum() {
        int sum = 0;
        for (int i = 0; i < people.length;i++) {
            ((MyPerson)people[i]).setVisited(false);
        }
        for (int i = 0; i < people.length;i++) {
            if(((MyPerson) people[i]).isVisited() == false) {
                sum++;
                traversal(((MyPerson) people[i]));
            }
        }
        return sum;
    }
//统计社区人数的数量
    public void traversal(MyPerson person) {
        person.setVisited(true);
        for (Person person1 : person.acquaintance) {
            if (((MyPerson) person1).isVisited() == false) {
                traversal((MyPerson) person1);
            }
        }
    }

    @Override
    public int queryTripleSum() {
        int sum = 0;
        for (int i = 0;i < people.length;i++) {
            Person[] acquaintances = ((MyPerson) people[i]).acquaintance;
            for (Person person1 : acquaintances) {
                for (Person person2 : acquaintances) {
                    if(people[i].getId() < person1.getId() && person1.getId() < person2.getId()) {
                        if(person1.isLinked(person2)) {
                            sum++;
                        }
                    }
                }
            }
        }
        return sum;
    }
//对每个人的邻居关系进行遍历。对于每个邻居关系，检查是否满足三元组的要求，即 id1 与 id2、id1 与 id3、id2 与 id3 都是相互认识的，
// 并统计满足条件的三元组数量。对于每个合法的三元组，将计数器sum加1。最后，返回满足条件的三元组数量sum
//在MyPerson类中，包含了一个Person[]类型的属性acquaintance，表示当前人的邻居关系。
// isLinked方法用于判断两个人之间是否存在连边。getId方法用于获取人的id

//    @Override
//    public int queryTripleSum() {
//        int sum = 0;
//        for (int i = 0;i >= 0 && i < people.length;i++) {
//            for (int j = i + 1;i < j && j < people.length;j++) {
//                if(!people[i].isLinked(people[j])) {
//                    continue;
//                }
//                for (int k = j + 1 ;j < k && k < people.length;k++) {
//                    if (people[j].isLinked(people[k]) && people[k].isLinked(people[i])) {
//                        sum++;
//                    }
//                }
//            }
//        }
//        return sum;
//    }



    @Override
    public boolean queryTripleSumOKTest(HashMap<Integer, HashMap<Integer, Integer>> beforeData, HashMap<Integer, HashMap<Integer, Integer>> afterData, int result) {
        for (int personId : beforeData.keySet()) {
            MyPerson person = new MyPerson(personId, personId + "", personId);
            try {
                addPerson(person);
            }
            catch (MyEqualPersonIdException e) {
            }
        }
        for (int personId : beforeData.keySet()) {
            HashMap<Integer, Integer> acquaintanceMap = beforeData.get(personId);
            for (int acquaintanceId : acquaintanceMap.keySet()) {
                if (getPerson(personId).isLinked(getPerson(acquaintanceId))) { continue; }
                try {
                    addRelation(personId, acquaintanceId, acquaintanceMap.get(acquaintanceId));
                }
                catch (MyPersonIdNotFoundException e) {
                }
                catch (MyEqualRelationException e) {
                }
            }
        }
        int re = queryTripleSum();
        if (beforeData.equals(afterData) && result == re) {
            return true;
        }
        return false;
    }
}
/*该方法首先根据beforeData中的数据构建初始的社交网络，使用addPerson方法将每个人添加到社交网络中。然后，根据beforeData中的数据添加邻居关系，
使用addRelation方法添加邻居关系。在添加邻居关系时，如果两个人之间已经存在连边（linked），则跳过。最后，计算社交网络中的三元组数量，
使用queryTripleSum方法计算。如果beforeData和afterData相等，
并且计算出的三元组数量与期望结果一致，则测试通过，返回true，否则测试失败，返回false。
 */
