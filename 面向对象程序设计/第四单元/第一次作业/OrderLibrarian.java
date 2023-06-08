package demo;

import java.util.*;

/**
 * 预定管理员
 */
public class OrderLibrarian {


    //学生姓名+A-0000，书籍信息map
    private Map<String, Book> orderBookMap;

    private Library library;

    public OrderLibrarian(Library library) {
        this.library = library;
        this.orderBookMap = new LinkedHashMap<>();
    }

    public void orderBook(Student student, Book book) {
        orderBookMap.put(student.getName() + ":" + book.getBookDesc(), book);
        System.out.println(library.getDate() + " " +student.getName()+" ordered "+book.getBookDesc()+" from ordering librarian");
    }


    public Map<String, Book> getOrderBookMap() {
        return orderBookMap;
    }

    //即将发放给学生的orderMap
    public void callStudent(Map<String, Book> callStudentBookMap) {

        for (Map.Entry<String, Book> entry : callStudentBookMap.entrySet()) {

            String[] split = entry.getKey().split(":");
            //21237643 borrowed B-0000 from ordering librarian
            System.out.println(library.getDate() + " " + split[0] + " borrowed " + split[1] + " from ordering librarian");

            orderBookMap.remove(entry.getKey());
        }
    }
}
