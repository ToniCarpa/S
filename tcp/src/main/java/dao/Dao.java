package dao;

import model.Post;
import model.Usuari;
import utils.Constants;
import utils.File;

import javax.servlet.http.Part;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

public class Dao {
    private Jdbc jdbc;

    public Dao(){
        jdbc = new Jdbc();
    }


    // --------------------------------------------------USUARIO--------------------------------------------------------

    // INSERT USER
    public void insertUsuario(Usuari usuari) throws SQLException {
        jdbc.conect();
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_INSERT_USER)) {
            ps.setString(1, usuari.getUsuari());
            ps.setString(2, usuari.getPassword());
            ps.setString(3, usuari.getEmail());
            ps.setString(4, usuari.getLinkedin());
            ps.setString(5, usuari.getGitlab());
            ps.execute();
        }
        jdbc.conn.commit();
        jdbc.close();
    }

    // DELETE USER
    public void deleteUsuario(Usuari usuario) throws SQLException { //PERFIL
        jdbc.conect();
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_DELETE_USER)) {
            ps.setInt(1, usuario.getId());
            ps.execute();
        }
        jdbc.conn.commit();
        jdbc.close();
    }

    // UPDATE USER
    /*
    public void updateUsuario(Usuari usuario) throws SQLException { //PERFIL
        jdbc.conect();
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_UPDATE_USER)) {
            ps.setString(1, usuario.getUsuari());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getLinkedin());
            ps.setString(5, usuario.getGitlab());
            ps.execute();
        }
        jdbc.conn.commit();
        jdbc.close();
    }
    */

    //USERBYNAME
    public Usuari getUsuarioByName(String name) throws SQLException { // ID HIDDEN
        jdbc.conect();
        Usuari u = null;
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_SELECT_USERBYNAME)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new Usuari();
                    u.setId(rs.getInt("id"));
                    u.setUsuari(rs.getString("usuari"));
                    u.setPassword(rs.getString("password"));
                    u.setEmail(rs.getString("email"));
                    u.setLinkedin(rs.getString("linkedin"));
                    u.setGitlab(rs.getString("gitlad"));
                }
            }
        }
        jdbc.close();
        return u;
    }

    // SELECT USUER BY ID
    public Usuari getUsuarioById(int id) throws SQLException { // ID HIDDEN
        jdbc.conect();
        Usuari u = null;
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_SELECT_USERBYID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new Usuari();
                            u.setId(rs.getInt("id"));
                            u.setUsuari(rs.getString("usuari"));
                            u.setPassword(rs.getString("password"));
                            u.setEmail(rs.getString("email"));
                            u.setLinkedin(rs.getString("linkedin"));
                            u.setGitlab(rs.getString("gitlad"));
                }
            }
        }
        jdbc.close();
        return u;
    }

    // SELECT USUER BY MAIL & PASWD
    public Usuari getUsuarioByMailPass(String mail, String pass) throws SQLException {
        Usuari u = null;
        jdbc.conect();
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_SELECT_USER_BYPASSMAIL)) {
            ps.setString(1, mail);
            ps.setString(2, pass);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    u = new Usuari(rs.getInt("id"),
                            rs.getString("usuari"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("linkedin"),
                            rs.getString("gitlad"));
                }
            }
        }
        jdbc.close();
        return u;
    }

    // SELECT ALLUSERS
    public ArrayList<Usuari> allUsuariosList() throws SQLException {
        ArrayList<Usuari> listUsuarios = new ArrayList<>();
        Usuari u = null;
        jdbc.conect();
        try (PreparedStatement pre = jdbc.conn.prepareStatement(Constants.SQL_SELECT_ALLUSERS)) {
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    listUsuarios.add(new Usuari(rs.getInt("id"),
                            rs.getString("usuari"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("linkedin"),
                            rs.getString("gitlad")));
                }
            }
        }
        jdbc.close();
        return listUsuarios;
    }


    // --------------------------------------------------POSTS----------------------------------------------------------

    // CREATE/INSERT POST
    public void creaPost(Post post, Part part) throws SQLException {
        jdbc.conect();

        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_INSERT_POST)) {
            ps.setInt(1, post.getUsuari().getId());
            ps.setString(2, post.getTitle());
            ps.setBytes(3, File.convertImage(part));
            ps.setString(4, post.getMessage());
            ps.setDate(5, post.getDate());

            ps.execute();
        }
        jdbc.conn.commit();
        jdbc.close();
    }

    // DELETE POST
    public void deletePost(int id) throws SQLException {
        jdbc.conect();
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_DELETE_POST)) {
            ps.setInt(1, id);
            ps.execute();
        }
        jdbc.conn.commit();
        jdbc.close();
    }


    // SELECT ALL POSTS
    public ArrayList<Post> allPostList() throws SQLException {
        jdbc.conect();
        ArrayList<Post> listAllPosts = new ArrayList<>();
        try (PreparedStatement pre = jdbc.conn.prepareStatement(Constants.SQL_SELECT_ALL_POSTS)) {
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    Post post = new Post(rs.getInt("id"),
                            getUsuarioById(rs.getInt("id_usuari")),
                            rs.getString("title"),
                            rs.getString("message"),
                            rs.getInt("likes"),
                            Date.valueOf(rs.getString("dat")));

                    if (rs.getBytes("image") != null) {
                        post.setImage(Base64.getEncoder().encodeToString(rs.getBytes("image")));
                    }
                    listAllPosts.add(post);
                }
            }
        }
        jdbc.close();
        return listAllPosts;
    }

    public ArrayList<Post> allPostListUser(int id) throws SQLException {
        jdbc.conect();
        ArrayList<Post> listAllPosts = new ArrayList<>();
        try (PreparedStatement pre = jdbc.conn.prepareStatement(Constants.SQL_SELECT_USER_POSTS)) {
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    Post post = new Post(rs.getInt("id"),
                            getUsuarioById(rs.getInt("id_usuari")),
                            rs.getString("title"),
                            rs.getString("message"),
                            rs.getInt("likes"),
                            Date.valueOf(rs.getString("dat")));

                    if (rs.getBytes("image") != null) {
                        post.setImage(Base64.getEncoder().encodeToString(rs.getBytes("image")));
                    }
                    listAllPosts.add(post);
                }
            }
        }
        jdbc.close();
        return listAllPosts;
    }


    public void likes(int id, int likes) throws SQLException {
        jdbc.conect();
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.INSERT_LIKE)) {
            ps.setInt(1, likes + 1);
            ps.setInt(2, id);
            ps.execute();
        }
        jdbc.conn.commit();
        jdbc.close();
    }
}