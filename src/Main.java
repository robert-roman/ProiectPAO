import ServiceClasses.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Service s1= SingletonService.getSingleton_Service();
}
