
public class testModule {
	public static void main(String[] args) {
		Module x = new Module();
		x.moduleInfo("CS4222", "s");
		Module s = new Module(); 
		s.moduleInfo("CS4023", "s");
		studentsModuleList example = new studentsModuleList();
		example.add(x);
		example.add(s);
		System.out.println(example.attemptedHours(1));
	}
}
