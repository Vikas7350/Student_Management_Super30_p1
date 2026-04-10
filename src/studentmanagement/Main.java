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
                case 4: updateStudent(); break;
                case 5: deleteStudent(); break;
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

    static void updateStudent() {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();

        ArrayList<Student> list = FileHandler.loadStudents();
        boolean found = false;

        try {
            ObjectOutputStream tempOut = new ObjectOutputStream(new FileOutputStream("temp.dat"));

            for (Student s : list) {
                if (s.getId() == id) {
                    found = true;
                    sc.nextLine();

                    System.out.print("Enter new Skills: ");
                    s.setSkills(sc.nextLine());

                    System.out.print("Enter new CGPA: ");
                    s.setCgpa(sc.nextDouble());

                    sc.nextLine();
                    System.out.print("Enter Placement Status: ");
                    s.setPlacementStatus(sc.nextLine());

                    System.out.print("Enter Company: ");
                    s.setCompany(sc.nextLine());
                }
                tempOut.writeObject(s);
            }

            tempOut.close();

            if (found) {
                new File("students.dat").delete();
                new File("temp.dat").renameTo(new File("students.dat"));
                System.out.println("Student updated successfully!");
            } else {
                new File("temp.dat").delete();
                System.out.println("Student not found!");
            }

        } catch (Exception e) {
            System.out.println("Error updating!");
        }
    }

    static void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();

        ArrayList<Student> list = FileHandler.loadStudents();
        boolean found = false;

        try {
            ObjectOutputStream tempOut = new ObjectOutputStream(new FileOutputStream("temp.dat"));

            for (Student s : list) {
                if (s.getId() == id) {
                    found = true;
                    continue; 
                }
                tempOut.writeObject(s);
            }

            tempOut.close();

            if (found) {
                new File("students.dat").delete();
                new File("temp.dat").renameTo(new File("students.dat"));
                System.out.println("Student deleted successfully!");
            } else {
                new File("temp.dat").delete();
                System.out.println("Student not found!");
            }

        } catch (Exception e) {
            System.out.println("Error deleting!");
        }
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