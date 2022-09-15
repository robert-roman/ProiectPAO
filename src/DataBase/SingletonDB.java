package DataBase;


import java.sql.SQLException;

public class SingletonDB
{
    private static DataBaseConnect_CRUD Singleton_DataBase;
    public static DataBaseConnect_CRUD getSingleton_DataBase() throws SQLException, ClassNotFoundException {
        if (Singleton_DataBase == null)
            Singleton_DataBase = new DataBaseConnect_CRUD();
        return Singleton_DataBase;
    }
}
