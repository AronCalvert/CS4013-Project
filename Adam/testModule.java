import java.util.Scanner;
import java.io.*;
import java.util.*;

public class testModule {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        
        Programme testProg = new Programme("LM123", "Mathematics", 4);
        courseModule test1 = new courseModule("AH8013", "CRITICAL APPROACHES TO DISPLAY AND PARTICIPATION", 9);
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
        
        testProg.programmeSaveCSV();
        
        BufferedReader x = new BufferedReader(new FileReader("LM123.CSV"));
        List<String> CSVdetails;
        do {
            CSVdetails = readCSVline(x);
            if(CSVdetails != null)
            {
                for(int i = 0; i < CSVdetails.size(); i++)
                {
                    System.out.printf(CSVdetails.get(i) + ",");
                    
                }
                System.out.printf("\n");
            }
        }
        while(CSVdetails != null);
        
        x.close();
        
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
}
