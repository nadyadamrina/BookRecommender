package recommender.model;

import java.util.Date;

public class History {
    protected int historyId;
    protected String userName;
    protected String searchContent;
    protected Date created;

    public History(int historyId, String userName, String searchContent, Date created) {
        this.historyId = historyId;
        this.userName = userName;
        this.searchContent = searchContent;
        this.created = created;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "History{" +
                "historyId=" + historyId +
                ", userName='" + userName + '\'' +
                ", searchContent='" + searchContent + '\'' +
                ", created=" + created +
                '}';
    }
}
