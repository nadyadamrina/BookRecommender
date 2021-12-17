package recommender.model;

public class Preferences {
    protected int preferenceId;
    protected String userName;
    protected Books.Genre primaryGenre;
    protected Books.Genre secondaryGenre;

    public Preferences(int preferenceId, String userName, Books.Genre primaryGenre, Books.Genre secondaryGenre) {
        this.preferenceId = preferenceId;
        this.userName = userName;
        this.primaryGenre = primaryGenre;
        this.secondaryGenre = secondaryGenre;
    }

    public Preferences(String userName, Books.Genre primaryGenre, Books.Genre secondaryGenre) {
        this.userName = userName;
        this.primaryGenre = primaryGenre;
        this.secondaryGenre = secondaryGenre;
    }

    public int getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(int preferenceId) {
        this.preferenceId = preferenceId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Books.Genre getPrimaryGenre() {
        return primaryGenre;
    }

    public void setPrimaryGenre(Books.Genre primaryGenre) {
        this.primaryGenre = primaryGenre;
    }

    public Books.Genre getSecondaryGenre() {
        return secondaryGenre;
    }

    public void setSecondaryGenre(Books.Genre secondaryGenre) {
        this.secondaryGenre = secondaryGenre;
    }

    @Override
    public String toString() {
        return "Preferences{" +
                "preferenceId=" + preferenceId +
                ", primaryGenre=" + primaryGenre +
                ", secondaryGenre=" + secondaryGenre +
                '}';
    }
}
