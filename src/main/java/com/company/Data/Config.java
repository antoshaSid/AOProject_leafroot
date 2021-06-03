package com.company.Data;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

//Набор данных для программы
public final class Config {

    public static final String SERVER_URL = "http://dsde1032-23219.fornex.org/";
    public static final String SERVER_REGISTRATION_URL = "http://dsde1032-23219.fornex.org/create_user.php";
    public static final String SERVER_GET_USER = "http://dsde1032-23219.fornex.org/get_user.php";
    public static final Path CACHE_PATH = Paths.get("/Users/antonsidlyar/Desktop/AOProject_leafroot");
    public static final Path CHAT_CACHE_PATH = Paths.get("/Users/antonsidlyar/Desktop/AOProject_leafroot/Cache/ChatsData");
    public static final Path SESSION_PATH = Paths.get("/Users/antonsidlyar/Desktop/AOProject_leafroot/session");
    public static final String SALT = "67452301EFCDAB89";
    public static final String PRIVATE_KEY_PATH = "private.key";
    public static final String SERVER_IP = "91.228.154.50";
    public static final String SERVER_PORT = "2346";
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
}
