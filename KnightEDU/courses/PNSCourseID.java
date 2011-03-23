/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KnightEDU.courses;

/**
 *
 * @author Alexander Darino
 */
public class PNSCourseID extends CourseID{
    /**

     */
    /**

     */
    /**

     */
    protected final String prefix, number, suffix;

    /**

     @param prefix
     @param number
     @param suffix
     @return
     */
    public static PNSCourseID create(String prefix, String number, String suffix)
    {
        if (!(isValidPrefix(prefix) && isValidNumber(number) && isValidSuffix(suffix)))
            return null;

        return new PNSCourseID(prefix, number, suffix);
    }

    /**
     
     @param prefix
     @param number
     @param suffix
     */
    protected PNSCourseID(String prefix, String number, String suffix)
    {
        super (prefix + number + suffix);
        this.prefix = prefix;
        this.number = number;
        this.suffix = suffix;
    }

    /**

     @return
     */
    public String getNumber()
    {
        return number;
    }

    /**

     @return
     */
    public String getPrefix()
    {
        return prefix;
    }

    /**

     @return
     */
    public String getSuffix()
    {
        return suffix;
    }

    /**

     @param s
     @return
     */
    protected static boolean isValidNumber(String s)
    {
        if (s == null) return false;

        for (int i = 0; i < s.length(); i++)
            if (!Character.isDigit(s.charAt(i))) return false;

        return true;
    }

    /**

     @param s
     @return
     */
    protected static boolean isValidPrefix(String s)
    {
        if (s == null) return false;
        for (int i = 0; i < s.length(); i++)
            if (!Character.isLetter(s.charAt(i))) return false;


        return true;
    }

    /**

     @param s
     @return
     */
    protected static boolean isValidSuffix(String s)
    {
        return isValidPrefix(s);
    }

}
