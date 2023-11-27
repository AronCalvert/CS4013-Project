import java.io.*;
import java.util.*;

public class Admin {
	private String username = "Uladmin123";
	private String studentsFilepath;
	private String facultyFilepath;
	private Course course;
	//private ArrayList<Student> students;
	private ArrayList<Student> students;
	private ArrayList<Programme> programmes;
	private ArrayList<Faculty> faculties;
	private Student newStudent;
	
/*  •	Create programmes/courses
	•	Add modules
	•	Create faculty
	•	Add students to courses
	•	Create modules
*/
	Admin() {
		students = new ArrayList<Student>();
		programmes = new ArrayList<Programme>();
		faculties = new ArrayList<Faculty>();
	}
	
	public boolean login(String username, String password) {
		return (this.username.equals(username) && this.password.equals(password));
	}
	
	//add list of students
	public void createCourse(String faculty, String courseName, ModuleList modules) {
		course = new Course(faculty,courseName,modules);
	}
	
	public void createFaculty(String facultyName) {
		
	}
	public void createFaculty(String name, String department) {
        Faculty newFaculty = new Faculty(name, department);
        faculties.add(newFaculty);
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
	public void addModule(String progCode, int progYear, int semester, courseModule newModule) {
        Programme selectedProgramme = null;
        for (Programme programme : programmes) {
            if (programme.getProgCode().equals(progCode)) {
                selectedProgramme = programme;
                break;
            }
        }
        if (selectedProgramme != null) {
            selectedProgramme.addModule(progYear, semester, newModule);
        } else {
            System.out.println(progCode + " not found.");
        }
    }
	
	public void createProgramme(String progCode, String progName, int numberOfYears) {
        Programme newProgramme = new Programme(progCode, progName, numberOfYears);
        programmes.add(newProgramme);
    }
	
	public void createStudent(String studentID, String name, String program, String department, int yearOfStudy) {
		newStudent = new Student(studentID,name, program, department, yearOfStudy);
	}
	
	