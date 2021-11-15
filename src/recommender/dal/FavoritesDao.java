package recommender.dal;

public class FavoritesDao {
    protected ConnectionManager connectionManager;

    private static FavoritesDao instance = null;
    protected FavoritesDao() {
        connectionManager = new ConnectionManager();
    }
    public static FavoritesDao getInstance() {
        if(instance == null) {
            instance = new FavoritesDao();
        }
        return instance;
    }
}
