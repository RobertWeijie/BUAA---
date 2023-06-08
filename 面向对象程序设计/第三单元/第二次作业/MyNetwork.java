package src2;

import com.oocourse.spec2.exceptions.*;
import com.oocourse.spec2.main.Group;
import com.oocourse.spec2.main.Message;
import com.oocourse.spec2.main.Network;
import com.oocourse.spec2.main.Person;

import java.util.*;

public class MyNetwork implements Network {


    public Person[] people;

    public Group[] groups;

    public Message[] messages;

    public MyNetwork() {
        people = new Person[0];
        groups = new Group[0];
        messages = new Message[0];
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
    public void modifyRelation(int id1, int id2, int value) throws PersonIdNotFoundException, EqualPersonIdException, RelationNotFoundException {
        if(!contains(id1)) {
            throw new MyPersonIdNotFoundException(id1);
        } else if(!contains(id2)) {
            throw new MyPersonIdNotFoundException(id2);
        } else if(id1 == id2) {
            throw new MyEqualPersonIdException(id1);
        } else if(!getPerson(id1).isLinked(getPerson(id2))) {
            throw new MyRelationNotFoundException(id1, id2);
        }
        MyPerson p1 = (MyPerson) getPerson(id1);
        MyPerson p2 = (MyPerson) getPerson(id2);
        boolean del = false;
        int del1=-1;
        int del2=-1;
        for(int i=0;i<p1.acquaintance.length;i++) {
            if(p1.acquaintance[i].equals(p2)) {
                del1=i;
                if(p1.value[i] + value <= 0) {
                    del = true;
                } else {
                    del = false;
                }
                break;
            }
        }
        for(int i=0;i<p2.acquaintance.length;i++) {
            if(p2.acquaintance[i].equals(p1)) {
                del2=i;
                break;
            }
        }
        //删除
        if(del == true) {
            p1.value = MyArrayUtils.removeElement(p1.value, del1);
            p2.value = MyArrayUtils.removeElement(p2.value, del2);
            p1.acquaintance = MyArrayUtils.removeElement(p1.acquaintance, del1);
            p2.acquaintance = MyArrayUtils.removeElement(p2.acquaintance, del2);
        } else { //修改
            p1.value[del1]+=value;
            p2.value[del2]+=value;
        }
    }


    /*@ public normal_behavior
     @ requires contains(id1) && contains(id2) && getPerson(id1).isLinked(getPerson(id2));
     @ ensures \result == getPerson(id1).queryValue(getPerson(id2));
     @ also
     @ public exceptional_behavior
     @ signals (PersonIdNotFoundException e) !contains(id1);
     @ signals (PersonIdNotFoundException e) contains(id1) && !contains(id2);
     @ signals (RelationNotFoundException e) contains(id1) && contains(id2) &&
     @         !getPerson(id1).isLinked(getPerson(id2));
     @*/
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

      /*@ ensures \result ==
      @         (\sum int i; 0 <= i && i < people.length &&
      @         (\forall int j; 0 <= j && j < i; !isCircle(people[i].getId(), people[j].getId()));
      @         1);
      @*/

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

    @Override
    public void addGroup(Group group) throws EqualGroupIdException {
        for (Group group1 : groups) {
            if(group1.equals(group)) {
                throw new MyEqualGroupIdException(group.getId());
            }
        }
        Group[] newGroups = new Group[groups.length + 1];
        System.arraycopy(groups, 0, newGroups, 0, groups.length);
        newGroups[newGroups.length - 1] = group;
        groups = newGroups;
    }

    @Override
    public Group getGroup(int id) {
        for (Group group : groups) {
            if(id == group.getId()) {
                return group;
            }
        }
        return null;
    }

    @Override
    public void addToGroup(int id1, int id2) throws GroupIdNotFoundException, PersonIdNotFoundException, EqualPersonIdException {
        MyGroup gr = (MyGroup) getGroup(id2);
        if(gr == null) throw new MyGroupIdNotFoundException(id2);
        MyPerson p = (MyPerson) getPerson(id1);
        if(p==null) throw new MyPersonIdNotFoundException(id1);
        if(gr.hasPerson(p)) throw new MyEqualPersonIdException(id1);
        if(gr.people.length<=1111) {
            gr.addPerson(p);
        }
    }

    @Override
    public int queryGroupValueSum(int id) throws GroupIdNotFoundException {
        MyGroup gr = (MyGroup) getGroup(id);
        if(gr == null) throw new MyGroupIdNotFoundException(id);
        return gr.getValueSum();
    }

    @Override
    public int queryGroupAgeVar(int id) throws GroupIdNotFoundException {
        MyGroup gr = (MyGroup) getGroup(id);
        if(gr == null) throw new MyGroupIdNotFoundException(id);
        return gr.getAgeVar();
    }

    @Override
    public void delFromGroup(int id1, int id2) throws GroupIdNotFoundException, PersonIdNotFoundException, EqualPersonIdException {
        MyGroup gr = (MyGroup) getGroup(id2);
        if(gr == null) throw new MyGroupIdNotFoundException(id2);
        MyPerson p = (MyPerson) getPerson(id1);
        if(p==null) throw new MyPersonIdNotFoundException(id1);
        if(!gr.hasPerson(p)) throw new MyEqualPersonIdException(id1);
        gr.delPerson(p);
    }

    @Override
    public boolean containsMessage(int id) {
        for (Message message : messages) {
            if(message.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addMessage(Message message) throws EqualMessageIdException, EqualPersonIdException {
        for (Message message1 : messages) {
            if(message1.equals(message)) throw new MyEqualMessageIdException(message.getId());
        }
        if(message.getType() == 0 && message.getPerson1() == message.getPerson2()) {
            throw new MyEqualPersonIdException(message.getId());
        }
        messages = MyArrayUtils.insertElement(messages, message);
    }

    @Override
    public Message getMessage(int id) {
        for (Message message : messages) {
            if(id==message.getId()) return message;
        }
        return null;
    }

    @Override
    public void sendMessage(int id) throws RelationNotFoundException, MessageIdNotFoundException, PersonIdNotFoundException {
        MyMessage me = (MyMessage) getMessage(id);
        if(me==null) throw new MyMessageIdNotFoundException(id);
        if(me.getType()==0&&!me.getPerson1().isLinked(me.getPerson2())) throw new MyRelationNotFoundException(me.getPerson1().getId(), me.getPerson2().getId());
        if(me.getType()==1&&!me.getGroup().hasPerson(me.getPerson1())) throw new MyPersonIdNotFoundException(me.getPerson1().getId());
        if(me.getType()==0) {
            me.getPerson1().addSocialValue(me.getSocialValue());
            me.getPerson2().addSocialValue(me.getSocialValue());
            ((MyPerson)me.getPerson2()).messages = MyArrayUtils.insertAtBeginning(((MyPerson)me.getPerson2()).messages, me);
        } else if (me.getType()==1) {
            MyGroup gr = (MyGroup) me.getGroup();
            for (Person person : gr.people) {
                person.addSocialValue(me.getSocialValue());
            }
        }
        int delIndex=-1;
        for(int i=0;i<messages.length;i++) {
            if(messages[i].equals(me)) {
                delIndex=i;
            }
        }
        messages = MyArrayUtils.removeElement(messages, delIndex);
    }

    @Override
    public int querySocialValue(int id) throws PersonIdNotFoundException {
        MyPerson person = (MyPerson) getPerson(id);
        if(person==null) throw new MyPersonIdNotFoundException(id);
        return person.getSocialValue();
    }

    @Override
    public List<Message> queryReceivedMessages(int id) throws PersonIdNotFoundException {
        MyPerson person = (MyPerson) getPerson(id);
        if(person==null) throw new MyPersonIdNotFoundException(id);
        return person.getReceivedMessages();
    }

    @Override
    public int queryBestAcquaintance(int id) throws PersonIdNotFoundException, AcquaintanceNotFoundException {
        MyPerson person = (MyPerson) getPerson(id);
        if(person==null) throw new MyPersonIdNotFoundException(id);
        if(person.acquaintance.length==0)
            throw new MyAcquaintanceNotFoundException(id);
        Set<Integer> idSet = new TreeSet<Integer>();
        int max = ((MyPerson) person).value[0];
        for (int i=0;i<((MyPerson) person).value.length;i++) {
            if(max<((MyPerson) person).value[i]) {
                idSet.clear();
                max = ((MyPerson) person).value[i];
                idSet.add(((MyPerson) person).acquaintance[i].getId());
            } else if(max==((MyPerson) person).value[i]) {
                idSet.add(((MyPerson) person).acquaintance[i].getId());
            }
        }
        return idSet.iterator().next();
    }

    @Override
    public int queryCoupleSum() {
        int sum = 0;
        HashSet<Integer> couples = new HashSet();
        for (Person person : people) {
            try {
                if(couples.contains(person.getId())) continue;
                int couple1 = person.getId();
                int couple2 = queryBestAcquaintance(couple1);
                if(queryBestAcquaintance(couple2) == couple1) {
                    sum++;
                    couples.add(couple1);
                    couples.add(couple2);
                }
            } catch (PersonIdNotFoundException e) {
            } catch (AcquaintanceNotFoundException e) {
            }
        }
        return sum;
    }

    public boolean compareArrays(Person[] p1, Person[] p2) {
        if (p1.length != p2.length) {
            return false; // 数组长度不同，直接返回false
        }

        for (int i = 0; i < p1.length; i++) {
            if (!p1[i].equals(p2[i])) {
                return false; // 有不相同的元素，返回false
            }
        }

        return true; // 所有元素都相同，返回true
    }


    @Override
    public int modifyRelationOKTest(int id1, int id2, int value, HashMap<Integer, HashMap<Integer, Integer>> beforeData, HashMap<Integer, HashMap<Integer, Integer>> afterData) {
        MyNetwork before = convertData(beforeData);
        MyNetwork after = convertData(afterData);
        try {
            convertData(beforeData).modifyRelation(id1, id2, value);
            // Condition 1
            if (after.people.length != before.people.length) {
                return 1;
            }

            // Condition 2
            for (int i = 0; i < before.people.length; i++) {
                boolean found = false;
                for (int j = 0; j < after.people.length; j++) {
                    if (after.people[j].getId() == before.people[i].getId()) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    return 2;
                }
            }

            // Condition 3
            for (int i = 0; i < after.people.length; i++) {
                if (before.people[i].getId() != id1 && before.people[i].getId() != id2) {
                    if (!after.people[i].equals(before.people[i])) {
                        return 3;
                    } else if (!compareArrays(((MyPerson)before.people[i]).acquaintance, ((MyPerson)after.people[i]).acquaintance)) {
                        return 3;
                    } else if (!Arrays.equals(((MyPerson)before.people[i]).value, ((MyPerson)after.people[i]).value)) {
                        return 3;
                    }
                }
            }

            if(before.getPerson(id1).queryValue(before.getPerson(id2)) + value > 0) {
                // Condition 4
                if (!after.getPerson(id1).isLinked(after.getPerson(id2)) || !after.getPerson(id2).isLinked(after.getPerson(id1))) {
                    return 4;
                }

                if (after.getPerson(id1).queryValue(after.getPerson(id2)) != before.getPerson(id1).queryValue(before.getPerson(id2)) + value) {
                    return 5;
                }

                if (after.getPerson(id2).queryValue(after.getPerson(id1)) != before.getPerson(id2).queryValue(before.getPerson(id1)) + value) {
                    return 6;
                }

                if (((MyPerson)after.getPerson(id1)).acquaintance.length != ((MyPerson)before.getPerson(id1)).acquaintance.length) {
                    return 7;
                }

                if (((MyPerson)after.getPerson(id2)).acquaintance.length != ((MyPerson)before.getPerson(id2)).acquaintance.length) {
                    return 8;
                }

                for (int i = 0; i < ((MyPerson)after.getPerson(id1)).acquaintance.length; i++) {
                    if (!((MyPerson)after.getPerson(id1)).acquaintance[i].equals(((MyPerson)before.getPerson(id1)).acquaintance[i])) {
                        return 9;
                    }
                }

                for (int i = 0; i < ((MyPerson)after.getPerson(id2)).acquaintance.length; i++) {
                    if (!((MyPerson)after.getPerson(id2)).acquaintance[i].equals(((MyPerson)before.getPerson(id2)).acquaintance[i])) {
                        return 10;
                    }
                }

                for (int i = 0; i < ((MyPerson)after.getPerson(id1)).acquaintance.length; i++) {
                    if (((MyPerson)after.getPerson(id1)).acquaintance[i].getId() != id2) {
                        if (((MyPerson)after.getPerson(id1)).value[i] != ((MyPerson)before.getPerson(id1)).value[i]) {
                            return 11;
                        }
                    }
                }

                for (int i = 0; i < ((MyPerson)after.getPerson(id2)).acquaintance.length; i++) {
                    if (((MyPerson)after.getPerson(id2)).acquaintance[i].getId() != id1) {
                        if (((MyPerson)after.getPerson(id2)).value[i] != ((MyPerson)before.getPerson(id2)).value[i]) {
                            return 12;
                        }
                    }
                }

                if (((MyPerson)after.getPerson(id1)).value.length != ((MyPerson)after.getPerson(id1)).acquaintance.length) {
                    return 13;
                }

                if (((MyPerson)after.getPerson(id2)).value.length != ((MyPerson)after.getPerson(id2)).acquaintance.length) {
                    return 14;
                }
            } else {

                if (after.getPerson(id1).isLinked(after.getPerson(id2)) || after.getPerson(id2).isLinked(after.getPerson(id1))) {
                    return 15;
                }

                if (((MyPerson)before.getPerson(id1)).value.length != ((MyPerson)after.getPerson(id1)).acquaintance.length + 1) {
                    return 16;
                }

                if (((MyPerson)before.getPerson(id2)).value.length != ((MyPerson)after.getPerson(id2)).acquaintance.length + 1) {
                    return 17;
                }

                if (((MyPerson)after.getPerson(id1)).value.length != ((MyPerson)after.getPerson(id1)).acquaintance.length) {
                    return 18;
                }

                if (((MyPerson)after.getPerson(id2)).value.length != ((MyPerson)after.getPerson(id2)).acquaintance.length) {
                    return 19;
                }

                for (int i = 0; i < ((MyPerson)after.getPerson(id1)).acquaintance.length; i++) {
                    boolean exist = false;
                    Person person1 = ((MyPerson) after.getPerson(id1)).acquaintance[i];
                    for(int j = 0; j < ((MyPerson)before.getPerson(id1)).acquaintance.length; j++) {
                        Person person2 = ((MyPerson) before.getPerson(id1)).acquaintance[j];
                        if(person1.equals(person2)) {
                            exist = true;
                            if(((MyPerson)after.getPerson(id1)).value[i] != ((MyPerson)before.getPerson(id1)).value[j]) {
                                return 20;
                            }
                        }
                    }
                    if(exist == false) {
                        return 20;
                    }
                }

                for (int i = 0; i < ((MyPerson)after.getPerson(id2)).acquaintance.length; i++) {
                    boolean exist = false;
                    Person person1 = ((MyPerson) after.getPerson(id2)).acquaintance[i];
                    for(int j = 0; j < ((MyPerson)before.getPerson(id2)).acquaintance.length; j++) {
                        Person person2 = ((MyPerson) before.getPerson(id2)).acquaintance[j];
                        if(person1.equals(person2)) {
                            exist = true;
                            if(((MyPerson)after.getPerson(id2)).value[i] != ((MyPerson)before.getPerson(id2)).value[j]) {
                                return 21;
                            }
                        }
                    }
                    if(exist == false) {
                        return 21;
                    }
                }

            }


        } catch (PersonIdNotFoundException e) {
            if(beforeData.equals(afterData)) {
                return 0;
            } else {
                return -1;
            }
        } catch (EqualPersonIdException e) {
            if(beforeData.equals(afterData)) {
                return 0;
            } else {
                return -1;
            }
        } catch (RelationNotFoundException e) {
            if(beforeData.equals(afterData)) {
                return 0;
            } else {
                return -1;
            }
        }
        return 0;
    }

    public MyNetwork convertData(HashMap<Integer, HashMap<Integer, Integer>> data) {
        MyNetwork mn = new MyNetwork();
        for (int personId : data.keySet()) {
            MyPerson person = new MyPerson(personId, personId + "", personId);
            try {
                mn.addPerson(person);
            }
            catch (MyEqualPersonIdException e) {
            }
        }
        for (int personId : data.keySet()) {
            HashMap<Integer, Integer> acquaintanceMap = data.get(personId);
            for (int acquaintanceId : acquaintanceMap.keySet()) {
                if (mn.getPerson(personId).isLinked(mn.getPerson(acquaintanceId))) { continue; }
                ((MyPerson)mn.getPerson(personId)).acquaintance = MyArrayUtils.insertElement(((MyPerson)mn.getPerson(personId)).acquaintance, mn.getPerson(acquaintanceId));
                ((MyPerson)mn.getPerson(personId)).value = MyArrayUtils.insertElement(((MyPerson)mn.getPerson(personId)).value, acquaintanceMap.get(acquaintanceId).intValue());
//                    mn.addRelation(personId, acquaintanceId, acquaintanceMap.get(acquaintanceId));
            }
        }
        return mn;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MyNetwork myNetwork = (MyNetwork) obj;
        return Arrays.equals(people, myNetwork.people) &&
                Arrays.equals(groups, myNetwork.groups) &&
                Arrays.equals(messages, myNetwork.messages);
    }
}
