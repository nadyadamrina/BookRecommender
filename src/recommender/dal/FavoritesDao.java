package recommender.dal;

import recommender.model.Favorites;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavoritesDao {
    private static FavoritesDao instance = null;
    protected ConnectionManager connectionManager;

    protected FavoritesDao() {
        connectionManager = new ConnectionManager();
    }

    public static FavoritesDao getInstance() {
        if (instance == null) {
            instance = new FavoritesDao();
        }
        return instance;
    }

    public Favorites create(Favorites favorite) throws SQLException {
        String insertFavorite =
                "INSERT INTO Favorites(Created,UserName,ISBN) " +
                        "VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertFavorite, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setTimestamp(1, new Timestamp(favorite.getCreated().getTime()));
            insertStmt.setString(2, favorite.getUserName());
            insertStmt.setString(3, favorite.getIsbn());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int favoriteId = -1;
            if (resultKey.next()) {
                favoriteId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            favorite.setFavoriteId(favoriteId);
            return favorite;
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

    public Favorites getFavoriteByFavoriteId(int favoriteId) throws SQLException {
        String selectFavorite =
                "SELECT * " +
                        "FROM Favorites " +
                        "WHERE FavoriteId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectFavorite);
            selectStmt.setInt(1, favoriteId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int resultFavoriteId = results.getInt("FavoriteId");
                Date created = new Date(results.getTimestamp("Created").getTime());
                String userName = results.getString("UserName");
                String ISBN = results.getString("ISBN");

                Favorites favorites = new Favorites(resultFavoriteId, created, userName, ISBN);
                return favorites;
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

    public List<Favorites> getFavoritesByUserName(String userName) throws SQLException {
        List<Favorites> favorites = new ArrayList<Favorites>();
        String selectFavorites =
                "SELECT * " +
                        "FROM Favorites " +
                        "WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectFavorites);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int favoriteId = results.getInt("FavoriteId");
                Date created = new Date(results.getTimestamp("Created").getTime());
                String resultUserName = results.getString("UserName");
                String ISBN = results.getString("ISBN");

                Favorites favorite = new Favorites(favoriteId, created, resultUserName, ISBN);
                favorites.add(favorite);
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
        return favorites;
    }

    public Favorites delete(Favorites favorite) throws SQLException {
        String deleteFavorite = "DELETE FROM Favorites WHERE FavoriteId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteFavorite);
            deleteStmt.setInt(1, favorite.getFavoriteId());
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
