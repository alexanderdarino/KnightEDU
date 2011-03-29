/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KnightEDU.DBMS.SQL;

import KnightEDU.DBMS.SQL.Query.Component;
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



    /**
     *
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
     *
     */
    protected String driver = "org.apache.derby.jdbc.EmbeddedDriver";

    /**
     *
     */
    protected String dbName="C:\\universityDB";

    /**
     *
     */
    protected String connectionURL = "jdbc:derby:" + dbName + ";create=true";

    /**
     *
     */
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

    /**
     *
     * @param prefix
     * @param number
     * @param suffix
     * @return
     */
    public boolean isValidCourseID(String prefix, String number, String suffix)
    {
        return isValidCoursePrefix(prefix) && isValidCourseNumber(number) && isValidCourseSuffix(suffix);
    }

    /**
     *
     * @param prefix
     * @param number
     * @param suffix
     * @param name
     * @param description
     * @param credits
     * @param gradeType
     * @return
     */
    public KnightEDU.Course addCourseID(String prefix, String number, String suffix, String name, String description, Credits credits, Type gradeType)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param courseID
     * @param name
     * @param description
     * @param credits
     * @param gradeType
     * @return
     */
    public Course addCourse(String courseID, String name, String description, Credits credits, Type gradeType)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param courseID
     * @return
     */
    public boolean containsCourse(String courseID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param courseID
     * @return
     */
    public Course getCourse(String courseID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param course
     */
    public void updateCourse(Course course)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param days
     * @param location
     * @param timeStart
     * @param timeEnd
     * @return
     */
    public Section addSection(Days days, Location location, int timeStart, int timeEnd)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param sectionID
     * @return
     */
    public Section getSection(String sectionID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param sectionID
     * @return
     */
    public boolean containsSection(String sectionID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param section
     */
    public void updateSection(Section section)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private ResultSet query(String tables, String whereClause, String groupByClause, String havingClause)
    {
        try {
            Statement s;
            ResultSet myCourses;
            s = conn.createStatement();
            String queryString = "select * from " + tables;
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

    /**
     *
     * @param whereClause
     * @param groupByClause
     * @param havingClause
     * @return
     */
    public Set<KnightEDU.Course> queryCourse(String whereClause, String groupByClause, String havingClause)
    {
        query("Course", whereClause, groupByClause, havingClause);
        //java.util.MAP map = con.getTypeMap();
        //map.put("SchemaName.ADDRESS", Address.class);
        //rs.next();
        //Struct address = (Struct)rs.getObject("LOCATION");
        return null;
    }

    /**
     *
     * @return
     */
    public Query.Course queryCourse()
    {
        return new Query.Course(this);
    }

    /**
     *
     * @param courseID
     * @param term
     * @param year
     * @param primaryComponentID
     * @return
     */
    public Class addClass(CourseID courseID, Term term, int year, int primaryComponentID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param courseID
     * @param term
     * @param year
     * @return
     */
    public Class getClass(CourseID courseID, Term term, int year)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param courseID
     * @param term
     * @param year
     * @return
     */
    public boolean containsClass(CourseID courseID, Term term, int year)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param classObj
     */
    public void updateClass(Class classObj)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param whereClause
     * @param groupByClause
     * @param havingClause
     * @return
     */
    public Set<Class> queryClass(String whereClause, String groupByClause, String havingClause)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @return
     */
    public Query.Class queryClass()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param courseID
     */
    public void removeCourse(String courseID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param sectionID
     */
    public void removeSection(String sectionID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param courseID
     * @param term
     * @param year
     */
    public void removeClass(CourseID courseID, Term term, int year)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param componentID
     * @param sectionID
     * @param sectionNumber
     * @param capacity
     * @return
     */
    public Offering addComponentOffering(int componentID, int sectionID, int sectionNumber, int capacity)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param componentID
     * @param sectionID
     * @return
     */
    public boolean containsComponentOffering(int componentID, int sectionID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param componentID
     * @param sectionID
     * @return
     */
    public Offering getComponentOffering(int componentID, int sectionID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @return
     */
    public Component.Offering queryComponentOffering()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param whereClause
     * @param groupByClause
     * @param havingClause
     * @return
     */
    public Set<Offering> queryComponentOffering(String whereClause, String groupByClause, String havingClause)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param componentID
     * @param sectionID
     */
    public void removeComponentOffering(int componentID, int sectionID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @return
     */
    public Query.Section querySection()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param whereClause
     * @param groupByClause
     * @param havingClause
     * @return
     */
    public Set<KnightEDU.Section> querySection(String whereClause, String groupByClause, String havingClause)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param type
     * @return
     */
    public KnightEDU.Component addComponent(KnightEDU.Component.Type type)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param componentID
     * @return
     */
    public boolean containsComponent(int componentID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param componentID
     * @return
     */
    public KnightEDU.Component getComponent(int componentID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @return
     */
    public Component queryComponent()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param whereClause
     * @param groupByClause
     * @param havingClause
     * @return
     */
    public Set<KnightEDU.Component> queryComponent(String whereClause, String groupByClause, String havingClause)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param componentID
     */
    public void removeComponent(int componentID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param componentOffering
     */
    public void updateComponentOffering(Offering componentOffering)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param component
     */
    public void updateComponent(KnightEDU.Component component)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param CourseIDPrefixLength
     * @param CourseIDNumberLength
     * @param CourseIDSuffixLength
     */
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

    /**
     *
     * @throws SQLException
     */
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
