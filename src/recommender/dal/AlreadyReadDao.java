package recommender.dal;

public class AlreadyReadDao {
    protected ConnectionManager connectionManager;

    private static AlreadyReadDao instance = null;
    protected AlreadyReadDao() {
        connectionManager = new ConnectionManager();
    }
    public static AlreadyReadDao getInstance() {
        if(instance == null) {
            instance = new AlreadyReadDao();
        }
        return instance;
    }
}
