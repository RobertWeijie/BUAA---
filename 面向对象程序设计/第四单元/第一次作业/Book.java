package demo;

public class Book {



    //序列号
    private String sequenceNumber;
    private String category;
    private int num = 1;

    public Book(String sequenceNumber, String category, int num) {
        this.sequenceNumber = sequenceNumber;
        this.category = category;
        this.num = num;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public String getCategory() {
        return category;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getBookDesc() {
        return this.getCategory() + "-" + this.getSequenceNumber();
    }

}
