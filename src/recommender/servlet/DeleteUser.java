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

@WebServlet("/deleteuser")
public class DeleteUser extends HttpServlet {
    protected UsersDao usersDao;

    @Override
    public void init() {
        usersDao = UsersDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        messages.put("title", "Delete User");
        req.getRequestDispatcher("/DeleteUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("title", "Invalid UserName");
            messages.put("disableSubmit", "true");
        } else {
            // Delete the BlogUser.
            Users user = new Users();
            user.setUserName(userName);
            try {
                user = usersDao.delete(user);
                // Update the message.
                if (user == null) {
                    messages.put("title", "Successfully deleted " + userName);
                    messages.put("disableSubmit", "true");
                } else {
                    messages.put("title", "Failed to delete " + userName);
                    messages.put("disableSubmit", "false");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/DeleteUser.jsp").forward(req, resp);
    }
}
