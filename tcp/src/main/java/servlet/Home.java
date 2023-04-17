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
@WebServlet(name = "home", urlPatterns = {"/home.do", "/home"})
public class Home extends HttpServlet {
    private PostService postService;

    public Home() {
        this.postService = new PostService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (this.postService.checkUser(req)) {
            req.setAttribute("listPost", this.postService.listPosts(req));
            getServletContext().getRequestDispatcher("jsp/home.jsp").forward(req, resp);
            if(req.getParameter("like") != null){
                postService.sumLikes(req);
            }
        } else {
            req.setAttribute("error", "no estas registrado");
            getServletContext().getRequestDispatcher("jsp/register.jsp").forward(req, resp);
        }
    }
}
