package recommender.dal;

import recommender.model.Ratings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingsDao {
    private static RatingsDao instance = null;
    protected ConnectionManager connectionManager;

    protected RatingsDao() {
        connectionManager = new ConnectionManager();
    }

    public static RatingsDao getInstance() {
        if (instance == null) {
            instance = new RatingsDao();
        }
        return instance;
    }

    public Ratings create(Ratings rating) throws SQLException {
        String insertRating = "INSERT INTO Ratings(Created,RatingValue,UserName,ISBN) VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertRating, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setTimestamp(1, new Timestamp(rating.getCreated().getTime()));
            insertStmt.setInt(2, rating.getRatingId());
            insertStmt.setString(3, rating.getUserName());
            insertStmt.setString(4, rating.getIsbn());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int ratingId = -1;
            if (resultKey.next()) {
                ratingId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            rating.setRatingId(ratingId);
            return rating;
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
            if (resultKey != null) {
                resultKey.close();
            }
        }
    }

    public Ratings getRatingByRatingId(int ratingId) throws SQLException {
        String selectRating =
                "SELECT * " +
                        "FROM Ratings " +
                        "WHERE RatingId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRating);
            selectStmt.setInt(1, ratingId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int resultRatingId = results.getInt("RatingId");
                Date created = new Date(results.getTimestamp("Created").getTime());
                int ratingValue = results.getInt("RatingValue");
                String userName = results.getString("UserName");
                String ISBN = results.getString("ISBN");

                Ratings rating = new Ratings(resultRatingId, created, ratingValue, userName, ISBN);
                return rating;
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

    public List<Ratings> getRatingsByUserName(String userName) throws SQLException {
        List<Ratings> ratings = new ArrayList<Ratings>();
        String selectRatings =
                "SELECT * " +
                        "FROM Ratings " +
                        "WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRatings);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int ratingId = results.getInt("RatingId");
                Date created = new Date(results.getTimestamp("Created").getTime());
                int ratingValue = results.getInt("RatingValue");
                String resultUserName = results.getString("UserName");
                String ISBN = results.getString("ISBN");

                Ratings rating = new Ratings(ratingId, created, ratingValue, resultUserName, ISBN);
                ratings.add(rating);
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
        return ratings;
    }

    public List<Ratings> getRatingsByISBN(String ISBN) throws SQLException {
        List<Ratings> ratings = new ArrayList<Ratings>();
        String selectRatings =
                "SELECT * " +
                        "FROM Ratings " +
                        "WHERE ISBN=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRatings);
            selectStmt.setString(1, ISBN);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int ratingId = results.getInt("RatingId");
                Date created = new Date(results.getTimestamp("Created").getTime());
                int ratingValue = results.getInt("RatingValue");
                String userName = results.getString("UserName");
                String resultISBN = results.getString("ISBN");

                Ratings rating = new Ratings(ratingId, created, ratingValue, userName, resultISBN);
                ratings.add(rating);
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
        return ratings;
    }

    public Ratings updateRatingValue(Ratings rating, int newRatingValue) throws SQLException {
        String updateRating = "UPDATE Ratings SET RatingValue=? WHERE RatingId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateRating);
            updateStmt.setInt(1, newRatingValue);
            updateStmt.setLong(2, rating.getRatingId());
            updateStmt.executeUpdate();

            rating.setRatingValue(newRatingValue);
            return rating;
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

    public Ratings delete(Ratings rating) throws SQLException {
        String deleteRating = "DELETE FROM Ratings WHERE RatingId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteRating);
            deleteStmt.setInt(1, rating.getRatingId());
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
