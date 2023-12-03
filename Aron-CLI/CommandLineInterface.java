import java.util.Scanner;
import java.io.*;
import java.util.*;

public class CommandLineInterface {
    static Admin admin = new Admin();

    public static void main(String[] args) throws IOException {
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
            scanner.nextLine();

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

    private static void handleAdminOperations(Scanner scanner) throws IOException {
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
                    programmeSaveCSV(admin.getProgramme(progCode));
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

                    System.out.println("Enter programme code:");
                    String program = scanner.nextLine(); // Read the entire line
                    //TODO adam, fix prog details
                    //Programme programme = new Programme(program, "", 0);
                    Programme programme = programmeReadCSV("XY123");

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

                    //TODO adam, fix regYear
                    admin.createStudent(studentID, studentName, programme, yearOfStudy, 0);
                    Student newStudent = new Student(studentID, studentName, programme, yearOfStudy, 0);
                    studentSaveCSV(newStudent);
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

    /**
     * handleFacultyOperations displays the faculty menu.
     * 
     * @param Scanner keyboard, input. 
     * 
     * @return VOID.
     * 
     * @author (Adam Fogarty, 22367748);(Aron Calvert, 22370374)
     * @version (2.0)
     */
    private static void handleFacultyOperations(Scanner keyboard) throws IOException {
        //Scanner keyboard = new Scanner(System.in);
        boolean facultyFound = false;
        boolean exit = false;
        Faculty facultyDetails = null;
        
        do{
            System.out.println(new String(new char[50]).replace("\0", "\r\n")); //clear console
            System.out.printf("Enter Faculty Name to Continue: ");
            String name = keyboard.nextLine();
            facultyDetails = facultyFindInCSV(name);
            if(facultyDetails !=null )
            {
                facultyFound = true;
                System.out.println("Welcome " + facultyDetails.getName() +  "\n");
                //System.out.printf(facultyDetails.getCSVString());
            }
            else
            {
                System.out.println("Name not found!");
                System.out.println("Press 'X' to exit or 'Enter' to continue.");
                String command = keyboard.nextLine().toUpperCase();
                if(command.equals("X")){
                    exit = true;
                }
            }
        }
        while((facultyFound == false)&&(exit == false));
        
        
        while (exit==false) {
            
            System.out.println("Choose an operation:");
            System.out.println("1. View assigned modules");
            System.out.println("2. Assign grades to students");
            System.out.println("3. Exit");

            int operationChoice = keyboard.nextInt();
            switch (operationChoice) {
                case 1:
                {
                    System.out.println("Your assigned modules are: ");
                    ArrayList<String> modules = facultyDetails.getModules();
                    for(int i = 0; i < modules.size(); i ++)
                    {
                        System.out.println(modules.get(i));
                    }
                    break;
                }
                case 2:
                {
                    // Implement grade assignment functionality here
                    break; 
                }

                case 3:
                {
                    exit = true;
                    break;
                }
                
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
                    //student.viewTranscript();
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    
    /**
     * facultySaveCSV writes all faculty details to CSV file, "Faculty.CSV".
     * 
     * @param ArrayList<Faculty> allFac, ArrayList containing all faculty in university.
     * 
     * @return VOID.
     * 
     * @author (Adam Fogarty, 22367748)
     * @version (1.0)
     */
    public static void facultySaveCSV(ArrayList<Faculty> allFac) throws IOException
    {
        String fileName = "Faculty.CSV";
        
        FileWriter myWriter = new FileWriter(fileName);
        try
        {
            String CSVout = new String("Name,Department,Modules" + "\n");
            myWriter.write(CSVout);
            for(int i = 0; i < allFac.size(); i ++)
            {
                CSVout = allFac.get(i).getCSVString();
                myWriter.write(CSVout);
            }
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
    }
    
    /**
     * facultyFindInCSV finds a single faculty in CSV file, "Faculty.CSV".
     *
     * @param String name, name of faculty member to find.
     * 
     * @return Faculty, Details for faculty member if found, null if not found.
     * 
     * @author (Adam Fogarty, 22367748)
     * @version (1.0)
     */
    public static Faculty facultyFindInCSV(String name) throws IOException,FileNotFoundException
    {
        Faculty Fac = null;
        
        BufferedReader x = new BufferedReader(new FileReader("Faculty.CSV"));
        List<String> CSVdetails;
        CSVdetails = readCSVline(x);
        do {
            CSVdetails = readCSVline(x);
            if(CSVdetails != null && CSVdetails.get(0).equals(name))
            {
                Fac = new Faculty(CSVdetails.get(0), CSVdetails.get(1));
                for(int i = 2; i < CSVdetails.size(); i++)
                {
                    Fac.addModule(CSVdetails.get(i));
                }
                break;
            }  
        }
        while(CSVdetails != null);
        
        x.close();
        return Fac;
    }
    
    /**
     * readCSVline reads a single line from a CSV file and splits into a list of strings.
     *
     * @param BufferedReader x, stream to read CSV line from.
     * 
     * @return List<String>, line read from CSV broken into individual strings, null if not read.
     * 
     * @author (Adam Fogarty, 22367748)
     * @version (1.0)
     */
    public static List<String> readCSVline(BufferedReader x) throws IOException
    {
        String line;
        List<String> parts = null;
        if((line = x.readLine()) != null)
        {
           parts = new ArrayList<>(List.of(line.split(",")));
        }
        return parts;            
    }
    
    /**
     * programmeSaveCSV saves programme details to a CSV file named "'ProgCode'.CSV".
     *
     * @param Programme programme, object containing details to save.
     * 
     * @return VOID.
     * 
     * @author (Adam Fogarty, 22367748)
     * @version (1.0)
     */
    public static void programmeSaveCSV(Programme programme) throws IOException
    {
        String fileName = programme.getProgCode() + ".CSV";
        String CSVout = programme.getProgrammeCSV();
        FileWriter myWriter = new FileWriter(fileName);
        try
        {
            myWriter.write(CSVout);
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
    }
    
    /**
     * studentSaveCSV saves student details to a CSV file named "'StudentID'.CSV".
     *
     * @param Student student, object containing details to save.
     * 
     * @return VOID.
     * 
     * @author (Adam Fogarty, 22367748)
     * @version (1.0)
     */
    public static void studentSaveCSV(Student student) throws IOException
    {
        String fileName = student.getStudentID() + ".CSV";
        String CSVout = student.getStudentCSV();
        FileWriter myWriter = new FileWriter(fileName);
        try
        {
            myWriter.write(CSVout);
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
    }
    
    /**
     * programmeReadCSV reads programme details from a named CSV file.
     *
     * @param String FileName, name of file to read from.
     * 
     * @return Programme, object containing programme details, null if not found.
     * 
     * @author (Adam Fogarty, 22367748)
     * @version (1.0)
     */
    public static Programme programmeReadCSV(String FileName) throws IOException,FileNotFoundException
    {
        Programme newProg = null;
        BufferedReader x = new BufferedReader(new FileReader(FileName + ".CSV"));
        List<String> CSVdetails;
        CSVdetails = readCSVline(x);
        if(CSVdetails != null)
        {
            newProg = new Programme(CSVdetails.get(0), CSVdetails.get(1), Integer.valueOf(CSVdetails.get(2)));
        }
        for(int i = 0; i < newProg.getNumberOfYears(); i ++)
        {
            CSVdetails = readCSVline(x);
            if(CSVdetails != null)
            {
                for(int j = 1; j < CSVdetails.size(); j++)
                {
                    courseModule temp = moduleLoadFromCSV(CSVdetails.get(j));
                    if(temp != null)
                    {
                        newProg.addModule(Integer.valueOf(CSVdetails.get(0)), 1, temp);
                    }
                }          
            }
            
            CSVdetails = readCSVline(x);
            if(CSVdetails != null)
            {
                for(int j = 1; j < CSVdetails.size(); j++)
                {
                    courseModule temp = moduleLoadFromCSV(CSVdetails.get(j));
                    if(temp != null)
                    {
                        newProg.addModule(Integer.valueOf(CSVdetails.get(0)), 2, temp);
                    }
                }
            }
        }
        
        x.close();
        return newProg;
    }
    
    /**
     * moduleLoadFromCSV loads courseModule details from a CSV file named "allModules.CSV".
     *
     * @param String moduleCode, name of module to find and load.
     * 
     * @return courseModule, object containing module details, null if not found.
     * 
     * @author (Adam Fogarty, 22367748)
     * @version (1.0)
     */
    public static courseModule moduleLoadFromCSV(String moduleCode) throws IOException,FileNotFoundException
    {
        courseModule newModule = null;
        
        BufferedReader x = new BufferedReader(new FileReader("allModules.CSV"));
        List<String> CSVdetails;
        CSVdetails = readCSVline(x);
        do {
            CSVdetails = readCSVline(x);
            if(CSVdetails != null && CSVdetails.get(0).equals(moduleCode))
            {
                newModule = new courseModule(CSVdetails.get(0), CSVdetails.get(1), Integer.valueOf(CSVdetails.get(2)));
                
                break;
            }  
        }
        while(CSVdetails != null);
        
        x.close();
        return newModule;
    }
}
