import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private String fileName;

    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    public List<Student> loadStudentsFromFile() {
        List<Student> loadedStudents = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int studentID = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String email = parts[3];
                    Student student = new Student(studentID, name, age, email);
                    loadedStudents.add(student);
                }
            }
        } catch (IOException e) {
            System.out.println("There seems to be an error loading student data from file.");
            return null;
        }
        return loadedStudents;
    }

    public void saveStudentsToFile(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Student student : students) {
                String line = student.getStudentID() + "," + student.getName() + ","
                        + student.getAge() + "," + student.getEmail();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("There seems to be an error saving student data to file.");
        }
    }
}
