package utils;

public class Constants {
    public static final String DB_USER_CONNECTION = "root";
    public static final String DB_PASS_CONNECTION = "root";
    public static final String DB_DRIVER_CONNECTION = "com.mysql.cj.jdbc.Driver";
    public static final String SCHEMA_NAME = "m06uf4servlets";
    public static final String DB_URL_CONNECTION = "jdbc:mysql://localhost:3306/" + SCHEMA_NAME + "?serverTimezone=UTC";

    // POSTS
    public static final String SQL_INSERT_POST =
             "INSERT into POST (id_usuari, title, message, image, likes, dat) values (?,?,?,?,0,now())";
    public static final String SQL_DELETE_POST =
            "DELETE FROM POST WHERE id = ?;";
    public static final String SQL_SELECT_ALL_POSTS =
            "SELECT * FROM POST ORDER BY date DESC;";


    // USUARIO
    public static final String SQL_INSERT_USER =
            "INSERT INTO USUARI (name, password, email, linkdin, gitlab) VALUES (?,?,?,?,?)";
    public static final String SQL_DELETE_USER =
            "DELETE USUARI WHERE id =?;";
    public static final String SQL_SELECT_USERBYID =
            "SELECT * FROM usuari WHERE id=?;";
    public static final String SQL_SELECT_USERBYNAME =
            "SELECT * FROM usuari WHERE usuari=?;";
    public static final String SQL_SELECT_USER_BYPASSMAIL =
            "SELECT password, email FROM usuari WHERE id = ?;";
    public static final String SQL_SELECT_ALLUSERS =
            "SELECT * FROM usuari;";
    public static final String SQL_SELECT_USER_POSTS =
            "SELECT posts FROM usuari WHERE id = ?;";
    public static final String INSERT_LIKE =
            "UPDATE post SET likes =? WHERE id =?;";

}