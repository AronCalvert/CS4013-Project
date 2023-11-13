import java.io.IOException;
import java.util.*;

/**
public class StudentCLI {
	private Scanner in;  
	public StudentCLI() {
		in = new Scanner(System.in);
	}
	
	studentsModuleList allModules = new studentsModuleList();
	public void run()
	         throws IOException
	   {
	      boolean more = true;
	      
	      while (more)
	      { 
	         System.out.println("Select a faculty:");
	         System.out.println("A. Arts, Humanities and Social Sciences");
	         System.out.println("B. Business");
	         System.out.println("C. Education and Health Sciences");
	         System.out.println("D. Science and Engineering");
	         System.out.println("E. Interfaculty");
	         System.out.println("F. UL Administration");
	         System.out.println("G. Get QCA");
	         System.out.println("Q. Quit");
	         String command = in.nextLine().toUpperCase();
	         
	         
	          if (command.equals("Q")) {
	        	 more = false;
	         } else if (command.equals("G")) {
	        	 more = false;
	        	 QCAcalculator calculator = new QCAcalculator();
	         	 double result = calculator.QCAcalculation(1,allModules);
	           	System.out.println("qcs: " + calculator.getQcs());
	         	System.out.println("attemptedHours: " + calculator.getAttemptedHours());
	         	System.out.println("nonQualityHours: " + calculator.getNonQualityHours());
	         	System.out.println("qcs / (attemptedHours - nonQualityHours): " + (calculator.getQcs() / (calculator.getAttemptedHours() - calculator.getNonQualityHours())));
	         	System.out.println("Result: " + result);
	         } else { 
	        	 System.out.println("Enter the Module code:");
	        	 String moduleCode = in.nextLine().toUpperCase();
	         
	        	 System.out.println("Please enter your grade:");
	         	 String grade = in.nextLine().toUpperCase();
	         	System.out.println("Calling moduleInfo method");
	        	 Module x = new Module();
	        	 x.moduleInfo(moduleCode, grade, command);
	         	 allModules.add(x);
	         	 
	         }
	      }
	   }
}
*/
