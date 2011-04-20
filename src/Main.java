import KnightEDU.*;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
//KnightEDU.DBMS.SQL.DB db = new KnightEDU.DBMS.SQL.DB(3,4,1).queryCourse().descriptionContains(null).nameContains(null).invoke();
// TEST: Navigate through your course query - keep track of the hierarchy of information!!!
//        KnightEDU.DBMS.SQL.DB db = new KnightEDU.DBMS.SQL.DB(3,4,1);
//        KnightEDU.DBMS.Query.Course courseQuery = db.queryCourse();
//        //courseQuery = courseQuery.descriptionContains("description");
//        Set<Course> result = null;
//        try {
//            result = ((KnightEDU.DBMS.Query.CourseID.PNS) courseQuery.specifyCourseID()).containsPrefix("enc").build().invoke();
//        }
//        catch (InvalidPrefixException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }


// TEST: CourseSchedule methods
//        KnightEDU.DBMS.SQL.DB db = new KnightEDU.DBMS.SQL.DB(3,4,1);
// PASSED:        db.addCourseSchedule(new CourseID("ABC1234"), YearParity.EVEN, Term.FALL);
// PASSED:        db.containsCourseSchedule(new CourseID("ABC1234"), YearParity.EVEN, Term.FALL);
// PASSED:        db.getCourseSchedules(new CourseID("ABC1234"));
// PASSED:        db.removeCourseSchedule(new CourseID("ABC1234"), YearParity.EVEN, Term.FALL);

// TEST: getClass() method
        KnightEDU.DBMS.SQL.DB db = new KnightEDU.DBMS.SQL.DB(3,4,1);
        db.getClass(new CourseID("ENC1101"), 2011, Term.SPRING, 1);





// TEST: Get ALL classes available... passed
//        KnightEDU.DBMS.SQL.DB db = new KnightEDU.DBMS.SQL.DB(3,4,1);
//        Set<KnightEDU.Course.Offering> result = db.queryCourseOffering().invoke();

// TEST: WE now know the primary component IDs. Let's get the classes:
//        KnightEDU.DBMS.SQL.DB db = new KnightEDU.DBMS.SQL.DB(3,4,1);
//        Set<Integer> result = db.queryComponent().getClassIDs(1);

// TEST:  We now have the class IDs. Let's obtain the classes.
//        KnightEDU.DBMS.SQL.DB db = new KnightEDU.DBMS.SQL.DB(3,4,1);
//        KnightEDU.Class result = db.getClass(1);

// TEST:  We now have the class object containing the instructor ID. who is the instructor?
//        KnightEDU.DBMS.SQL.DB db = new KnightEDU.DBMS.SQL.DB(3,4,1);
//        KnightEDU.Employee result = db.getEmployee(1);

//// TEST:  Pull up the student transcript:
//        KnightEDU.DBMS.SQL.DB db = new KnightEDU.DBMS.SQL.DB(3,4,1);
//        KnightEDU.Transcript result = db.getTranscript(123456);
        
        System.out.println();

    }
}
