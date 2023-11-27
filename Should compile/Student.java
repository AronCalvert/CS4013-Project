import java.util.HashMap;
import java.io.*;

public class Student {
    private String studentID;
    private String name;
    private String program;
    private String department;
    private int yearOfStudy;
    private HashMap<String, Double> grades; // Store module code and grade
    private String output = "Desktop/output.csv";
    
    public Student(String studentID, String name, String program, String department, int yearOfStudy) {
        this.studentID = studentID;
        this.name = name;
        this.program = program;
        this.department = department;
        this.yearOfStudy = yearOfStudy;
        this.grades = new HashMap<>();
    }
    
    public void addGrade(String moduleCode, double grade) {
        grades.put(moduleCode, grade);
    }
    
    public double calculateQCA() {
        double totalGrades = 0;
        for (double grade : grades.values()) {
            totalGrades += grade;
        }
        return grades.size() > 0 ? totalGrades / grades.size() : 0;
    }
    
    public void viewTranscript() {
        System.out.println("Transcript for " + name);
        for (String module : grades.keySet()) {
            System.out.println("Module: " + module + ", Grade: " + grades.get(module));
        }
    }
    
    public boolean checkProgression() {
        double qca = calculateQCA();
        return qca >= 2.0;
    }
    
    public String getStudentID() {
        return this.studentID;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void getTranscript() {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
			writer.write("Student Transcript");
			writer.newLine();
			writer.newLine();
			writer.write("Name          " + this.name);
			writer.newLine();
			writer.newLine();
			writer.write("Student ID    " + this.studentID);
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
}