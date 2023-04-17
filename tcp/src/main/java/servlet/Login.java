package servlet;

import dao.Dao;
import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "login", urlPatterns = {"/login.do", "/login"})
public class Login extends HttpServlet {
    private PostService postService;

    public Login()  {
        super();
        this.postService = new PostService();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (this.postService.checkUser(req)) {
            getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Usuario o contraseña incorrectos");
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    }



