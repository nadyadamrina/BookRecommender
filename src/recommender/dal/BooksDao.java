package recommender.dal;

import recommender.model.Books;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BooksDao {
    private static BooksDao instance = null;
    protected ConnectionManager connectionManager;

    protected BooksDao() {
        connectionManager = new ConnectionManager();
    }

    public static BooksDao getInstance() {
        if (instance == null) {
            instance = new BooksDao();
        }
        return instance;
    }

    public Books create(Books book) throws SQLException {
        String insertBook = "INSERT INTO Books(ISBN,Title,AuthorId,Genre,Description,ImageUri,AverageRating," +
                "PublicationMonth,PublicationDay,PublicationYear,Publisher,NumberOfPages,NumberOfRatings) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertBook);
            insertStmt.setString(1, book.getIsbn());
            insertStmt.setString(2, book.getTitle());
            insertStmt.setInt(3, book.getAuthorId());
            insertStmt.setString(4, book.getGenre().toString());
            insertStmt.setString(5, book.getDescription());
            insertStmt.setString(6, book.getImageUrl());
            insertStmt.setBigDecimal(7, book.getAverageRating());
            insertStmt.setInt(8, book.getPublicationMonth());
            insertStmt.setInt(9, book.getPublicationDay());
            insertStmt.setInt(10, book.getPublicationYear());
            insertStmt.setString(11, book.getPublisher());
            insertStmt.setInt(12, book.getNumberOfPages());
            insertStmt.setInt(13, book.getNumberOfRatings());
            insertStmt.executeUpdate();
            return book;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    public Books getBookByISBN(String ISBN) throws SQLException {
        String selectBook = "SELECT * FROM Books WHERE ISBN=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectBook);
            selectStmt.setString(1, ISBN);
            results = selectStmt.executeQuery();
            if (results.next()) {
                String title = results.getString("Title");
                int authorId = results.getInt("AuthorId");
                String genreAsString = results.getString("Genre");
                Books.Genre genre = Books.Genre.parse(genreAsString);
                String description = results.getString("Description");
                String imageUri = results.getString("ImageUrl");
                BigDecimal averageRating = results.getBigDecimal("AverageRating");
                int publicationMonth = results.getInt("PublicationMonth");
                int publicationDay = results.getInt("PublicationDay");
                int publicationYear = results.getInt("PublicationYear");
                String publisher = results.getString("Publisher");
                int numberOfPages = results.getInt("NumberOfPages");
                int numberOfRating = results.getInt("NumberOfRatings");

                Books book = new Books(ISBN, title, authorId, genre, description, imageUri, averageRating,
                        publicationMonth, publicationDay, publicationYear, publisher, numberOfPages, numberOfRating);
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }

    public Books getBookByTitle(String title) throws SQLException {
        String selectBook = "SELECT * FROM Books WHERE Title=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectBook);
            selectStmt.setString(1, title);
            results = selectStmt.executeQuery();
            if (results.next()) {
                String ISBN = results.getString("ISBN");
                String resultTitle = results.getString("Title");
                int authorId = results.getInt("AuthorId");
                Books.Genre genre = Books.Genre.parse(results.getString("Genre"));
                String description = results.getString("Description");
                String imageUri = results.getString("ImageUri");
                BigDecimal averageRating = results.getBigDecimal("AverageRating");
                int publicationMonth = results.getInt("PublicationMonth");
                int publicationDay = results.getInt("PublicationDay");
                int publicationYear = results.getInt("PublicationYear");
                String publisher = results.getString("Publisher");
                int numberOfPages = results.getInt("NumberOfPages");
                int numberOfRating = results.getInt("NumberOfRatings");

                Books book = new Books(ISBN, resultTitle, authorId, genre, description, imageUri, averageRating,
                        publicationMonth, publicationDay, publicationYear, publisher, numberOfPages, numberOfRating);
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }

    public List<Books> getBooksByGenre(String genre) throws SQLException {
        List<Books> books = new ArrayList<Books>();
        String selectRestaurants =
                "SELECT * FROM Books WHERE Genre=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRestaurants);
            selectStmt.setString(1, genre);
            results = selectStmt.executeQuery();
            while (results.next()) {
                String ISBN = results.getString("ISBN");
                String title = results.getString("Title");
                int authorId = results.getInt("AuthorId");
                Books.Genre resultGenre = Books.Genre.parse(genre);
                String description = results.getString("Description");
                String imageUri = results.getString("ImageUri");
                BigDecimal averageRating = results.getBigDecimal("AverageRating");
                int publicationMonth = results.getInt("PublicationMonth");
                int publicationDay = results.getInt("PublicationDay");
                int publicationYear = results.getInt("PublicationYear");
                String publisher = results.getString("Publisher");
                int numberOfPages = results.getInt("NumberOfPages");
                int numberOfRating = results.getInt("NumberOfRatings");

                Books book = new Books(ISBN, title, authorId, resultGenre, description, imageUri, averageRating,
                        publicationMonth, publicationDay, publicationYear, publisher, numberOfPages, numberOfRating);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return books;
    }

    public List<Books> getBooksByAuthorId(int authorId) throws SQLException {
        List<Books> books = new ArrayList<Books>();
        String selectRestaurants =
                "SELECT * FROM Books WHERE AuthorId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRestaurants);
            selectStmt.setInt(1, authorId);
            results = selectStmt.executeQuery();
            while (results.next()) {
                String ISBN = results.getString("ISBN");
                String title = results.getString("Title");
                Books.Genre genre = Books.Genre.parse(results.getString("Genre"));
                String description = results.getString("Description");
                String imageUri = results.getString("ImageUri");
                BigDecimal averageRating = results.getBigDecimal("AverageRating");
                int publicationMonth = results.getInt("PublicationMonth");
                int publicationDay = results.getInt("PublicationDay");
                int publicationYear = results.getInt("PublicationYear");
                String publisher = results.getString("Publisher");
                int numberOfPages = results.getInt("NumberOfPages");
                int numberOfRating = results.getInt("NumberOfRatings");

                Books book = new Books(ISBN, title, authorId, genre, description, imageUri, averageRating,
                        publicationMonth, publicationDay, publicationYear, publisher, numberOfPages, numberOfRating);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return books;
    }

    public Books updateGenre(Books book, String newGenre) throws SQLException {
        String updateGenre = "UPDATE Books SET Genre=? WHERE ISBN=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateGenre);
            updateStmt.setString(1, newGenre);
            updateStmt.setString(2, book.getIsbn());
            updateStmt.executeUpdate();

            book.setGenre(Books.Genre.parse(newGenre));
            return book;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (updateStmt != null) {
                updateStmt.close();
            }
        }
    }

    public Books updateDescription(Books book, String description) throws SQLException {
        String updateDescription = "UPDATE Books SET Description=? WHERE ISBN=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateDescription);
            updateStmt.setString(1, description);
            updateStmt.setString(2, book.getIsbn());
            updateStmt.executeUpdate();

            book.setDescription(description);
            return book;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (updateStmt != null) {
                updateStmt.close();
            }
        }
    }

    public Books delete(Books book) throws SQLException {
        String deleteBook = "DELETE FROM Books WHERE ISBN=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteBook);
            deleteStmt.setString(1, book.getIsbn());
            deleteStmt.executeUpdate();

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }
}
