/*
 * specific students modules
 */
import java.util.ArrayList;

public class studentsModuleList {
    //array list to hold modules of student
    //store instances of the module class
    private ArrayList<Module> set;

    public studentsModuleList() {
        //initilaise 'set'. Creates new instance of 'ArrayList<Module>.
        set = new ArrayList<Module>();
    }

    //adds module to array list
    public void add(Module x) {
        set.add(x);
    }
    
    //calulates total attemped hours of the module
    public int attemptedHours(double factor) {
        int total = 0;
        for (Module x: set ) {
            total += (x.creditValue*factor);
        }
        return total;
    }
    //return number of elements so how many modules the student has
    public int size() {
        return set.size();
    }
    //gets a specific module in the list
    public Module get(int i) {
        return set.get(i);
    }
    
    
}