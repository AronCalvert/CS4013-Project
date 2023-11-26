import java.util.List;

public class Faculty {
    private String facultyID;
    private String name;
    private String department;
    private List<Student> students; // This assumes access to a list of all students

    public Faculty(String facultyID, String name, String department, List<Student> students) {
        this.facultyID = facultyID;
        this.name = name;
        this.department = department;
        this.students = students;
    }

    public void viewStudentTranscript(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                System.out.println("Transcript for Student ID: " + studentID);
                student.viewTranscript();
                return;
            }
        }
        System.out.println("Student with ID " + studentID + " not found.");
    }

    public void submitStudentGrade(String studentID, String moduleCode, double grade) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                student.addGrade(moduleCode, grade);
                System.out.println("Grade submitted for Student ID: " + studentID + ", Module: " + moduleCode);
                return;
            }
        }
        System.out.println("Student with ID " + studentID + " not found.");
    }

    
}
