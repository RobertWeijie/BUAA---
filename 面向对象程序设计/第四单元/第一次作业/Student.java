package demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Student {


    private String name;

    //已借到的书
    private List<Book> borrowedBooks;
    private List<Book> smearedBooks;
    //打交道的管理员等
    private SelfServiceMachine selfServiceMachine;
    private BorrowReturnLibrarian borrowReturnLibrarian;
    private OrderLibrarian orderLibrarian;

    public Student(String name, ArrangementLibrarian arrangementLibrarian, SelfServiceMachine selfServiceMachine, BorrowReturnLibrarian borrowReturnLibrarian, OrderLibrarian orderLibrarian) {
        this.name = name;
        this.selfServiceMachine = selfServiceMachine;
        this.borrowReturnLibrarian = borrowReturnLibrarian;
        this.orderLibrarian = orderLibrarian;
        this.borrowedBooks = new ArrayList<>();
        this.smearedBooks = new ArrayList<>();
    }

    //借书
    // 21890631 queried C-0000 from self-service machine
    public void borrowBook(Book book) {
        boolean bookRemain = selfServiceMachine.selectBookRemain(this, book);
        if (!bookRemain) {
            //预定
            orderLibrarian.orderBook(this, book);
        }else {
            if (book.getCategory().equals("A")) {
                return;
            }
            if (book.getCategory().equals("B")) {
                boolean b = borrowReturnLibrarian.borrowBook(this, book);
                if(b == true) {
                    Iterator<String> iterator = orderLibrarian.getOrderBookMap().keySet().iterator();
                    while (iterator.hasNext()) {
                        String key = iterator.next();
                        if(key.split(":")[0].equals(name) && (""+key.split(":")[1].charAt(0)).equals(book.getCategory())) {
                            iterator.remove();
                        }
                    }
                }
            }
            if (book.getCategory().equals("C")) {
                selfServiceMachine.borrowBook(this, book);
            }
        }
    }


    //还书
    public void returnBook(Book book) {
        if (book.getCategory().equals("B")) {
            borrowReturnLibrarian.returnBook(this, book);
        }
        if (book.getCategory().equals("C")) {
            selfServiceMachine.returnBook(this, book);
        }
    }


    //损毁
    public void smeareBook(Book book) {
//        if (book.getCategory().equals("B")) {
//            borrowReturnLibrarian.smeareBook(this, book);
//        }
//        if (book.getCategory().equals("C")) {
//            selfServiceMachine.smeareBook(this, book);
//        }
        this.addSmearedBooks(book);
    }

    //丢失的书除去 borrowedBooks
    public void lostBook(Book book) {
        if (book.getCategory().equals("B")) {
            borrowReturnLibrarian.lostBook(this, book);
        }
        if (book.getCategory().equals("C")) {
            selfServiceMachine.lostBook(this, book);
        }
    }

    /**
     * 移除已借的书籍
     * @param book
     */
    public void removeBorrowedBook(Book book) {
        Iterator<Book> iterator = borrowedBooks.iterator();
        while (iterator.hasNext()) {
            Book next = iterator.next();
            if (next.getBookDesc().equals(book.getBookDesc())) {
                iterator.remove();
            }
        }
    }


    public void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
    }

    public void addSmearedBooks(Book book) {
        smearedBooks.add(book);
    }
    public List<Book> getSmearedBooks() {
        return smearedBooks;
    }

//    public void removeBorrowedBook(Book book) {
//        borrowedBooks.remove(book);
//    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
