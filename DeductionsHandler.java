/*
==========================================================================================================================================================================
    ПРОЕКТ СТУДЕНТА 3 КУРСУ ГРУПИ 6.1211-2пі математичного факультету Запорізького національного університету
    Проект представлено виключно як виконання практичного завданння екзаменаційної сесії з дисципліни "Мова програмування Java" (Горбенко В.І.)
    ПРОЕКТ Є ОСОБИСТОЮ ВЛАСНІСТЮ ТА НЕ МОЖЕ ВИКОРИСТОВУВАТИСЬ ДЛЯ ФІНАНСОВИХ ЦІЛЕЙ.
    Дата останньої зміни 12.12.2023 19:30.
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
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  
// Клас операцій з відрахування студентів
public class DeductionsHandler extends DataHandler {
    private int choice;
    private Scanner sc = new Scanner(System.in, "windows-1251");
    private ResultSet resultSet;
    //Операції зі студентами
    public void operations()  throws SQLException, ClassNotFoundException  {
        System.out.print("1 - Вивести відрахованих студентів\n2 - Вивести відрахованих студентів за параметрами\n3 - Додати відрахування\nВибір:");
        choice = sc.nextInt();
        switch(choice) {
                //Загальна база відрахованних студентів
            case 1: {
                //Запит до бази даних
                resultSet = getDeductionsData("SELECT d.Commandment_number,p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted,d.Deductions_types_name,d.Date,d.Diploma_number FROM deductions as d INNER JOIN students as st ON d.Students_id=st.id INNER JOIN people as p ON p.id=st.People_ID;");
                System.out.printf("| %-3s | %-15s | %-15s | %-15s | %-10s | %-15s | %-30s | %-15s | %-15s |\n",
                                     "Код", "Прізвище","Ім'я","По-батькові","Спец.","Рік вступу","Причина","Дата","Диплом");
                //Форматоване виведення даних з отриманої множини значень
                while(resultSet.next()) {
                    //Виведення за ідентифікатором окрім нульового, нульовий був для початку автоматичного збільшення індексу запису
                    if(resultSet.getString(1).compareTo("0")!=0) {
                        System.out.printf("| %-3s | %-15s | %-15s | %-15s | %-10s | %-15s | %-30s | %-15s | %-15s |\n",
                    resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),
                        resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
                    }
                }
                break;
            }
            //Виведення за параметрами
            case 2: {
                //Змінна введення та шаблону типів String
                String value="",template = "SELECT d.Commandment_number,p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted,d.Deductions_types_name,d.Date,d.Diploma_number FROM deductions as d INNER JOIN students as st ON d.Students_id=st.id INNER JOIN people as p ON p.id=st.People_ID WHERE ";
                sc.nextLine();
                System.out.print("1.Прізвище: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    //Якщо множина не порожня, перевірка на відсутність попередніх додавань властивостей для ЗВІТУ
                    if(template.compareTo("SELECT d.Commandment_number,p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted,d.Deductions_types_name,d.Date,d.Diploma_number FROM deductions as d INNER JOIN students as st ON d.Students_id=st.id INNER JOIN people as p ON p.id=st.People_ID WHERE ")==0){
                        template+=" p.surname='"+value+"' ";
                    }
                    else {
                        //Якщо властивості запиту вже були додані, то просто додаю AND і власне властивість
                        template+=" AND p.surname='"+value+"' ";
                    }
                }
                //Подальші атрибути властивостей опрацьовуємо аналогічно першому
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
                //Закриття запиту
                template+=';';
                //Формування результату запиту
                resultSet = getDeductionsData(template);
                System.out.printf("| %-3s | %-15s | %-15s | %-15s | %-10s | %-15s | %-30s | %-15s | %-15s |\n",
                                     "Код", "Прізвище","Ім'я","По-батькові","Спец.","Рік вступу","Причина","Дата","Диплом");
                //Форматоване виведення результату
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
            //Додати відрахування    
            case 3: {
                //Шаблон формування запиту додавання відрахування
                String template = "INSERT INTO deductions (`Students_id`,`Deductions_types_name`,`Date`,`Diploma_number`) VALUES (";
                //Перевірка на існування студента
                String template_person_check="SELECT st.id FROM students as st INNER JOIN people as p ON p.id = st.People_ID WHERE ";
                //Змінна для збереження результату введення
                String value = "";
                //Поля введення тут обов'язкові
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
                //Закриття шалону для запиту на знаходження студента
                template_person_check+= " AND p.last_name='"+value+"';";
                //Запит
                resultSet = getDeductionsData(template_person_check);
                //Перевірка існування запису на основі підрахунку кількостей записів
                int count = 0;
                //Змінна зберігання ідентифікатора студента
                String user_id = "";
                while(resultSet.next()){
                    user_id = resultSet.getString(1);
                    count++;
                }
                //Коли нема студента - завершення операції одразу
                if(count==0) {
                    System.out.println("Студента не знайдено!");
                    break;
                }
                //Перевірка існування наказу на відрахування цього студента
                resultSet = getDeductionsData("SELECT d.Commandment_number from deductions as d INNER JOIN students as st ON st.id=d.Students_id WHERE d.Students_id='"+user_id+"';");
                count=0;
                String commandment_id = "";
                while(resultSet.next()) {
                    commandment_id = resultSet.getString(1);
                    count++;
                }
                //Запис знайдено
                if(count!=0) {
                    System.out.println("Цей студент вже відрахований наказом №"+commandment_id+"!");
                    break;
                }
                //Продовжуэмо формувати наказ на відрахування
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
                //Дата виставляється поточна, тобто легко підробити дату не вийде
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
                //Закриття формування шаблону запиту на додавання запису до бази даних
                template += ",'"+ value + "');";
                //Виконання сформованого вище запиту
                statement.executeUpdate(template);
                break;
            }
        }
    }
    //Функція обробки запитів на отримання даних
    public ResultSet getDeductionsData(String template) throws SQLException, ClassNotFoundException {
        return statement.executeQuery(template);
    }
}
