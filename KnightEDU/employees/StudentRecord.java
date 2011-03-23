package KnightEDU.employees;

import KnightEDU.courses.Grade;
import KnightEDU.courses.classes.Class;
import java.util.TreeMap;

/**
 *
 * @author Alexander Darino
 */
public class StudentRecord{

    int uid;
    /**

     */
    protected TreeMap<Class, Grade> enrollments = new TreeMap();//////////////////////////////////////////


    /**

     @param uid
     */
    protected StudentRecord(int uid)
    {
        this.uid = uid;
    }


    /**

     @param classEnrolled
     @param grade
     */
    public void setEnrollment(Class classEnrolled, Grade grade)
    {
        enrollments.put(classEnrolled, grade);
    }

    /**

     @param classEnrolled
     */
    public void removeEnrollment(Class classEnrolled)
    {
        for (Class i: enrollments.keySet())
        {
            if (i.getClassID() == classEnrolled.getClassID())
            {
                enrollments.remove(i);
                break;
            }
        }
    }

    /**

     @return
     */
    public TreeMap<Integer, Grade> getEnrollments()
    {
        TreeMap r_val = new TreeMap();
        for (Class i: enrollments.keySet())
        {
            r_val.put(i.getClassID(), enrollments.get(i));
        }
        return r_val;
    }

}
