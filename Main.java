import java.util.Scanner;
import java.util.List;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentDatabase database = new StudentDatabase();

    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        //StudentDatabase database = new StudentDatabase();

        while (true) {
	    System.out.println("--------------------------------------");
            System.out.println("\nStudent Management System");
	    System.out.println("--------------------------------------");
            System.out.println("1. Add Student");
            System.out.println("2. Find Student by ID");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. List All Students");
            System.out.println("6. Input Student Grades");
	    System.out.println("7. Show Student Grades");
            System.out.println("8. Exit");
	    System.out.println("______________________________________");
            System.out.print("Choose an Option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
		    System.out.println("Adding a New Student");
		    System.out.println("--------------------------------------");
                    System.out.print("Enter Student ID: ");
                    int studentID = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    Student newStudent = new Student(studentID, name, age, email);
                    database.addStudent(newStudent);
                    System.out.println("\nStudent added successfully.");
                    break;
                case 2:
		    System.out.println("Finding a Student by ID");
		    System.out.println("--------------------------------------");
                    System.out.print("Enter Student ID to find: ");
                    int searchID = scanner.nextInt();
                    Student foundStudent = database.findStudentByID(searchID);
                    if (foundStudent != null) {
                        System.out.println(foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 3:
		    System.out.println("Updating a Student");
		    System.out.println("--------------------------------------");
                    System.out.print("Enter Student ID to update: ");
                    int updateID = scanner.nextInt();
                    Student existingStudent = database.findStudentByID(updateID);
                    if (existingStudent != null) {
                        System.out.print("Enter new Name: ");
                        String updatedName = scanner.nextLine();
			scanner.nextLine();
                        System.out.print("Enter new Age: ");
                        int updatedAge = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline?
                        System.out.print("Enter new Email: ");
                        String updatedEmail = scanner.nextLine();

                        existingStudent.setName(updatedName);
                        existingStudent.setAge(updatedAge);
                        existingStudent.setEmail(updatedEmail);
                        database.updateStudent(existingStudent);
                        System.out.println("Student updated successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
		    System.out.println("Deleting a Student");
		    System.out.println("--------------------------------------");
                    System.out.print("Enter Student ID to delete: ");
                    int deleteID = scanner.nextInt();
                    database.deleteStudent(deleteID);
                    System.out.println("Student deleted successfully.");
                    break;
                case 5:
		    System.out.println("Listing All Students");
		    System.out.println("--------------------------------------");
                    System.out.println("List of all students:");
			List<Student> allStudents = database.getAllStudents();
			if(allStudents.isEmpty()) {
				System.out.println("No students found");
			} else {
				System.out.printf("%-12s | %-30s | %-5s | %-30s%n", "Student ID", "Name", "Age", "Email");
				System.out.println("");
			for(Student student : allStudents) {
				System.out.printf("%-12d | %-30s | %-5d | %-30s%n", student.getStudentID(), student.getName(), student.getAge(), student.getEmail());
			}
//                    for (Student student : database.getAllStudents()) { *UPDATED*
//                        System.out.println(student); *Updated*
                    }
                    break;
                case 6:
			inputGrades();
		    break;
		case 7:
			System.out.print("Enter Student ID to show grades: ");
			System.out.println("--------------------------------------");
			int IDforGrade = scanner.nextInt();
			int[][] grades = database.getGrades(IDforGrade);
			if(grades != null) {
				System.out.println("Grades for Student ID " + IDforGrade + ";");
				for(int i = 0; i < grades.length; i++) {
				System.out.print("Subject " + (i + 1) + ": ");
				for(int j = 0; j < grades[i].length; j++) {
					System.out.print(grades[i][j] + " ");
				}
				System.out.println();
				}
			}
		    break;
		case 8:
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                    break;
                default: //simple error handling
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

	private static void inputGrades() {
		System.out.print("Enter Student ID to input grades: ");
		System.out.println("--------------------------------------");
			int studentID = scanner.nextInt();
			scanner.nextLine(); //new line !!!

			Student student = database.findStudentByID(studentID);
			if(student != null) {
				System.out.println("Enter grades for student: " + student.getName());
				for(int i = 0; i < 6; i++) { //THere are 6 main mandatory subjects in IB 12
					for(int j = 0; j < 2; j++) { //There are 2 papers per subject
						System.out.print("Enter grade for subject " + (i + 1) + ", assessment " + (j + 1) + ": ");
						int grade = scanner.nextInt();
						student.setGrade(i, j, grade);
					}
				}
				System.out.println("Grades were inputted successfully!");
				} else {
					System.out.println("Student was not found");
				}
	}


}
