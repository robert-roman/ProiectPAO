package ServiceClasses;

import java.sql.SQLException;

public class SingletonService
{
    private static Service Singleton_Service;
    public static Service getSingleton_Service() throws SQLException, ClassNotFoundException {
        if (Singleton_Service == null)
            Singleton_Service = new Service();
        return Singleton_Service;
    }
}
