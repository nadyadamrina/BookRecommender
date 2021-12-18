package recommender.servlet;

import recommender.dal.PreferencesDao;
import recommender.model.Preferences;

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

@WebServlet("/findpreferences")
public class FindPreferences extends HttpServlet {
    protected PreferencesDao preferencesDao;

    @Override
    public void init() {
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
            messages.put("title", "Preferences for " + userName);
        }

        List<Preferences> preferences = new ArrayList<>();
        try {
            preferences = preferencesDao.getPreferencesByUserName(userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("preferences", preferences);

        req.getRequestDispatcher("/FindPreferences.jsp").forward(req, resp);
    }

}