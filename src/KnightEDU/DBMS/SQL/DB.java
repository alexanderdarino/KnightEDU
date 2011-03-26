/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KnightEDU.DBMS.SQL;

import KnightEDU.DBMS.Query;
import KnightEDU.DBMS.Query.Component;
import KnightEDU.DBMS.Section;
import KnightEDU.Course;
import KnightEDU.CourseID;
import KnightEDU.Credits;
import KnightEDU.Grade.Type;
import KnightEDU.Term;
import KnightEDU.Class;
import KnightEDU.Component.Offering;
import KnightEDU.Days;
import KnightEDU.Location;
import java.util.Set;

/**
 *
 * @author Alexander Darino
 */
public class DB implements KnightEDU.DBMS.Course, KnightEDU.DBMS.Section, KnightEDU.DBMS.Class, KnightEDU.DBMS.ComponentOffering, KnightEDU.DBMS.Component{



    protected final int PREFIX_LENGTH;
    /**
     * Number length requirement
     */
    protected final int NUMBER_LENGTH;
    /**
     * Maximum suffix length
     */
    protected final int SUFFIX_LENGTH;

    /**
     * Determines if the specified string is a valid course number.
     * @param s string to be tested
     * @return <code>true</code> if the argument string consists of 4 digits.
     */
    public boolean isValidCourseNumber(String s)
    {
        if (s == null) return false;
        if (s.length() != NUMBER_LENGTH) return false;

        return true;
    }

    /**
     * Determines if the specified string is a valid prefix.
     * @param s string to be tested
     * @return <code>true</code> if the argument string consists of 3 uppercase letters.
     */
    public boolean isValidCoursePrefix(String s)
    {
        if (s == null) return false;
        if (s.length() != PREFIX_LENGTH) return false;

        return true;
    }

    /**
     * Determines if the specified string is a valid suffix.
     * @param s string to be tested
     * @return <code>true</code> if the argument string is either empty or
     * consists of one letter.
     */
    public boolean isValidCourseSuffix(String s)
    {
        if (s == null) return false;
        if (s.length() > SUFFIX_LENGTH) return false;

        return true;
    }

    public boolean isValidCourseID(String prefix, String number, String suffix)
    {
        return isValidCoursePrefix(prefix) && isValidCourseNumber(number) && isValidCourseSuffix(suffix);
    }

    public KnightEDU.Course addCourseID(String prefix, String number, String suffix, String name, String description, Credits credits, Type gradeType)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Course addCourse(String courseID, String name, String description, Credits credits, Type gradeType)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsCourse(String courseID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Course getCourse(String courseID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateCourse(Course course)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Section addSection(Days days, Location location, int timeStart, int timeEnd)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Section getSection(String sectionID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsSection(String sectionID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateSection(Section section)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set<KnightEDU.Course> queryCourse(String whereClause, String groupByClause, String havingClause)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Query.Course queryCourse()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Class addClass(CourseID courseID, Term term, int year, int primaryComponentID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Class getClass(CourseID courseID, Term term, int year)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsClass(CourseID courseID, Term term, int year)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateClass(Class classObj)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set<Class> queryClass(String whereClause, String groupByClause, String havingClause)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Query.Class queryClass()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeCourse(String courseID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeSection(String sectionID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeClass(CourseID courseID, Term term, int year)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Offering addComponentOffering(int componentID, int sectionID, int sectionNumber, int capacity)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsComponentOffering(int componentID, int sectionID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Offering getComponentOffering(int componentID, int sectionID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Component.Offering queryComponentOffering()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set<Offering> queryComponentOffering(String whereClause, String groupByClause, String havingClause)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeComponentOffering(int componentID, int sectionID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Query.Section querySection()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set<KnightEDU.Section> querySection(String whereClause, String groupByClause, String havingClause)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public KnightEDU.Component addComponent(KnightEDU.Component.Type type)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsComponent(int componentID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public KnightEDU.Component getComponent(int componentID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Component queryComponent()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set<KnightEDU.Component> queryComponent(String whereClause, String groupByClause, String havingClause)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeComponent(int componentID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateComponentOffering(Offering componentOffering)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateComponent(KnightEDU.Component component)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
