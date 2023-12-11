import java.io.*;
import java.util.*;

/**
 * The Admin class represents an administrator in the educational system.
 * It manages operations such as login, faculty and program creation, student management, and provides methods for viewing information.
 * 
 * Instances of this class are responsible for handling administrative operations and maintaining information about students, faculties, and programs.
 * 
 * 
 * @author (Paudie Kelly, 22342842);(Ella Curtin, 22363564)
 * @version (4.0)
 */
public class Admin {
    private String username = "Uladmin123";
    private String password = "Password";
    //private courseModule newModule; // Changed from Module to courseModule
    private String studentsFilepath;
    private String facultyFilepath;
    //private Course course;
    private ArrayList<Student> students;
    private ArrayList<Programme> programmes; 
    private ArrayList<Faculty> faculties;
    private Student newStudent;
    private String output = "/Users/paudie/Desktop/output.csv";
    private ArrayList<programmeYear> programmeYears;

    /**
     * Default constructor for objects of class Admin.
     * Initializes empty lists for students, programs, and faculties.
     */
    Admin() {
        students = new ArrayList<Student>();
        programmes = new ArrayList<Programme>();
        faculties = new ArrayList<Faculty>();
    }

    /**
     * Attempts to log in with the specified username and password.
     * 
     * @param username The username for login.
     * @param password The password for login.
     * @return true if login is successful, false otherwise.
     */
    public boolean login(String username, String password) {
        this.username = username;
        return (this.username.equals(username) && this.password.equals(password));
    }

    /**
     * Creates a new faculty with the given name and department.
     * 
     * @param name       The name of the faculty.
     * @param department The department of the faculty.
     */
    public void createFaculty(String name, String department) {
        Faculty newFaculty = new Faculty(name, department);
        faculties.add(newFaculty);
    }
    
    //adam
    /**
     * Adds an existing faculty to the list of faculties.
     * 
     * @param newFac The faculty to be added.
     */
    public void addFaculty(Faculty newFac)
    {
        faculties.add(newFac);
    }
    
    //adam
    /**
     * Retrieves the list of faculties.
     * 
     * @return The list of faculties.
     */
    public ArrayList<Faculty> getFaculty()
    {
        return faculties;
    }
    
    /*
    public void createModule(String moduleCode, String title, int creditValue) {
        newModule = new courseModule(moduleCode, title, creditValue);
    }
    */
   
    /**
     * Adds a module to a specific program, year, and semester.
     * 
     * @param progCode  The code of the program.
     * @param progYear  The year of the program.
     * @param semester  The semester of the program.
     * @param module    The module to be added.
     */
    public void addModule(String progCode, int progYear, int semester, courseModule module) {
        Programme selectedProgramme = null;
        for (Programme programme : programmes) {
            if (programme.getProgCode().equals(progCode)) {
                selectedProgramme = programme;
                break;
            }
        }
        if (selectedProgramme != null) {
            selectedProgramme.addModule(progYear, semester, module);
        } else {
            System.out.println(progCode + " not found.");
        }
    }

    /**
     * Creates a new program with the given code, name, and number of years.
     * 
     * @param progCode       The code of the program.
     * @param progName       The name of the program.
     * @param numberOfYears  The number of years in the program.
     */
    public void createProgramme(String progCode, String progName, int numberOfYears) {
        Programme newProgramme = new Programme(progCode, progName, numberOfYears);
        programmes.add(newProgramme);
    }
    
    /**
     * Retrieves a program by its code.
     * 
     * @param progCode The code of the program.
     * @return The program with the specified code, or {@code null} if not found.
     */
    public Programme getProgramme(String progCode) {
        Programme selectedProgramme = null;
        for (Programme programme : programmes) {
            if (programme.getProgCode().equals(progCode)) {
                selectedProgramme = programme;
                break;
            }
        }
        return selectedProgramme;
    }
    
    /**
     * Creates a new student with the given ID, name, programme, year of study, and registration year.
     * 
     * @param studentID  The ID of the student.
     * @param name       The name of the student.
     * @param programme  The programme of the student.
     * @param yearOfStudy The year of study of the student.
     * @param regYear    The registration year of the student.
     */
    public void createStudent(String studentID, String name, Programme programme, int yearOfStudy,int regYear) {
        newStudent = new Student(studentID, name, programme, yearOfStudy, regYear);
        students.add(newStudent);
    }

    /*public void getTranscript() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            writer.write("User: " + username);
            writer.newLine();
            writer.write("Students: ");
            writer.newLine();
            for (Student student : students) {
                writer.write(student.getStudentID() + "   " + student.getName());
                writer.newLine();
            }

            writer.write("Faculties: ");
            writer.newLine();
            for (Faculty faculty : faculties) {
                writer.write(faculty.getName());
                writer.newLine();
            }

            writer.write("Courses: ");
            writer.newLine();
            for (Programme programme : programmes) {
                writer.write(programme.getProgCode() + "   " + programme.getName());
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Displays information about faculties.
     */
    public void viewFaculties() {
        for (Faculty faculty : faculties) {
            System.out.println("Name: " + faculty.getName() + ", Department: " + faculty.getDepartment());
        }
    }

    /**
     * Displays information about students.
     */
    public void viewStudents() {
        for (Student student : students) {
            System.out.println("ID: " + student.getStudentID() + ", Name: " + student.getName() + ", Programme: " + student.getProgramme());
        }
    }

    /**
     * Retrieves a list of all modules across program years.
     * 
     * @return A list of course modules.
     */
    public List<courseModule> getModules() {
        List<courseModule> allModules = new ArrayList<>();

        for (programmeYear year : programmeYears) {
            allModules.addAll(year.sem1);
            allModules.addAll(year.sem2); 
        }

        return allModules;
    }

    /**
     * Displays information about programs and associated modules.
     */
    public void viewProgrammes() {
        if (programmes.isEmpty()) {
            System.out.println("No programmes available.");
            return;
        }

        System.out.println("List of Programmes:");
        for (Programme programme : programmes) {
            String progCode = programme.getProgCode();
            String progName = programme.getName();
            int numberOfYears = programme.getNumberOfYears();

            System.out.println("Programme Code: " + progCode + ", Name: " + progName + ", Duration: " + numberOfYears + " years");

            // Optionally, you can also print the modules for each year of the programme
            List<courseModule> modules = programme.getModules();
            for (courseModule module : modules) {
                System.out.println("\tModule: " + module.getModuleCode() + " - " + module.getModuleName());
            }
        }
    }

    /**
     * Retrieves a student by their ID.
     * 
     * @param studentId The ID of the student to retrieve.
     * @return The student with the specified ID, or {@code null} if not found.
     */
    public Student getStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    /**
     * Assigns a module to a specific faculty.
     * 
     * @param facultyName The name of the faculty.
     * @param module      The module to be assigned.
     */
    public void assignModuleToFaculty(String facultyName, courseModule module) {
        for (Faculty faculty : faculties) {
            if (faculty.getName().equals(facultyName)) {
                faculty.addModule(module.getModuleCode());
                break;
            }
        }
    }

    /**
     * Retrieves a faculty by its name.
     * 
     * @param facultyName The name of the faculty to retrieve.
     * @return The faculty with the specified name, or {@code null} if not found.
     */
    public Faculty getFacultyByName(String facultyName) {
        for (Faculty faculty : faculties) {
            if (faculty.getName().equals(facultyName)) {
                return faculty;
            }
        }
        return null; // Return null if the faculty is not found

    }
    
    /*
    public courseModule getNewModule()
    {
        courseModule Module = new courseModule(newModule.getModuleCode(), newModule.getModuleName(), newModule.getModuleCredits());
        return Module;
    }
    */
}
