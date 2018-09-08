import Model.Student;
import Resolver.StudentResolver;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        StudentResolver studentResolver = new StudentResolver();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Menu: \n" +
                "1. Pokaż rekordy. \n" +
                "2. Dodaj rekord. \n" +
                "3. Usuń rekord [id=] \n" +
                "4. Nadpisz rekord [id=] \n" +
                "5. Koniec");

        int choose = scanner.nextInt();

        switch (choose) {
            case 1:
                List<Student> studentList = studentResolver.get();
                for (Student student : studentList){
                    System.out.println(student.getId() + " " + student.getName() + " " + student.getLastname());
                }
                break;
            case 2:
                Map<String, String> params = new HashMap<>();
                System.out.println("Podaj imie: ");
                String name = scanner.next();
                params.put("name", name);
                System.out.println("Podaj nazwisko: ");
                String lastname = scanner.next();
                params.put("lastname", lastname);
                studentResolver.insert(params);
                break;
            case 3:
                System.out.println("Podaj id: ");
                int id = scanner.nextInt();
                try {
                    studentResolver.delete(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                System.out.println("Podaj id do aktualizacji: ");
                int idU = scanner.nextInt();
                Map<String, String> updateParams = new HashMap<>();
                System.out.println("Podaj nowe imie i nazwisko: ");
                String nameU = scanner.next();
                updateParams.put("name", nameU);
                String lastnameU = scanner.next();
                updateParams.put("lastname", lastnameU);
                studentResolver.update(idU, updateParams);
                break;
            case 5:
                System.out.println("Elo");
                break;
            default:
                System.out.println("Fatal");
        }
    }
}
