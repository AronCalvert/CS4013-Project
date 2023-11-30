import java.util.Scanner;

public class CommandLineInterface {
    static Admin admin = new Admin();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean mainExit = false;
        while (!mainExit) {
            System.out.println("Welcome to the University Management System");
            System.out.println("Please choose your role:");
            System.out.println("1. Student");
            System.out.println("2. Faculty");
            System.out.println("3. Admin");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleStudentOperations(scanner);
                    break;
                case 2:
                    handleFacultyOperations(scanner);
                    break;
                case 3:
                    handleAdminOperations(scanner);
                    break;
                case 4:
                    mainExit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void handleAdminOperations(Scanner scanner) {
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
            System.out.println("7. View Faculties");
            System.out.println("8. View Students");
            System.out.println("9. View Programmes");
            System.out.println("10. Exit");

            int operationChoice = scanner.nextInt();
            switch (operationChoice) {

                case 1: // Create Faculty
                    scanner.nextLine();
                    System.out.println("Enter faculty name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter faculty department:");
                    String department = scanner.nextLine();
                    admin.createFaculty(name, department);
                    System.out.println("Faculty created successfully.");
                    break;

                case 2: // Create Module
                    scanner.nextLine();
                    System.out.println("Enter module code:");
                    String moduleCode = scanner.nextLine();
                    System.out.println("Enter module title:");
                    String title = scanner.nextLine();
                    System.out.println("Enter credit value:");
                    int creditValue;
                    try {
                        creditValue = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input for credit value. Please enter a number.");
                        continue;
                    }
                    admin.createModule(moduleCode, title, creditValue);
                    System.out.println("Module created successfully.");
                    break;

                case 3: // Add Module to Programme
                    scanner.nextLine();
                    System.out.println("Enter programme code:");
                    String progCode = scanner.nextLine();

                    System.out.println("Enter programme year:");
                    int progYear;
                    try {
                        progYear = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input for programme year. Please enter a number.");
                        continue;
                    }

                    System.out.println("Enter semester:");
                    int semester;
                    try {
                        semester = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input for semester. Please enter a number.");
                        continue;
                    }

                    // Inputs for creating courseModule
                    System.out.println("Enter module code:");
                    String moduleCode1 = scanner.nextLine();
                    System.out.println("Enter module title:");
                    String moduleTitle = scanner.nextLine();
                    System.out.println("Enter credit value:");
                    int moduleCreditValue;
                    try {
                        moduleCreditValue = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input for credit value. Please enter a number.");
                        continue;
                    }

                    // Create a courseModule instance
                    courseModule cm = new courseModule(moduleCode1, moduleTitle, moduleCreditValue);

                    // Add the courseModule to the programme
                    admin.addModule(progCode, progYear, semester, cm);
                    System.out.println("Module added to programme successfully.");
                    break;

                case 4: // Create Programme
                    scanner.nextLine();
                    System.out.println("Enter programme code:");
                    String programmeCode = scanner.nextLine();
                    System.out.println("Enter programme name:");
                    String programmeName = scanner.nextLine();
                    System.out.println("Enter number of years:");
                    int numberOfYears;
                    try {
                        numberOfYears = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input for number of years. Please enter a number.");
                        continue;
                    }
                    admin.createProgramme(programmeCode, programmeName, numberOfYears);
                    System.out.println("Programme created successfully.");
                    break;
                case 5: // Create Student
                    scanner.nextLine();
                    System.out.println("Enter student ID:");
                    String studentID = scanner.nextLine(); // Read the entire line

                    System.out.println("Enter student name:");
                    String studentName = scanner.nextLine(); // Read the entire line

                    System.out.println("Enter program:");
                    String program = scanner.nextLine(); // Read the entire line

                    System.out.println("Enter department:");
                    String studentDepartment = scanner.nextLine(); // Read the entire line

                    System.out.println("Enter year of study:");
                    int yearOfStudy;
                    try {
                        yearOfStudy = Integer.parseInt(scanner.nextLine()); // Parse the integer from the line
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input for year of study. Please enter a number.");
                        continue; // Go back to the start of the loop to retry
                    }

                    admin.createStudent(studentID, studentName, program, studentDepartment, yearOfStudy);
                    System.out.println("Student created successfully.");
                    break;

                case 6: // Get Transcript
                    admin.getTranscript();
                    System.out.println("Transcript generated successfully.");
                    break;
                case 7: // View Faculties
                    admin.viewFaculties();
                    break;
                case 8: // View Students
                    admin.viewStudents();
                    break;
                case 9: // View Programmes
                    admin.viewProgrammes();
                    break;
                case 10: // Exit
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void handleFacultyOperations(Scanner scanner) {
        scanner.next();
        System.out.println("Enter Faculty Name:");
        String facultyName = scanner.next();
        Faculty faculty = admin.getFacultyByName(facultyName);
        if (faculty == null) {
            System.out.println("Faculty not found.");
            return;
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an operation:");
            System.out.println("1. View assigned modules");
            System.out.println("2. Assign grades to students");
            System.out.println("3. Exit");

            int operationChoice = scanner.nextInt();
            switch (operationChoice) {
                case 1:
                    for (courseModule module : faculty.getAssignedModules()) {
                        System.out.println("Module: " + module.getModuleName());
                    }
                    break;
                case 2:
                    // Implement grade assignment functionality here
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void handleStudentOperations(Scanner scanner) {
        System.out.println("Enter Student ID:");
        String studentId = scanner.next();
        Student student = admin.getStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an operation:");
            System.out.println("1. View transcript");
            System.out.println("2. Exit");
            int operationChoice = scanner.nextInt();

            switch (operationChoice) {
                case 1:
                    student.viewTranscript();
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
