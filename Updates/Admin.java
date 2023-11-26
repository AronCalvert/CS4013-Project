import java.io.*;

public class Admin {
	private String username = "Uladmin123";
	private String password = "Password";
	private Module newModule;
	private String studentsFilepath;
	private String facultyFilepath;
	private Course course;
	//private ArrayList<Student> students;
/*  •	Create programmes/courses
	•	Add modules
	•	Create faculty
	•	Assign faculty to courses
	•	Create students
	•	Add students to courses
	•	Create modules
*/
	
	public boolean login(String username, String password) {
		return (this.username.equals(username) && this.password.equals(password));
	}
	
	//add list of students
	public void createCourse(String faculty, String courseName, ModuleList modules) {
		course = new Course(faculty,courseName,modules);
	}
	
	public void createFaculty(String facultyName) {
		
	}
	
	public void createModule(String faculty, String moduleCode, String title, int creditValue) {
		newModule = new Module(faculty, moduleCode, title, creditValue);
	}
	
	// Add whatever Adam did to the parameters and method
	public void createStudent(String studentID) {
		/*
		if (students.contains(studentID) {
			this.studentsFilepath = studentID + ".csv";
		} else {
			System.out.println("Student does not exist");
		} */
	}
	
	
	
}
	
