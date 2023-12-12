/*
==========================================================================================================================================================================
    ПРОЕКТ СТУДЕНТА 3 КУРСУ ГРУПИ 6.1211-2пі математичного факультету Запорізького національного університету
    Проект представлено виключно як виконання практичного завданння екзаменаційної сесії з дисципліни "Мова програмування Java" (Горбенко В.І.)
    ПРОЕКТ Є ОСОБИСТОЮ ВЛАСНІСТЮ ТА НЕ МОЖЕ ВИКОРИСТОВУВАТИСЬ ДЛЯ ФІНАНСОВИХ ЦІЛЕЙ.
    Дата останньої зміни 12.12.2023 18:49.
==========================================================================================================================================================================
*/
// Імпорт пакетів
import java.sql.SQLException;
import java.lang.ClassNotFoundException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
// Клас для представлення першого рівня навігації функцій.
public class Independent_work extends DataHandler {
    //Оголошення екземплярів
    private StudentsHandler student_data = new StudentsHandler();
    private SubjectsHandler subject_data = new SubjectsHandler();
    private HostelsHandler hostel_data = new HostelsHandler();
    private SpecialtiesHandler specialty_data = new SpecialtiesHandler();
    private ExceptionsHandler exception_data = new ExceptionsHandler();
    private DeductionsHandler deduction_data = new DeductionsHandler();
    private MarksHandler mark_data = new MarksHandler();
    private ResultSet resultSet;
    //Запит на обрання функції
    private void processApplication() throws SQLException, ClassNotFoundException {
        int choice;
        System.out.println("Додаток << Персональні справи студентів >> ");
        Scanner sc = new Scanner(System.in);
        System.out.print("1 - Студенти\n2 - Предмети\n3 - Гуртожитки\n4 - Спеціальності\n5 - Пільги\n6 - Відрахування\n7 - Відомості\nВиберіть:");
        choice = sc.nextInt();
        switch(choice) {
            case 1: {
                //Студенти
                student_data.operations();
                break;
            }
            case 2: {
                //Предмети
                subject_data.operations();
                break; 
            }
            case 3: {
                //Гуртожитки
                hostel_data.operations();
                break;
            }
            case 4: {
                //Спеціальності
                specialty_data.operations();
                break;
            }
            case 5: {
                //Пільги
                exception_data.operations();
                break;
            }
            case 6: {
                //Відрахування
                deduction_data.operations();
            }
            case 7: {
                //Відомості та рейтинги
                mark_data.operations();
            }
        }
    }
    public static void main(String[] args) throws SQLException,ClassNotFoundException {
        //Встановлення кодування консолі. Підключення бази данних SQL
       System.setProperty("console.encoding", "UTF-8");
       System.out.println(System.getProperty("console.encoding"));
       DataHandler.GetConnection();
       Independent_work idz = new Independent_work();
       idz.processApplication();
    }
}
