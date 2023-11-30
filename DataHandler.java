
import java.sql.SQLException;
import java.lang.ClassNotFoundException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
public class DataHandler {
    protected static Connection connection;
    protected static Statement statement;
    protected static void GetConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver downloaded!");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dekanat","root","password");
        System.out.println("Database connected!");
        statement = connection.createStatement();
    }
}
