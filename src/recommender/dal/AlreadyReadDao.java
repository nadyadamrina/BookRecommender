package recommender.dal;

import recommender.model.AlreadyRead;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlreadyReadDao {
    private static AlreadyReadDao instance = null;
    protected ConnectionManager connectionManager;

    protected AlreadyReadDao() {
        connectionManager = new ConnectionManager();
    }

    public static AlreadyReadDao getInstance() {
        if (instance == null) {
            instance = new AlreadyReadDao();
        }
        return instance;
    }

    public AlreadyRead create(AlreadyRead alreadyRead) throws SQLException {
        String insertAlreadyRead = "INSERT INTO AlreadyRead(Completed,UserName,ISBN) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertAlreadyRead, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setTimestamp(1, new Timestamp(alreadyRead.getCompleted().getTime()));
            insertStmt.setString(2, alreadyRead.getUserName());
            insertStmt.setString(3, alreadyRead.getIsbn());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int alreadyReadId = -1;
            if (resultKey.next()) {
                alreadyReadId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            alreadyRead.setAlreadyReadId(alreadyReadId);
            return alreadyRead;
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

    public AlreadyRead getAlreadyReadById(int alreadyReadId) throws SQLException {
        String selectAlreadyRead =
                "SELECT * " +
                        "FROM AlreadyRead " +
                        "WHERE AlreadyReadId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAlreadyRead);
            selectStmt.setInt(1, alreadyReadId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int resultAlreadyReadId = results.getInt("AlreadyReadId");
                Date completed = new Date(results.getTimestamp("Completed").getTime());
                String userName = results.getString("UserName");
                String ISBN = results.getString("ISBN");

                AlreadyRead alreadyRead = new AlreadyRead(resultAlreadyReadId, completed, userName, ISBN);
                return alreadyRead;
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

    public List<AlreadyRead> getAlreadyReadsByUserName(String userName) throws SQLException {
        List<AlreadyRead> alreadyReads = new ArrayList<AlreadyRead>();
        String selectAlreadyReads =
                "SELECT * " +
                        "FROM AlreadyRead " +
                        "WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAlreadyReads);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int alreadyReadId = results.getInt("AlreadyReadId");
                Date completed = new Date(results.getTimestamp("Completed").getTime());
                String resultUserName = results.getString("UserName");
                String ISBN = results.getString("ISBN");

                AlreadyRead alreadyRead = new AlreadyRead(alreadyReadId, completed, resultUserName, ISBN);
                alreadyReads.add(alreadyRead);
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
        return alreadyReads;
    }

    public List<AlreadyRead> getAlreadyReadsByISBN(String ISBN) throws SQLException {
        List<AlreadyRead> alreadyReads = new ArrayList<AlreadyRead>();
        String selectAlreadyReads =
                "SELECT * " +
                        "FROM AlreadyRead " +
                        "WHERE ISBN=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAlreadyReads);
            selectStmt.setString(1, ISBN);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int alreadyReadId = results.getInt("AlreadyReadId");
                Date completed = new Date(results.getTimestamp("Completed").getTime());
                String userName = results.getString("UserName");
                String resultISBN = results.getString("ISBN");

                AlreadyRead alreadyRead = new AlreadyRead(alreadyReadId, completed, userName, resultISBN);
                alreadyReads.add(alreadyRead);
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
        return alreadyReads;
    }

    public AlreadyRead delete(AlreadyRead alreadyRead) throws SQLException {
        String deleteAlready = "DELETE FROM AlreadyRead WHERE AlreadyReadId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteAlready);
            deleteStmt.setInt(1, alreadyRead.getAlreadyReadId());
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
