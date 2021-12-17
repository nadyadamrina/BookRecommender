package recommender.tools;

import recommender.dal.*;
import recommender.model.*;
import recommender.model.Books.Genre;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Inserter {
    public static void main(String[] args) throws SQLException {
        UsersDao usersDao = UsersDao.getInstance();
        AuthorsDao authorsDao = AuthorsDao.getInstance();
        BooksDao booksDao = BooksDao.getInstance();
        CheckoutsDao checkoutsDao = CheckoutsDao.getInstance();
        FavoritesDao favoritesDao = FavoritesDao.getInstance();
        HistoryDao historyDao = HistoryDao.getInstance();
        RatingsDao ratingsDao = RatingsDao.getInstance();
        AlreadyReadDao alreadyReadDao = AlreadyReadDao.getInstance();
        PreferencesDao preferencesDao = PreferencesDao.getInstance();
        ReviewsDao reviewsDao = ReviewsDao.getInstance();
        RecommendationsDao recommendationsDao = RecommendationsDao.getInstance();
        Date date = new Date();
        BigDecimal bigDecimal = new BigDecimal("5.0");

        Users user1 = new Users("julie", "123", "Julie", "Han", "julie@", "1234");
        user1 = usersDao.create(user1);
        Users user2 = new Users("chris", "123", "Chris", "Holzheu", "chris@", "12345");
        user2 = usersDao.create(user2);
        Authors author1 = new Authors(1, "Sean", "Stevens");
        author1 = authorsDao.create(author1);
        Authors author2 = new Authors(2, "Nadiia", "Ramthun");
        author2 = authorsDao.create(author2);
        Books book1 = new Books("ISBN1", "Harry Potter", 1, Genre.ScienceFictionAndFantasy, "Description", "ImageURL", bigDecimal, 10, 10, 2000, "Big Apple", 300, 5);
        book1 = booksDao.create(book1);
        Books book2 = new Books("ISBN2", "The Lord of the Rings", 2, Genre.ScienceFictionAndFantasy, "Description", "ImageURL", bigDecimal, 8, 8, 2000, "Penguin", 500, 10);
        book2 = booksDao.create(book2);
        Books book3 = new Books("ISBN3", "The Catcher in the Rye", 1, Genre.LiteratureAndFiction, "Description", "ImageURL", bigDecimal, 9, 9, 2000, "Penguin", 200, 20);
        book3 = booksDao.create(book3);
        Books book4 = new Books("ISBN4", "Animal Farm", 2, Genre.LiteratureAndFiction, "Description", "ImageURL", bigDecimal, 7, 7, 2000, "Big Apple", 200, 15);
        book4 = booksDao.create(book4);
        Checkouts checkout1 = new Checkouts(1, "ISBN1", date);
        checkout1 = checkoutsDao.create(checkout1);
        Checkouts checkout2 = new Checkouts(2, "ISBN2", date);
        checkout2 = checkoutsDao.create(checkout2);
        Checkouts checkout3 = new Checkouts(3, "ISBN3", date);
        checkout3 = checkoutsDao.create(checkout3);
        Favorites favorites1 = new Favorites(1, date, "julie", "ISBN1");
        favorites1 = favoritesDao.create(favorites1);
        Favorites favorites2 = new Favorites(2, date, "chris", "ISBN2");
        favorites2 = favoritesDao.create(favorites2);
        History history1 = new History(1, "julie", "Harry Potter", date);
        history1 = historyDao.create(history1);
        History history2 = new History(2, "chris", "The Lord of the Rings", date);
        history2 = historyDao.create(history2);
        Ratings ratings1 = new Ratings(1, date, 5, "julie", "ISBN1");
        ratings1 = ratingsDao.create(ratings1);
        Ratings ratings2 = new Ratings(2, date, 5, "chris", "ISBN2");
        ratings2 = ratingsDao.create(ratings2);
        Ratings ratings3 = new Ratings(3, date, 4, "julie", "ISBN3");
        ratings3 = ratingsDao.create(ratings3);
        AlreadyRead alreadyRead1 = new AlreadyRead(1, date, "julie", "ISBN2");
        alreadyRead1 = alreadyReadDao.create(alreadyRead1);
        AlreadyRead alreadyRead2 = new AlreadyRead(2, date, "chris", "ISBN3");
        alreadyRead2 = alreadyReadDao.create(alreadyRead2);
        Preferences preference1 = new Preferences(1, "julie", Genre.ComicsAndGraphicNovels, Genre.ScienceFictionAndFantasy);
        preference1 = preferencesDao.create(preference1);
        Preferences preference2 = new Preferences(2, "chris", Genre.ComputersAndTechnology, Genre.LiteratureAndFiction);
        preference2 = preferencesDao.create(preference2);
        Reviews review1 = new Reviews(1, date, "Description1", "julie", "ISBN1", 5);
        review1 = reviewsDao.create(review1);
        Reviews review2 = new Reviews(2, date, "Description2", "chris", "ISBN2", 5);
        review2 = reviewsDao.create(review2);
        Recommendations recommendation1 = new Recommendations(1, "julie", "ISBN1", date);
        recommendation1 = recommendationsDao.create(recommendation1);
        Recommendations recommendation2 = new Recommendations(2, "chris", "ISBN2", date);
        recommendation2 = recommendationsDao.create(recommendation2);

        Users u1 = usersDao.getUserFromUserName("chris");
        System.out.format("Reading person: u:%s f:%s l:%s \n",
                u1.getUserName(), u1.getFirstName(), u1.getLastName());
        u1 = usersDao.updateName(user2, "chris", "potter");
        System.out.format("Reading updated person: u:%s f:%s l:%s \n",
                u1.getUserName(), u1.getFirstName(), u1.getLastName());
        Users deletedUser = usersDao.delete(user2);

        List<Authors> authorList1 = authorsDao.getAuthorsByFirstName("Nadiia");
        for (Authors author : authorList1) {
            System.out.format("Reading authors: u:%s f:%s l:%s \n",
                    author.getAuthorId(), author.getFirstName(), author.getLastName());
        }
        Authors a1 = authorsDao.updateLastName(author2, "Rowling");
        Authors deletedAuthor = authorsDao.delete(author2);

        Books b1 = booksDao.getBookByISBN("ISBN1");
        System.out.format("Reading book by ISBN: u:%s f:%s l:%s \n",
                b1.getIsbn(), b1.getTitle(), b1.getGenre());
        Books b2 = booksDao.getBookByTitle("Harry Potter");
        System.out.format("Reading book by title: u:%d f:%s l:%s \n",
                b2.getAuthorId(), b2.getTitle(), b2.getGenre());
        List<Books> bookList1 = booksDao.getBooksByGenre("LiteratureAndFiction");
        for (Books book : bookList1) {
            System.out.format("Reading books by genre: u:%s f:%s l:%s \n",
                    book.getIsbn(), book.getTitle(), book.getGenre().toString());
        }
        List<Books> bookList2 = booksDao.getBooksByAuthorId(1);
        for (Books book : bookList2) {
            System.out.format("Reading books by author: u:%s f:%s l:%d \n",
                    book.getIsbn(), book.getTitle(), book.getAuthorId());
        }
        Books b3 = booksDao.updateGenre(book2, "LiteratureAndFiction");
        System.out.format("Reading updated book genre: u:%d f:%s l:%s \n",
                b3.getAuthorId(), b3.getTitle(), b3.getGenre());
        Books b4 = booksDao.updateDescription(book3, "NewDescription");
        System.out.format("Reading updated book description: u:%d f:%s l:%s \n",
                b4.getAuthorId(), b4.getTitle(), b4.getDescription());
        Books deletedBook = booksDao.delete(book3);

        Checkouts co1 = checkoutsDao.getCheckoutByCheckoutId(1);
        System.out.format("Reading checkout by id: u:%d f:%s \n",
                co1.getCheckoutId(), co1.getIsbn());
        List<Checkouts> coList1 = checkoutsDao.getCheckoutsByISBN("ISBN1");
        for (Checkouts checkout : coList1) {
            System.out.format("Reading checkout by id: u:%d f:%s \n",
                    checkout.getCheckoutId(), checkout.getIsbn(), checkout.getCheckoutDate());
        }
        Checkouts delectedCheckout = checkoutsDao.delete(checkout1);

        Favorites f1 = favoritesDao.getFavoriteByFavoriteId(1);
        System.out.format("Reading favorites by id: u:%d f:%s s:%s \n",
                f1.getFavoriteId(), f1.getIsbn(), f1.getUserName());
        List<Favorites> f1List1 = favoritesDao.getFavoritesByUserName("julie");
        for (Favorites favorite : f1List1) {
            System.out.format("Reading favorites by username: u:%d f:%s s:%s \n",
                    favorite.getFavoriteId(), favorite.getIsbn(), favorite.getUserName());
        }
        Favorites deletedFavorites = favoritesDao.delete(favorites2);

        History h1 = historyDao.getHistoryByHistoryId(1);
        System.out.format("Reading history by id: u:%d f:%s s:%s \n",
                h1.getHistoryId(), h1.getSearchContent(), h1.getUserName());
        List<History> h1List1 = historyDao.getHistoryByUserName("chris");
        for (History history : h1List1) {
            System.out.format("Reading history by username: u:%d f:%s s:%s \n",
                    h1.getHistoryId(), h1.getSearchContent(), h1.getUserName());
        }
        History deletedHistory = historyDao.delete(history2);

        Ratings r1 = ratingsDao.getRatingByRatingId(1);
        System.out.format("Reading ratings by id: u:%d f:%s s:%s r:%d \n",
                r1.getRatingId(), r1.getIsbn(), r1.getUserName(), r1.getRatingValue());
        List<Ratings> rList1 = ratingsDao.getRatingsByUserName("julie");
        for (Ratings rating : rList1) {
            System.out.format("Reading ratings by userName: u:%d f:%s s:%s r:%d \n",
                    rating.getRatingId(), rating.getIsbn(), rating.getUserName(), rating.getRatingValue());
        }
        List<Ratings> rList2 = ratingsDao.getRatingsByISBN("ISBN2");
        for (Ratings rating : rList2) {
            System.out.format("Reading ratings by userName: u:%d f:%s s:%s r:%d \n",
                    rating.getRatingId(), rating.getIsbn(), rating.getUserName(), rating.getRatingValue());
        }
        r1 = ratingsDao.updateRatingValue(ratings1, 3);
        Ratings deletedRating = ratingsDao.delete(ratings2);

        AlreadyRead ar1 = alreadyReadDao.getAlreadyReadById(1);
        System.out.format("Reading already read by id: u:%d f:%s s:%s \n",
                ar1.getAlreadyReadId(), ar1.getIsbn(), ar1.getUserName());
        List<AlreadyRead> arList1 = alreadyReadDao.getAlreadyReadsByUserName("chris");
        for (AlreadyRead read : arList1) {
            System.out.format("Reading already read by userName: u:%d f:%s s:%s \n",
                    read.getAlreadyReadId(), read.getUserName(), read.getIsbn());
        }
        List<AlreadyRead> arList2 = alreadyReadDao.getAlreadyReadsByISBN("ISBN1");
        for (AlreadyRead read : arList2) {
            System.out.format("Reading already read by isbn: u:%d f:%s s:%s \n",
                    read.getAlreadyReadId(), read.getUserName(), read.getIsbn());
        }
        AlreadyRead deletedAlreadyRead = alreadyReadDao.delete(alreadyRead2);

        Preferences pre1 = preferencesDao.getPreferenceById(1);
        System.out.format("Reading preferences by id: u:%d n:%s f:%s s:%s \n",
                pre1.getPreferenceId(), pre1.getUserName(), pre1.getPrimaryGenre().toString(), pre1.getSecondaryGenre().toString());
        List<Preferences> preList1 = preferencesDao.getPreferencesByUserName("julie");
        for (Preferences preference : preList1) {
            System.out.format("Reading preferences by username: u:%d n:%s f:%s s:%s \n",
                    preference.getPreferenceId(), preference.getUserName(),
                    preference.getPrimaryGenre().toString(), preference.getSecondaryGenre().toString());
        }
        Preferences deletedPreference = preferencesDao.delete(preference2);

        Reviews rev1 = reviewsDao.getReviewById(1);
        System.out.format("Reading reviews by id: u:%d n:%s f:%s s:%s \n",
                rev1.getReviewId(), rev1.getUserName(), rev1.getDescription(), rev1.getIsbn());
        List<Reviews> revList1 = reviewsDao.getReviewsByISBN("ISBN1");
        for (Reviews review : revList1) {
            System.out.format("Reading reviews by ISBN: u:%d n:%s f:%s s:%s \n",
                    review.getReviewId(), review.getUserName(), review.getDescription(), review.getIsbn());
        }
        List<Reviews> revList2 = reviewsDao.getReviewsByUserName("chris");
        for (Reviews review : revList2) {
            System.out.format("Reading reviews by username: u:%d n:%s f:%s s:%s \n",
                    review.getReviewId(), review.getUserName(), review.getDescription(), review.getIsbn());
        }
        Reviews deletedReview = reviewsDao.delete(review2);

        Recommendations rec1 = recommendationsDao.getRecommendationById(1);
        System.out.format("Reading recommendations by id: u:%d n:%s f:%s \n",
                rec1.getRecommendationId(), rec1.getIsbn(), rec1.getUserName());
        List<Recommendations> recList1 = recommendationsDao.getRecommendationsByUserName("julie");
        for (Recommendations recommendation : recList1) {
            System.out.format("Reading recommendations by username: u:%d n:%s f:%s \n",
                    recommendation.getRecommendationId(), recommendation.getIsbn(), recommendation.getUserName());
        }
        List<Recommendations> recList2 = recommendationsDao.getRecommendationsByISBN("ISBN1");
        for (Recommendations recommendation : recList2) {
            System.out.format("Reading recommendations by isbn: u:%d n:%s f:%s \n",
                    recommendation.getRecommendationId(), recommendation.getIsbn(), recommendation.getUserName());
        }
        Recommendations deletedRecommendation = recommendationsDao.delete(recommendation2);

    }
}