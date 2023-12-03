import java.util.Scanner;
import java.io.*;
import java.util.*;

public class testModule {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        //test code for course programme
        Programme testProg = new Programme("LM111", "testProg", 4);
        courseModule test1 = new courseModule("MS4043", "CRITICAL APPROACHES TO DISPLAY AND PARTICIPATION", 9);
        courseModule test2 = new courseModule("AS2202", "INTRODUCTION TO SPANISH LANGUAGE 2", 3);
        courseModule test3 = new courseModule("AS2301", "INTRODUCTION TO SPANISH LANGUAGE 1", 3);
        courseModule test4 = new courseModule("AW4001", "ACADEMIC LITERACIES 1", 6);
        courseModule test5 = new courseModule("AW4002", "ACADEMIC LITERACIES 2", 6);
        courseModule test6 = new courseModule("AW4006", "PEER-TUTORING IN ACADEMIC WRITING", 6);        
        courseModule test7 = new courseModule("AW6001", "ACADEMIC LITERACIES FOR INTERNATIONAL", 3);
        courseModule test8 = new courseModule("AW7992", "BROADENING: ENGLISH AS WE SPEAK IT IN IRELAND", 3);
        testProg.addModule(1, 1, test1);
        testProg.addModule(1, 2, test2);
        testProg.addModule(2, 1, test3);
        testProg.addModule(1, 1, test4);
        testProg.addModule(1, 2, test5);
        testProg.addModule(1, 2, test6);
        testProg.addModule(2, 2, test4);
        testProg.addModule(3, 1, test5);
        testProg.addModule(3, 2, test6);
        testProg.addModule(4, 1, test7);
        testProg.addModule(4, 2, test8);
        
        programmeSaveCSV(testProg);
        
        
        //test code for faculty
        Faculty testFac1 = new Faculty("John Doe", "Business");
        testFac1.addModule("MS4043");
        testFac1.addModule("MS4613");
        Faculty testFac2 = new Faculty("Jane Doe", "Arts");
        testFac2.addModule("AC4004");
        testFac2.addModule("CS4013");
        testFac2.addModule("MS4403");
        Faculty testFac3 = new Faculty("Bill Gates", "Mathematics");
        testFac3.addModule("AC1234");
        testFac3.addModule("BE5678");
        Faculty testFac4 = new Faculty("John Cena", "Business");
        testFac4.addModule("MS1789");
        testFac4.addModule("AC5612");
        testFac4.addModule("EM5123");
        
        ArrayList<Faculty> allFaculty = new ArrayList<Faculty>();
        allFaculty.add(testFac1);
        allFaculty.add(testFac2);
        allFaculty.add(testFac3);
        allFaculty.add(testFac4);
        
        facultySaveCSV(allFaculty);
        Faculty testSearch = facultyLoadFromCSV("Bill Gates");
        if(testSearch !=null )
        {
            System.out.printf(testSearch.getCSVString());
        }
        else
        {
            System.out.printf("Faculty not found");
        }
        
        displayFacultyMenu();
        
        /**Scanner in = new Scanner(System.in);
        studentsModuleList allModules = new studentsModuleList();
        boolean more = true;
          
          while (more)
          { 
             System.out.println("Select a faculty:");
             System.out.println("A. Arts, Humanities and Social Sciences");
             System.out.println("B. Business");
             System.out.println("C. Education and Health Sciences");
             System.out.println("D. Interfaculty");
             System.out.println("E. Science and Engineering");
             System.out.println("F. UL Administration");
             System.out.println("G. Get Result");
             System.out.println("Q. Quit");
             String command = in.nextLine().toUpperCase();
             
             
             if (command.equals("Q")) {
                 more = false;
             } else if (command.equals("G")) {
                 more = false;
                 QCAcalculator calculator = new QCAcalculator();
                  double result = calculator.QCAcalculation(1,allModules);
                  System.out.println("QCA: " + result);
             } else { 
                 System.out.println("Enter the Module code:");
                 String moduleCode = in.nextLine().toUpperCase();
             
                 System.out.println("Please enter your grade:");
                  String grade = in.nextLine().toUpperCase();
                 Module x = new Module();
                 x.moduleInfo(moduleCode, grade, command);
                  allModules.add(x);
                  
             }
          }*/
    } 
    
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
    
    public static Programme programmeReadCSV(String FileName) throws IOException,FileNotFoundException
    {
        Programme newProg = null;
        BufferedReader x = new BufferedReader(new FileReader(FileName));
        List<String> CSVdetails;
        CSVdetails = readCSVline(x);
        if(CSVdetails != null)
        {
            newProg = new Programme(CSVdetails.get(0), CSVdetails.get(1), Integer.valueOf(CSVdetails.get(2)));
        }
        for(int i = 0; i < newProg.getProgYears(); i ++)
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
    
    public static courseModule moduleLoadFromCSV(String moduleCode) throws IOException,FileNotFoundException
    {
        courseModule newModule = null;
        
        BufferedReader x = new BufferedReader(new FileReader("allModules.csv"));
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

    public static Faculty facultyLoadFromCSV(String name) throws IOException,FileNotFoundException
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
    
    public static void displayFacultyMenu() throws IOException
    {
        Scanner keyboard = new Scanner(System.in);
        boolean facultyFound = false;
        Faculty facultyDetails = null;
        do{
            System.out.println(new String(new char[50]).replace("\0", "\r\n")); //clear console
            System.out.printf("Enter Name to Continue.");
            String name = keyboard.nextLine();
            facultyDetails = facultyLoadFromCSV(name);
            if(facultyDetails !=null )
            {
                facultyFound = true;
                //System.out.printf(facultyDetails.getCSVString());
            }
            else
            {
                System.out.println("Name not found!");
                System.out.println("Press 'X' to exit or 'Enter' to continue.");
                String command = keyboard.nextLine().toUpperCase();
                if(command.equals("X")){
                    break;
                }
            }
        }
        while(facultyFound == false);
        
        if(facultyFound == true)
        {
            System.out.println("Welcome " + facultyDetails.getName() +  "\n");
            System.out.println("Your assigned modules are: ");
            ArrayList<String> modules = facultyDetails.getModules();
            for(int i = 0; i < modules.size(); i ++)
            {
                System.out.println(modules.get(i));
            }
            System.out.println("\nEnter the Module Code to Grade or Review.");
            String moduleCode = keyboard.nextLine().toUpperCase();
            
            Programme tempProg = programmeReadCSV("LM111.CSV");
            if(tempProg.checkModuleInProg(moduleCode))
            {
                System.out.println(tempProg.getProgCode());
            }
            
            tempProg = programmeReadCSV("LM123.CSV");
            if(tempProg.checkModuleInProg(moduleCode))
            {
                System.out.println(tempProg.getProgCode());
            }
            
            tempProg = programmeReadCSV("LM124.CSV");
            if(tempProg.checkModuleInProg(moduleCode))
            {
                System.out.println(tempProg.getProgCode());
            }
            
            /** 
            if (command.equals("S")) {
                more = false;
            } else if (command.equals("G")) {
                more = false;
                QCAcalculator calculator = new QCAcalculator();
                double result = calculator.QCAcalculation(1,allModules);
                System.out.println("QCA: " + result);
            }*/
        }
        
    }
}