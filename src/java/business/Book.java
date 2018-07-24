
package business;

public class Book {
    private int storeid, qty;
    private String bookcd, title, author;
    private double price;
    
    public Book() {
        storeid = 0;
        bookcd = "";
        qty = 0;
        title = "";
        price = 0.0;
        author = "";
    }

    public Book(String bookcd, int storeid) {
        setBookcd(bookcd);
        setStoreid(storeid);
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    public String getBookcd() {
        return bookcd;
    }

    public void setBookcd(String bookcd) {
        this.bookcd = bookcd;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
