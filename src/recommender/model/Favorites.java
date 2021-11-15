package recommender.model;

import java.util.Date;

public class Favorites {
    protected int favoriteId;
    protected Date created;
    protected String userName;
    protected String isbn;

    public Favorites(int favoriteId, Date created, String userName, String isbn) {
        this.favoriteId = favoriteId;
        this.created = created;
        this.userName = userName;
        this.isbn = isbn;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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
        return "Favorites{" +
                "favoriteId=" + favoriteId +
                ", created=" + created +
                ", userName='" + userName + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
