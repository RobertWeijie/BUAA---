package demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//书架
public class Library {


    //[2023-05-05]
    private String date;
    private String arrangeDate;
    private List<Book> books;

    public Library() {
        arrangeDate = "[2023-01-01]";
        books = new ArrayList<>();
    }

    public void addBooks(List<Book> bs) {
        for (Book b : bs) {
            boolean isContain = false;
            for (Book book : books) {
                if (book.getCategory().equals(b.getCategory())
                        && book.getSequenceNumber().equals(b.getSequenceNumber())) {
                    book.setNum(book.getNum() + 1);
                    isContain = true;
                }
            }

            if (!isContain) {
                books.add(b);
            }
        }
        books.addAll(bs);
    }

    //被借书
    public void borrowBook(Book b) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book next = iterator.next();
            if (next.getCategory().equals(b.getCategory())
                    && next.getSequenceNumber().equals(b.getSequenceNumber())) {
                if (next.getNum() > 1) {
                    next.setNum(next.getNum() - 1);
                }else {
                    iterator.remove();
                }
            }
        }
    }

    public Book findBook(Book b) {
        // 遍历图书列表，查找匹配的图书
        for (Book book : books) {
            if (book.getCategory().equals(b.getCategory()) && book.getSequenceNumber().equals(b.getSequenceNumber())) {
                return book;
            }
        }
        return null; // 没找到匹配的图书
    }

    public void returnBook(Book returnBook) {

        boolean isContain = false;
        for (Book book : books) {
            if (book.getCategory().equals(returnBook.getCategory())
                    && book.getSequenceNumber().equals(returnBook.getSequenceNumber())) {
                book.setNum(book.getNum() + 1);
                isContain = true;
            }
        }

        if (!isContain) {
            books.add(returnBook);
        }
    }

    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getArrangeDate() {
        return arrangeDate;
    }

    public void setArrangeDate(String arrangeDate) {
        this.arrangeDate = arrangeDate;
    }


}
