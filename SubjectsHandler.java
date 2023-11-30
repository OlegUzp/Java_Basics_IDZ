/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import java.util.Calendar;
//import java.util.
public class SubjectsHandler extends DataHandler {
    private int choice;
    private Scanner sc = new Scanner(System.in, "windows-1251");
    private ResultSet resultSet;
    public void operations()  throws SQLException, ClassNotFoundException  {
        System.out.print("1 - Вивести предмети\n2 - Перевірити на існування дисципліни\n3 - Додати дисципліну\nВибір:");
        choice = sc.nextInt();
        switch(choice) {
            case 1: {
                //Загальний вивід предметів
                resultSet = getData("select * from subjects;");
                System.out.printf("| %-3s | %-100s | %-10s |\n",
                                     "id", "Назва", "Рівень");
                while(resultSet.next()) {
                    System.out.printf("| %-3s | %-100s | %-10s |\n",
                            resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
                }
                break;
            }
            case 2: {
                sc.nextLine();
                System.out.println("Введіть дисципліну: ");
                String value;
                value = sc.nextLine();
                resultSet = getData("SELECT * from subjects where `name` LIKE '%"+value+"%';");
                System.out.printf("| %-3s | %-100s | %-10s |\n",
                                     "id", "Назва", "Рівень");
                while(resultSet.next()) {
                    System.out.printf("| %-3s | %-100s | %-10s |\n",
                            resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
                }
                break;
            }
            case 3: {
                sc.nextLine();
                System.out.println("Введіть назву дисципліни: ");
                String value;
                value = sc.nextLine();
                resultSet = getData("SELECT `code` from subjects where `name`='%"+value+"%';");
                int count = 0;
                while(resultSet.next()) {count++;}
                if(count!=0) {System.out.println("Ця дисципліна вже додана!");}
                else {
                    String template = "INSERT INTO subjects (`name`,`StudyType_name`) VALUES ('"+value+"',";
                    System.out.println("Введіть `Бакалавр` або `Магістр`: ");
                    value = sc.nextLine();
                    while(value.compareTo("Бакалавр")!=0 && value.compareTo("Магістр")!=0) {
                        System.out.println("Введіть `Бакалавр` або `Магістр`: ");
                        value = sc.nextLine();
                    }
                    template += "'"+value+"');";
//                    System.out.println(template);
                    statement.executeUpdate(template);
                }
                break;
            }
            default: {
                break;
            }
        }
    }
    private ResultSet getData(String request) throws SQLException, ClassNotFoundException  {
        return statement.executeQuery(request);
    }
}
