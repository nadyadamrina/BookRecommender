package recommender.model;

public class Preferences {
    protected int preferenceId;
    protected Books.Genre primaryGenre;
    protected Books.Genre secondaryGenre;

    public Preferences(int preferenceId, Books.Genre primaryGenre, Books.Genre secondaryGenre) {
        this.preferenceId = preferenceId;
        this.primaryGenre = primaryGenre;
        this.secondaryGenre = secondaryGenre;
    }

    public int getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(int preferenceId) {
        this.preferenceId = preferenceId;
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
