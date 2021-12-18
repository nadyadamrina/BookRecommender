package recommender.servlet;

import recommender.dal.AuthorsDao;
import recommender.dal.BooksDao;
import recommender.dal.PreferencesDao;
import recommender.dal.RecommendationsDao;
import recommender.model.Authors;
import recommender.model.Books;
import recommender.model.Preferences;
import recommender.model.Recommendations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/recommendation")
public class FindRecommendations extends HttpServlet {
    protected RecommendationsDao recommendationsDao;
    protected BooksDao booksDao;
    protected AuthorsDao authorsDao;
    protected PreferencesDao preferencesDao;

    @Override
    public void init() {
        recommendationsDao = RecommendationsDao.getInstance();
        booksDao = BooksDao.getInstance();
        authorsDao = AuthorsDao.getInstance();
        preferencesDao = PreferencesDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        executeRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        executeRequest(req, resp);
    }

    private void executeRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("title", "Invalid username.");
        } else {
            messages.put("title", "Recommendations for " + userName);
        }
        List<Preferences> preferences = new ArrayList<>();
        List<Recommendations> allRecommendations = new ArrayList<>();
        List<Books> books = new ArrayList<>();
        List<Authors> authors = new ArrayList<>();
        try {
            preferences = preferencesDao.getPreferencesByUserName(userName);
//            recommendations = recommendationsDao.getRecommendationsByGenre(userName);
            for (Preferences preference : preferences) {
                String pSearch = preference.toString();
                List<Recommendations> recommendations = new ArrayList<>();
                recommendations = recommendationsDao.getRecommendationsByGenre(pSearch);
                for (Recommendations recommendation : recommendations) {
                    allRecommendations.add(recommendation);
                    Books book = booksDao.getBookByISBN(recommendation.getIsbn());
                    books.add(book);
                    authors.add(authorsDao.getAuthorById(book.getAuthorId()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        req.setAttribute("recommendations", allRecommendations);
        req.setAttribute("books", books);
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("/FindRecommendations.jsp").forward(req, resp);
    }

}
