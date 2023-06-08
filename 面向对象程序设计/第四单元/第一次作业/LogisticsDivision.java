package demo;

import java.util.ArrayList;
import java.util.List;

/**
 *后勤部门
 */
public class LogisticsDivision {


    private Library library;
    private List<Book> fixBooks;

    // 处理书籍修复逻辑
    public void repairBook(Book book) {
        fixBooks.add(book);
        System.out.println(library.getDate() + " " +book.getBookDesc() + " got repaired by logistics division");
    }

    public LogisticsDivision(Library library) {
        this.library = library;
        this.fixBooks = new ArrayList<>();
    }

    public List<Book> getFixBooks() {
        return fixBooks;
    }

    public void setFixBooks(List<Book> fixBooks) {
        this.fixBooks = fixBooks;
    }
}
