import java.util.Scanner;
import java.util.ArrayList;

public class MainApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the University Management System");
        System.out.println("Please choose your role:");
        System.out.println("1. Student");
        System.out.println("2. Faculty");
        System.out.println("3. Admin");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // Handle student login or operations
                break;
            case 2:
                handleFacultyOperations(scanner);
                break;
            case 3:
                handleAdminOperations(scanner);
                break;
            default:
                System.out.println("Invalid choice. Exiting.");
        }

        scanner.close();
    }

    private static void handleAdminOperations(Scanner scanner) {
        Admin admin = new Admin();

        // Admin login
        System.out.println("Please enter admin username:");
        String username = scanner.next();
        System.out.println("Please enter admin password:");
        String password = scanner.next();

        if (!admin.login(username, password)) {
            System.out.println("Login failed. Exiting.");
            return;
        }
        System.out.println("Login successful.");

        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an operation:");
            System.out.println("1. Create Faculty");
            System.out.println("2. Create Module");
            System.out.println("3. Add Module to Programme");
            System.out.println("4. Create Programme");
            System.out.println("5. Create Student");
            System.out.println("6. Get Transcript");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1: // Create Faculty
                    System.out.println("Enter faculty name:");
                    String name = scanner.next();
                    System.out.println("Enter faculty department:");
                    String department = scanner.next();
                    admin.createFaculty(name, department);
                    System.out.println("Faculty created successfully.");
                    break;
                case 2: // Create Module
                    System.out.println("Enter module code:");
                    String moduleCode = scanner.next();
                    System.out.println("Enter module title:");
                    String title = scanner.next();
                    System.out.println("Enter credit value:");
                    int creditValue = scanner.nextInt();
                    admin.createModule(moduleCode, title, creditValue);
                    System.out.println("Module created successfully.");
                    break;
                case 3: // Add Module to Programme

                    System.out.println("Enter programme code:");
                    String progCode = scanner.next();
                    System.out.println("Enter programme year:");
                    int progYear = scanner.nextInt();
                    System.out.println("Enter semester:");
                    int semester = scanner.nextInt();

                    // Convert Module to courseModule before adding
                    courseModule cm = admin.convertToCourseModule(admin.newModule);
                    if (cm != null) {
                        admin.addModule(progCode, progYear, semester, cm);
                        System.out.println("Module added to programme successfully.");
                    } else {
                        System.out.println("Error: No module to add.");
                    }
                    break;

                case 4: // Create Programme
                    System.out.println("Enter programme code:");
                    String programmeCode = scanner.next();
                    System.out.println("Enter programme name:");
                    String programmeName = scanner.next();
                    System.out.println("Enter number of years:");
                    int numberOfYears = scanner.nextInt();
                    admin.createProgramme(programmeCode, programmeName, numberOfYears);
                    System.out.println("Programme created successfully.");
                    break;
                case 5: // Create Student
                    System.out.println("Enter student ID:");
                    String studentID = scanner.next();
                    System.out.println("Enter student name:");
                    String studentName = scanner.next();
                    System.out.println("Enter program:");
                    String program = scanner.next();
                    System.out.println("Enter department:");
                    String studentDepartment = scanner.next();
                    System.out.println("Enter year of study:");
                    int yearOfStudy = scanner.nextInt();
                    admin.createStudent(studentID, studentName, program, studentDepartment, yearOfStudy);
                    System.out.println("Student created successfully.");
                    break;
                case 6: // Get Transcript
                    admin.getTranscript();
                    System.out.println("Transcript generated successfully.");
                    break;
                case 7: // Exit
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void handleFacultyOperations(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an operation:");
            System.out.println("1. View assigned modules");
            System.out.println("2. View list of students in a module");
            System.out.println("3. Assign grades to students");
            System.out.println("4. Exit");
        }
    }

    private static void handleStudentOperations(Scanner scanner){
        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an operation:");
            System.out.println("1. View transcript");
        }
    }
}

