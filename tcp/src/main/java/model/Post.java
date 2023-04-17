package model;

import java.sql.Date;

public class Post {
    private int id;
    private Usuari usuari;
    private String title;
    private String message;
    private int likes;
    private String image;
    private Date date;

    public Post() {
    }

    public Post(int id, Usuari usuari, String title, String message, int likes, Date date) {
        this.id = id;
        this.usuari = usuari;
        this.title = title;
        this.message = message;
        this.likes = likes;
        this.date = date;
    }

    public Post(Usuari usuari, String title, String message, Date date) {
        this.usuari = usuari;
        this.title = title;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuari getUsuari() {
        return usuari;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public int getLikes() {
        return likes;
    }

    public String getImage() {
        return image;
    }
}
