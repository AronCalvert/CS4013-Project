import java.util.Scanner;

public class testModule {
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
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
	      }
	} 
}
