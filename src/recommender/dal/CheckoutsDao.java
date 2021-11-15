package recommender.dal;

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
}
