package recommender.dal;

public class RatingsDao {
    protected ConnectionManager connectionManager;

    private static RatingsDao instance = null;
    protected RatingsDao() {
        connectionManager = new ConnectionManager();
    }
    public static RatingsDao getInstance() {
        if(instance == null) {
            instance = new RatingsDao();
        }
        return instance;
    }
}
