package recommender.dal;

public class HistoryDao {
    protected ConnectionManager connectionManager;

    private static HistoryDao instance = null;
    protected HistoryDao() {
        connectionManager = new ConnectionManager();
    }
    public static HistoryDao getInstance() {
        if(instance == null) {
            instance = new HistoryDao();
        }
        return instance;
    }
}
