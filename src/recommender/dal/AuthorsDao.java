package recommender.dal;

import recommender.model.Authors;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorsDao {
    private static AuthorsDao instance = null;
    protected ConnectionManager connectionManager;

    protected AuthorsDao() {
        connectionManager = new ConnectionManager();
    }

    public static AuthorsDao getInstance() {
        if (instance == null) {
            instance = new AuthorsDao();
        }
        return instance;
    }

    public Authors create(Authors author) throws SQLException {
        String insertAuthor = "INSERT INTO Authors(FirstName, LastName) VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertAuthor, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, author.getFirstName());
            insertStmt.setString(2, author.getLastName());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int authorId = -1;
            if (resultKey.next()) {
                authorId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            author.setAuthorId(authorId);
            return author;
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

    public Authors updateLastName(Authors author, String newLastName) throws SQLException {
        String updateAuthor = "UPDATE Authors SET LastName=? WHERE AuthorId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateAuthor);
            updateStmt.setString(1, newLastName);
            updateStmt.setInt(2, author.getAuthorId());
            updateStmt.executeUpdate();

            author.setLastName(newLastName);
            return author;
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

    public List<Authors> getAuthorsByFirstName(String firstName) throws SQLException {
        List<Authors> authors = new ArrayList<Authors>();
        String selectAuthors =
                "SELECT AuthorId,FirstName,LastName " +
                        "FROM Authors " +
                        "WHERE FirstName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAuthors);
            selectStmt.setString(1, firstName);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int authorId = results.getInt("AuthorId");
                String lastName = results.getString("LastName");
                Authors author = new Authors(authorId, firstName, lastName);
                authors.add(author);
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
        return authors;
    }

    public Authors delete(Authors author) throws SQLException {
        String deleteUser = "DELETE FROM Authors WHERE AuthorId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteUser);
            deleteStmt.setInt(1, author.getAuthorId());
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

    public Authors getAuthorById(int authorId) throws SQLException {
        String selectBook = "SELECT * FROM Authors WHERE AuthorId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectBook);
            selectStmt.setInt(1, authorId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int authorIdResult = results.getInt("AuthorId");
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                return new Authors(authorIdResult, firstName, lastName);
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
}
