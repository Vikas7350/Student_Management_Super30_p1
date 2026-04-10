package studentmanagement;
import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String FILE_NAME = "students.dat";

    public static void saveStudents(ArrayList<Student> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.out.println("Error saving data");
        }
    }

    public static ArrayList<Student> loadStudents() {
        ArrayList<Student> list = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            list = (ArrayList<Student>) ois.readObject();
        } catch (Exception e) {
            // File not found initially
        }
        return list;
    }
}