/*
==========================================================================================================================================================================
    ПРОЕКТ СТУДЕНТА 3 КУРСУ ГРУПИ 6.1211-2пі математичного факультету Запорізького національного університету
    Проект представлено виключно як виконання практичного завданння екзаменаційної сесії з дисципліни "Мова програмування Java" (Горбенко В.І.)
    ПРОЕКТ Є ОСОБИСТОЮ ВЛАСНІСТЮ ТА НЕ МОЖЕ ВИКОРИСТОВУВАТИСЬ ДЛЯ ФІНАНСОВИХ ЦІЛЕЙ.
    Дата останньої зміни 12.12.2023 19:57.
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
import java.util.Date;
import java.util.ArrayList;
//Клас для роботи з оцінками студентів у відомостях
public class MarksHandler extends DataHandler {
    private int choice;
    private Scanner sc = new Scanner(System.in, "windows-1251");
    private ResultSet resultSet;
    //Операції
    public void operations()  throws SQLException, ClassNotFoundException  {
        System.out.print("1 - Вивести усі оцінки студентів\n2 - Вивести оцінки за параметрами\n3 - Додати оцінку в відомість\n4 - Вивести рейтинг потоку без зазначення стипендії\n5 - Обрахувати стипендіантів та вивести рейтинг\nВибір:");
        choice = sc.nextInt();
        switch(choice) {
            //Усі оцінки
            case 1: {
                resultSet = getData("SELECT ap.id,p.surname,p.name,p.last_name,sp.code,sp.name,sub.name,sub.StudyType_name,ap.Date_enter_marks,ap.credits,ap.marks FROM academic_performances as ap INNER JOIN students as st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID INNER JOIN subjects as sub ON ap.Subjects_code=sub.code INNER JOIN specialties as sp ON sp.code = st.Specialties_code ORDER BY ap.id ASC;");
                System.out.printf("| %-5s | %-20s | %-20s | %-20s | %-5s | %-20s | %-50s | %-10s | %-15s | %-7s | %-6s |\n",
                                     "Наказ", "Прізвище", "Ім'я","По-батькові","Спец.","Назва спеціальності","Предмет","Рівень","Дата виставлення","Кредити","Оцінка");
                while(resultSet.next()) {
                    System.out.printf("| %-5s | %-20s | %-20s | %-20s | %-5s | %-20s | %-50s | %-10s | %-15s | %-7s | %-6s |\n",
                        resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8), resultSet.getString(9),
                        resultSet.getString(10), resultSet.getString(11));
                }
                break;
            }
            case 2: {
                //Оцінки за параметрами (опціональні)
                sc.nextLine();
                String template = "SELECT ap.id,p.surname,p.name,p.last_name,sp.code,sp.name,sub.name,sub.StudyType_name,ap.Date_enter_marks,ap.credits,ap.marks FROM academic_performances as ap INNER JOIN students as st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID INNER JOIN subjects as sub ON ap.Subjects_code=sub.code INNER JOIN specialties as sp ON sp.code = st.Specialties_code ";
                String value = "";
                System.out.print("1.Прізвище студента: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT ap.id,p.surname,p.name,p.last_name,sp.code,sp.name,sub.name,sub.StudyType_name,ap.Date_enter_marks,ap.credits,ap.marks FROM academic_performances as ap INNER JOIN students as st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID INNER JOIN subjects as sub ON ap.Subjects_code=sub.code INNER JOIN specialties as sp ON sp.code = st.Specialties_code ")==0) {
                        template += "WHERE p.surname='"+value+"' ";
                    }
                    else {
                        template += "AND p.surname='"+value+"' ";
                    }
                }
                System.out.print("2.Ім'я студента: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT ap.id,p.surname,p.name,p.last_name,sp.code,sp.name,sub.name,sub.StudyType_name,ap.Date_enter_marks,ap.credits,ap.marks FROM academic_performances as ap INNER JOIN students as st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID INNER JOIN subjects as sub ON ap.Subjects_code=sub.code INNER JOIN specialties as sp ON sp.code = st.Specialties_code ")==0) {
                        template += "WHERE p.name='"+value+"' ";
                    }
                    else {
                        template += "AND p.name='"+value+"' ";
                    }
                }
                System.out.print("3.По-батькові студента: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT ap.id,p.surname,p.name,p.last_name,sp.code,sp.name,sub.name,sub.StudyType_name,ap.Date_enter_marks,ap.credits,ap.marks FROM academic_performances as ap INNER JOIN students as st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID INNER JOIN subjects as sub ON ap.Subjects_code=sub.code INNER JOIN specialties as sp ON sp.code = st.Specialties_code ")==0) {
                        template += "WHERE p.last_name='"+value+"' ";
                    }
                    else {
                        template += "AND p.last_name='"+value+"' ";
                    }
                }
                System.out.print("4.Код спеціальності: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT ap.id,p.surname,p.name,p.last_name,sp.code,sp.name,sub.name,sub.StudyType_name,ap.Date_enter_marks,ap.credits,ap.marks FROM academic_performances as ap INNER JOIN students as st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID INNER JOIN subjects as sub ON ap.Subjects_code=sub.code INNER JOIN specialties as sp ON sp.code = st.Specialties_code ")==0) {
                        template += "WHERE sp.code='"+value+"' ";
                    }
                    else {
                        template += "AND sp.code='"+value+"' ";
                    }
                }
                System.out.print("5.Назва спеціальності: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT ap.id,p.surname,p.name,p.last_name,sp.code,sp.name,sub.name,sub.StudyType_name,ap.Date_enter_marks,ap.credits,ap.marks FROM academic_performances as ap INNER JOIN students as st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID INNER JOIN subjects as sub ON ap.Subjects_code=sub.code INNER JOIN specialties as sp ON sp.code = st.Specialties_code ")==0) {
                        template += "WHERE sp.name='"+value+"' ";
                    }
                    else {
                        template += "AND sp.name='"+value+"' ";
                    }
                }
                System.out.print("6.Назва предмету: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT ap.id,p.surname,p.name,p.last_name,sp.code,sp.name,sub.name,sub.StudyType_name,ap.Date_enter_marks,ap.credits,ap.marks FROM academic_performances as ap INNER JOIN students as st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID INNER JOIN subjects as sub ON ap.Subjects_code=sub.code INNER JOIN specialties as sp ON sp.code = st.Specialties_code ")==0) {
                        template += "WHERE sub.name LIKE '%"+value+"%' ";
                    }
                    else {
                        template += "AND sub.name LIKE '%"+value+"%' ";
                    }
                }
                System.out.print("7.Рівень підготовки в межах предмету (Бакалавр або Магістр): ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT ap.id,p.surname,p.name,p.last_name,sp.code,sp.name,sub.name,sub.StudyType_name,ap.Date_enter_marks,ap.credits,ap.marks FROM academic_performances as ap INNER JOIN students as st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID INNER JOIN subjects as sub ON ap.Subjects_code=sub.code INNER JOIN specialties as sp ON sp.code = st.Specialties_code ")==0) {
                        template += "WHERE sub.StudyType_name LIKE '%"+value+"%' ";
                    }
                    else {
                        template += "AND sub.StudyType_name='"+value+"' ";
                    }
                }
                System.out.print("8.Дата наказу ВІД: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT ap.id,p.surname,p.name,p.last_name,sp.code,sp.name,sub.name,sub.StudyType_name,ap.Date_enter_marks,ap.credits,ap.marks FROM academic_performances as ap INNER JOIN students as st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID INNER JOIN subjects as sub ON ap.Subjects_code=sub.code INNER JOIN specialties as sp ON sp.code = st.Specialties_code ")==0) {
                        template += "WHERE ap.Date_enter_marks>='"+value+"' ";
                    }
                    else {
                        template += "AND ap.Date_enter_marks>='"+value+"' ";
                    }
                }
                System.out.print("9.Дата наказу ДО: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT ap.id,p.surname,p.name,p.last_name,sp.code,sp.name,sub.name,sub.StudyType_name,ap.Date_enter_marks,ap.credits,ap.marks FROM academic_performances as ap INNER JOIN students as st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID INNER JOIN subjects as sub ON ap.Subjects_code=sub.code INNER JOIN specialties as sp ON sp.code = st.Specialties_code ")==0) {
                        template += "WHERE ap.Date_enter_marks<='"+value+"' ";
                    }
                    else {
                        template += "AND ap.Date_enter_marks<='"+value+"' ";
                    }
                }
                System.out.print("10.Кредити ВІД: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT ap.id,p.surname,p.name,p.last_name,sp.code,sp.name,sub.name,sub.StudyType_name,ap.Date_enter_marks,ap.credits,ap.marks FROM academic_performances as ap INNER JOIN students as st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID INNER JOIN subjects as sub ON ap.Subjects_code=sub.code INNER JOIN specialties as sp ON sp.code = st.Specialties_code ")==0) {
                        template += "WHERE ap.credits>='"+value+"' ";
                    }
                    else {
                        template += "AND ap.credits>='"+value+"' ";
                    }
                }
                System.out.print("11.Кредити ДО: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT ap.id,p.surname,p.name,p.last_name,sp.code,sp.name,sub.name,sub.StudyType_name,ap.Date_enter_marks,ap.credits,ap.marks FROM academic_performances as ap INNER JOIN students as st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID INNER JOIN subjects as sub ON ap.Subjects_code=sub.code INNER JOIN specialties as sp ON sp.code = st.Specialties_code ")==0) {
                        template += "WHERE ap.credits<='"+value+"' ";
                    }
                    else {
                        template += "AND ap.credits<='"+value+"' ";
                    }
                }
                System.out.print("12.Оцінки ВІД: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT ap.id,p.surname,p.name,p.last_name,sp.code,sp.name,sub.name,sub.StudyType_name,ap.Date_enter_marks,ap.credits,ap.marks FROM academic_performances as ap INNER JOIN students as st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID INNER JOIN subjects as sub ON ap.Subjects_code=sub.code INNER JOIN specialties as sp ON sp.code = st.Specialties_code ")==0) {
                        template += "WHERE ap.marks>='"+value+"' ";
                    }
                    else {
                        template += "AND ap.marks>='"+value+"' ";
                    }
                }
                System.out.print("13.Оцінки ДО: ");
                value = sc.nextLine();
                if(!value.isEmpty()) {
                    if(template.compareTo("SELECT ap.id,p.surname,p.name,p.last_name,sp.code,sp.name,sub.name,sub.StudyType_name,ap.Date_enter_marks,ap.credits,ap.marks FROM academic_performances as ap INNER JOIN students as st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID INNER JOIN subjects as sub ON ap.Subjects_code=sub.code INNER JOIN specialties as sp ON sp.code = st.Specialties_code ")==0) {
                        template += "WHERE ap.marks<='"+value+"' ";
                    }
                    else {
                        template += "AND ap.marks<='"+value+"' ";
                    }
                }
                template += " ORDER BY ap.id ASC;";
                resultSet = getData(template);
                System.out.printf("| %-5s | %-20s | %-20s | %-20s | %-5s | %-20s | %-50s | %-10s | %-15s | %-7s | %-6s |\n",
                                     "Наказ", "Прізвище", "Ім'я","По-батькові","Спец.","Назва спеціальності","Предмет","Рівень","Дата виставлення","Кредити","Оцінка");
                while(resultSet.next()) {
                    System.out.printf("| %-5s | %-20s | %-20s | %-20s | %-5s | %-20s | %-50s | %-10s | %-15s | %-7s | %-6s |\n",
                        resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8), resultSet.getString(9),
                        resultSet.getString(10), resultSet.getString(11));
                }
                break;
            }
            case 3: {
                //Додати оцінку студента
                sc.nextLine();
                String template_student = "SELECT st.id from students as st INNER JOIN people as p ON p.id=st.People_ID WHERE ";
                String template_insert = "INSERT INTO academic_performances (`Students_id`,`Subjects_code`,`Date_enter_marks`,`Marks`,`credits`) VALUES (";
                String template_find_subject_mark = "SELECT ap.id from academic_performances as ap WHERE ";
                String value = "";
                System.out.print("1. Прізвище: ");
                value=sc.nextLine();
                while(value.isEmpty()) {
                    System.out.print("1. Прізвище: ");
                    value=sc.nextLine();
                }
                template_student += "p.surname='"+value+"' ";
                System.out.print("2. Ім`я: ");
                value=sc.nextLine();
                while(value.isEmpty()) {
                    System.out.print("2. Ім`я: ");
                    value=sc.nextLine();
                }
                template_student += " AND p.name='"+value+"' ";
                System.out.print("3. По-батькові: ");
                value=sc.nextLine();
                while(value.isEmpty()) {
                    System.out.print("3. По-батькові: ");
                    value=sc.nextLine();
                }
                template_student += " AND p.last_name='"+value+"' ";
                System.out.print("4. Спеціальність: ");
                value=sc.nextLine();
                while(value.isEmpty()) {
                    System.out.print("4. Спеціальність: ");
                    value=sc.nextLine();
                }
                template_student += " AND st.Specialties_code='"+value+"';";
                resultSet = getData(template_student);
                int count = 0;
                String student_id="";
                while(resultSet.next()) {
                    student_id=resultSet.getString(1);
                    count++;
                }
                if(count==0) {
                    System.out.println("За такою спеціальністю студента не знайдено!");
                    break;
                }
                String deduction_check = "SELECT `Commandment_number` FROM deductions WHERE `Students_id`='"+student_id+"';";
                resultSet = getData(deduction_check);
                count = 0;
                while(resultSet.next()) {count++;}
                if(count!=0) {
                    System.out.println("Людину вже відраховано!");
                    break;
                }
                template_insert+="'"+student_id+"',";
                template_find_subject_mark+="ap.Students_id='"+student_id+"' ";
                System.out.print("5. Предмет: ");
                value=sc.nextLine();
                while(value.isEmpty()) {
                    System.out.print("5. Предмет: ");
                    value=sc.nextLine();
                }
                resultSet = getData("SELECT `code` from subjects where `name`='"+value+"';");
                count = 0;
                while(resultSet.next()) {
                    template_insert+="'"+resultSet.getString(1)+"',";
                    template_find_subject_mark+=" AND ap.Subjects_code='"+resultSet.getString(1)+"';";
                    count++;
                }
                if(count==0) {
                    System.out.println("Предмет не знайдено!");
                    break;
                }
                resultSet=getData(template_find_subject_mark);
                count = 0;
                while(resultSet.next()) {
                    count++;
                }
                if(count!=0) {
                    System.out.println("Оцінка вже виставлена!");
                    break;
                }
                template_insert+="'"+java.time.LocalDate.now()+"',";
                System.out.print("6. Оцінка від 60 до 100: ");
                value=sc.nextLine();
                while(value.isEmpty() && (value.length()!=2 && value.length()!=3) && (Integer.parseInt(value)<60 && Integer.parseInt(value)>100)) {
                    System.out.print("6. Оцінка від 60 до 100: ");
                    value=sc.nextLine();
                }
                template_insert+="'"+value+"',";
                System.out.print("6. Кредити: ");
                value=sc.nextLine();
                while(value.isEmpty() && value.length()!=1) {
                    System.out.print("6. Кредити: ");
                    value=sc.nextLine();
                }
                template_insert+="'"+value+"');";
                statement.executeUpdate(template_insert);
                break;
            }
            case 4: {
                //Рейтинг за параметрами
                sc.nextLine();
                String template = "SELECT p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted,SUM(ap.Marks*ap.credits)/SUM(ap.credits) AS 'rate' FROM academic_performances as ap INNER JOIN students AS st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID ";
                String value;
                System.out.print("1.Введіть прізвище: ");
                value = sc.nextLine();
                if(!value.isEmpty()){
                    if(template.compareTo("SELECT p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted,SUM(ap.Marks*ap.credits)/SUM(ap.credits) AS 'rate' FROM academic_performances as ap INNER JOIN students AS st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID ")==0) {
                        template += " WHERE p.surname = '"+value+"' ";
                    }
                    else {
                        template += " AND p.surname = '"+value+"' ";
                    }
                }
                System.out.print("2.Введіть ім'я: ");
                value = sc.nextLine();
                if(!value.isEmpty()){
                    if(template.compareTo("SELECT p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted,SUM(ap.Marks*ap.credits)/SUM(ap.credits) AS 'rate' FROM academic_performances as ap INNER JOIN students AS st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID ")==0) {
                        template += " WHERE p.name = '"+value+"' ";
                    }
                    else {
                        template += " AND p.name = '"+value+"' ";
                    }
                }
                System.out.print("3.Введіть по-батькові: ");
                value = sc.nextLine();
                if(!value.isEmpty()){
                    if(template.compareTo("SELECT p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted,SUM(ap.Marks*ap.credits)/SUM(ap.credits) AS 'rate' FROM academic_performances as ap INNER JOIN students AS st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID ")==0) {
                        template += " WHERE p.last_name = '"+value+"' ";
                    }
                    else {
                        template += " AND p.last_name = '"+value+"' ";
                    }
                }
                System.out.print("4.Введіть спеціальність: ");
                value = sc.nextLine();
                if(!value.isEmpty()){
                    if(template.compareTo("SELECT p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted,SUM(ap.Marks*ap.credits)/SUM(ap.credits) AS 'rate' FROM academic_performances as ap INNER JOIN students AS st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID ")==0) {
                        template += " WHERE st.Specialties_code = '"+value+"' ";
                    }
                    else {
                        template += " AND st.Specialties_code = '"+value+"' ";
                    }
                }
                System.out.print("5.Введіть рік вступу: ");
                value = sc.nextLine();
                if(!value.isEmpty()){
                    if(template.compareTo("SELECT p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted,SUM(ap.Marks*ap.credits)/SUM(ap.credits) AS 'rate' FROM academic_performances as ap INNER JOIN students AS st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID ")==0) {
                        template += " WHERE st.Year_accepted = '"+value+"' ";
                    }
                    else {
                        template += " AND st.Year_accepted = '"+value+"' ";
                    }
                }
                template += " GROUP BY ap.Students_id,p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted ORDER BY `rate` DESC;";
                System.out.println(template);
                resultSet = getData(template);
                System.out.printf("| %-20s | %-20s | %-20s | %-5s | %-15s | %-10s |\n",
                                     "Прізвище", "Ім'я","По-батькові","Спец.","Рік вступу","Оцінка");
                while(resultSet.next()) {
                    System.out.printf("| %-20s | %-20s | %-20s | %-5s | %-15s | %-10s |\n",
                        resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
                }
                break;
            }
            case 5: {
                //Стипендіальний рейтинг
                sc.nextLine();
                String template = "SELECT p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted,SUM(ap.Marks*ap.credits)/SUM(ap.credits) AS 'rate',st.id,st.Scholarship FROM academic_performances as ap INNER JOIN students AS st ON st.id=ap.Students_id INNER JOIN people as p ON p.id=st.People_ID ";
                String value;
                System.out.print("1.Введіть спеціальність: ");
                value = sc.nextLine();
                while(value.isEmpty()) {
                    System.out.print("1.Введіть спеціальність: ");
                    value = sc.nextLine();
                }
                
                String template_clear = "UPDATE students SET `ScholarShip`='Відсутня' WHERE `Specialties_code`='"+value+"' ";
                template+= " WHERE st.Specialties_code='"+value+"' ";
                System.out.print("2.Введіть рік вступу: ");
                value = sc.nextLine();
                while(value.isEmpty()) {
                    System.out.print("2.Введіть рік вступу: ");
                    value = sc.nextLine();
                }
                template_clear+="AND Year_accepted='"+value+"';";
                statement.executeUpdate(template_clear);
                template+= " AND st.Year_accepted='"+value+"' GROUP BY ap.Students_id,p.surname,p.name,p.last_name,st.Specialties_code,st.Year_accepted ORDER BY `rate` DESC;";
                resultSet = getData(template);
                int count = 0;
                while(resultSet.next()) {
                    count++;
                }
                count = (int) (0.4*(double)count);
                resultSet=getData(template);
                int check_count = 0;
                ArrayList<String> students_id = new ArrayList();
                ArrayList<String> students_academic_scholarship = new ArrayList();
                while(resultSet.next()) {
                    if(check_count<count) {
                        students_academic_scholarship.add(resultSet.getString(7));
                    }
                    else {
                         students_id.add(resultSet.getString(7));
                    }
                    check_count++;
                }
                for(int i = 0;i<students_academic_scholarship.size();i++) {
                    statement.executeUpdate("UPDATE students SET `ScholarShip`='академічна' WHERE `id`='"+students_academic_scholarship.get(i)+"';");
                }
                ArrayList<String> students_exceptions_id = new ArrayList();
                for(int i = 0;i<students_id.size();i++) {
                    resultSet = getData("SELECT `id` FROM students WHERE `id`='"+students_id.get(i)+"' AND `ScholarShip`!='академічна' AND `Exceptions_code`!=0;");
                    while(resultSet.next()) {
                        students_exceptions_id.add(resultSet.getString(1));
                    }
                }
                for(int i = 0;i<students_exceptions_id.size();i++){
                    statement.executeUpdate("UPDATE students SET `ScholarShip`='соціальна' WHERE `id`='"+students_exceptions_id.get(i)+"';");
//                    
                }
                resultSet = getData(template);
                System.out.printf("| %-20s | %-20s | %-20s | %-5s | %-15s | %-10s | %-20s |\n",
                                     "Прізвище", "Ім'я","По-батькові","Спец.","Рік вступу","Оцінка","Стипендія");
                while(resultSet.next()) {
                    System.out.printf("| %-20s | %-20s | %-20s | %-5s | %-15s | %-10s | %-20s |\n",
                        resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(8));
                }
                break;
            }



            default: {
                System.out.println("Невірний вибір!");
            }
        
        }
    }
    //Функція оформлення запитів типу SELECT до бази даних
    private ResultSet getData(String request) throws SQLException, ClassNotFoundException {
        return statement.executeQuery(request);
    }
}
