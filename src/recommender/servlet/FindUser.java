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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/finduser")
public class FindUser extends HttpServlet {
    protected UsersDao usersDao;

    @Override
    public void init() {
        usersDao = UsersDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sendRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sendRequest(req, resp);
    }

    private void sendRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        Users user = new Users();

        String username = req.getParameter("username");
        if (username == null || username.trim().isEmpty()) {
            messages.put("success", "Please enter a valid username.");
        } else {

            try {
                user = usersDao.getUserFromUserName(username);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for " + username);

            messages.put("previousUserName", username);
        }
        req.setAttribute("user", user);

        req.getRequestDispatcher("/FindUser.jsp").forward(req, resp);
    }

}
