package recommender.model;

import java.util.Date;

public class Ratings {
    protected int ratingId;
    protected Date created;
    protected int ratingValue;
    protected String userName;
    protected String isbn;

    public Ratings(int ratingId, Date created, int ratingValue, String userName, String isbn) {
        this.ratingId = ratingId;
        this.created = created;
        this.ratingValue = ratingValue;
        this.userName = userName;
        this.isbn = isbn;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Ratings{" +
                "ratingId=" + ratingId +
                ", created=" + created +
                ", ratingValue=" + ratingValue +
                ", userName='" + userName + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
