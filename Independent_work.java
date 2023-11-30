/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author 29ole
 */
import java.sql.SQLException;
import java.lang.ClassNotFoundException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
public class Independent_work extends DataHandler {
    private StudentsHandler student_data = new StudentsHandler();
    private SubjectsHandler subject_data = new SubjectsHandler();
    private HostelsHandler hostel_data = new HostelsHandler();
    private SpecialtiesHandler specialty_data = new SpecialtiesHandler();
    private ExceptionsHandler exception_data = new ExceptionsHandler();
    private DeductionsHandler deduction_data = new DeductionsHandler();
    private MarksHandler mark_data = new MarksHandler();
    private ResultSet resultSet;
    private void processApplication() throws SQLException, ClassNotFoundException {
        int choice;
        System.out.println("Додаток << Персональні справи студентів >> ");
        Scanner sc = new Scanner(System.in);
        System.out.print("1 - Студенти\n2 - Предмети\n3 - Гуртожитки\n4 - Спеціальності\n5 - Пільги\n6 - Відрахування\n7 - Відомості\nВиберіть:");
        choice = sc.nextInt();
        switch(choice) {
            case 1: {
                //Загальна студенти
                student_data.operations();
                break;
            }
            case 2: {
                //Загальна предмети
                subject_data.operations();
                break; 
            }
            case 3: {
                //Загальне гуртожитки
                hostel_data.operations();
                break;
            }
            case 4: {
                //Загальне спеціальності
                specialty_data.operations();
                break;
            }
            case 5: {
                //Загальне пільги
                exception_data.operations();
                break;
            }
            case 6: {
                //Загальне відрахування
                deduction_data.operations();
            }
            case 7: {
                //Відомості та рейтинги
                mark_data.operations();
            }
        }
    }
    public static void main(String[] args) throws SQLException,ClassNotFoundException {
       System.setProperty("console.encoding", "UTF-8");
       System.out.println(System.getProperty("console.encoding"));
       DataHandler.GetConnection();
       Independent_work idz = new Independent_work();
       idz.processApplication();
    }
}
