import java.util.*;
import java.io.*;
public class Module {
	String code;
	String title;
	int creditValue;
		
	public void moduleInfo(String moduleCode, String fac) {
		
		switch (fac.toUpperCase()) {
        case "A":
            fac = "/Users/paudie/Downloads/arts.csv";
            break;
        case "B":
            fac = "/Users/paudie/Downloads/business.csv";
            break;
        case "H":
            fac = "/Users/paudie/Downloads/health-sciences.csv";
            break;
        case "I":
            fac = "/Users/paudie/Downloads/interfaculty.csv";
            break;
        case "S":
            fac = "/Users/paudie/Downloads/science-and-engineering.csv";
            break;
        case "U":
        	fac = "/Users/paudie/Downloads/ul-admin.csv";
        	break;
        default:
            throw new ModuleException("Please enter a valid faculty");
		}
		
		try {
			BufferedReader x = new BufferedReader(new FileReader(fac));
			String line;
			while ((line = x.readLine()) != null) {
				if (line.startsWith(moduleCode)) {
					List<String> parts = new ArrayList<>(List.of(line.split(",")));
					creditValue = Integer.valueOf(parts.get(parts.size()-1));
					parts.remove(parts.size()-1);
					parts.remove(0);
					title = String.join(",", parts);
				}
			}
			code = moduleCode;
			x.close();
		} catch(Exception e) {
			throw new ModuleException("This module is not in the selected faculty");
		}
	}
	
	
}
