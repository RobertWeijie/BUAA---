package demo;

import java.util.ArrayList;
import java.util.List;

/**
 * 自助机
 */
public class SelfServiceMachine {

    //留在自助机的书籍列表
    private List<Book> machineBooks;

    private Library library;
    private LogisticsDivision logisticsDivision;

    public SelfServiceMachine(Library library, LogisticsDivision logisticsDivision) {
        this.library = library;
        this.logisticsDivision = logisticsDivision;
        this.machineBooks = new ArrayList<>();
    }

    public boolean selectBookRemain(Student student, Book book) {
        System.out.println(library.getDate() + " " + student.getName()+" queried " +
                book.getCategory() + "-" + book.getSequenceNumber() + " from self-service machine");
        Book findBook = library.findBook(book);
        if (null != findBook) {
            return true;
        }
        return false;
    }


    // 处理借书操作
    public boolean borrowBook(Student student, Book book) {
        // 符合借书数目限制则成功借书
        boolean canBorrow = true;
        for (Book b : student.getBorrowedBooks()) {
            if (book.getSequenceNumber().equals(b.getSequenceNumber())) {
                canBorrow = false;
            }
        }
        library.borrowBook(book);
        if (canBorrow) {
            student.addBorrowedBook(book);
//            [2023-01-05] 21890631 borrowed C-0000 from self-service machine
            System.out.println(library.getDate() + " " +student.getName() + " borrowed " + book.getBookDesc() + " from self-service machine");
            return true;
        }

        //借书失败 书本当即被留在自助机器处
        machineBooks.add(book);
        return false;
    }

    // 处理还书操作
    public void returnBook(Student student, Book book) {
        //判断 是否是之前损坏的书籍
        for (Book smearedBook : student.getSmearedBooks()) {
            if (book.getBookDesc().equals(smearedBook.getBookDesc())) {
                smeareBook(student,book);
                System.out.println(library.getDate() + " " +student.getName() + " returned " + book.getBookDesc() + " to self-service machine");
                logisticsDivision.repairBook(book);
                student.removeBorrowedBook(book);
                return;
            }
        }
        // 2023-01-201 21987640 returned B-0000 to borrowing and returning librarian
        System.out.println(library.getDate() + " " +student.getName() + " returned " + book.getBookDesc() + " to self-service machine");
        student.removeBorrowedBook(book);
//        library.returnBook(book);
        machineBooks.add(book);
    }

    public void smeareBook(Student student, Book book) {
        System.out.println(library.getDate() + " " +student.getName() + " got punished by borrowing and returning librarian");
//        logisticsDivision.repairBook(book);
    }

    public void lostBook(Student student, Book book) {
        //21987640 got punished by borrowing and returning librarian
        System.out.println(library.getDate() + " " +student.getName() + " got punished by borrowing and returning librarian");
        student.removeBorrowedBook(book);
    }

    public List<Book> getMachineBooks() {
        return machineBooks;
    }

    public void setMachineBooks(List<Book> machineBooks) {
        this.machineBooks = machineBooks;
    }
}
