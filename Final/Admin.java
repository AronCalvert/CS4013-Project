import java.io.*;
import java.util.*;

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

    Admin() {
        students = new ArrayList<Student>();
        programmes = new ArrayList<Programme>();
        faculties = new ArrayList<Faculty>();
    }

    public boolean login(String username, String password) {
        this.username = username;
        return (this.username.equals(username) && this.password.equals(password));
    }

    public void createFaculty(String name, String department) {
        Faculty newFaculty = new Faculty(name, department);
        faculties.add(newFaculty);
    }
    
    //adam
    public void addFaculty(Faculty newFac)
    {
        faculties.add(newFac);
    }
    
    //adam
    public ArrayList<Faculty> getFaculty()
    {
        return faculties;
    }
    
    /*
    public void createModule(String moduleCode, String title, int creditValue) {
        newModule = new courseModule(moduleCode, title, creditValue);
    }
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

    public void createProgramme(String progCode, String progName, int numberOfYears) {
        Programme newProgramme = new Programme(progCode, progName, numberOfYears);
        programmes.add(newProgramme);
    }
    
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

    public void viewFaculties() {
        for (Faculty faculty : faculties) {
            System.out.println("Name: " + faculty.getName() + ", Department: " + faculty.getDepartment());
        }
    }

    public void viewStudents() {
        for (Student student : students) {
            System.out.println("ID: " + student.getStudentID() + ", Name: " + student.getName() + ", Programme: " + student.getProgramme());
        }
    }

    public List<courseModule> getModules() {
        List<courseModule> allModules = new ArrayList<>();

        for (programmeYear year : programmeYears) {
            allModules.addAll(year.sem1);
            allModules.addAll(year.sem2); 
        }

        return allModules;
    }

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

    public Student getStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public void assignModuleToFaculty(String facultyName, courseModule module) {
        for (Faculty faculty : faculties) {
            if (faculty.getName().equals(facultyName)) {
                faculty.addModule(module.getModuleCode());
                break;
            }
        }
    }

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
