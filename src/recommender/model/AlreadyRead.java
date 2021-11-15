package recommender.model;

import java.util.Date;

public class AlreadyRead {
    protected int alreadyReadId;
    protected Date completed;
    protected String userName;
    protected String isbn;

    public AlreadyRead(int alreadyReadId, Date completed, String userName, String isbn) {
        this.alreadyReadId = alreadyReadId;
        this.completed = completed;
        this.userName = userName;
        this.isbn = isbn;
    }

    public int getAlreadyReadId() {
        return alreadyReadId;
    }

    public void setAlreadyReadId(int alreadyReadId) {
        this.alreadyReadId = alreadyReadId;
    }

    public Date getCompleted() {
        return completed;
    }

    public void setCompleted(Date completed) {
        this.completed = completed;
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
        return "AlreadyRead{" +
                "alreadyReadId=" + alreadyReadId +
                ", completed=" + completed +
                ", userName='" + userName + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
