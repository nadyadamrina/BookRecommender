package recommender.dal;

import recommender.model.Checkouts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CheckoutsDao {
    protected ConnectionManager connectionManager;

    private static CheckoutsDao instance = null;
    protected CheckoutsDao() {
        connectionManager = new ConnectionManager();
    }
    public static CheckoutsDao getInstance() {
        if(instance == null) {
            instance = new CheckoutsDao();
        }
        return instance;
    }

    public Checkouts create(Checkouts checkout) throws SQLException {
        String insertCheckout =
                "INSERT INTO Checkouts(ISBN,CheckOutDate) " +
                        "VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCheckout,
                    Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, checkout.getIsbn());
            insertStmt.setTimestamp(2, new Timestamp(checkout.getCheckoutDate().getTime()));
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int checkoutId = -1;
            if(resultKey.next()) {
                checkoutId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            checkout.setCheckoutId(checkoutId);
            return checkout;
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

    public Checkouts getCheckoutByCheckoutId(int checkoutId) throws SQLException {
        String selectCheckoutId =
                "SELECT * " +
                        "FROM Checkouts " +
                        "WHERE CheckoutId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCheckoutId);
            selectStmt.setInt(1, checkoutId);
            results = selectStmt.executeQuery();

            if(results.next()) {
                int resultCheckoutId = results.getInt("CheckoutId");
                String ISBN = results.getString("ISBN");
                Date checkoutDate = new Date(results.getTimestamp("CheckOutDate").getTime());

                Checkouts checkout = new Checkouts(resultCheckoutId, ISBN, checkoutDate);
                return checkout;
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

    public List<Checkouts> getCheckoutsByISBN(String ISBN) throws SQLException {
        List<Checkouts> checkouts = new ArrayList<Checkouts>();
        String selectCheckouts =
                "SELECT * " +
                        "FROM Checkouts " +
                        "WHERE ISBN=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCheckouts);
            selectStmt.setString(1, ISBN);
            results = selectStmt.executeQuery();

            while(results.next()) {
                int checkoutId = results.getInt("CheckoutId");
                String resultISBN = results.getString("ISBN");
                Date checkoutDate = new Date(results.getTimestamp("CheckOutDate").getTime());

                Checkouts checkout = new Checkouts(checkoutId, resultISBN, checkoutDate);
                checkouts.add(checkout);
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
        return checkouts;
    }

    public List<Checkouts> getTop5CheckoutsByGenre(String genre) throws SQLException {
        List<Checkouts> checkouts = new ArrayList<Checkouts>();
        String selectGenre =
                "SELECT Books.Title, Books.Genre, COUNT(*) AS CheckoutCount" +
                        "FROM Checkouts INNER JOIN Books ON " +
                        "Checkouts.ISBN = Books.ISBN" +
                        "WHERE Books.Genre =?" +
                        "GROUP BY Checkouts.ISBN" +
                        "ORDER BY CheckoutCount DESC" +
                        "LIMIT 5;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectGenre);
            selectStmt.setString(1, genre);
            results = selectStmt.executeQuery();

            while(results.next()) {
                int checkoutId = results.getInt("CheckoutId");
                String ISBN = results.getString("ISBN");
                Date checkoutDate = new Date(results.getTimestamp("CheckOutDate").getTime());

                Checkouts checkout = new Checkouts(checkoutId, ISBN, checkoutDate);
                checkouts.add(checkout);
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
        return checkouts;
    }

    public Checkouts delete(Checkouts checkout) throws SQLException {
        String deleteCheckout = "DELETE FROM Checkouts WHERE CheckoutId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteCheckout);
            deleteStmt.setInt(1, checkout.getCheckoutId());
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
