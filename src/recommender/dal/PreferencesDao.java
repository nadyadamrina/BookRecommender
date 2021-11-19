package recommender.dal;

import recommender.model.Books;
import recommender.model.Preferences;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreferencesDao {
    protected ConnectionManager connectionManager;

    private static PreferencesDao instance = null;
    protected PreferencesDao() {
        connectionManager = new ConnectionManager();
    }
    public static PreferencesDao getInstance() {
        if(instance == null) {
            instance = new PreferencesDao();
        }
        return instance;
    }

    public Preferences create(Preferences preference) throws SQLException {
        String insertPreference = "INSERT INTO Preferences(UserName,PrimaryGenre,SecondaryGenre) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertPreference, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, preference.getUserName());
            insertStmt.setString(2, preference.getPrimaryGenre().name());
            insertStmt.setString(3, preference.getSecondaryGenre().name());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int preferenceId = -1;
            if(resultKey.next()) {
                preferenceId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            preference.setPreferenceId(preferenceId);
            return preference;
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

    public Preferences getPreferenceById(int preferenceId) throws SQLException {
        String selectPreference =
                "SELECT * " +
                        "FROM Preferences " +
                        "WHERE PreferenceId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPreference);
            selectStmt.setInt(1, preferenceId);
            results = selectStmt.executeQuery();
            if(results.next()) {
                int resultPreferenceId = results.getInt("PreferenceId");
                String userName = results.getString("UserName");
                Books.Genre genrePrimary = Books.Genre.parse(results.getString("PrimaryGenre"));
                Books.Genre secondaryGenre = Books.Genre.parse(results.getString("SecondaryGenre"));

                Preferences preference = new Preferences(resultPreferenceId, userName, genrePrimary, secondaryGenre);
                return preference;
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

    public List<Preferences> getPreferencesByUserName(String userName) throws SQLException {
        List<Preferences> preferences = new ArrayList<Preferences>();
        String selectPreferences =
                "SELECT * " +
                        "FROM Preferences " +
                        "WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPreferences);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            while(results.next()) {
                int preferenceId = results.getInt("PreferenceId");
                String resultUserName = results.getString("UserName");
                Books.Genre genrePrimary = Books.Genre.parse(results.getString("PrimaryGenre"));
                Books.Genre secondaryGenre = Books.Genre.parse(results.getString("SecondaryGenre"));

                Preferences preference = new Preferences(preferenceId, resultUserName, genrePrimary, secondaryGenre);
                preferences.add(preference);
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
        return preferences;
    }

    public Preferences delete(Preferences preference) throws SQLException {
        String deletePreference = "DELETE FROM Preferences WHERE PreferenceId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deletePreference);
            deleteStmt.setInt(1, preference.getPreferenceId());
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
