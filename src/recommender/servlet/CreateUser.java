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

@WebServlet("/createuser")
public class CreateUser extends HttpServlet {
    protected UsersDao usersDao;

    @Override
    public void init() {
        usersDao = UsersDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        req.getRequestDispatcher("/CreateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
            String password = req.getParameter("password");
            String firstName = req.getParameter("firstname");
            String lastName = req.getParameter("lastname");
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");

            try {
                Users user = new Users(userName, password, firstName, lastName, email, phone);
                user = usersDao.create(user);
                messages.put("success", "Successfully created " + userName);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/CreateUser.jsp").forward(req, resp);
    }
}
