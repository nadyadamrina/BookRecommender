package recommender.model;

import java.util.Date;

public class Recommendations {
    protected int recommendationId;
    protected String userName;
    protected String isbn;
    protected Date created;

    public Recommendations(int recommendationId, String userName, String isbn, Date created) {
        this.recommendationId = recommendationId;
        this.userName = userName;
        this.isbn = isbn;
        this.created = created;
    }

    public int getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(int recommendationId) {
        this.recommendationId = recommendationId;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Recommendations{" +
                "recommendationId=" + recommendationId +
                ", userName='" + userName + '\'' +
                ", isbn='" + isbn + '\'' +
                ", created=" + created +
                '}';
    }
}
