package recommender.dal;

public class RecommendationsDao {
    protected ConnectionManager connectionManager;

    private static RecommendationsDao instance = null;
    protected RecommendationsDao() {
        connectionManager = new ConnectionManager();
    }
    public static RecommendationsDao getInstance() {
        if(instance == null) {
            instance = new RecommendationsDao();
        }
        return instance;
    }
}
