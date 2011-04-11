
import KnightEDU.Course;
import KnightEDU.Term;
import java.util.Set;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alexander Darino
 */
public class Main {
    public static void main (String args[])
    {
        KnightEDU.DBMS.SQL.DB db = new KnightEDU.DBMS.SQL.DB(3,4,1);
        Set<Course.Offering> courseOfferings = db.queryCourseOffering().offeredTerm(Term.FALL).invoke();
        for (Course.Offering courseOffering : courseOfferings)
        {
            Set<Integer> componentIDs = db.queryComponent().getComponentIDs(courseOffering.getPrimaryComponentID());
            for (Integer componentID : componentIDs)
            {
                Set<Integer> classIDs = db.queryComponent().getClassIDs(componentID);
                for (Integer classID : classIDs)
                {
                    KnightEDU.Class myClass = db.getClass(classID);
                    System.out.println(myClass);
                }
            }
        }
    }
}
