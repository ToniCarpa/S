package servlet;


import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name = "edit", urlPatterns = {"/edit.do", "/edit"})
public class Edit extends HttpServlet {
    private PostService postService;

    public Edit() {
        super();
        this.postService = new PostService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (postService.checkUser(req)) {
            if (req.getParameter("delete") != null) {
                postService.deletePost(req);
            } else if (req.getParameter("new") != null) {
                postService.newPost(req);
            } else {
                getServletContext().getRequestDispatcher("jsp/user.jsp").forward(req, resp);
            }
        } else {
            getServletContext().getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }


}

