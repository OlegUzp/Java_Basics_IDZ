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
public class StudentsHandler extends DataHandler {
    private int choice;
    private Scanner sc = new Scanner(System.in, "windows-1251");
    private ResultSet resultSet;
    public void operations()  throws SQLException, ClassNotFoundException  {
        System.out.print("1 - Загальна інформація про студентів\n2 - Загальна інформація за параметрами\n3 - Вивести загальну звіт про студента\n4 - Додати студента\nОберіть:");
        choice = sc.nextInt();
        switch(choice) {
            case 1: {
                //Загальна інформація
                resultSet = getStudentsData();
                System.out.printf("| %-3s | %-20s | %-20s | %-20s | %-6s | %-10s | %-10s | %-12s | %-5s | %-10s |\n",
                                     "id", "Прізвище", "Ім'я", "По-батькові", "Спец.", "Пол", "Дата нар.", "Телефон", "Гуртожиток", "Адреса");
                while(resultSet.next()) {
                    System.out.printf("| %-3s | %-20s | %-20s | %-20s | %-6s | %-10s | %-10s | %-12s | %-5s | %-30s |\n",
                            resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                            resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                            resultSet.getString(7), resultSet.getString(8), resultSet.getString(9),
                            resultSet.getString(10), resultSet.getString(11));
                }
                break;
            }
            case 2: {
                //Інформація про студентів за параметрами
                String template="select st.id,p.surname,p.name,p.last_name,st.Specialties_code,p.sex,p.date_of_birth,p.phone_number,st.Hostel_number,p.address,p.email,st.Exceptions_code from students as st INNER JOIN people as p ON p.id = st.People_id ";
                String input_value;
                sc.nextLine();
                System.out.print("1.Прізвище:");
                input_value = sc.nextLine();
                if(!input_value.isEmpty()) {
                    if(template.compareTo("select st.id,p.surname,p.name,p.last_name,st.Specialties_code,p.sex,p.date_of_birth,p.phone_number,st.Hostel_number,p.address,p.email,st.Exceptions_code from students as st INNER JOIN people as p ON p.id = st.People_id ")==0) {
                        template += " WHERE p.surname='"+input_value+"'";
                    }
                    else {
                        template += " AND p.surname='"+input_value+"'";
                    }
                }
                System.out.print("2.Ім'я:");
                input_value = sc.nextLine();
                if(!input_value.isEmpty()) {
                    if(template.compareTo("select st.id,p.surname,p.name,p.last_name,st.Specialties_code,p.sex,p.date_of_birth,p.phone_number,st.Hostel_number,p.address,p.email,st.Exceptions_code from students as st INNER JOIN people as p ON p.id = st.People_id ")==0) {
                       template += " WHERE p.name='"+input_value+"'";
                    }
                    else {
                        template += " AND p.name='"+input_value+"'";
                    }
                }
                System.out.print("3.По-батькові:");
                 input_value = sc.nextLine();
                if(!input_value.isEmpty()) {
                    if(template.compareTo("select st.id,p.surname,p.name,p.last_name,st.Specialties_code,p.sex,p.date_of_birth,p.phone_number,st.Hostel_number,p.address,p.email,st.Exceptions_code from students as st INNER JOIN people as p ON p.id = st.People_id ")==0) {
                       template += " WHERE p.last_name='"+input_value+"'";
                    }
                    else {
                        template += " AND p.last_name='"+input_value+"'";
                    }
                }
                System.out.print("4.Стать:");
                input_value = sc.nextLine();
                if(!input_value.isEmpty()) {
                    if(template.compareTo("select st.id,p.surname,p.name,p.last_name,st.Specialties_code,p.sex,p.date_of_birth,p.phone_number,st.Hostel_number,p.address,p.email,st.Exceptions_code from students as st INNER JOIN people as p ON p.id = st.People_id ")==0) {
                       template += " WHERE p.sex='"+input_value+"'";
                    }
                    else {
                        template += " AND p.sex='"+input_value+"'";
                    }
                }
                System.out.print("5.Рік народження (формат рік-місяць-день):");
                input_value = sc.nextLine();
                if(!input_value.isEmpty()) {
                    if(template.compareTo("select st.id,p.surname,p.name,p.last_name,st.Specialties_code,p.sex,p.date_of_birth,p.phone_number,st.Hostel_number,p.address,p.email,st.Exceptions_code from students as st INNER JOIN people as p ON p.id = st.People_id ")==0) {
                       template += " WHERE p.date_of_birth='"+input_value+"'";
                    }
                    else {
                        template += " AND p.date_of_birth='"+input_value+"'";
                    }
                }
                System.out.print("6.Спеціальність:");
                 input_value = sc.nextLine();
                if(!input_value.isEmpty()) {
                    if(template.compareTo("select st.id,p.surname,p.name,p.last_name,st.Specialties_code,p.sex,p.date_of_birth,p.phone_number,st.Hostel_number,p.address,p.email,st.Exceptions_code from students as st INNER JOIN people as p ON p.id = st.People_id ")==0) {
                       template += " WHERE st.specialties_code='"+input_value+"'";
                    }
                    else {
                        template += " AND st.specialties_code='"+input_value+"'";
                    }
                }
                System.out.print("7.Курс:");
                input_value = sc.nextLine();
                if(!input_value.isEmpty()) {
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    int year = currentYear-Integer.parseInt(input_value)+1;
                    if(template.compareTo("select st.id,p.surname,p.name,p.last_name,st.Specialties_code,p.sex,p.date_of_birth,p.phone_number,st.Hostel_number,p.address,p.email,st.Exceptions_code from students as st INNER JOIN people as p ON p.id = st.People_id ")==0) {
                       template += " WHERE st.Year_accepted="+String.valueOf(year);
                    }
                    else {
                        template += " AND st.Year_accepted="+String.valueOf(year);
                    }
                }
                
                System.out.print("8.Гуртожиток номер:");
                input_value = sc.nextLine();
                if(!input_value.isEmpty()) {
                    input_value = "0";
                    if(template.compareTo("select st.id,p.surname,p.name,p.last_name,st.Specialties_code,p.sex,p.date_of_birth,p.phone_number,st.Hostel_number,p.address,p.email,st.Exceptions_code from students as st INNER JOIN people as p ON p.id = st.People_id ")==0) {
                       template += " WHERE st.Hostel_number="+String.valueOf(input_value);
                    }
                    else {
                        template += " AND st.Hostel_number="+String.valueOf(input_value);
                    }
                }
                System.out.print("9.Код пільги:");
                input_value = sc.nextLine();
                if(!input_value.isEmpty()) {
                    if(template.compareTo("select st.id,p.surname,p.name,p.last_name,st.Specialties_code,p.sex,p.date_of_birth,p.phone_number,st.Hostel_number,p.address,p.email,st.Exceptions_code from students as st INNER JOIN people as p ON p.id = st.People_id ")==0) {
                       template += " WHERE st.Exceptions_code="+String.valueOf(input_value);
                    }
                    else {
                        template += " AND st.Exceptions_code="+String.valueOf(input_value);
                    }
                }
                template+=";";
                resultSet = getParametresStudentsData(template);
                System.out.printf("| %-3s | %-20s | %-20s | %-20s | %-6s | %-10s | %-10s | %-12s | %-5s | %-10s | %-10s | %-3s |\n",
                                     "id", "Прізвище", "Ім'я", "По-батькові", "Спец.", "Пол", "Дата нар.", "Телефон", "Гуртожиток", "Адреса","Пошта","Пільга");
                while(resultSet.next()) {
                    System.out.printf("| %-3s | %-20s | %-20s | %-20s | %-6s | %-10s | %-10s | %-12s | %-5s | %-30s | %-10s | %-3s |\n",
                            resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                            resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                            resultSet.getString(7), resultSet.getString(8), resultSet.getString(9),
                            resultSet.getString(10), resultSet.getString(11),resultSet.getString(12));
                }
                break;
            }
            case 3: {
                //Звіт про студента
                String template = "SELECT st.id,p.surname,p.name,p.last_name,p.date_of_birth,p.sex,p.address,p.phone_number,p.email FROM students as st INNER JOIN people as p ON p.id=st.People_ID ";
                String value;
                System.out.print("1.Введіть прізвище: ");
                value=sc.nextLine();
                while(value.isEmpty()) {
                    System.out.print("1.Введіть прізвище: ");
                    value=sc.nextLine();
                }
                template += " WHERE p.surname='"+value+"' ";
                System.out.print("2.Введіть ім'я: ");
                value=sc.nextLine();
                while(value.isEmpty()) {
                    System.out.print("2.Введіть ім'я: ");
                    value=sc.nextLine();
                }
                template += " AND p.name='"+value+"' ";
                System.out.print("3.Введіть по-батькові: ");
                value=sc.nextLine();
                while(value.isEmpty()) {
                    System.out.print("3.Введіть по-батькові: ");
                    value=sc.nextLine();
                }
                template += " AND p.last_name='"+value+"' ";
                System.out.print("4.Введіть спеціальність: ");
                value=sc.nextLine();
                while(value.isEmpty()) {
                    System.out.print("4.Введіть спеціальнінсть: ");
                    value=sc.nextLine();
                }
                template += " AND st.Specialties_code='"+value+"' ";
                System.out.print("5.Рік вступу: ");
                value=sc.nextLine();
                while(value.isEmpty()) {
                    System.out.print("5.Рік вступу: ");
                    value=sc.nextLine();
                }
                template += " AND st.Year_accepted='"+value+"';";
                System.out.println(template);
                resultSet = getParametresStudentsData(template);
                int count=0;
                while(resultSet.next()) {
                    count++;
                }
                if(count==0){
                    System.out.println("Студента не знайдено!");
                    break;
                }
                resultSet = getParametresStudentsData(template);
                String student_id="";
                while(resultSet.next()) {
                    student_id=resultSet.getString(1);
                    System.out.println("============<<ПОВНИЙ ЗВІТ>>=============");
                    System.out.println("1.Прізвище: "+resultSet.getString(2));
                    System.out.println("2.Ім'я: "+resultSet.getString(3));
                    System.out.println("3.По-батькові: "+resultSet.getString(4));
                    System.out.println("4.Дата народження: "+resultSet.getString(5));
                    System.out.println("5.Стать: "+resultSet.getString(6));
                    System.out.println("6.Адреса проживання (якщо '-' то потребує поселення в гуртожиток): "+resultSet.getString(7));
                    System.out.println("7.Телефон: "+resultSet.getString(8));
                    System.out.println("8.Пошта: "+resultSet.getString(9));
                }
                template="SELECT sp.code,sp.name,sp.StudyTypes_name,st.Year_accepted FROM students as st INNER JOIN specialties as sp ON st.Specialties_code=sp.code WHERE st.id='"+student_id+"';";
                resultSet = getParametresStudentsData(template);
                while(resultSet.next()) {
                    System.out.println("9.Спеціальність: "+resultSet.getString(1));
                    System.out.println("10.Назва спеціальності: "+resultSet.getString(2));
                    System.out.println("11.Рівень навчання: "+resultSet.getString(3));
                    System.out.println("12.Рік вступу: "+resultSet.getString(4));
                }
                template = "SELECT st.Exceptions_code,e.definition FROM exceptions as e INNER JOIN students as st ON st.Exceptions_code=e.code WHERE st.id='"+student_id+"' AND st.Exceptions_code!=0;";
                count=0;
                resultSet = getParametresStudentsData(template);
                while(resultSet.next()){count++;}
                if(count==0){
                    System.out.println("13.Пільги не надано.");
                }
                else {
                    System.out.println("13.Пільга надана.");
                    System.out.println("13.1.Код пільги: "+ resultSet.getString(1));
                    System.out.println("13.2.Підстава надання: "+ resultSet.getString(2));
                }
                template="SELECT h.Number,h.Address,p.address FROM hostels as h INNER JOIN students as st ON st.Hostel_number=h.Number INNER JOIN people as p ON p.id=st.People_ID WHERE st.id='"+student_id+"';";
                resultSet = getParametresStudentsData(template);
                while(resultSet.next()) {
                    if(resultSet.getString(3).compareTo("-")==0 && resultSet.getString(1).compareTo("0")==0){
                        System.out.println("14.Очікує на поселення.");
                    }
                    else if(resultSet.getString(3).compareTo("-")!=0 && resultSet.getString(1).compareTo("0")==0){
                        System.out.println("14. Відмовився від поселення в гуртожиток.");
                    }
                    else {
                        System.out.println("14.Поселено в гуртожиток.");
                        System.out.println("14.1.Гуртожиток №"+resultSet.getString(1));
                        System.out.println("14.2.Адреса гуртожитку: "+resultSet.getString(2));
                    }
                }
                template="SELECT `Scholarship` from students WHERE `id`='"+student_id+"';";
                resultSet = getParametresStudentsData(template);
                while(resultSet.next()) {
                    System.out.println("15.Стипендія: " + resultSet.getString(1));
                }
                template="SELECT sub.name,ap.credits,ap.Marks,ap.Date_enter_marks FROM academic_performances as ap INNER JOIN subjects as sub ON sub.code=ap.Subjects_code WHERE ap.Students_id='"+student_id+"' ORDER BY ap.Date_enter_marks ASC;";
                count=0;
                resultSet = getParametresStudentsData(template);
                while(resultSet.next()) {
                    count++;
                }
                if(count==0){
                    System.out.println("16.Відомість порожня, оцінок не виставлено!");
                }
                else {
                    System.out.println("16.Оцінки з відомості: ");
                    System.out.printf("| %-40s | %-8s | %-10s | %-10s |\n",
                            "Назва предмету","Кредити","Оцінка","Дата");
                    resultSet = getParametresStudentsData(template);
                    while(resultSet.next()) {
                        System.out.printf("| %-40s | %-8s | %-10s | %-10s |\n",
                                resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                    }
                }
                template = "SELECT SUM(`marks`*`credits`)/SUM(`credits`) FROM academic_performances WHERE `Students_id`='"+student_id+"' GROUP BY `Students_id`;";
                resultSet = getParametresStudentsData(template);
                while(resultSet.next()) {
                    System.out.println("17. Середньозважена оцінка за семестр: "+resultSet.getString(1));
                }
            }
            case 4: {
                //Додати студента
                String template_people = "INSERT INTO people (`surname`,`name`,`last_name`,`date_of_birth`,`sex`,`address`,`phone_number`,`email`) VALUES (";
                String template_People_ID_search = "SELECT `id` from people where ";
                String template_student = "INSERT INTO students (`People_ID`,`Exceptions_code`,`Hostel_number`,`Specialties_code`,`Year_accepted`) VALUES (";
                String template;
                String input_value;
                sc.nextLine();
                System.out.print("1.Прізвище:");
                input_value = sc.nextLine();
                while(input_value.isEmpty()) {
                    System.out.print("1.Прізвище:");
                    input_value = sc.nextLine();
                }
                template_people += "'" + input_value + "',";
                template_People_ID_search += "`surname`='"+input_value+"' AND ";
                System.out.print("2.Ім'я:");
                input_value = sc.nextLine();
                while(input_value.isEmpty()) {
                    System.out.print("2.Ім'я:");
                    input_value = sc.nextLine();
                }
                template_people += "'" + input_value + "',";
                template_People_ID_search += "`name`='"+input_value+"' AND ";
                System.out.print("3.По-батькові:");
                input_value = sc.nextLine();
                while(input_value.isEmpty()) {
                    System.out.print("3.По-батькові:");
                    input_value = sc.nextLine();
                }
                template_people += "'" + input_value + "',";
                template_People_ID_search += "`last_name`='"+input_value+"';";
                resultSet = statement.executeQuery(template_People_ID_search);
                int row_count = 0;
                while(resultSet.next()) {row_count++;}
                if(row_count>0) {
                    System.out.println("Така людина вже зареєстрована!");
                    break;
                }
                System.out.print("4.Дата народження (формат рік-місяць-день):");
                input_value = sc.nextLine();
                while(input_value.isEmpty()) {
                    System.out.print("4.Дата народження:");
                    input_value = sc.nextLine();
                }
                template_people += "'" + input_value + "',";
                System.out.print("5.Пол:");
                input_value = sc.nextLine();
                while(input_value.isEmpty() && (input_value.compareTo("Чоловічий")==0 || input_value.compareTo("Жіночий")==0)) {
                    System.out.print("5.Пол:");
                    input_value = sc.nextLine();
                }
                template_people += "'" + input_value + "',";
                System.out.print("6.Адреса:");
                input_value = sc.nextLine();
                while(input_value.isEmpty()) {
                    System.out.print("6.Адреса:");
                    input_value = sc.nextLine();
                }
                int choice;
                System.out.print("6.1. Чи потребує гуртожитку (0-ні/1-так): ");
                choice = (int) sc.nextInt();
                while(choice!=0 && choice!=1){
                    System.out.print("6.1. Чи потребує гуртожитку (0-ні/1-так): ");
                    choice = (int) sc.nextInt();
                }
                switch(choice){
                    case 0: {
                        break;
                    }
                    case 1: {
                        input_value="-";
                        break;
                    }
                }
                sc.nextLine();
                template_people += "'" + input_value + "',";
                System.out.print("7.Телефон:");
                input_value = sc.nextLine();
                while(input_value.isEmpty()) {
                    System.out.print("7.Телефон:");
                    input_value = sc.nextLine();
                }
                template_people += "'" + input_value + "',";
                System.out.print("8.Пошта:");
                input_value = sc.nextLine();
                while(input_value.isEmpty()) {
                    System.out.print("8.Пошта:");
                    input_value = sc.nextLine();
                }
                template_people += "'" + input_value + "');";
                statement.executeUpdate(template_people);
                resultSet = statement.executeQuery(template_People_ID_search);
                int current_id=0;
                while(resultSet.next()) {current_id = resultSet.getInt(1);}
                template_student += "'" + current_id + "',";
                input_value = "";
                while(input_value.isEmpty()) {
                    System.out.print("9.Пільга(якщо не передбачена тисніть Enter):");
                    input_value = sc.nextLine();
                    if(input_value.isEmpty()) {input_value = "0";}
                }
                template_student += "'" + input_value + "',";
                input_value="";
                while(input_value.isEmpty()) {
                    System.out.print("10.Номер гуртожитку для поселення (якщо немає потреби тисніть Enter):");
                    input_value = sc.nextLine();
                    if(input_value.isEmpty()) {input_value = "0"; break;}
                    while(!input_value.isEmpty()) {
                        template = "SELECT `Address` FROM hostels where `Number`='"+input_value+"';";
                        resultSet = statement.executeQuery(template);
                            row_count = 0;
                            while(resultSet.next()) {
                                template = "UPDATE people SET address = '" + resultSet.getString(1)+"' where id='"+current_id+"';";
                                row_count++;
                            }
                            if(row_count!=0) {
                                statement.executeUpdate(template);
                                break;
                            }
                            else {
                                input_value = "";
                                break;
                            }
                    }
                }
                template_student += "'" + input_value + "',";
                input_value = "";
                while(input_value.isEmpty()) {
                    System.out.print("11.Спеціальність:");
                    input_value = sc.nextLine();
                    template = "SELECT `code` from specialties WHERE `code`='"+input_value+"';";
                    resultSet = statement.executeQuery(template);
                    row_count = 0;
                    while(resultSet.next()) {row_count++;}
                    if(row_count!=0) {
                        break;
                    }
                    input_value = "";
                }
                template_student += "'"+input_value+"',";
                System.out.print("12.Рік вступу:");
                input_value = sc.nextLine();
                while(input_value.isEmpty()) {
                    System.out.print("12.Рік вступу:");
                    input_value = sc.nextLine();
                }
                while(Integer.parseInt(input_value)>Calendar.getInstance().get(Calendar.YEAR)) {
                    System.out.print("12.Рік вступу:");
                    input_value = sc.nextLine();
                    while(input_value.isEmpty()) {
                        System.out.print("12.Рік вступу:");
                        input_value = sc.nextLine();
                    }
                }
                template_student += "'" + input_value + "');";
                statement.executeUpdate(template_student);
                break;
            }
        }
    }
    public ResultSet getStudentsData() throws SQLException, ClassNotFoundException {
        return statement.executeQuery("select st.id,p.surname,p.name,p.last_name,st.Specialties_code,p.sex,p.date_of_birth,p.phone_number,st.Hostel_number,p.address,p.email from students as st INNER JOIN people as p ON p.id = st.People_id;");
    }
    public ResultSet getParametresStudentsData(String request) throws SQLException, ClassNotFoundException {
        return statement.executeQuery(request);
    }
}
