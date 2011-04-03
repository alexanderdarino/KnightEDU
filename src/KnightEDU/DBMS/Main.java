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
        //Days days = KnightEDU.Days.TR;
        //Location location = new KnightEDU.Location.BR("HEC","100");
        //db.addSection(days, location, 1000, 1200);
        //db.getSection("1");
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
        //db.containsComponent(1);
        //db.getComponent(1);
        //KnightEDU.Component updateComponent = new KnightEDU.Component(1,2);
        //db.updateComponent(null);
        db.removeComponent(1);
        db.closeDB();

    }

}
