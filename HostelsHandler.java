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
public class HostelsHandler extends DataHandler {
    private int choice;
    private Scanner sc = new Scanner(System.in, "windows-1251");
    private ResultSet resultSet;
    public void operations()  throws SQLException, ClassNotFoundException  {
        System.out.print("1 - Вивести гуртожитки\n2 - Перевірити на існування гуртожитки за параметрами\n3 - Додати гуртожиток\n4 - Вивести студентів,які очікують поселення, або вже поселені в певний гуртожиток\nВибір:");
        choice = sc.nextInt();
        switch(choice) {
            case 1: {
                resultSet = getResponse("SELECT * from hostels where `Number`!='0';");
                System.out.printf("| %-3s | %-100s | %-10s | %-10s |\n",
                                     "Номер", "Адреса", "Телефон","Пошта");
                while(resultSet.next()) {
                    System.out.printf("| %-3s | %-100s | %-10s | %-10s |\n",
                            resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4));
                }
                break;
            }
            case 2: {
                String value,template="SELECT * from hostels ";
                sc.nextLine();
                System.out.println("1. Введіть номер гуртожитку (пропустити - Enter): ");
                value=sc.nextLine();
                if(value.compareTo("")!=0){
                    if(template.compareTo("SELECT * from hostels ")==0) {
                        template += " WHERE `Number`='"+value+"' ";
                    }
                    else {
                        template += " AND `Number`='"+value+"' ";
                    }
                }
                System.out.println("2. Введіть адресу (пропустити - Enter): ");
                value=sc.nextLine();
                if(value.compareTo("")!=0){
                    if(template.compareTo("SELECT * from hostels ")==0) {
                        template += " WHERE `Address`='"+value+"' ";
                    }
                    else {
                        template += " AND `Address`='"+value+"' ";
                    }
                }
                System.out.println("3. Введіть телефон (пропустити - Enter): ");
                value=sc.nextLine();
                if(value.compareTo("")!=0){
                    if(template.compareTo("SELECT * from hostels ")==0) {
                        template += " WHERE `Phone_number`='"+value+"' ";
                    }
                    else {
                        template += " AND `Phone_number`='"+value+"' ";
                    }
                }
                System.out.println("4. Введіть пошту (пропустити - Enter): ");
                value=sc.nextLine();
                if(value.compareTo("")!=0){
                    if(template.compareTo("SELECT * from hostels ")==0) {
                        template += " WHERE `email`='"+value+"' ";
                    }
                    else {
                        template += " AND `email`='"+value+"' ";
                    }
                }
                template += ";";
                resultSet = getResponse(template);
                System.out.printf("| %-3s | %-100s | %-10s | %-10s |\n",
                                     "Номер", "Адреса", "Телефон","Пошта");
                while(resultSet.next()) {
                    System.out.printf("| %-3s | %-100s | %-10s | %-10s |\n",
                            resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4));
                }
                break;
            }
            case 3: {
                sc.nextLine();
                String template_request="";
                String check_request ="";
                String input_value = "";
                while(input_value.compareTo("")==0) {
                    template_request = "INSERT INTO hostels (`Number`,`Address`,`Phone_number`,`email`) VALUES (";
                    check_request = "SELECT `Number` from hostels where ";
                    System.out.println("1. Введіть номер гуртожитку: ");
                    input_value = sc.nextLine();
                    while(input_value.length()!=1) {
                        System.out.println("1. Введіть номер гуртожитку: ");
                        input_value = sc.nextLine();
                    }
                    template_request+="'"+input_value+"',";
                    check_request+= "`Number`='"+input_value+"' ";
                    System.out.println("2. Введіть адресу: ");
                    input_value = sc.nextLine();
                    while(input_value.isEmpty()) {
                        System.out.println("2. Введіть адресу: ");
                        input_value = sc.nextLine();
                    }
                    template_request+="'"+input_value+"',";
                    check_request+= "OR `Address`='"+input_value+"';";
                    resultSet = getResponse(check_request);
                    int count = 0;
                    while(resultSet.next()) {count++;}
                    input_value="";
                    if(count==0) {break;}
                }
                System.out.println("3. Введіть телефон: ");
                input_value = sc.nextLine();
                while(input_value.isEmpty()) {
                    System.out.println("3. Введіть Телефон: ");
                    input_value = sc.nextLine();
                }
                template_request+="'"+input_value+"',";
                System.out.println("4. Введіть пошту: ");
                input_value = sc.nextLine();
                while(input_value.isEmpty()) {
                    System.out.println("4. Введіть пошту: ");
                    input_value = sc.nextLine();
                }
                template_request+="'"+input_value+"');";
                statement.executeUpdate(template_request);
                break;
            }
            case 4: {
                String value,template="SELECT st.id,p.surname,p.name,p.last_name,st.Specialties_code,sp.name,st.Year_accepted,h.Number,h.Address FROM students as st INNER JOIN people as p ON p.id=st.People_ID INNER JOIN hostels as h ON h.Number=st.Hostel_number INNER JOIN specialties as sp ON st.Specialties_code=sp.code ";
                int choice;
                System.out.print("1. Чи очікує людина на поселення(0-так,1-ні): ");
                choice = sc.nextInt();
                while(choice !=0 && choice!=1) {
                    System.out.print("1. Чи очікує людина на поселення(0-так,1-ні): ");
                    choice = sc.nextInt();
                }
                switch(choice) {
                    case 0: {
                        template+="where p.address='-' ";
                        
                        break;
                    }
                    case 1: {
                        template+="where p.address!='-' ";
                        System.out.print("1.1. Введіть гуртожиток: ");
                        value=sc.next();
                        while(value.isEmpty()){
                            System.out.print("1.1. Введіть гуртожиток: ");
                            value=sc.next();
                        }
                        template+=" AND st.Hostel_number='"+value+"' AND p.address=h.Address ";
                        break;
                    }
                }
                sc.nextLine();
                System.out.print("2. Введіть прізвище особи(опціонально): ");
                value=sc.nextLine();
                if(!value.isEmpty()) {
                    template+=" AND p.surname LIKE '%"+value+"%' ";
                }
                System.out.print("3. Введіть ім'я особи(опціонально): ");
                value=sc.nextLine();
                if(!value.isEmpty()) {
                    template+=" AND p.name LIKE '%"+value+"%' ";
                }
                System.out.print("4. Введіть по-батькові особи(опціонально): ");
                value=sc.nextLine();
                if(!value.isEmpty()) {
                    template+=" AND p.last_name LIKE '%"+value+"%' ";
                }
                System.out.print("5. Введіть спеціальність особи(опціонально): ");
                value=sc.nextLine();
                if(!value.isEmpty()) {
                    template+=" AND st.Specialties_code='"+value+"' ";
                }
                System.out.print("6. Введіть рік вступу особи(опціонально): ");
                    value=sc.nextLine();
                if(!value.isEmpty()) {
                    template+=" AND st.Year_accepted='"+value+"' ";
                }
                template+=";";
                resultSet=getResponse(template);
                System.out.printf("| %-3s | %-20s | %-20s | %-20s | %-10s | %-50s | %-10s | %-15s | %-30s |\n",
                                     "ID", "Прізвище", "Ім'я","По-батькові", "Код спец.", "Назва спеціальності", "Рік вступу", "№ гуртожитку","Адреса");
                while(resultSet.next()) {
                    System.out.printf("| %-3s | %-20s | %-20s | %-20s | %-10s | %-50s | %-10s | %-15s | %-30s |\n",
                            resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
                }
                break;
            }
            default: {
                System.out.println("Невірний вибір функції!");
                break;
            }
        }
    }
    private ResultSet getResponse(String request) throws SQLException, ClassNotFoundException  {
        return statement.executeQuery(request);
    }
}
