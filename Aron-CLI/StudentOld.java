import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class StudentOld {
    private String studentID;
    private String name;
    private String program;
    private String department;
    private int yearOfStudy;
    private HashMap<String, Double> grades;
    private String output = "Desktop/output.csv";

    public StudentOld(String studentID, String name, String program, String department, int yearOfStudy) {
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

    public String getProgram(){
        return this.program;
    }

    public int getYearOfStudy(){
        return this.yearOfStudy;
    }

    public void getTranscript() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            writer.write("Student Transcript");
            writer.newLine();
            writer.newLine();
            writer.write("Name: " + this.name);
            writer.newLine();
            writer.write("Student ID: " + this.studentID);
            writer.newLine();
            writer.newLine();
            for (Map.Entry<String, Double> entry : grades.entrySet()) {
                writer.write("Module: " + entry.getKey() + ", Grade: " + entry.getValue());
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}