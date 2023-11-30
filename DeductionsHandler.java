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
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  
//import java.util.
public class DeductionsHandler extends DataHandler {
    private int choice;
    private Scanner sc = new Scanner(System.in, "windows-1251");
    private ResultSet resultSet;
    public void operations()  throws SQLException, ClassNotFoundException  {
        System.out.print("1 - Вивести відрахованих студентів\n2 - Вивести відрахованих студентів за параметрами\n3 - Додати відрахування\nВибір:");
        choice = sc.nextInt();
        switch(choice) {
            case 1: {
                resultSet = getDeductionsData("SELECT d.Commandment_number,p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted,d.Deductions_types_name,d.Date,d.Diploma_number FROM deductions as d INNER JOIN students as st ON d.Students_id=st.id INNER JOIN people as p ON p.id=st.People_ID;");
                System.out.printf("| %-3s | %-15s | %-15s | %-15s | %-10s | %-15s | %-30s | %-15s | %-15s |\n",
                                     "Код", "Прізвище","Ім'я","По-батькові","Спец.","Рік вступу","Причина","Дата","Диплом");
                while(resultSet.next()) {
                    if(resultSet.getString(1).compareTo("0")!=0) {
                        System.out.printf("| %-3s | %-15s | %-15s | %-15s | %-10s | %-15s | %-30s | %-15s | %-15s |\n",
                    resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),
                        resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
                    }
                }
                break;
            }
            case 2: {
                String value="",template = "SELECT d.Commandment_number,p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted,d.Deductions_types_name,d.Date,d.Diploma_number FROM deductions as d INNER JOIN students as st ON d.Students_id=st.id INNER JOIN people as p ON p.id=st.People_ID WHERE ";
                sc.nextLine();
                System.out.print("1.Прізвище: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT d.Commandment_number,p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted,d.Deductions_types_name,d.Date,d.Diploma_number FROM deductions as d INNER JOIN students as st ON d.Students_id=st.id INNER JOIN people as p ON p.id=st.People_ID WHERE ")==0){
                        template+=" p.surname='"+value+"' ";
                    }
                    else {
                        template+=" AND p.surname='"+value+"' ";
                    }
                }
                System.out.print("2.Ім'я: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT d.Commandment_number,p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted,d.Deductions_types_name,d.Date,d.Diploma_number FROM deductions as d INNER JOIN students as st ON d.Students_id=st.id INNER JOIN people as p ON p.id=st.People_ID WHERE ")==0){
                        template+=" p.name='"+value+"' ";
                    }
                    else {
                        template+=" AND p.name='"+value+"' ";
                    }
                }
                System.out.print("3.По-батькові: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT d.Commandment_number,p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted,d.Deductions_types_name,d.Date,d.Diploma_number FROM deductions as d INNER JOIN students as st ON d.Students_id=st.id INNER JOIN people as p ON p.id=st.People_ID WHERE ")==0){
                        template+=" p.last_name='"+value+"' ";
                    }
                    else {
                        template+=" AND p.last_name='"+value+"' ";
                    }
                }
                System.out.print("4.Спеціальність: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT d.Commandment_number,p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted,d.Deductions_types_name,d.Date,d.Diploma_number FROM deductions as d INNER JOIN students as st ON d.Students_id=st.id INNER JOIN people as p ON p.id=st.People_ID WHERE ")==0){
                        template+=" st.Specialties_code='"+value+"' ";
                    }
                    else {
                        template+=" AND st.Specialties_code='"+value+"' ";
                    }
                }
                System.out.print("5.Рік вступу: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT d.Commandment_number,p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted,d.Deductions_types_name,d.Date,d.Diploma_number FROM deductions as d INNER JOIN students as st ON d.Students_id=st.id INNER JOIN people as p ON p.id=st.People_ID WHERE ")==0){
                        template+=" st.Year_accepted='"+value+"' ";
                    }
                    else {
                        template+=" AND st.Year_accepted='"+value+"' ";
                    }
                }
                System.out.print("6.Причина відрахування: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT d.Commandment_number,p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted,d.Deductions_types_name,d.Date,d.Diploma_number FROM deductions as d INNER JOIN students as st ON d.Students_id=st.id INNER JOIN people as p ON p.id=st.People_ID WHERE ")==0){
                        template+=" d.Deductions_types_name='"+value+"' ";
                    }
                    else {
                        template+=" AND d.Deductions_types_name='"+value+"' ";
                    }
                }
                System.out.print("7.Дата відрахування (формат рік-місяць-день): ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT d.Commandment_number,p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted,d.Deductions_types_name,d.Date,d.Diploma_number FROM deductions as d INNER JOIN students as st ON d.Students_id=st.id INNER JOIN people as p ON p.id=st.People_ID WHERE ")==0){
                        template+=" d.Date='"+value+"' ";
                    }
                    else {
                        template+=" AND d.Date='"+value+"' ";
                    }
                }
                System.out.println("8.Номер диплому: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT d.Commandment_number,p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted,d.Deductions_types_name,d.Date,d.Diploma_number FROM deductions as d INNER JOIN students as st ON d.Students_id=st.id INNER JOIN people as p ON p.id=st.People_ID WHERE ")==0){
                        template+=" d.Diploma_number='"+value+"' ";
                    }
                    else {
                        template+=" AND d.Diploma_number='"+value+"' ";
                    }
                }
                template+=';';
                resultSet = getDeductionsData(template);
                System.out.printf("| %-3s | %-15s | %-15s | %-15s | %-10s | %-15s | %-30s | %-15s | %-15s |\n",
                                     "Код", "Прізвище","Ім'я","По-батькові","Спец.","Рік вступу","Причина","Дата","Диплом");
                while(resultSet.next()) {
                    if(resultSet.getString(1).compareTo("0")!=0) {
                        System.out.printf("| %-3s | %-15s | %-15s | %-15s | %-10s | %-15s | %-30s | %-15s | %-15s |\n",
                    resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),
                        resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
                    }
                }
                break;
            }
            case 3: {
                //Додати відрахування
                String template = "INSERT INTO deductions (`Students_id`,`Deductions_types_name`,`Date`,`Diploma_number`) VALUES (";
                String template_person_check="SELECT st.id FROM students as st INNER JOIN people as p ON p.id = st.People_ID WHERE ";
                String value = "";
                System.out.print("1.Введіть прізвище: ");
                value = sc.nextLine();
                while(value.isEmpty()) {
                    System.out.print("1.Введіть прізвище: ");
                    value = sc.nextLine();
                }
                template_person_check+= " p.surname='"+value+"' ";
                
                System.out.print("2.Введіть ім'я: ");
                value = sc.nextLine();
                while(value.isEmpty()) {
                    System.out.print("2.Введіть ім`я: ");
                    value = sc.nextLine();
                }
                template_person_check+= " AND p.name='"+value+"' ";
                
                System.out.print("3.Введіть по-батькові: ");
                value = sc.nextLine();
                while(value.isEmpty()) {
                    System.out.print("3.Введіть по-батькові: ");
                    value = sc.nextLine();
                }
                template_person_check+= " AND p.last_name='"+value+"';";
                resultSet = getDeductionsData(template_person_check);
                int count = 0;
                String user_id = "";
                while(resultSet.next()){
                    user_id = resultSet.getString(1);
                    count++;
                }
                if(count==0) {
                    System.out.println("Студента не знайдено!");
                    break;
                }
                resultSet = getDeductionsData("SELECT d.Commandment_number from deductions as d INNER JOIN students as st ON st.id=d.Students_id WHERE d.Students_id='"+user_id+"';");
                count=0;
                String commandment_id = "";
                while(resultSet.next()) {
                    commandment_id = resultSet.getString(1);
                    count++;
                }
                if(count!=0) {
                    System.out.println("Цей студент вже відрахований наказом №"+commandment_id+"!");
                    break;
                }
                template+="'"+user_id+"',";
                System.out.print("4.Тип відрахування: ");
                String type = "";
                value = sc.nextLine();
                while(value.isEmpty() || (value.compareTo("Академічна заборгованність")!=0 && value.compareTo("Закінчення курсу навчання")!=0 && value.compareTo("Порушення умов контракту")!=0)) {
                    System.out.print("4.Тип відрахування: ");
                    value = sc.nextLine();
                }
                type=value;
                template+="'"+value+"',";
                template+="'"+java.time.LocalDate.now()+"'";
                value = "";
                if(type.compareTo("Закінчення курсу навчання")==0){
                    System.out.print("5.Номер диплому: ");
                    value = sc.nextLine();
                    while(value.isEmpty()){
                        System.out.print("5.Номер диплому: ");
                        value = sc.nextLine();
                    }
                }
                template += ",'"+ value + "');";
                statement.executeUpdate(template);
                break;
            }
        }
    }
    public ResultSet getDeductionsData(String template) throws SQLException, ClassNotFoundException {
        return statement.executeQuery(template);
    }
}
