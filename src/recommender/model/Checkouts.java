package recommender.model;

import java.util.Date;

public class Checkouts {
    protected int checkoutId;
    protected String isbn;
    protected Date checkoutDate;

    public Checkouts(int checkoutId, String isbn, Date checkoutDate) {
        this.checkoutId = checkoutId;
        this.isbn = isbn;
        this.checkoutDate = checkoutDate;
    }

    public int getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(int checkoutId) {
        this.checkoutId = checkoutId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    @Override
    public String toString() {
        return "Checkouts{" +
                "checkoutId=" + checkoutId +
                ", isbn='" + isbn + '\'' +
                ", checkoutDate=" + checkoutDate +
                '}';
    }
}
