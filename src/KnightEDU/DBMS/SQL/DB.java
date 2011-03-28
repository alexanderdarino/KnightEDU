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
        try {
            PreparedStatement psInsert;
            psInsert = conn.prepareStatement("insert into COURSE values (?) (?) (?) (?) (?) (?) (?) ");
            psInsert.setString(1,prefix);
            psInsert.setString(2,number);
            psInsert.setString(3,suffix);
            psInsert.setString(4,name);
            psInsert.setString(5,description);
            psInsert.setString(6,credits.toString());
            psInsert.setString(7,gradeType.toString());
            psInsert.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public Course addCourse(String courseID, String name, String description, Credits credits, Type gradeType)
    {
        try {
            PreparedStatement psInsert;
            psInsert = conn.prepareStatement("insert into COURSE values (?) (?) (?) (?) (?) ");
            psInsert.setString(1,courseID);
            psInsert.setString(2,name);
            psInsert.setString(3,description);
            psInsert.setString(4,credits.toString());
            psInsert.setString(5,gradeType.toString());
            psInsert.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsCourse(String courseID)
    {
        try {
            Statement s;
            ResultSet myCourses;
            s = conn.createStatement();
            String queryString = "select * from COURSE C WHERE C.COURSEID = ";
            queryString = queryString + courseID;
            myCourses = s.executeQuery(queryString);
            while (myCourses.next())
            {
               if (myCourses != null)
               return true;
            }
            return false;
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public Course getCourse(String courseID)
    {
        try {
            Statement s;
            ResultSet myCourses;
            s = conn.createStatement();
            String queryString = "select * from COURSE C WHERE C.COURSEID = ";
            queryString = queryString + courseID;
            myCourses = s.executeQuery(queryString);
            while (myCourses.next())
            {
               //TODO
            }
            return null;
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateCourse(Course course)
    {
        try {
            PreparedStatement psUpdate;
            psUpdate = conn.prepareStatement("update COURSE SET PREFIX = (?), NUMBER = (?), SUFFIX = (?), NAME = (?), DESCRIPTION = (?), CREDITS = (?), GRADETYPE = (?) WHERE COURSEID = (?)");
            psUpdate.setString(1,"");
            psUpdate.setString(2,"");
            psUpdate.setString(3,"");
            psUpdate.setString(4,course.getName());
            psUpdate.setString(5,course.getDescription());
            psUpdate.setString(6,course.getCredits().toString());
            psUpdate.setString(7,course.getGradeType().toString());
            psUpdate.setString(8,course.getId().toString());
            psUpdate.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public Section addSection(Days days, Location location, int timeStart, int timeEnd)
    {
         try {
            PreparedStatement psInsert;
            psInsert = conn.prepareStatement("insert into SECTION values (?) (?) (?) (?) (?) ");
            psInsert.setString(1,days.toString());
            psInsert.setString(2,location.toString());
            psInsert.setInt(3,timeStart);
            psInsert.setInt(4,timeEnd);
            psInsert.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public Section getSection(String sectionID)
    {
         try {
            Statement s;
            ResultSet myCourses;
            s = conn.createStatement();
            String queryString = "select * from SECTION S WHERE S.SECTIONID = ";
            queryString = queryString + sectionID;
            myCourses = s.executeQuery(queryString);
            while (myCourses.next())
            {
               //TODO
            }
            return null;
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsSection(String sectionID)
    {
        try {
            Statement s;
            ResultSet myCourses;
            s = conn.createStatement();
            String queryString = "select * from SECTION S WHERE S.SECTIONID = ";
            queryString = queryString + sectionID;
            myCourses = s.executeQuery(queryString);
            while (myCourses.next())
            {
               if (myCourses != null)
               return true;
            }
            return false;
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateSection(Section section)
    {
        try {
            PreparedStatement psUpdate;
            psUpdate = conn.prepareStatement("update SECTION SET PREFIX = (?), NUMBER = (?), SUFFIX = (?), NAME = (?), DESCRIPTION = (?), CREDITS = (?), GRADETYPE = (?) WHERE COURSEID = (?)");
            psUpdate.setString(1,"");
            psUpdate.setString(2,"");
            psUpdate.setString(3,"");
            //psUpdate.setString(4,course.getName());
            //psUpdate.setString(5,course.getDescription());
            //psUpdate.setString(6,course.getCredits().toString());
            //psUpdate.setString(7,course.getGradeType().toString());
            //psUpdate.setString(8,course.getId().toString());
            psUpdate.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet.");
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

    public Set<KnightEDU.Course> queryCourse(String whereClause, String groupByClause, String havingClause)
    {
        query("Course", whereClause, groupByClause, havingClause);
        //java.util.MAP map = con.getTypeMap();
        //map.put("SchemaName.ADDRESS", Address.class);
        //rs.next();
        //Struct address = (Struct)rs.getObject("LOCATION");
        return null;
    }

    public Query.Course queryCourse()
    {
        return new Query.Course(this);
    }

    public Class addClass(CourseID courseID, Term term, int year, int primaryComponentID)
    {
         try {
            PreparedStatement psInsert;
            psInsert = conn.prepareStatement("insert into CLASS values (?) (?) (?) (?) (?) ");
            psInsert.setString(1,courseID.toString());
            psInsert.setString(2,term.toString());
            psInsert.setInt(3,year);
            psInsert.setInt(4,primaryComponentID);
            psInsert.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public Class getClass(CourseID courseID, Term term, int year)
    {
         try {
            Statement s;
            ResultSet myClasses;
            s = conn.createStatement();
            String queryString = "select * from CLASS C WHERE C.COURSEID = ";
            queryString = queryString + courseID;
            queryString = queryString + " AND C.TERM = ";
            queryString = queryString + term.toString();
            queryString = queryString + " AND C.YEAR = ";
            queryString = queryString + year;
            myClasses = s.executeQuery(queryString);
            while (myClasses.next())
            {
               //TODO
            }
            return null;
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsClass(CourseID courseID, Term term, int year)
    {
        try {
            Statement s;
            ResultSet myClasses;
            s = conn.createStatement();
            String queryString = "select * from CLASS C WHERE C.COURSEID = ";
            queryString = queryString + courseID;
            queryString = queryString + " AND C.TERM = ";
            queryString = queryString + term.toString();
            queryString = queryString + " AND C.YEAR = ";
            queryString = queryString + year;
            myClasses = s.executeQuery(queryString);
            while (myClasses.next())
            {
               if (myClasses != null)
               return true;
            }
            return false;
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateClass(Class classObj)
    {
        try {
            PreparedStatement psUpdate;
            psUpdate = conn.prepareStatement("update CLASS SET COUSEID = (?), TERM = (?), YEAR = (?), primaryComponentGroupID = (?) WHERE COURSEID = (?)");
            psUpdate.setString(1,classObj.getCourseID().toString());
            psUpdate.setString(2,classObj.getTerm().toString());
            psUpdate.setInt(3,classObj.getYear());
            psUpdate.setString(4,classObj.getComponentGroupIDs().toString());
            psUpdate.setString(8,classObj.getCourseID().toString());
            psUpdate.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set<Class> queryClass(String whereClause, String groupByClause, String havingClause)
    {
         query("Class", whereClause, groupByClause, havingClause);
        //throw new UnsupportedOperationException("Not supported yet.");
         return null;
    }

    public Query.Class queryClass()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeCourse(String courseID)
    {
        try {
            PreparedStatement psDelete;
            psDelete = conn.prepareStatement("delete FROM COURSE WHERE COURSEID = (?)");
            psDelete.setString(1,courseID);
            psDelete.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeSection(String sectionID)
    {
        try {
            PreparedStatement psDelete;
            psDelete = conn.prepareStatement("delete FROM SECTION WHERE SECTIONID = (?)");
            psDelete.setString(1,sectionID);
            psDelete.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeClass(CourseID courseID, Term term, int year)
    {
        try {
            PreparedStatement psDelete;
            psDelete = conn.prepareStatement("delete FROM CLASS WHERE COURSEID = (?) AND TERM = (?) AND YEAR = (?) ");
            psDelete.setString(1,courseID.toString());
            psDelete.setString(2,term.toString());
            psDelete.setInt(3,year);
            psDelete.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Offering addComponentOffering(int componentID, int sectionID, int sectionNumber, int capacity)
    {
         try {
            PreparedStatement psInsert;
            psInsert = conn.prepareStatement("insert into COMPONENTOFFERING values (?) (?) (?) (?) (?) ");
            psInsert.setInt(1,componentID);
            psInsert.setInt(2,sectionID);
            psInsert.setInt(3,sectionNumber);
            psInsert.setInt(4,capacity);
            psInsert.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsComponentOffering(int componentID, int sectionID)
    {
        try {
            Statement s;
            ResultSet myComponentOffering;
            s = conn.createStatement();
            String queryString = "select * from COMPONENTOFFERING C WHERE C.COMPONENTID = ";
            queryString = queryString + componentID;
            queryString = queryString + " AND C.SECTIONID = ";
            queryString = queryString + sectionID;
            myComponentOffering = s.executeQuery(queryString);
            while (myComponentOffering.next())
            {
               if (myComponentOffering != null)
               return true;
            }
            return false;
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public Offering getComponentOffering(int componentID, int sectionID)
    {
         try {
            Statement s;
            ResultSet myComponentOffering;
            s = conn.createStatement();
            String queryString = "select * from COMPONENTOFFERING C WHERE C.COMPONENTID = ";
            queryString = queryString + componentID;
            queryString = queryString + " AND C.SECTIONID = ";
            queryString = queryString + sectionID;
            myComponentOffering = s.executeQuery(queryString);
            while (myComponentOffering.next())
            {
               //TODO
            }
            return null;
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public Component.Offering queryComponentOffering()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set<Offering> queryComponentOffering(String whereClause, String groupByClause, String havingClause)
    {
         query("ComponentOffering", whereClause, groupByClause, havingClause);
        //throw new UnsupportedOperationException("Not supported yet.");
         return null;
    }

    public void removeComponentOffering(int componentID, int sectionID)
    {
        try {
            PreparedStatement psDelete;
            psDelete = conn.prepareStatement("delete FROM COMPONENTOFFERING WHERE COMPONENTID = (?) AND SECTIONID = (?) ");
            psDelete.setInt(1,componentID);
            psDelete.setInt(2,sectionID);
            psDelete.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public Query.Section querySection()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set<KnightEDU.Section> querySection(String whereClause, String groupByClause, String havingClause)
    {
        query("Section", whereClause, groupByClause, havingClause);
        //throw new UnsupportedOperationException("Not supported yet.");
        return null;
    }

    public KnightEDU.Component addComponent(KnightEDU.Component.Type type)
    {
         try {
            PreparedStatement psInsert;
            psInsert = conn.prepareStatement("insert into COMPONENT values (?) (?) (?) (?) (?) ");
            psInsert.setString(1,type.toString());
            //psInsert.setString(2,name);
            //psInsert.setString(3,description);
            //psInsert.setString(4,credits.toString());
            //psInsert.setString(5,gradeType.toString());
            psInsert.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsComponent(int componentID)
    {
        try {
            Statement s;
            ResultSet myComponent;
            s = conn.createStatement();
            String queryString = "select * from COMPONENT C WHERE C.COMPONENTID = ";
            queryString = queryString + componentID;
            myComponent = s.executeQuery(queryString);
            while (myComponent.next())
            {
               if (myComponent != null)
               return true;
            }
            return false;
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public KnightEDU.Component getComponent(int componentID)
    {
         try {
            Statement s;
            ResultSet myComponent;
            s = conn.createStatement();
            String queryString = "select * from COMPONENT C WHERE C.COMPONENTID = ";
            queryString = queryString + componentID;
            myComponent = s.executeQuery(queryString);
            while (myComponent.next())
            {
               //TODO
            }
            return null;
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public Component queryComponent()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set<KnightEDU.Component> queryComponent(String whereClause, String groupByClause, String havingClause)
    {
        query("Component", whereClause, groupByClause, havingClause);
        //throw new UnsupportedOperationException("Not supported yet.");
        return null;
    }

    public void removeComponent(int componentID)
    {
        try {
            PreparedStatement psDelete;
            psDelete = conn.prepareStatement("delete FROM COMPONENT WHERE COMPONENTID = (?)");
            psDelete.setInt(1,componentID);
            psDelete.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateComponentOffering(Offering componentOffering)
    {
        try {
            PreparedStatement psUpdate;
            psUpdate = conn.prepareStatement("update COMPONENTOFFERING SET SECTIONID = (?), SECTIONNUMBER = (?), CAPACITY = (?) WHERE COMPONENTID = (?)");
            //psUpdate.setInt(1,componentOffering.getComponentID());
            psUpdate.setInt(1,componentOffering.getSectionID());
            psUpdate.setInt(2,componentOffering.getSectionNumber());
            psUpdate.setInt(3,componentOffering.getCapacity());
            //psUpdate.setString(5,course.getDescription());
            //psUpdate.setString(6,course.getCredits().toString());
            //psUpdate.setString(7,course.getGradeType().toString());
            psUpdate.setInt(8,componentOffering.getComponentID());
            psUpdate.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateComponent(KnightEDU.Component component)
    {
        try {
            PreparedStatement psUpdate;
            psUpdate = conn.prepareStatement("update COMPONENT SET OFFERING = (?) WHERE COMPONENTID = (?)");
            psUpdate.setString(1,component.getOfferings().toString());
            psUpdate.setInt(8,component.getID());
            psUpdate.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet.");
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
