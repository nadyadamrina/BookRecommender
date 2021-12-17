package recommender.dal;

import recommender.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {
    private static UsersDao instance = null;
    protected ConnectionManager connectionManager;

    protected UsersDao() {
        connectionManager = new ConnectionManager();
    }

    public static UsersDao getInstance() {
        if (instance == null) {
            instance = new UsersDao();
        }
        return instance;
    }

    public Users create(Users user) throws SQLException {
        String insertUser = "INSERT INTO Users(UserName,Password,FirstName,LastName,Phone,Email) VALUES(?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertUser);

            insertStmt.setString(1, user.getUserName());
            insertStmt.setString(2, user.getPassword());
            insertStmt.setString(3, user.getFirstName());
            insertStmt.setString(4, user.getLastName());
            insertStmt.setString(5, user.getPhone());
            insertStmt.setString(6, user.getEmail());

            insertStmt.executeUpdate();

            return user;
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

    public Users getUserFromUserName(String userName) throws SQLException {
        String selectUser = "SELECT UserName,Password,FirstName,LastName,Phone,Email FROM Users WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectUser);
            selectStmt.setString(1, userName);

            results = selectStmt.executeQuery();

            if (results.next()) {
                String resultUserName = results.getString("UserName");
                String password = results.getString("Password");
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                String phone = results.getString("Phone");
                String email = results.getString("Email");
                Users user = new Users(resultUserName, password, firstName, lastName, email, phone);
                return user;
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

    public List<Users> getUsersFromLastName(String lastName) throws SQLException {
        List<Users> usersList = new ArrayList<>();

        String selectUsers = "SELECT UserName,Password,FirstName,LastName,Phone,Email FROM Users WHERE LastName=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectUsers);
            selectStmt.setString(1, lastName);
            results = selectStmt.executeQuery();
            while (results.next()) {
                String userName = results.getString("UserName");
                String password = results.getString("Password");
                String firstName = results.getString("FirstName");
                String lastNameResult = results.getString("LastName");
                String email = results.getString("Email");
                String phone = results.getString("Phone");

                Users user = new Users(userName, password, firstName, lastNameResult, email, phone);

                usersList.add(user);
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

        return usersList;
    }

    public Users updateName(Users user, String newFirstName, String newLastName) throws SQLException {
        String updateUser = "UPDATE Users SET FirstName=?, LastName=? WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateUser);
            updateStmt.setString(1, newFirstName);
            updateStmt.setString(2, newLastName);
            updateStmt.setString(3, user.getUserName());
            updateStmt.executeUpdate();

            user.setLastName(newLastName);
            return user;
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

    public Users delete(Users user) throws SQLException {
        String deleteUser = "DELETE FROM Users WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteUser);
            deleteStmt.setString(1, user.getUserName());
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
