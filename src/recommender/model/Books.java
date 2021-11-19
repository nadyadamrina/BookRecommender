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
        EngineeringAndTransportation("Engineering & Transportation"), GayAndLesbian("Gay & Lesbian"),
        HealthFitnessAndDieting("Health, Fitness & Dieting"),
        History("History"), HumorAndEntertainment("Humor & Entertainment"), Law("Law"),
        LiteratureAndFiction("Literature & Fiction"), MedicalBooks("Medical Books"),
        MysteryThrillerAndSuspense("Mystery, Thriller & Suspense"), ParentingAndRelationships("Parenting & Relationships"),
        PoliticsAndSocialSciences("Politics & Social Sciences"), Reference("Reference"), ReligionAndSpirituality("Religion & Spirituality"),
        Romance("Romance"), ScienceAndMath("Science & Math"), ScienceFictionAndFantasy("Science Fiction & Fantasy"),
        SelfHelp("Self-Help"), SportsAndOutdoors("Sports & Outdoors"), TeenAndYoungAdult("Teen & Young Adult"),
        TestPreparation("Test Preparation"), Travel("Travel"), Unknown("Unknown");

        private final String value;

        Genre(String value) {
            this.value = value;
        }

        public static Genre parse(String value) {
            switch (value) {
                case "Arts & Photography":
                    return Genre.ArtsAndPhotography;
                case "Biographies & Memoirs":
                    return Genre.BiographiesAndMemoirs;
                case "Business & Money":
                    return Genre.BusinessAndMoney;
                case "Calendars":
                    return Genre.Calendars;
                case "Children's Books":
                    return Genre.ChildrensBooks;
                case "Christian Books & Bibles":
                    return Genre.ChristianBooksAndBibles;
                case "Comics & Graphic Novels":
                    return Genre.ComicsAndGraphicNovels;
                case "Computers & Technology":
                    return Genre.ComputersAndTechnology;
                case "Cookbooks, Food & Wine":
                    return Genre.CookbooksFoodAndWine;
                case "Crafts, Hobbies & Home":
                    return Genre.CraftsHobbiesAndHome;
                case "Education & Teaching":
                    return Genre.EducationAndTeaching;
                case "Engineering & Transportation":
                    return Genre.EngineeringAndTransportation;
                case "Gay & Lesbian":
                    return Genre.GayAndLesbian;
                case "Health, Fitness & Dieting":
                    return Genre.HealthFitnessAndDieting;
                case "History":
                    return Genre.History;
                case "Humor & Entertainment":
                    return Genre.HumorAndEntertainment;
                case "Law":
                    return Genre.Law;
                case "Literature & Fiction":
                    return Genre.LiteratureAndFiction;
                case "Medical Books":
                    return Genre.MedicalBooks;
                case "Mystery, Thriller & Suspense":
                    return Genre.MysteryThrillerAndSuspense;
                case "Parenting & Relationships":
                    return Genre.ParentingAndRelationships;
                case "Politics & Social Sciences":
                    return Genre.PoliticsAndSocialSciences;
                case "Reference":
                    return Genre.Reference;
                case "Religion & Spirituality":
                    return Genre.ReligionAndSpirituality;
                case "Romance":
                    return Genre.Romance;
                case "Science & Math":
                    return Genre.ScienceAndMath;
                case "Science Fiction & Fantasy":
                    return Genre.ScienceFictionAndFantasy;
                case "Self-Help":
                    return Genre.SelfHelp;
                case "Sports & Outdoors":
                    return Genre.SportsAndOutdoors;
                case "Teen & Young Adult":
                    return Genre.TeenAndYoungAdult;
                case "Test Preparation":
                    return Genre.TestPreparation;
                case "Travel":
                    return Genre.Travel;

                default:
                    return Genre.Unknown;
            }
        }
    }
}
