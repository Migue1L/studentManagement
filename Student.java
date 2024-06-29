public class Student {
	private int studentID;
	private String name;
	private int age;
	private String email;
	private int[][] grades; //This will be two array to store grades- work on it later...

    public Student(int studentID, String name, int age, String email) {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.email = email;
	this.grades = new int[6][2]; //There are 6 subjects with avg of 2 papers per in IB
    }

	public int getGrade(int subjectNum, int assessNum) {
		return grades[subjectNum][assessNum];
	}
	public void setGrade(int subjectNum, int assessNum, int grade) { //also shows constructors being used in these parameters
		grades[subjectNum][assessNum] = grade;
	}

	public int getStudentID() {
		return studentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGrades(int[][] grades) {
	this.grades = grades;
	}

    public int[][] getGrades() {
	return grades;
    }



    @Override
    public String toString() {
        return "Student ID: " + studentID +
               "\nName: " + name +
               "\nAge: " + age +
               "\nEmail: " + email;
    }
}


