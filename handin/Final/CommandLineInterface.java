import java.util.Scanner;
import java.io.*;
import java.util.*;

/**
 * The CommandLineInterface class serves as the main entry point for the University Management System.
 * It provides a command-line interface for students, faculty, and administrators to interact with the system.
 * The class includes methods for handling various operations based on the user's role.
 * 
 * 
 * @author (Adam Fogarty, 22367748);(Aron Calvert, 22370374)
 * @version (4.0)
 */
public class CommandLineInterface {
    static Admin admin = new Admin();

    /**
     * The main method initializes the system and presents a menu for users to choose their role.
     * The system allows users to perform operations based on their role until they choose to exit.
     * 
     * @param args Command-line arguments (not used in this application).
     * @throws IOException If an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean mainExit = false;
        //initialised list of faculty from CSV.
        facultyLoadFromCSV();
        
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

    /**
     * Handles various administrative operations in the university management system.
     * This method prompts the user to enter an admin username and password to login.
     * Once logged in, the user can perform operations like creating faculty, assigning modules to faculty, creating programs, 
     * adding modules to programs, creating students, getting transcripts, and viewing faculties, students, and programs.
     *
     * @param scanner The Scanner object for user input.
     * @throws IOException If an I/O error occurs.
     * 
     * @author (Adam Fogarty, 22367748);(Aron Calvert, 22370374)
     * @version (2.0)
     */
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
            System.out.println("2. Assign Module to Faculty");
            System.out.println("3. Create Programme");
            System.out.println("4. Add Module to Programme");
            System.out.println("5. Create Student");
            System.out.println("6. Get Transcript");
            System.out.println("7. View Faculties");
            System.out.println("8. View Students");
            System.out.println("9. View Programmes");
            System.out.println("10. Exit");

            int operationChoice = scanner.nextInt();
            scanner.nextLine();
            switch (operationChoice) {

                case 1: // Create Faculty
                {
                    System.out.println("Enter faculty name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter faculty department:");
                    String department = scanner.nextLine();
                    admin.createFaculty(name, department);
                    System.out.println("Faculty created successfully.");
                    facultySaveCSV(admin.getFaculty());
                    break;
                }
                
                case 2: // Assign Module to faculty
                {
                    System.out.println("Enter module code:");
                    String moduleCode = scanner.nextLine();
                    courseModule newFacMod = moduleLoadFromCSV(moduleCode);
                    if(newFacMod != null)
                    {
                        System.out.println("Enter faculty name:");
                        String name = scanner.nextLine();
                        ArrayList<Faculty> allFac = admin.getFaculty();
              
                        for (Faculty fac : allFac)
                        {   
                            if(fac.getName().equals(name))
                            {
                                fac.addModule(moduleCode);
                                facultySaveCSV(admin.getFaculty());
                                System.out.println("Module added successfully.");
                            }
                        }
                    }
                    else
                    {
                        System.out.println("Module not found");
                    }
                    break;
                }
                
                case 3: // Create Programme
                {
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
                    programmeSaveCSV(admin.getProgramme(programmeCode));
                    break;
                }
                
                case 4: // Add Module to Programme
                {
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

                    System.out.println("Enter semester (1 or 2):");
                    int semester;
                    try {
                        semester = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input for semester. Please enter a number.");
                        continue;
                    }

                    // Inputs for creating courseModule
                    System.out.println("Enter module code:");
                    String moduleCode = scanner.nextLine();
                    courseModule newMod = moduleLoadFromCSV(moduleCode);
                    
                    // Add the courseModule to the programme
                    admin.addModule(progCode, progYear, semester, newMod);
                    System.out.println("Module added to programme successfully.");
                    programmeSaveCSV(admin.getProgramme(progCode));
                    break;
                }

                case 5: // Create Student
                {
                    System.out.println("Enter student ID:");
                    String studentID = scanner.nextLine(); // Read the entire line

                    System.out.println("Enter student name:");
                    String studentName = scanner.nextLine(); // Read the entire line

                    System.out.println("Enter programme code:");
                    String program = scanner.nextLine(); // Read the entire line
                    
                    Programme programme = programmeReadCSV(program);
                    
                    System.out.println("Enter year of registration:");
                    int yearOfReg;
                    try {
                        yearOfReg = Integer.parseInt(scanner.nextLine()); // Parse the integer from the line
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input for year of registration. Please enter a number.");
                        continue; // Go back to the start of the loop to retry
                    }

                    System.out.println("Enter year of study (1..4): ");
                    int yearOfStudy;
                    try {
                        yearOfStudy = Integer.parseInt(scanner.nextLine()); // Parse the integer from the line
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input for year of study. Please enter a number.");
                        continue; // Go back to the start of the loop to retry
                    }

                    
                    admin.createStudent(studentID, studentName, programme, yearOfStudy, yearOfReg);
                    Student newStudent = new Student(studentID, studentName, programme, yearOfStudy, yearOfReg);
                    studentSaveCSV(newStudent);
                    System.out.println("Student created successfully.");
                    
                    break;
                }
                
                case 6: // Get Transcript
                {
                    System.out.println("Enter student ID:");
                    String studentID = scanner.nextLine(); // Read the entire line
                    Student student = studentReadCSV(studentID);
                    if (student == null) {
                        System.out.println("Student not found.");
                        return;
                    }
                    getTranscript(student);
                    System.out.println(getTranscript(student));
                    break;
                }
                
                case 7: // View Faculties
                {
                    admin.viewFaculties();
                    break;
                }
                case 8: // View Students
                {
                    admin.viewStudents();
                    break;
                }
                case 9: // View Programmes
                {
                    admin.viewProgrammes();
                    break;
                }
                case 10: // Exit
                {
                    exit = true;
                    break;
                }
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
     * @version (3.0)
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
            System.out.println("3. View student transcript");
            System.out.println("4. Exit");

            int operationChoice = keyboard.nextInt();
            keyboard.nextLine();
            switch (operationChoice) {
                case 1:
                {
                    System.out.println(new String(new char[60]).replace("\0", "-")); //add header
                    System.out.println("Your assigned modules are: ");
                    ArrayList<String> modules = facultyDetails.getModules();
                    for(int i = 0; i < modules.size(); i ++)
                    {
                        courseModule tempMod = moduleLoadFromCSV(modules.get(i));
                        System.out.println(tempMod.getCourseModuleCSV().replace(",", "\t"));
                    }
                    System.out.println(new String(new char[60]).replace("\0", "-")); //add footer
                    break;
                }
                case 2:
                {
                    System.out.println("Enter desired student ID: ");
                    String studentID = keyboard.nextLine().toUpperCase();
                    //scanner.nextLine();
                    System.out.println("Enter Module Code: ");
                    String moduleCode = keyboard.nextLine().toUpperCase();
                    System.out.println("Year of study: ");
                    String year = keyboard.nextLine().toUpperCase();
                    System.out.println("Semester (1 or 2): ");
                    String semester = keyboard.nextLine().toUpperCase();
                    System.out.println("Enter the grade achieved: ");
                    String grade = keyboard.nextLine().toUpperCase();
                    
                    Student existingStudent = studentReadCSV(studentID);
                    if(existingStudent != null)
                    {
                        existingStudent.getProgramme().setModuleGrade(Integer.valueOf(year), Integer.valueOf(semester), moduleCode, grade);
                        studentSaveCSV(existingStudent);
                    }
                    break; 
                }
                
                case 3: // Get Transcript
                {
                    System.out.println("Enter student ID:");
                    String studentID = keyboard.nextLine(); // Read the entire line
                    
                    Student student = studentReadCSV(studentID);
                    if (student == null) {
                        System.out.println("Student not found.");
                        return;
                    }
                    getTranscript(student);
                    System.out.println(getTranscript(student));
                    break;
                }

                case 4:
                {
                    exit = true;
                    break;
                }
                
                
                
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    /**
     * Handles various operations for a student in a university management system.
     * This method prompts the user to enter a student ID, retrieves the corresponding student object,
     * and allows the student to view their transcript or exit the system.
     *
     * @param scanner The Scanner object for user input.
     * @throws IOException If an I/O error occurs.
     * 
     * @author (Adam Fogarty, 22367748);(Aron Calvert, 22370374)
     * @version (2.0)
     */
    private static void handleStudentOperations(Scanner scanner) throws IOException {
        System.out.println("Enter Student ID:");
        String studentId = scanner.next();
        //Student student = admin.getStudentById(studentId);
        Student student = studentReadCSV(studentId);
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
            scanner.nextLine();
            switch (operationChoice) {
                case 1:
                {
                    getTranscript(student);
                    System.out.println(getTranscript(student));
                    break;
                }
                
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
        String fileName = "Admin\\Faculty.CSV";
        
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
        //System.out.println("Successfully wrote to the file.");
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
        
        BufferedReader x = new BufferedReader(new FileReader("Admin\\Faculty.CSV"));
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
     * facultyFindInCSV finds a single faculty in CSV file, "Faculty.CSV".
     *
     * @param String name, name of faculty member to find.
     * 
     * @return Faculty, Details for faculty member if found, null if not found.
     * 
     * @author (Adam Fogarty, 22367748)
     * @version (1.0)
     */
    public static Faculty facultyLoadFromCSV() throws IOException,FileNotFoundException
    {
        Faculty Fac = null;
        
        BufferedReader x = new BufferedReader(new FileReader("Admin\\Faculty.CSV"));
        List<String> CSVdetails;
        CSVdetails = readCSVline(x);
        do {
            CSVdetails = readCSVline(x);
            if(CSVdetails != null)
            {
                Fac = new Faculty(CSVdetails.get(0), CSVdetails.get(1));
                for(int i = 2; i < CSVdetails.size(); i++)
                {
                    Fac.addModule(CSVdetails.get(i));
                }
                admin.addFaculty(Fac);
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
        String fileName = "Programmes\\" + programme.getProgCode() + ".CSV";
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
     * programmeReadCSV reads programme details from a named CSV file.
     *
     * @param String FileName, name of file to read from.
     * 
     * @return Programme, object containing programme details, null if not found.
     * 
     * @author (Adam Fogarty, 22367748)
     * @version (1.0)
     */
    public static Programme programmeReadCSV(String progCode) throws IOException,FileNotFoundException
    {
        Programme newProg = null;
        BufferedReader x = new BufferedReader(new FileReader("Programmes\\" + progCode + ".CSV"));
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
        String fileName = "Students\\" + student.getStudentID() + ".CSV";
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
    public static Student studentReadCSV(String studentID) throws IOException,FileNotFoundException
    {
        Student newStudent = null;
        BufferedReader x = new BufferedReader(new FileReader("Students\\" + studentID + ".CSV"));
        List<String> CSVdetails;
        CSVdetails = readCSVline(x);
        if(CSVdetails != null)
        {
            Programme newProg = programmeReadCSV(CSVdetails.get(2));
            if(newProg!=null)
            {
                newStudent = new Student(CSVdetails.get(0), CSVdetails.get(1), newProg, Integer.valueOf(CSVdetails.get(4)), Integer.valueOf(CSVdetails.get(5)));
                
                do {
                    CSVdetails = readCSVline(x);
                    if(CSVdetails != null)
                    {
                        newStudent.getProgramme().setModuleGrade(Integer.valueOf(CSVdetails.get(0)), Integer.valueOf(CSVdetails.get(1)), CSVdetails.get(2), CSVdetails.get(5));
                    }  
                }
                while(CSVdetails != null);
            }
        }

        x.close();
        return newStudent;
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
        
        BufferedReader x = new BufferedReader(new FileReader("Admin\\allModules.CSV"));
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
    
    /**
     * getTranscript 
     *
     * @param String moduleCode, name of module to find and load.
     * 
     * @return courseModule, object containing module details, null if not found.
     * 
     * @author (Adam Fogarty, 22367748)
     * @version (1.0)
     */
    public static String getTranscript(Student student)
    {
        String transcript = new String(new char[80]).replace("\0", "-");
        transcript += "\n";
        
        transcript += "Name: " + student.getName() + "\n";
        transcript += "Student ID: " + student.getStudentID() + "\n";
        transcript += "Programme of Study: " + student.getProgramme().getProgCode() + "\t" + student.getProgramme().getName() + "\n"; 
        transcript += "Year of Registration: " + student.getRegYear() + "\n";
        transcript += "Current Year of Study: " + student.getYearOfStudy() + "\n";
        Programme programme = student.getProgramme();
        
            for(int i = 0; i < programme.getNumberOfYears(); i++)
            {
                ArrayList<programmeYear> programmeYears = programme.getProgYears();
                programmeYear year = programmeYears.get(i);
                
                String moduleDetails = new String(new char[80]).replace("\0", "-");
                moduleDetails += "\n";
                moduleDetails += "Academic Year " + (i + student.getRegYear()) + "\n";
                moduleDetails += "Semester 1" + "\n";
                    for(int j = 0; j < year.sem1.size(); j++)
                    {
                        gradedCourseModule module = year.sem1.get(j);
                        moduleDetails += module.getCourseModuleText();
                        transcript += moduleDetails + "\n";
                        moduleDetails = "";
                    }
                    double qca = QCAcalculation(1, year.sem1);
                    transcript += "QCA For Semester: " + String.format("%.2f", qca) + "\n";
                    transcript += new String(new char[80]).replace("\0", "-") + "\n";
                
                moduleDetails += "Semester 2" + "\n";
                    for(int j = 0; j < year.sem2.size(); j++)
                    {
                        gradedCourseModule module = year.sem2.get(j);
                        moduleDetails += module.getCourseModuleText();
                        transcript += moduleDetails + "\n";
                        moduleDetails = "";
                    }
                    qca = QCAcalculation(1, year.sem2);
                    transcript += "QCA For Semester: " + String.format("%.2f", qca) + "\n";
                    transcript += new String(new char[80]).replace("\0", "-") + "\n";
                
            }     
        transcript += new String(new char[80]).replace("\0", "-");
        return transcript;
    }
    
    /**
     * Calculates the Quality Credit Average (QCA) based on a given factor and a set of graded course modules.
     *
     * The QCA is computed using the formula:
     * QCA = Σ(QPV * Credits * Factor) / (Attempted Hours - Non-Quality Hours)
     *
     * @param factor The factor used to weigh the credit hours.
     * @param set    An ArrayList of gradedCourseModule objects representing the student's graded modules.
     * @return The calculated Quality Credit Average (QCA).
     * 
     * @author (Aron Calvert, 22370374)
     * @version (2.0)
     */
    public static double QCAcalculation(int factor, ArrayList<gradedCourseModule> set) {
        double moduleQpv = 0;
        double qcs = 0;
        int nonQualityHours = 0;
        int attemptedHours = 0;
        int qpv = 0;
        String grade = null;
        int credits = 0;
  
        for (int i = 0; i< set.size(); i++) 
        {
            gradedCourseModule module = set.get(i);
            grade = module.getGrade();
            attemptedHours += (module.getModuleCredits() * factor);
            switch (grade) {
                case "A1":
                    moduleQpv = 4.00;
                    break;
                case "A2":
                    moduleQpv = 3.60;
                    break;
                case "B1":
                    moduleQpv = 3.20;
                    break;
                case "B2":
                    moduleQpv = 3.00;
                    break;
                case "B3":
                    moduleQpv = 2.80;
                    break;
                case "C1":
                    moduleQpv = 2.60;
                    break;
                case "C2":
                    moduleQpv = 2.40;
                    break;
                case "C3":
                    moduleQpv = 2.00;
                    break;
                case "D1":
                    moduleQpv = 1.60;
                    break;
                case "D2":
                    moduleQpv = 1.20;
                    break;
                case "F":
                case "NG":
                    moduleQpv = 0.00;
                    break;
                case "P":
                case "I":
                case "N":
                case "H":
                case "EX":
                    nonQualityHours += (module.getModuleCredits() * factor);
                    break;
                case "M":
                case "G":
                case "R":
                case "W":

                    break;
                default:
                    System.out.println("Invalid grade"); 
                    break;
            }
            credits= module.getModuleCredits();
            qcs += (moduleQpv * credits * factor);
        }
        return (qcs / (attemptedHours - nonQualityHours));
    }
}
