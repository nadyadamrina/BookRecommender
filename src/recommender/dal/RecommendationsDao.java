package recommender.dal;

import recommender.model.Recommendations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecommendationsDao {
    protected ConnectionManager connectionManager;

    private static RecommendationsDao instance = null;
    protected RecommendationsDao() {
        connectionManager = new ConnectionManager();
    }
    public static RecommendationsDao getInstance() {
        if(instance == null) {
            instance = new RecommendationsDao();
        }
        return instance;
    }

    public Recommendations create(Recommendations recommendation) throws SQLException {
        String insertRecommendation = "INSERT INTO Recommendations(UserName,ISBN,Created) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertRecommendation, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, recommendation.getUserName());
            insertStmt.setString(2, recommendation.getIsbn());
            insertStmt.setTimestamp(3, new Timestamp(recommendation.getCreated().getTime()));
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int recommendationId = -1;
            if (resultKey.next()) {
                recommendationId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            recommendation.setRecommendationId(recommendationId);
            return recommendation;
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

    public Recommendations getRecommendationById(int recommendationId) throws SQLException {
        String selectRecommendation =
                "SELECT * " +
                        "FROM Recommendations " +
                        "WHERE RecommendationId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRecommendation);
            selectStmt.setInt(1, recommendationId);
            results = selectStmt.executeQuery();
            if(results.next()) {
                int resultRecId = results.getInt("RecommendationId");
                String userName = results.getString("UserName");
                String ISBN = results.getString("ISBN");
                Date created = new Date(results.getTimestamp("Created").getTime());

                Recommendations recommendation = new Recommendations(resultRecId, userName, ISBN, created);
                return recommendation;
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

    public List<Recommendations> getRecommendationsByGenre(String username, String genre) throws SQLException {
        List<Recommendations> recommendations = new ArrayList<Recommendations>();
        String selectRecommendations =
                "SELECT Books.Title, Books.Genre, Books.ISBN, COUNT(*) AS CheckoutCount " +
                        "FROM Checkouts INNER JOIN Books ON " +
                        "Checkouts.ISBN = Books.ISBN " +
                        "WHERE Books.Genre = ? " +
                        "GROUP BY Checkouts.ISBN " +
                        "ORDER BY CheckoutCount DESC " +
                        "LIMIT 5;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRecommendations);
            selectStmt.setString(1, genre);
            results = selectStmt.executeQuery();

            while(results.next()) {
                String ISBN = results.getString("ISBN");

                Recommendations recommendation = new Recommendations(username, ISBN);
                recommendations.add(recommendation);
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
        return recommendations;
    }

    public List<Recommendations> getRecommendationsByISBN(String ISBN) throws SQLException {
        List<Recommendations> recommendations = new ArrayList<Recommendations>();
        String selectRecommendations =
                "SELECT * " +
                        "FROM Recommendations " +
                        "WHERE ISBN=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRecommendations);
            selectStmt.setString(1, ISBN);
            results = selectStmt.executeQuery();

            while(results.next()) {
                int recommendationId = results.getInt("RecommendationId");
                String userName = results.getString("UserName");
                String resultISBN = results.getString("ISBN");
                Date created = new Date(results.getTimestamp("Created").getTime());

                Recommendations recommendation = new Recommendations(recommendationId, userName, resultISBN, created);
                recommendations.add(recommendation);
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
        return recommendations;
    }

    public Recommendations delete(Recommendations recommendation) throws SQLException {
        String deleteRecommendation = "DELETE FROM Recommendations WHERE RecommendationId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteRecommendation);
            deleteStmt.setInt(1, recommendation.getRecommendationId());
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
