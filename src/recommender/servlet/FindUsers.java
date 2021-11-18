package recommender.servlet;

import recommender.dal.UsersDao;
import recommender.model.Users;

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

@WebServlet("/findusers")
public class FindUsers extends HttpServlet {
    protected UsersDao usersDao;

    @Override
    public void init() {
        usersDao = UsersDao.getInstance();
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
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Users> usersList = new ArrayList<>();
        String lastName = req.getParameter("lastname");
        if (lastName == null || lastName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
            try {
                usersList = usersDao.getUsersFromLastName(lastName);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for " + lastName);

            messages.put("previousLastName", lastName);
        }
        req.setAttribute("users", usersList);

        req.getRequestDispatcher("/FindUsers.jsp").forward(req, resp);
    }
}
