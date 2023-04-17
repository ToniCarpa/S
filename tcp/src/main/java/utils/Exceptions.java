package utils;

import java.util.Arrays;
import java.util.List;

public class Exceptions extends Exception{
    public static final int ERROR_BD_CONNECT = 0;
    public static final int ERROR_BD_LOAD = 1;
    public static final int ERROR_BD_DATA = 2;
    public static final int ERROR_03 = 3;
    public static final int ERROR_04 = 4;

    private int value;

    public Exceptions(int value){
        this.value = value;
    }

    private List<String> msg = Arrays.asList(
            "ERROR EN CONEXION DE BASE DE DATOS"
    );
    public  String getMsg(){
        return msg.get(value);
    }
}
