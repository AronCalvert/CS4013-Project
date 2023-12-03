
public class QCAcalculator {
    
 
    
    public double QCAcalculation(int factor, ArrayList<gradedCourseModule> set) {
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
