package KnightEDU.DBMS;

import KnightEDU.courses.CourseID;
import KnightEDU.courses.PNSCourseID;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alexander Darino
 */
public class CourseIDDB {
    /**
     * Prefix length requirement
     */
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

     @param prefixLength
     @param numberLength
     @param suffixLength
     @return
     */
    public static CourseIDDB createCourseIDDB(int prefixLength, int numberLength, int suffixLength)
    {
        if (!(prefixLength > 0 && numberLength > 0 && suffixLength >= 0)) return null;
        return new CourseIDDB(prefixLength, numberLength, suffixLength);
    }

    /**

     @param prefixLength
     @param numberLength
     @param suffixLength
     */
    protected CourseIDDB(int prefixLength, int numberLength, int suffixLength)
    {

        throw new UnsupportedOperationException("Not yet implemented");
//        PREFIX_LENGTH = prefixLength;
//        NUMBER_LENGTH = numberLength;
//        SUFFIX_LENGTH = suffixLength;
    }

    /**

     @param prefix
     @param number
     @param suffix
     @return
     */
    public PNSCourseID addCourse(String prefix, String number, String suffix)
    {
        throw new UnsupportedOperationException("Not yet implemented");

//        if (isValidPrefix(prefix) && isValidNumber(number) && isValidSuffix(suffix))
//            return new PNSCourseID(prefix, number, suffix);
//        return null;
    }

    /**

     @param courseID
     @return
     */
    public boolean containsCourse(String courseID)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**

     @param courseID
     @return
     */
    public PNSCourseID getCourse(String courseID)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**

     @param courseID
     @return
     */
    public boolean updateCourse(CourseID courseID)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**

     @param courseID
     @return
     */
    public PNSCourseID remove(String courseID)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Determines if the specified string is a valid course number.
     * @param s string to be tested
     * @return <code>true</code> if the argument string consists of 4 digits.
     */
    protected boolean isValidNumber(String s)
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
    protected boolean isValidPrefix(String s)
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
    protected boolean isValidSuffix(String s)
    {
        if (s == null) return false;
        if (s.length() > SUFFIX_LENGTH) return false;

        return true;
    }
}
