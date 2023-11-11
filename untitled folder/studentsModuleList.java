import java.util.ArrayList;

public class studentsModuleList {
	
	private ArrayList<Module> set;
	
	public studentsModuleList() {
		set = new ArrayList<Module>();
	}
	
	public void add(Module x) {
		   set.add(x);
	   }
	 public int attemptedHours(double factor) {
		 int total = 0;
		 for (Module x: set ) {
			total += (x.creditValue*factor);
		 }
		 return total;
	 }
}
