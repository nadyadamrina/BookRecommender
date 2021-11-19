package recommender.model;

import java.math.BigDecimal;

public class Books {
    protected String isbn;
    protected String title;
    protected int AuthorId;
    protected Genre genre;
    protected String description;
    protected String imageUrl;
    protected BigDecimal averageRating;
    protected int publicationMonth;
    protected int publicationDay;
    protected int publicationYear;
    protected String publisher;
    protected int numberOfPages;
    protected  int numberOfRatings;

    public Books(String isbn, String title, int authorId, Genre genre, String description, String imageUrl, BigDecimal averageRating,
                 int publicationMonth, int publicationDay, int publicationYear, String publisher, int numberOfPages, int numberOfRatings) {
        this.isbn = isbn;
        this.title = title;
        this.AuthorId = authorId;
        this.genre = genre;
        this.description = description;
        this.imageUrl = imageUrl;
        this.averageRating = averageRating;
        this.publicationMonth = publicationMonth;
        this.publicationDay = publicationDay;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.numberOfPages = numberOfPages;
        this.numberOfRatings = numberOfRatings;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(int authorId) {
        AuthorId = authorId;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }

    public int getPublicationMonth() {
        return publicationMonth;
    }

    public void setPublicationMonth(int publicationMonth) {
        this.publicationMonth = publicationMonth;
    }

    public int getPublicationDay() {
        return publicationDay;
    }

    public void setPublicationDay(int publicationDay) {
        this.publicationDay = publicationDay;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    @Override
    public String toString() {
        return "Books{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", AuthorId=" + AuthorId +
                ", genre=" + genre +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", averageRating=" + averageRating +
                ", publicationMonth=" + publicationMonth +
                ", publicationDay=" + publicationDay +
                ", publicationYear=" + publicationYear +
                ", publisher='" + publisher + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", numberOfRatings=" + numberOfRatings +
                '}';
    }

    public enum Genre {
        ArtsAndPhotography("Arts & Photography"), BiographiesAndMemoirs("Biographies & Memoirs"),
        BusinessAndMoney("Business & Money"), Calendars("Calendars"), ChildrensBooks("Children's Books"),
        ChristianBooksAndBibles("Christian Books & Bibles"), ComicsAndGraphicNovels("Comics & Graphic Novels"),
        ComputersAndTechnology("Computers & Technology"), CookbooksFoodAndWine("Cookbooks, Food & Wine"),
        CraftsHobbiesAndHome("Crafts, Hobbies & Home"), EducationAndTeaching("Education & Teaching"),
        EngineeringAndTransportation("Engineering & Transportation"), GayAndLesbian("Gay & Lesbian"), HealthFitnessAndDieting("Health, Fitness & Dieting"),
        History("History"), HumorAndEntertainment("Humor & Entertainment"), Law("Law"),
        LiteratureAndFiction("Literature & Fiction"), MedicalBooks("Medical Books"),
        MysteryThrillerAndSuspense("Mystery, Thriller & Suspense"), ParentingAndRelationships("Parenting & Relationships"),
        PoliticsAndSocialSciences("Politics & Social Sciences"), Reference("Reference"), ReligionAndSpirituality("Religion & Spirituality"),
        Romance("Romance"), ScienceAndMath("Science & Math"), ScienceFictionAndFantasy("Science Fiction & Fantasy"),
        SelfHelp("Self-Help"), SportsAndOutdoors("Sports & Outdoors"), TeenAndYoungAdult("Teen & Young Adult"),
        TestPreparation("Test Preparation"), Travel("Travel");

        private final String value;

        Genre(String value) {
            this.value = value;
        }
    }
}
