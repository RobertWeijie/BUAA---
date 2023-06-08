package demo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Library library = new Library();
        List<Book> books = new ArrayList<>();
        LogisticsDivision logisticsDivision = new LogisticsDivision(library);
        BorrowReturnLibrarian borrowReturnLibrarian = new BorrowReturnLibrarian(library, logisticsDivision);
        OrderLibrarian orderLibrarian = new OrderLibrarian(library);
        SelfServiceMachine selfServiceMachine = new SelfServiceMachine(library, logisticsDivision);
        ArrangementLibrarian arrangementLibrarian = new ArrangementLibrarian(library,selfServiceMachine,borrowReturnLibrarian,logisticsDivision,orderLibrarian );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Scanner scanner = new Scanner(System.in);

//        System.out.print("请输入一行数据：");
        String input = scanner.nextLine();

        for (int i = 0; i < Integer.valueOf(input); i++) {
            String s = scanner.nextLine();
            // 添加书籍
            // A-0000 3
            String[] split = s.split(" ");
            String[] strings = split[0].split("-");

            Book book = new Book(strings[1], strings[0], Integer.valueOf(split[1]));
            books.add(book);
        }

        List<String> stringList = new ArrayList<>();
        input = scanner.nextLine();
        for (int i = 0; i < Integer.valueOf(input); i++) {
            stringList.add(scanner.nextLine());
        }

        scanner.close();

        library.setBooks(books);
        HashMap<String, Student> studentHashMap = new HashMap<>();

        for (String s : stringList) {
            String oldDate = library.getArrangeDate();
            //[2023-01-05] 121987640 borrowed A-0000
            String[] split = s.split(" ");

            if (null != oldDate) {
                //唤醒 整理管理员工作
                LocalDate date1 = LocalDate.parse(oldDate.substring(1, oldDate.length() - 1), formatter);
                LocalDate date2 = LocalDate.parse(split[0].substring(1, 11), formatter);
                long daysBetween = ChronoUnit.DAYS.between(date1, date2);
                while(!date1.plusDays(3).isAfter(date2)) {
                    date1 = date1.plusDays(3);
                    library.setArrangeDate("[" + formatter.format(date1) + "]");
                    library.setDate("[" + formatter.format(date1) + "]");
                    arrangementLibrarian.arrangementBook();
                }
            }

            library.setDate(split[0]);
            String name = split[1];
            if (null == studentHashMap.get(name)) {
                studentHashMap.put(name, new Student(name, arrangementLibrarian, selfServiceMachine, borrowReturnLibrarian, orderLibrarian));
            }
            Student student = studentHashMap.get(name);

            String[] bookSplit = split[3].split("-");
            Book book = new Book(bookSplit[1], bookSplit[0], 1);

            switch (split[2]){
                case "borrowed":
                    student.borrowBook(book);
                    break;
                case "returned":
                    student.returnBook(book);
                    break;
                case "smeared":
                    student.smeareBook(book);
                    break;
                case "lost":
                    student.lostBook(book);
                    break;
            }

        }
        LocalDate endArrangeDate = LocalDate.parse(library.getArrangeDate().substring(1, library.getArrangeDate().length() - 1), formatter);
        LocalDate endDate = LocalDate.parse(library.getDate().substring(1, library.getDate().length() - 1), formatter);
        while(!endArrangeDate.isAfter(endDate)) {
            endArrangeDate = endArrangeDate.plusDays(3);
        }
        library.setArrangeDate("[" + formatter.format(endArrangeDate) + "]");
        library.setDate("[" + formatter.format(endArrangeDate) + "]");
        arrangementLibrarian.arrangementBook();
    }

}
