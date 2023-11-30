/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class ExceptionsHandler extends DataHandler {
    private int choice;
    private Scanner sc = new Scanner(System.in, "windows-1251");
    private ResultSet resultSet;
    public void operations()  throws SQLException, ClassNotFoundException  {
        System.out.print("1 - Вивести пільги\n2 - Перевірити на існування пільги за кодом\n3 - Додати пільгу\n4 - Додати або видалити студента з пільги\nВибір:");
        choice = sc.nextInt();
        switch(choice) {
            case 1: {
                resultSet = getExceptionsData("select * from exceptions;");
                System.out.printf("| %-3s | %-100s |\n",
                                     "Код", "Опис");
                resultSet.next(); // пропуск нульової порожньої пільги
                while(resultSet.next()) {
                    System.out.printf("| %-3s | %-100s |\n",
                            resultSet.getString(1), resultSet.getString(2));
                }
                break;
            }
            case 2: {
                sc.nextLine();
                String value;
                System.out.print("Введіть код: ");
                value = sc.nextLine();
                resultSet = getExceptionsData("select * from exceptions where `code` LIKE '%"+value+"%';");
                System.out.printf("| %-3s | %-100s |\n",
                                     "Код", "Опис");
                while(resultSet.next()) {
                    if(resultSet.getString(1).compareTo("0")!=0) {
                        System.out.printf("| %-3s | %-100s |\n",
                                resultSet.getString(1), resultSet.getString(2));
                    }
                }
                break;
            }
            case 3: {
                sc.nextLine();
                String value = "";
                String insert_template = "INSERT INTO exceptions (`code`,`definition`) VALUES (";
                String exception_check = "SELECT `code` FROM exceptions WHERE `code`=";
                System.out.print("1.Введіть код: ");
                value = sc.nextLine();
                while(value.isEmpty()) {
                    System.out.print("1.Введіть код: ");
                    value = sc.nextLine();
                }
                exception_check+="'" +value+"';";
                int count = 0;
                resultSet = getExceptionsData(exception_check);
                while(resultSet.next()) {count++;}
                if(count==0){
                    insert_template+="'"+value+"',";
                    System.out.print("2.Введіть опис: ");
                    value = sc.nextLine();
                    while(value.isEmpty()) {
                        System.out.print("1.Введіть опис: ");
                        value = sc.nextLine();
                    }
                    insert_template+="'"+value+"');";
                    statement.executeUpdate(insert_template);
                    break;
                }
                System.out.println("Така пільга існує!");
                break;
            }
            case 4: {
                String template = "select st.id from students as st INNER JOIN people as p ON p.id = st.People_ID WHERE ";
                String alter_template = "UPDATE students SET `Exceptions_code`=";
                String user_id = "",entered_code = "",value = "";
                System.out.print("1.Прізвище:");
                value = sc.next();
                while(value.isEmpty()) {
                    System.out.println("1.Прізвище:");
                    value=sc.next();
                }
                template+= "p.surname = '"+value+"' ";
                System.out.print("2.Ім'я");
                value = sc.next();
                while(value.isEmpty()) {
                    System.out.print("2.Ім'я:");
                    value=sc.next();
                }
                template+= " AND p.name = '"+value+"' ";
                System.out.print("3.По-батькові");
                value = sc.next();
                while(value.isEmpty()) {
                    System.out.print("3.По-батькові:");
                    value=sc.next();
                }
                template+= " AND p.last_name = '"+value+"';";
                resultSet = getExceptionsData(template);
//                int count = 0;
                while (resultSet.next()) {
                    user_id = resultSet.getString(1);
                    System.out.print("4. Введіть код:");
                    value = sc.next();
                    while(value.isEmpty()) {
                        System.out.print("4. Введіть код (видалити пільгу - введіть 0):");
                        value=sc.next();
                    }
                    resultSet = getExceptionsData("SELECT `code` FROM exceptions where `code`='"+value+"';");
                    int count = 0;
                    while(resultSet.next()) {count++;}
                    if(count==0){
                        System.out.println("Коду не існує!");
                        break;
                    }
                    alter_template += "'"+value+"' WHERE `People_ID`='"+user_id+"';";
                    System.out.println(alter_template);
                    statement.executeUpdate(alter_template);
                    System.out.println("Успішно!");
                    break;
                }
                System.out.println("Запис не додано!");
                break;
            }
            default: {
                System.out.println("Невірний вибір!");
                break;
            }
        }
    }
    public ResultSet getExceptionsData(String template) throws SQLException, ClassNotFoundException {
        return statement.executeQuery(template);
    }
}
