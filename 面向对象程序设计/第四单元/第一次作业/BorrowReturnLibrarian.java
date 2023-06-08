package demo;

import java.util.ArrayList;
import java.util.List;

/**
 * 借还管理员
 */
public class BorrowReturnLibrarian {



    private Library library;
    private List<Book> borrowReturnBooks;
    private LogisticsDivision logisticsDivision;

    public BorrowReturnLibrarian(Library library, LogisticsDivision logisticsDivision) {
        this.library = library;
        this.logisticsDivision = logisticsDivision;
        this.borrowReturnBooks = new ArrayList<>();
    }

    // 处理借书操作
    public boolean borrowBook(Student student, Book book) {
        // 符合借书数目限制则成功借书
        boolean canBorrow = true;
        for (Book b : student.getBorrowedBooks()) {
            if (b.getCategory().equals(book.getCategory())) {
                canBorrow = false;
            }
        }
        library.borrowBook(book);
        if (canBorrow) {
            student.addBorrowedBook(book);
            System.out.println(library.getDate() + " " +student.getName() + " borrowed " + book.getBookDesc() + " from borrowing and returning librarian");
            return true;
        }

        //借书失败 书本当即被留在
        borrowReturnBooks.add(book);
        return false;
    }

    // 处理还书操作
    public void returnBook(Student student, Book book) {
        //判断 是否是之前损坏的书籍
        for (Book smearedBook : student.getSmearedBooks()) {
            if (book.getBookDesc().equals(smearedBook.getBookDesc())) {
                smeareBook(student, book);
                System.out.println(library.getDate() + " " +student.getName() + " returned " + book.getBookDesc() + " to borrowing and returning librarian");
                student.removeBorrowedBook(book);
                logisticsDivision.repairBook(book);
                return;
            }
        }
//        [2023-01-24] 121987640 returned C-0000 to self-service machine
        System.out.println(library.getDate() + " " +student.getName() + " returned " + book.getBookDesc() + " to borrowing and returning librarian");
        student.removeBorrowedBook(book);

        borrowReturnBooks.add(book);
//        library.returnBook(book);
    }

    //损毁书籍
    public void smeareBook(Student student, Book book) {
        //21987640 got punished by borrowing and returning librarian
        System.out.println(library.getDate() + " " +student.getName() + " got punished by borrowing and returning librarian");
    }

    public void lostBook(Student student, Book book) {
        //21987640 got punished by borrowing and returning librarian
        System.out.println(library.getDate() + " " +student.getName() + " got punished by borrowing and returning librarian");
        student.removeBorrowedBook(book);
    }

    public List<Book> getBorrowReturnBooks() {
        return borrowReturnBooks;
    }

    public void setBorrowReturnBooks(List<Book> borrowReturnBooks) {
        this.borrowReturnBooks = borrowReturnBooks;
    }
}
