package recommender.servlet;

import recommender.dal.PreferencesDao;
import recommender.model.Books;
import recommender.model.Preferences;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/createpreferences")
public class CreatePreferences extends HttpServlet {
    protected PreferencesDao preferencesDao;

    @Override
    public void init() {
        preferencesDao = PreferencesDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        req.setAttribute("genres", Books.Genre.getValues());
        req.getRequestDispatcher("/CreatePreferences.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String userName = req.getParameter("username");

        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
            Books.Genre primaryGenre = Books.Genre.parse(req.getParameter("primary"));
            Books.Genre secondaryGenre = Books.Genre.parse(req.getParameter("secondary"));

            try {
                Preferences preferences = new Preferences(userName, primaryGenre, secondaryGenre);
                preferences = preferencesDao.create(preferences);
                messages.put("success", "Successfully created preferences for " + userName);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.setAttribute("genres", Books.Genre.getValues());

        req.getRequestDispatcher("/CreatePreferences.jsp").forward(req, resp);
    }
}
