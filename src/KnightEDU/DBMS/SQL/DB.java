/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KnightEDU.DBMS.SQL;

import KnightEDU.DBMS.Query;
import KnightEDU.DBMS.Section;
import KnightEDU.courses.Course;
import KnightEDU.courses.Credits;
import KnightEDU.courses.Grade.Type;
import KnightEDU.courses.classes.Days;
import KnightEDU.courses.classes.Location;
import java.util.Set;

/**
 *
 * @author Alexander Darino
 */
public class DB implements KnightEDU.DBMS.Course, KnightEDU.DBMS.Section{



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

    public KnightEDU.courses.Course addCourseID(String prefix, String number, String suffix, String name, String description, Credits credits, Type gradeType)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set<Course> queryCourse(Query.Course query)
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

    public Course removeCourse(String courseID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Section addSection(Days days, Location location, int timeStart, int timeEnd)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Section removeSection(String sectionID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Section getSection(String sectionID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set<Section> querySection(Query.Section query)
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

}
