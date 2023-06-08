package demo;

import java.util.*;

/**
 * 整理管理员
 */
public class ArrangementLibrarian {


    private SelfServiceMachine selfServiceMachine;
    private BorrowReturnLibrarian borrowReturnLibrarian;
    private LogisticsDivision logisticsDivision;
    private OrderLibrarian orderLibrarian;
    private Library library;

    public ArrangementLibrarian(Library library, SelfServiceMachine selfServiceMachine, BorrowReturnLibrarian borrowReturnLibrarian, LogisticsDivision logisticsDivision, OrderLibrarian orderLibrarian) {
        this.selfServiceMachine = selfServiceMachine;
        this.borrowReturnLibrarian = borrowReturnLibrarian;
        this.logisticsDivision = logisticsDivision;
        this.orderLibrarian = orderLibrarian;
        this.library = library;
    }

    //开始整理书籍
    public void arrangementBook() {
//        if(library.getDate().equals("[2023-02-21]"))
//        {
//            System.out.println("asd");
//        }
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Book> books1 = new ArrayList<>();

        //收集书本
        books.addAll(library.getBooks());

        books1.addAll(selfServiceMachine.getMachineBooks());
        books1.addAll(borrowReturnLibrarian.getBorrowReturnBooks());
        books1.addAll(logisticsDivision.getFixBooks());
        selfServiceMachine.setMachineBooks(new ArrayList<>());
        borrowReturnLibrarian.setBorrowReturnBooks(new ArrayList<>());
        logisticsDivision.setFixBooks(new ArrayList<>());
        for (Book book1 : books1) {
             boolean isContained = false;
            for (Book book : books) {
                if(book.getBookDesc().equals(book1.getBookDesc())) {
                    isContained = true;
                    book.setNum(book.getNum()+1);
                }
            }
            if(isContained == false) {
                books.add(book1);
            }
        }
        //将在预定清单上的书按数量送到 预定管理员
        Map<String, Book> callStudentBookMap = new LinkedHashMap<String, Book>();
        Set<Map.Entry<String, Book>> entries = orderLibrarian.getOrderBookMap().entrySet();
        for (Map.Entry<String, Book> entry : entries) {
            Book orderBook = entry.getValue();
            Iterator<Book> iterator = books.iterator();
            while(iterator.hasNext()) {
                Book book = iterator.next();
                if(orderBook.getBookDesc().equals(book.getBookDesc())) {
                    if(book.getNum() == 1) {
                        iterator.remove();
                    } else {
                        book.setNum(book.getNum()-1);
                    }
                    callStudentBookMap.put(entry.getKey(), book);
                    continue;
                }
            }
        }
//        Iterator<Book> iterator = books.iterator();
//        while (iterator.hasNext()) {
//            Book book = iterator.next();
//
//            Set<Map.Entry<String, Book>> entries = orderLibrarian.getOrderBookMap().entrySet();
//            for (Map.Entry<String, Book> entry : entries) {
//                Book orderBook = entry.getValue();
//                if (book.getBookDesc().equals(orderBook.getBookDesc())) {
//                    callStudentBookMap.put(entry.getKey(), book);
//                    iterator.remove();
//                }
//            }
//        }
        //其余送上书架
        library.setBooks(books);
        orderLibrarian.callStudent(callStudentBookMap);
    }
}
