/*
==========================================================================================================================================================================
    ПРОЕКТ СТУДЕНТА 3 КУРСУ ГРУПИ 6.1211-2пі математичного факультету Запорізького національного університету
    Проект представлено виключно як виконання практичного завданння екзаменаційної сесії з дисципліни "Мова програмування Java" (Горбенко В.І.)
    ПРОЕКТ Є ОСОБИСТОЮ ВЛАСНІСТЮ ТА НЕ МОЖЕ ВИКОРИСТОВУВАТИСЬ ДЛЯ ФІНАНСОВИХ ЦІЛЕЙ.
    Дата останньої зміни 12.12.2023 20:01.
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
import java.util.Calendar;
//Клас роботи з даними спеціальностей
public class SpecialtiesHandler extends DataHandler {
    private int choice;
    private Scanner sc = new Scanner(System.in, "windows-1251");
    private ResultSet resultSet;
    public void operations()  throws SQLException, ClassNotFoundException  {
        System.out.print("1 - Вивести спеціальності\n2 - Перевірити на існування спеціальності\n3 - Додати спеціальність\nВибір:");
        choice = sc.nextInt();
        switch(choice) {
            case 1: {
                //Загальний вивід спеціальностей
                resultSet = getData("select * from specialties;");
                System.out.printf("| %-3s | %-100s | %-10s |\n",
                                     "Код", "Назва", "Рівень");
                while(resultSet.next()) {
                    System.out.printf("| %-3s | %-100s | %-10s |\n",
                            resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
                }
                break;
            }
            case 2: {
                //Перевірка існування за опціональними параметрами
                sc.nextLine();
                String value,template = "SELECT * from specialties ";
                System.out.println("1.Введіть код: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT * from specialties ")==0){
                        template += "WHERE `code`='"+value+"' ";
                    }
                    else {
                        template += "AND `code`='"+value+"' ";
                    }
                }
                System.out.println("2.Введіть назву спеціальності: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT * from specialties ")==0){
                        template += "WHERE `name` LIKE '%"+value+"%' ";
                    }
                    else {
                        template += "AND `name` LIKE '%"+value+"%' ";
                    }
                }
                System.out.println("3.Введіть рівень навчання: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT * from specialties ")==0){
                        template += "WHERE `StudyTypes_name`='"+value+"' ";
                    }
                    else {
                        template += "AND `StudyTypes_name`='"+value+"' ";
                    }
                }
                template+=";";
                System.out.println(template);
                resultSet = getData(template);
                System.out.printf("| %-3s | %-100s | %-10s |\n",
                                     "id", "Назва", "Рівень");
                while(resultSet.next()) {
                    System.out.printf("| %-3s | %-100s | %-10s |\n",
                            resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
                }
                break;
            }
            case 3: {
                //Додавання спеціальності і перевірка на попереднє існування
                sc.nextLine();
                String value;
                String specialty_check = "SELECT `code` from specialties where ";
                String create_template = "INSERT INTO specialties (`code`,`name`,`StudyTypes_name`) VALUES (";
                System.out.println("1.Введіть код спеціальності: ");
                value=sc.nextLine();
                while(value.isEmpty()){
                    System.out.println("1.Введіть код спеціальності: ");
                    value=sc.nextLine();
                }
                specialty_check += "`code`='"+value+"' ";
                create_template += "'"+value+"',";
                System.out.println("2.Введіть назву спеціальності: ");
                value=sc.nextLine();
                while(value.isEmpty()){
                    System.out.println("2.Введіть код спеціальності: ");
                    value=sc.nextLine();
                }
                specialty_check += " AND `name`='"+value+"';";
                create_template += "'"+value+"',";
                System.out.println("3.Введіть рівень (Бакалавр або Магістр): ");
                value = sc.nextLine();

                while(value.isEmpty() || (!value.equals("Бакалавр") && !value.equals("Магістр"))) {
                    System.out.println("3.Введіть рівень (Бакалавр або Магістр): ");
                    value = sc.nextLine();
                }

                create_template += "'"+value+"');";
                resultSet = getData(specialty_check);
                int count = 0;
                while(resultSet.next()){count++;}
                if(count==0) {
                    statement.executeUpdate(create_template);
                    System.out.println("Успішно!");
                }
                else {
                    System.out.println("Така дисципліна вже існує!");
                }
                break;
            }
            default: {
                System.out.println("Невірний вибір!");
                break;
            }
        }
    }
    //Функція отримання результату запитів типу SELECT з бази даних
    private ResultSet getData(String request) throws SQLException, ClassNotFoundException {
        return statement.executeQuery(request);
    }
}
