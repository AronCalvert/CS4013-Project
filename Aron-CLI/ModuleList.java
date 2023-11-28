import java.util.ArrayList;

public class ModuleList {
	
	private ArrayList<Module> set;
	
	public ModuleList() {
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
	 
	 public int size() {
		 return set.size();
	 }
	 
	 public Module get(int i) {
		 return set.get(i);
	 }
}
