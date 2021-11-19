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

@WebServlet("/updateuser")
public class UpdateUser extends HttpServlet {
    protected UsersDao usersDao;

    @Override
    public void init() {
        usersDao = UsersDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
            try {
                Users user = usersDao.getUserFromUserName(userName);
                if(user == null) {
                    messages.put("success", "UserName does not exist.");
                }
                req.setAttribute("user", user);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/UpdateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
            try {
                Users user = usersDao.getUserFromUserName(userName);
                if(user == null) {
                    messages.put("success", "UserName does not exist. No update to perform.");
                } else {
                    String newFirstName = req.getParameter("firstname");
                    String newLastName = req.getParameter("lastname");
                    if (newLastName == null || newLastName.trim().isEmpty()) {
                        messages.put("success", "Please enter a valid LastName.");
                    } else {
                        user = usersDao.updateName(user, newFirstName, newLastName);
                        messages.put("success", "Successfully updated " + userName);
                    }
                }
                req.setAttribute("user", user);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/UpdateUser.jsp").forward(req, resp);
    }
}
