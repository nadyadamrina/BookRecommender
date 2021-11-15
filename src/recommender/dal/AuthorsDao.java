package recommender.dal;

public class AuthorsDao {
    protected ConnectionManager connectionManager;

    private static AuthorsDao instance = null;
    protected AuthorsDao() {
        connectionManager = new ConnectionManager();
    }
    public static AuthorsDao getInstance() {
        if(instance == null) {
            instance = new AuthorsDao();
        }
        return instance;
    }
}
