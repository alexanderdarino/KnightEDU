/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KnightEDU.DBMS;
import KnightEDU.*;
import KnightEDU.DBMS.SQL.DB;
import KnightEDU.Grade.Type;
/**
 *
 * @author cc
 */
public class Main {
    public static void main(String [ ] args)
    {
        DB db = new DB(3,4,1);
        //db.queryCourse().descriptionContains("virus");
        //db.addCourse("COP5011", "Intro to mycourse", "my course", Credits.createCredits(3), Type.LETTER);
        //db.containsCourse("COP5011");
        //db.getCourse("COP5011");
        //KnightEDU.Course newCourse = KnightEDU.Course.create(KnightEDU.CourseID.PNS.create("COP", "5011", ""), "Intro to your course", "your course", KnightEDU.Credits.createCredits(3), KnightEDU.Grade.Type.LETTER);
        //db.updateCourse(newCourse);
        //db.removeCourse("COP5011");
        //Days days = KnightEDU.Days.MWF;
        //Location location = new KnightEDU.Location.BR("HEC","101");
        //db.addSection(days, location, 1100, 1200);
        //db.removeSection("3");
        //db.getSection("3");
        //KnightEDU.Section section = KnightEDU.Section.create(1, Days.M, 1100, 1200, "HEC-101");
        //db.containsSection("1");
        //db.updateSection(section);
        //KnightEDU.CourseID courseId = KnightEDU.CourseID.PNS.create("CDA","3103","");
        //db.addCourseOffering(courseId, Term.FALL, 2011, 1);
        //db.getCourseOffering(courseId, Term.FALL, 2011);
        //db.containsCourseOffering(courseId, Term.FALL, 2011);
        //KnightEDU.Course.Offering course = new KnightEDU.Course.Offering(courseId, Term.SPRING, 2012);
        //db.updateCourseOffering(courseId, Term.SPRING, 2012);
        //db.removeCourseOffering(courseId, Term.FALL, 2011);
        //db.addClass(1, 1, 1000, 100);
        //db.containsClass(1);
        //db.getClass(1);
        //db.removeClass(1);
        //KnightEDU.Class updateClass = new KnightEDU.Class(1, 1, 2000, 200);
        //db.updateClass(updateClass);
        //db.addComponent(KnightEDU.Component.Type.INDEPENDENT_STUDY);
        //db.removeComponent(1);
        //db.containsComponent(1);
        //db.getComponent(1);
        //KnightEDU.Component updateComponent = new KnightEDU.Component(1,2);
        //db.updateComponent(null);
        //db.removeComponent(1);
        //db.addEmployee(12345, "John", "Adam");
        //db.containsEmployee(1);
        //db.getEmployee(12345);
        //KnightEDU.Employee emp = new KnightEDU.Employee(12345, "bob", "Dole");
        //db.updateEmployee(emp);
        //db.removeEmployee(1);
        //KnightEDU.CourseID newCourseID = KnightEDU.CourseID.PNS.create("COP","3503","");
        //db.addTranscriptEntry(12345, newCourseID, 2011, KnightEDU.Term.FALL, KnightEDU.Grade.Letter.create("A"), 3);
        //KnightEDU.CourseID newCourseID2 = KnightEDU.CourseID.PNS.create("COP","3223","");
        //db.addTranscriptEntry(12345, newCourseID2, 2011, KnightEDU.Term.SPRING, KnightEDU.Grade.Letter.create("B"), 3);
        //KnightEDU.CourseID newCourseID3 = KnightEDU.CourseID.PNS.create("COP","3402","");
        //db.addTranscriptEntry(12345, newCourseID3, 2011, KnightEDU.Term.FALL, KnightEDU.Grade.Letter.create("A"), 3);
        //db.containsTranscriptEntry(12345, newCourseID, 2011, KnightEDU.Term.FALL);
        //db.getTranscriptEntry(12345, newCourseID, 2011, KnightEDU.Term.FALL);
        //KnightEDU.Transcript.Entry myentry = new KnightEDU.Transcript.Entry(newCourseID, 2011, KnightEDU.Term.SPRING, KnightEDU.Grade.Type.LETTER, KnightEDU.Grade.Letter.create("A"), 3);
        //db.updateTranscriptEntry(12345, myentry);
        //db.removeTranscriptEntry(12345, newCourseID, 2011, KnightEDU.Term.SPRING);
        //db.getInstructor(54321);
        db.getTranscript(12345);
        db.closeDB();

    }

}
