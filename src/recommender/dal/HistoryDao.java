package recommender.dal;

import recommender.model.History;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {
    private static HistoryDao instance = null;
    protected ConnectionManager connectionManager;

    protected HistoryDao() {
        connectionManager = new ConnectionManager();
    }

    public static HistoryDao getInstance() {
        if (instance == null) {
            instance = new HistoryDao();
        }
        return instance;
    }

    public History create(History history) throws SQLException {
        String insertHistory = "INSERT INTO History(UserName,SearchContent,Created) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertHistory, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, history.getUserName());
            insertStmt.setString(2, history.getSearchContent());
            insertStmt.setTimestamp(3, new Timestamp(history.getCreated().getTime()));
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int historyId = -1;
            if (resultKey.next()) {
                historyId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            history.setHistoryId(historyId);
            return history;
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

    public History getHistoryByHistoryId(int historyId) throws SQLException {
        String selectHistory =
                "SELECT * " +
                        "FROM History " +
                        "WHERE HistoryId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectHistory);
            selectStmt.setInt(1, historyId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int resultHistoryId = results.getInt("HistoryId");
                String userName = results.getString("UserName");
                String searchContent = results.getString("SearchContent");
                Date created = new Date(results.getTimestamp("Created").getTime());

                History history = new History(resultHistoryId, userName, searchContent, created);
                return history;
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

    public List<History> getHistoryByUserName(String userName) throws SQLException {
        List<History> historyList = new ArrayList<History>();
        String selectHistories =
                "SELECT * " +
                        "FROM History " +
                        "WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectHistories);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int historyId = results.getInt("HistoryId");
                String resultUserName = results.getString("UserName");
                String searchContent = results.getString("SearchContent");
                Date created = new Date(results.getTimestamp("Created").getTime());

                History history = new History(historyId, resultUserName, searchContent, created);
                historyList.add(history);
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
        return historyList;
    }

    public History delete(History history) throws SQLException {
        String deleteHistory = "DELETE FROM History WHERE HistoryId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteHistory);
            deleteStmt.setInt(1, history.getHistoryId());
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
