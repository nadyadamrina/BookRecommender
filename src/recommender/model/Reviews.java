package recommender.model;

import java.util.Date;

public class Reviews {
    protected int reviewId;
    protected Date created;
    protected String description;
    protected String userName;
    protected String isbn;
    protected int ratingId;

    public Reviews(int reviewId, Date created, String description, String userName, String isbn, int ratingId) {
        this.reviewId = reviewId;
        this.created = created;
        this.description = description;
        this.userName = userName;
        this.isbn = isbn;
        this.ratingId = ratingId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "reviewId=" + reviewId +
                ", created=" + created +
                ", description='" + description + '\'' +
                ", userName='" + userName + '\'' +
                ", isbn='" + isbn + '\'' +
                ", ratingId=" + ratingId +
                '}';
    }
}
