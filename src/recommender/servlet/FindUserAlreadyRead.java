package recommender.servlet;

import recommender.dal.AlreadyReadDao;
import recommender.model.AlreadyRead;
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

@WebServlet("/alreadyread")
public class FindUserAlreadyRead extends HttpServlet {
    protected AlreadyReadDao alreadyReadDao;

    @Override
    public void init() throws ServletException {
        alreadyReadDao = AlreadyReadDao.getInstance();
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

        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("title", "Invalid username.");
        } else {
            messages.put("title", "BlogPosts for " + userName);
        }

        List<AlreadyRead> alreadyReads;
        try {
            alreadyReads = alreadyReadDao.getAlreadyReadsByUserName(userName);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        req.setAttribute("alreadyread", alreadyReads);
        req.getRequestDispatcher("/AlreadyRead.jsp").forward(req, resp);
    }
}
