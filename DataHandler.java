/*
==========================================================================================================================================================================
    ПРОЕКТ СТУДЕНТА 3 КУРСУ ГРУПИ 6.1211-2пі математичного факультету Запорізького національного університету
    Проект представлено виключно як виконання практичного завданння екзаменаційної сесії з дисципліни "Мова програмування Java" (Горбенко В.І.)
    ПРОЕКТ Є ОСОБИСТОЮ ВЛАСНІСТЮ ТА НЕ МОЖЕ ВИКОРИСТОВУВАТИСЬ ДЛЯ ФІНАНСОВИХ ЦІЛЕЙ.
    Дата останньої зміни 12.12.2023 19:08.
==========================================================================================================================================================================
*/
// Імпорт пакетів
import java.sql.SQLException;
import java.lang.ClassNotFoundException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
//Підключення локальної бази даних на базі Microsoft SQL
public class DataHandler {
    protected static Connection connection;
    protected static Statement statement;
    protected static void GetConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver"); // завантажуємо драйвер MySQL JDBC (Java Database Connectivity)
        System.out.println("Driver downloaded!");
        // Наступним є підключення до бази даних за атрибутами:
        // "jdbc:mysql://localhost:3306/dekanat" - URL бази даних, де localhost - це адреса сервера бази даних, 3306 - порт, на якому працює MySQL, а dekanat - назва конкретної бази даних.
        // "root" - ім'я користувача бази даних (у цьому випадку, ймовірно, користувач з правами адміністратора).
        // "password" - пароль для з'єднання.
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dekanat","root","password");
        System.out.println("Database connected!");
        //Ініціалізація змінної для запитів до бази даних
        statement = connection.createStatement();
    }
}
