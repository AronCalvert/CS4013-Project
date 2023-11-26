import java.util.*;
import java.io.*;

public class Module {
    String code;
    String title;
    int creditValue;
    String grade;

    public void moduleInfo(String moduleCode, String grade, String fac) {
        boolean codeFound = false;

        switch (fac.toUpperCase()) {
            case "A":
                fac = "/Users/paudie/Downloads/arts.csv";
                break;
            case "B":
                fac = "/Users/paudie/Downloads/business.csv";
                break;
            case "C":
                fac = "/Users/paudie/Downloads/health-sciences.csv";
                break;
            case "D":
                fac = "/Users/paudie/Downloads/interfaculty.csv";
                break;
            case "E":
                fac = "/Users/paudie/Downloads/science-and-engineering.csv";
                break;
            case "F":
                fac = "/Users/paudie/Downloads/ul-admin.csv";
                break;
            default:
            	System.out.println("Error: Please enter a valid faculty");
            	break;
        }

        try {
            BufferedReader x = new BufferedReader(new FileReader(fac));
            String line;
            
            while ((line = x.readLine()) != null) {
                if (line.startsWith(moduleCode)) {
                    List<String> parts = new ArrayList<>(List.of(line.split(",")));
                    creditValue = Integer.valueOf(parts.get(parts.size() - 1));
                    parts.remove(parts.size() - 1);
                    parts.remove(0);
                    title = String.join(",", parts);
                    codeFound = true;
                    break;
                }
            }

            code = moduleCode.toUpperCase();
            this.grade = grade;
            
            
            x.close();

            if (!codeFound) {
                throw new ModuleException("This module is not in the selected faculty");
            }
        } catch (Exception e) {
       		e.printStackTrace();
       	}
        
       

    }

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public int getCreditValue() {
		return creditValue;
	}

	public String getGrade() {
		return grade;
	}
}