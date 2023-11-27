import java.io.*;
import java.util.*;

public class Admin {
	private String username = "Uladmin123";
	private String password = "Password";
	private Module newModule;
	private String studentsFilepath;
	private String facultyFilepath;
	private Course course;
	private ArrayList<Student> students;
	private ArrayList<Programme> programmes;
	private ArrayList<Faculty> faculties;
	private Student newStudent;
	
/*  •	Create programmes/courses
	•	Add modules
	•	Create faculty
	•	Assign faculty to courses
	•	Create students
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
	
	public void createFaculty(String name, String department) {
        Faculty newFaculty = new Faculty(name, department);
        faculties.add(newFaculty);
    }
	
	public void createModule(String faculty, String moduleCode, String title, int creditValue) {
		newModule = new Module(faculty, moduleCode, title, creditValue);
	}
	
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

	public void getTranscript() {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
			writer.write("User: " + username);
			writer.newLine();
			writer.write("Students: ");
			writer.newLine();
			for (Student student: students) {
				writer.write(student.getStudentID() + "   " + student.getName());
				writer.newLine();
			}
			
			writer.write("Faculties: ");
			writer.newLine();
			for(Faculty faculty: faculties) {
				writer.write(faculty.getName());
				writer.newLine();
			}
			
			writer.write("Courses: ");
			writer.newLine();
			for (Programme programme: programmes) {
				writer.write(programme.getProgCode() + "   " + programme.getName());
				writer.newLine();
			
			writer.close();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
	
