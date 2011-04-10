package KnightEDU;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Alexander Darino
 */
public class Instructor {

    protected Set<Integer> classIDsTaught = new HashSet();

    public Instructor(int employeeID) {
        this.employeeID = employeeID;
    }
    protected int employeeID;

    public void addClassID(int classID)
    {
        classIDsTaught.add(classID);
    }

    public Set<Integer> getClassIDsTaught()
    {
        return Collections.unmodifiableSet(classIDsTaught);
    }
}
