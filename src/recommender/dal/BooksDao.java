package recommender.dal;

public class BooksDao {
    protected ConnectionManager connectionManager;

    private static BooksDao instance = null;
    protected BooksDao() {
        connectionManager = new ConnectionManager();
    }
    public static BooksDao getInstance() {
        if(instance == null) {
            instance = new BooksDao();
        }
        return instance;
    }
}
