package model;

public class Usuari {
    private int id;
    private String usuari;
    private String password;
    private String email;
    private String linkedin;
    private String gitlab;

    public Usuari() {
    }

    public Usuari(int id, String usuari, String password, String email, String linkedin, String gitlab) {
        this.id = id;
        this.usuari = usuari;
        this.password = password;
        this.email = email;
        this.linkedin = linkedin;
        this.gitlab = gitlab;
    }

    public Usuari(String usuari, String password, String email, String linkedin, String gitlab) {
        this.usuari = usuari;
        this.password = password;
        this.email = email;
        this.linkedin = linkedin;
        this.gitlab = gitlab;
    }

    public int getId() {
        return id;
    }
    public String getUsuari() {
        return usuari;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getLinkedin() {
        return linkedin;
    }
    public String getGitlab() {
        return gitlab;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public void setGitlab(String gitlab) {
        this.gitlab = gitlab;
    }
}
