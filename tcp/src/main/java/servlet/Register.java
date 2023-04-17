package servlet;

import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "register", urlPatterns = {"/register.do","/register"})
public class Register extends HttpServlet {
    private PostService postService;

    public Register()  {
        super();
        this.postService = new PostService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (postService.checkUser(request)) {
            postService.newUser(request);
            getServletContext().getRequestDispatcher("jsp/home.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);        }
    }
}