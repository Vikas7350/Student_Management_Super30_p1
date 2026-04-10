package studentmanagement;
import java.util.*;
import java.io.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Student> list = FileHandler.loadStudents();

        while (true) {
            System.out.println("\n===== Student Placement Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Sort Students");
            System.out.println("7. Show Topper");
            System.out.println("8. Count Students");
            System.out.println("9. Export to Text File");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();

            switch (ch) {
                case 1: addStudent(list); break;
                case 2: viewStudents(list); break;
                case 3: searchStudent(list); break;
                case 4: updateStudent(list); break;
                case 5: deleteStudent(list); break;
                case 6: sortStudents(list); break;
                case 7: showTopper(list); break;
                case 8: countStudents(list); break;
                case 9: exportData(list); break;
                case 10:
                    FileHandler.saveStudents(list);
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void addStudent(ArrayList<Student> list) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        for (Student s : list) {
            if (s.getId() == id) {
                System.out.println("ID already exists!");
                return;
            }
        }

        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Branch: ");
        String branch = sc.nextLine();

        System.out.print("Enter Skills: ");
        String skills = sc.nextLine();

        System.out.print("Enter CGPA: ");
        double cgpa = sc.nextDouble();

        sc.nextLine();
        System.out.print("Placement Status (Placed/NotPlaced): ");
        String status = sc.nextLine();

        String company = "-";
        if (status.equalsIgnoreCase("Placed")) {
            System.out.print("Enter Company: ");
            company = sc.nextLine();
        }

        list.add(new Student(id, name, branch, skills, cgpa, status, company));
        System.out.println("Student added successfully!");
    }

    static void viewStudents(ArrayList<Student> list) {
        for (Student s : list) {
            System.out.println(s);
        }
    }

    static void searchStudent(ArrayList<Student> list) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        for (Student s : list) {
            if (s.getId() == id) {
                System.out.println("Found: " + s);
                return;
            }
        }
        System.out.println("Student not found!");
    }

    static void updateStudent(ArrayList<Student> list) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        for (Student s : list) {
            if (s.getId() == id) {
                System.out.println("1. Skills\n2. CGPA\n3. Placement");
                int ch = sc.nextInt();

                sc.nextLine();
                if (ch == 1) {
                    System.out.print("Enter Skills: ");
                    s.setSkills(sc.nextLine());
                } else if (ch == 2) {
                    System.out.print("Enter CGPA: ");
                    s.setCgpa(sc.nextDouble());
                } else if (ch == 3) {
                    System.out.print("Enter Status: ");
                    String status = sc.nextLine();
                    s.setPlacementStatus(status);
                }

                System.out.println("Updated successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    static void deleteStudent(ArrayList<Student> list) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        Iterator<Student> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == id) {
                it.remove();
                System.out.println("Deleted successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    static void sortStudents(ArrayList<Student> list) {
        System.out.println("1. CGPA\n2. Name\n3. ID");
        int ch = sc.nextInt();

        if (ch == 1) {
            list.sort((a, b) -> Double.compare(b.getCgpa(), a.getCgpa()));
        } else if (ch == 2) {
            list.sort(Comparator.comparing(Student::getName));
        } else {
            list.sort(Comparator.comparingInt(Student::getId));
        }

        System.out.println("Sorted!");
    }

    static void showTopper(ArrayList<Student> list) {
        if (list.isEmpty()) return;

        Student top = Collections.max(list, Comparator.comparingDouble(Student::getCgpa));
        System.out.println("Topper: " + top);
    }

    static void countStudents(ArrayList<Student> list) {
        System.out.println("Total Students: " + list.size());
    }

    static void exportData(ArrayList<Student> list) {
        try (PrintWriter pw = new PrintWriter("report.txt")) {
            for (Student s : list) {
                pw.println(s);
            }
            System.out.println("Exported to report.txt");
        } catch (Exception e) {
            System.out.println("Error exporting!");
        }
    }
}