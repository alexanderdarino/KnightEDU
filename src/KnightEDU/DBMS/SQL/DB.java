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
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    protected String driver = "org.apache.derby.jdbc.EmbeddedDriver";

    protected String dbName="C:\\universityDB";

    protected String connectionURL = "jdbc:derby:" + dbName + ";create=true";

    protected Connection conn = null;

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
        try {
            Statement s;
            ResultSet myCourses;
            s = conn.createStatement();
            String queryString = "select * from COURSE";
            if (whereClause != null && !whereClause.equals(""))
                queryString += " WHERE " + whereClause;
            myCourses = s.executeQuery(queryString);
            while (myCourses.next())
            {
                System.out.println(myCourses.getString("name"));
            }
            return null;
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Query.Course queryCourse()
    {
        return new Query.Course(this);
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

    public DB(int CourseIDPrefixLength, int CourseIDNumberLength, int CourseIDSuffixLength)
    {
        PREFIX_LENGTH = CourseIDPrefixLength;
        NUMBER_LENGTH = CourseIDNumberLength;
        SUFFIX_LENGTH = CourseIDSuffixLength;
        try
        {
        java.lang.Class.forName(driver);
        }catch(java.lang.ClassNotFoundException e)     {
          System.err.print("ClassNotFoundException: ");
          System.err.println(e.getMessage());
          System.out.println("\n    >>> Please check your CLASSPATH variable   <<<\n");
        }

        try {
            // Create (if needed) and connect to the database
            conn = DriverManager.getConnection(connectionURL);
        } catch (Throwable e)  {
        }
    }

    protected void closeDB() throws SQLException
    {

         conn.close();
         if (driver.equals("org.apache.derby.jdbc.EmbeddedDriver")) {
               boolean gotSQLExc = false;
               try {
                  DriverManager.getConnection("jdbc:derby:;shutdown=true");
               } catch (SQLException se)  {
                  if ( se.getSQLState().equals("XJ015") ) {
                     gotSQLExc = true;
                  }
               }
               if (!gotSQLExc) {
               	  System.out.println("Database did not shut down normally");
               }  else  {
                  System.out.println("Database shut down normally");
               }
            }
    }

}
