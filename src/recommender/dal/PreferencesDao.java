package recommender.dal;

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
}
