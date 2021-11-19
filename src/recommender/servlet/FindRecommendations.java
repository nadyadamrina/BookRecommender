package recommender.servlet;

import recommender.dal.AuthorsDao;
import recommender.dal.BooksDao;
import recommender.dal.RecommendationsDao;
import recommender.model.Authors;
import recommender.model.Books;
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

    @Override
    public void init() {
        recommendationsDao = RecommendationsDao.getInstance();
        booksDao = BooksDao.getInstance();
        authorsDao = AuthorsDao.getInstance();
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
            messages.put("title", "BlogPosts for " + userName);
        }

        List<Recommendations> recommendations = new ArrayList<>();
        List<Books> books = new ArrayList<>();
        List<Authors> authors = new ArrayList<>();
        try {
            recommendations = recommendationsDao.getRecommendationsByUserName(userName);
            for (Recommendations recommendation : recommendations) {
                Books book = booksDao.getBookByISBN(recommendation.getIsbn());
                books.add(book);
                authors.add(authorsDao.getAuthorById(book.getAuthorId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        req.setAttribute("recommendations", recommendations);
        req.setAttribute("books", books);
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("/FindRecommendations.jsp").forward(req, resp);
    }

}
