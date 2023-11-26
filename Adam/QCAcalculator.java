
public class QCAcalculator {
	
	double moduleQpv;
	double qcs;
	int nonQualityHours;
	int attemptedHours;
	int qpv;
	String grade;
	int credits;
	
	public double QCAcalculation(int factor, studentsModuleList set) {
		 moduleQpv = 0;
		 qcs = 0;
		 nonQualityHours = 0;
		 attemptedHours = 0;
		 qpv = 0;
		 grade = "";
		 credits = 0;
		for (int i = 0; i< set.size(); i++) {
			grade = set.get(i).getGrade();
            attemptedHours += (set.get(i).getCreditValue() * factor);
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
		    	case "I":
		    	case "P":
		    	case "N":
		    	case "H":
		    	case "EX":
		    		nonQualityHours += (set.get(i).getCreditValue() * factor);
		    		moduleQpv = 0;
		    		break;
		    	default:
		    		System.out.println("Invalid grade"); 
		    		break;
			}
			credits= set.get(i).getCreditValue();
			qcs += (moduleQpv * credits * factor);
		}
		return (qcs / (attemptedHours - nonQualityHours));
	}

	
}
