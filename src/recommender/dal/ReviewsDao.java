package recommender.dal;

import recommender.model.Reviews;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewsDao {
    protected ConnectionManager connectionManager;

    private static ReviewsDao instance = null;
    protected ReviewsDao() {
        connectionManager = new ConnectionManager();
    }
    public static ReviewsDao getInstance() {
        if(instance == null) {
            instance = new ReviewsDao();
        }
        return instance;
    }

    public Reviews create(Reviews review) throws SQLException {
        String insertReview = "INSERT INTO Reviews(Created,Description,UserName,ISBN,RatingId) VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertReview, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setTimestamp(1, new Timestamp(review.getCreated().getTime()));
            insertStmt.setString(2, review.getDescription());
            insertStmt.setString(3, review.getUserName());
            insertStmt.setString(4, review.getIsbn());
            insertStmt.setInt(5, review.getRatingId());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int reviewId = -1;
            if (resultKey.next()) {
                reviewId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            review.setReviewId(reviewId);
            return review;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(insertStmt != null) {
                insertStmt.close();
            }
            if(resultKey != null) {
                resultKey.close();
            }
        }
    }

    public Reviews getReviewById(int reviewId) throws SQLException {
        String selectReview =
                "SELECT * " +
                        "FROM Reviews " +
                        "WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReview);
            selectStmt.setInt(1, reviewId);
            results = selectStmt.executeQuery();
            if(results.next()) {
                int resultReviewId = results.getInt("ReviewId");
                Date created = new Date(results.getTimestamp("Created").getTime());
                String description = results.getString("Description");
                String userName = results.getString("UserName");
                String ISBN = results.getString("ISBN");
                int ratingId = results.getInt("RatingId");

                Reviews review = new Reviews(resultReviewId, created, description, userName, ISBN, ratingId);
                return review;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return null;
    }

    public List<Reviews> getReviewsByUserName(String userName) throws SQLException {
        List<Reviews> reviews = new ArrayList<Reviews>();
        String selectReviews =
                "SELECT * " +
                        "FROM Reviews " +
                        "WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReviews);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            while(results.next()) {
                int reviewId = results.getInt("ReviewId");
                Date created = new Date(results.getTimestamp("Created").getTime());
                String description = results.getString("Description");
                String resultUserName = results.getString("UserName");
                String ISBN = results.getString("ISBN");
                int ratingId = results.getInt("RatingId");

                Reviews review = new Reviews(reviewId, created, description, resultUserName, ISBN, ratingId);
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return reviews;
    }

    public List<Reviews> getReviewsByISBN(String ISBN) throws SQLException {
        List<Reviews> reviews = new ArrayList<Reviews>();
        String selectReviews =
                "SELECT * " +
                        "FROM Reviews " +
                        "WHERE ISBN=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReviews);
            selectStmt.setString(1, ISBN);
            results = selectStmt.executeQuery();
            while(results.next()) {
                int reviewId = results.getInt("ReviewId");
                Date created = new Date(results.getTimestamp("Created").getTime());
                String description = results.getString("Description");
                String userName = results.getString("UserName");
                String resultISBN = results.getString("ISBN");
                int ratingId = results.getInt("RatingId");

                Reviews review = new Reviews(reviewId, created, description, userName, resultISBN, ratingId);
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return reviews;
    }

    public Reviews delete(Reviews review) throws SQLException {
        String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteReview);
            deleteStmt.setInt(1, review.getReviewId());
            deleteStmt.executeUpdate();

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }
}
