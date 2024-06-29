import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class StudentDatabase {
	private List<Student> students;
	private FileHandler fileHandler;
	private final int num_subject = 6; //# of subjects vased on clients need
	private final int num_assess = 2; // # of assessments in clients IB school


    public StudentDatabase() {
        students = new ArrayList<>();
	fileHandler = new FileHandler("student_data.txt");
	loadStudentDataFromFile();
    }

    public void addStudent(Student student) { //Use index to insert based on ID
	int index = 0;
	for (Student s : students) {
		if(s.getStudentID() > student.getStudentID()) {
			break;
		}
		index++;
	}
        students.add(index, student); //So now student is at the correct position based on ID
	saveStudentDataToFile();
    }

    public Student findStudentByID(int studentID) { //trying to do insertion sorting.;
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }

    public void updateStudent(Student updatedStudent) {
        int index = students.indexOf(updatedStudent);
        if (index != -1) {
            students.set(index, updatedStudent);
	    saveStudentDataToFile();
        }
    }

    public void deleteStudent(int studentID) {
        students.removeIf(student -> student.getStudentID() == studentID);
	saveStudentDataToFile();
    }

    public List<Student> getAllStudents() {
        return students;
    }

    private void loadStudentDataFromFile() {
	List<Student> loadedStudents = fileHandler.loadStudentsFromFile();
	if(loadedStudents != null) {
		students.addAll(loadedStudents);
		students.sort(Comparator.comparingInt(Student::getStudentID)); //Comparator needed to sort students based on ID
	}
    }

    private void saveStudentDataToFile() {
	fileHandler.saveStudentsToFile(students);
    }

    public void addGrades(int studentID, int[][] grades) {
	Student student = findStudentByID(studentID);
	if(student != null) {
	    student.setGrades(grades);
	    saveStudentDataToFile();
	} else {
	    System.out.println("Student was not found, try again!");
	}
    }

    public int[][] getGrades(int studentID) {
	Student student = findStudentByID(studentID);
	if(student != null) {
		return student.getGrades();
	} else {
		System.out.println("Student was not found!");
		return null;
	}
    }
}
