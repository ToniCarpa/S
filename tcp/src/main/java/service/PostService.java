package service;

import dao.Dao;
import model.Post;
import model.Usuari;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostService {
    private Dao dao;
    private static PostService postService;

    public PostService() {
        dao = new Dao();
    }

    public static PostService getInstance() {
        if (postService == null) {
            postService = new PostService();
        }
        return postService;
    }

    // --------------------------------------------------LOGIN-------------------------------------------------------

    public boolean checkUser(HttpServletRequest request) {
        String password = new String(request.getParameter("pass").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String email = new String(request.getParameter("mail").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        try {
            Usuari u = existUser(email, password);
            request.getSession().setAttribute("id", u.getId());
            request.setAttribute("posts", dao.allPostList());
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuari existUser(String mail, String pass) {
        try {
            if (dao.getUsuarioByMailPass(mail, pass) == null) throw new SQLException();
            return dao.getUsuarioByMailPass(mail, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // --------------------------------------------------REGSITER-------------------------------------------------------

    public boolean newUser(HttpServletRequest request){
        HttpSession respuesta = request.getSession(true);
        try {
            // int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String password = request.getParameter("pass");
            String email = request.getParameter("mail");
            String linkdn = request.getParameter("link");
            String gitlab = request.getParameter("git");

            Usuari t = this.dao.getUsuarioByName(name);

            if (t != null) {
                respuesta.setAttribute("El nombre ya està cogido", respuesta);
                throw new SQLException();
            }

            Usuari u = new Usuari(name, password, email, linkdn, gitlab);
            // request.getSession().setAttribute("id", u.getId());

            dao.insertUsuario(u);

        } catch (NumberFormatException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return true;
    }

    // --------------------------------------------------HOME-----------------------------------------------------------

    // ALL POSTS
    public ArrayList<Post> listPosts(HttpServletRequest request) {
        try {
            ArrayList<Post> listPost = dao.allPostList();
            request.setAttribute("listPost", listPost);
            return listPost;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // LIKES
    public boolean sumLikes(HttpServletRequest request) {
        try {
            int id = (int) request.getSession().getAttribute("id");
            request.setAttribute("id", id);
            request.setAttribute("posts", dao.allPostList());
            ArrayList<Post> postlist = this.dao.allPostList();
            for (Post o : postlist) {
                dao.likes(o.getId(), o.getLikes());
                }
            return true;
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    // COMMENTS

    // --------------------------------------------------PROFILE--------------------------------------------------------

    // USER POSTLIST
    public ArrayList<Post> listUserPost(HttpServletRequest request) {
        try {
            int id = (int) request.getAttribute("id");

            ArrayList<Post> listUserPost = this.dao.allPostListUser(id);
            request.setAttribute("listUserPost", listUserPost);
            return listUserPost;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // --------------------------------------------------EDIT-----------------------------------------------------------

    //NEW POST
    public ArrayList<Post> newPost(HttpServletRequest request) {
        String title = new String(request.getParameter("titleP").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String message = new String(request.getParameter("messageP").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Part part = request.getPart("");
            dao.creaPost(new Post(dao.getUsuarioById(id), title, message, new Date(System.currentTimeMillis())), part);

            request.getSession().setAttribute("id", id);
            ArrayList<Post> listPost = this.dao.allPostList();
            request.setAttribute("listPost", listPost);
            return listPost;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    // DELETE POST
    public boolean deletePost(HttpServletRequest request) {
        HttpSession respuesta = request.getSession(true);
        try {
            int id = (int) request.getSession().getAttribute("id");
            request.getSession().setAttribute("id", id);
            request.setAttribute("posts", dao.allPostListUser(id));

            ArrayList<Post> postUserList = this.dao.allPostListUser(id);
            for (Post post : postUserList) {
                this.dao.deletePost(post.getId());
            }
            respuesta.setAttribute("Post Borrado", respuesta);
            return true;

        } catch (SQLException e) {
            respuesta.setAttribute("ERROR no BORRADO", respuesta);
            throw new RuntimeException(e);
        }
    }

    // EDIT POST

}
