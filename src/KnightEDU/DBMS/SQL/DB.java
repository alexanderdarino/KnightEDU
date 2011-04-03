/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KnightEDU.DBMS.SQL;

import KnightEDU.Course;
import KnightEDU.CourseID;
import KnightEDU.Credits;
import KnightEDU.Grade.Type;
import KnightEDU.Term;
import KnightEDU.Class;
import KnightEDU.Days;
import KnightEDU.Location;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexander Darino
 */
public class DB implements KnightEDU.DBMS.Course, KnightEDU.DBMS.CourseID.PNS, KnightEDU.DBMS.Section, KnightEDU.DBMS.Course.Offering, KnightEDU.DBMS.Class, KnightEDU.DBMS.Component
{
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
    protected String dbName="C:\\universityDB\\universityDB";

    /**
     *
     */
    protected String connectionURL = "jdbc:derby:" + dbName + ";";

    /**
     *
     */
    protected Connection conn = null;

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
    public void closeDB()
    {

        try {
             conn.close();
        } catch (Throwable e)  {
        }
        
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

    public Query.Course queryCourse()
    {
        //query("Course", whereClause, groupByClause, havingClause);
        //java.util.MAP map = con.getTypeMap();
        //map.put("SchemaName.ADDRESS", Address.class);
        //rs.next();
        //Struct address = (Struct)rs.getObject("LOCATION");
        return null;
    }

    public Course addCourse(String courseID, String name, String description, Credits credits, Type gradeType)
    {
        try {
            PreparedStatement psInsert = null;
            String sql = "insert into COURSE(ID, NAME, DESCRIPTION, CREDITSMIN, CREDITSMAX, PREREQUISITES) values (?,?,?,?,?,?)";
            psInsert = conn.prepareStatement(sql);
            psInsert.setString(1,courseID);
            psInsert.setString(2,name);
            psInsert.setString(3,description);
            psInsert.setInt(4,credits.getMinCredits());
            psInsert.setInt(5,credits.getMaxCredits());
            psInsert.setString(6,"none");
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
            String queryString = "select * from COURSE C WHERE C.ID = ";
            queryString = queryString + "'" + courseID + "'";
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
            String queryString = "select * from COURSE C WHERE C.ID = ";
            queryString = queryString + "'" + courseID + "'";
            myCourses = s.executeQuery(queryString);
            while (myCourses.next())
            {
                int minCredit = myCourses.getInt("CREDITSMIN");
                int maxCredit = myCourses.getInt("CREDITSMAX");
                String prefix = myCourses.getString("ID").substring(0,3);
                String number = myCourses.getString("ID").substring(3,7);
                CourseID courseId = CourseID.PNS.create(prefix,number,"");
                Course thisCourse = Course.create(courseId,myCourses.getString("NAME"), myCourses.getString("DESCRIPTION"), Credits.createCredits(minCredit, maxCredit), Type.LETTER );
                return thisCourse;
                //TODO
            }
            
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
            psUpdate = conn.prepareStatement("update COURSE SET NAME = ?, DESCRIPTION = ?, CREDITSMIN = ?, CREDITSMAX = ?, PREREQUISITES = 'NONE'  WHERE ID = ?");
            //psUpdate.setString(1,course.getId().toString());
            psUpdate.setString(1,course.getName().toString());
            psUpdate.setString(2,course.getDescription().toString());
            psUpdate.setInt(3,course.getCredits().getMinCredits());
            psUpdate.setInt(4,course.getCredits().getMaxCredits());
            
            psUpdate.setString(5,course.getId().toString());
            psUpdate.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeCourse(String courseID)
    {
        try {
            PreparedStatement psDelete;
            psDelete = conn.prepareStatement("delete FROM COURSE C WHERE C.ID = (?)");
            psDelete.setString(1,courseID);
            psDelete.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public KnightEDU.Section addSection(Days days, Location location, int timeStart, int timeEnd)
    {
        try {
            /*int section_id = 0;
            Statement s;
            s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT sectionsid.NEXTVAL");
            if ( rs.next() ) {
            section_id = rs.getInt(1);
            }
            
            section_id++;
            */
            PreparedStatement psInsert;
            psInsert = conn.prepareStatement("insert into SECTIONS (id, days, timeStart, timeFinish, location) values (sectionid.NEXTVAL,?,?,?,?)");
            //psInsert.setInt(1,section_id);
            psInsert.setString(1,days.toString());
            psInsert.setInt(2,timeStart);
            psInsert.setInt(3,timeEnd);
            psInsert.setString(4,location.toString());
            psInsert.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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

    public KnightEDU.Section getSection(String sectionID)
    {
        try {
            int sectionid = Integer.parseInt(sectionID);
            Statement s;
            ResultSet mySection;
            s = conn.createStatement();
            String queryString = "select * from SECTIONS S WHERE S.ID = ";
            queryString = queryString + sectionID;
            mySection = s.executeQuery(queryString);
            while (mySection.next())
            {
                String days = mySection.getString("days");
                days = days.trim();
                int timeStart = mySection.getInt("timeStart");
                int timeFinish = mySection.getInt("timeFinish");
                String location = mySection.getString("location");
                KnightEDU.Section thisSection = null;
                if (days.equals("U"))
                    thisSection = thisSection.create(sectionid, Days.U, timeStart, timeFinish, location);
                else if (days.equals("M"))
                    thisSection = thisSection.create(sectionid, Days.M, timeStart, timeFinish, location);
                else if (days.equals("T"))
                    thisSection = thisSection.create(sectionid, Days.T, timeStart, timeFinish, location);
                else if (days.equals("W"))
                    thisSection = thisSection.create(sectionid, Days.W, timeStart, timeFinish, location);
                else if (days.equals("R"))
                    thisSection = thisSection.create(sectionid, Days.R, timeStart, timeFinish, location);
                else if (days.equals("F"))
                    thisSection = thisSection.create(sectionid, Days.F, timeStart, timeFinish, location);
                else if (days.equals("S"))
                    thisSection = thisSection.create(sectionid, Days.S, timeStart, timeFinish, location);
                else if (days.equals("MW"))
                    thisSection = thisSection.create(sectionid, Days.MW, timeStart, timeFinish, location);
                else if (days.equals("MWF"))
                    thisSection = thisSection.create(sectionid, Days.MWF, timeStart, timeFinish, location);
                else if (days.equals("TR"))
                    thisSection = thisSection.create(sectionid, Days.TR, timeStart, timeFinish, location);
                else if (days.equals("TBA"))
                    thisSection = thisSection.create(sectionid, Days.TBA, timeStart, timeFinish, location);
                return thisSection;
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

    public Query.Section querySection()
    {
        //query("Section", whereClause, groupByClause, havingClause);
        //throw new UnsupportedOperationException("Not supported yet.");
        return null;
    }

    public boolean containsSection(String sectionID)
    {
        try {

            Statement s;
            ResultSet mySection;
            s = conn.createStatement();
            String queryString = "select * from SECTIONS S WHERE S.ID = ";
            queryString = queryString + sectionID;
            mySection = s.executeQuery(queryString);
            while (mySection.next())
            {
               if (mySection != null)
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

    public void updateSection(KnightEDU.Section section)
    {
        try {
            PreparedStatement psUpdate;
            psUpdate = conn.prepareStatement("update SECTIONS S SET days = ?, timeStart = ?, timeFinish = ?, location = ? WHERE S.ID = ?");
            psUpdate.setString(1, section.getDays().toString());
            psUpdate.setInt(2, section.getTimeStart());
            psUpdate.setInt(3, section.getTimeEnd());
            psUpdate.setString(4,section.getLocation().toString());
            psUpdate.setInt(5,section.getSectionID());
            psUpdate.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public KnightEDU.Course.Offering addCourseOffering(CourseID courseID, Term term, int year, int primaryComponentID)
    {
        try {
            PreparedStatement psInsert;
            psInsert = conn.prepareStatement("insert into CourseOffered (courseID, term, yearOffered) values (?,?,?)");
            psInsert.setString(1,courseID.toString());
            psInsert.setString(2,term.toString());
            psInsert.setInt(3,year);
            //psInsert.setInt(4,primaryComponentID);
            psInsert.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeCourseOffering(CourseID courseID, Term term, int year)
    {
        try {
            PreparedStatement psDelete;
            psDelete = conn.prepareStatement("delete FROM CourseOffered WHERE COURSEID = ? AND TERM = ? AND yearOffered = ?");
            psDelete.setString(1,courseID.toString());
            psDelete.setString(2,term.toString());
            psDelete.setInt(3,year);
            psDelete.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public Course.Offering getCourseOffering(CourseID courseID, Term term, int year)
    {
        try {
            Statement s;
            ResultSet myCourseOffering;
            s = conn.createStatement();
            String queryString = "select * from CourseOffered C WHERE C.COURSEID = '";
            queryString = queryString + courseID.toString() + "'";
            queryString = queryString + " AND C.TERM = '";
            queryString = queryString + term.toString() + "'";
            queryString = queryString + " AND C.yearOffered = ";
            String newyear = Integer.toString(year);
            queryString = queryString + newyear.toString();
            myCourseOffering = s.executeQuery(queryString);
            while (myCourseOffering.next())
            {
                String prefix = myCourseOffering.getString("COURSEID").substring(0,3);
                String number = myCourseOffering.getString("COURSEID").substring(3,7);
                CourseID courseId = CourseID.PNS.create(prefix,number,"");
                String thisterm = myCourseOffering.getString("TERM");
                int thisyear = myCourseOffering.getInt("yearOffered");
                //KnightEDU.Course.Offering newOffering = KnightEDU.Course.Offering(courseId, thisterm, thisyear, 1);
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

    public boolean containsCourseOffering(CourseID courseID, Term term, int year)
    {
        try {
            Statement s;
            ResultSet myCourseOffering;
            s = conn.createStatement();
            String queryString = "select * from CourseOffered C WHERE C.COURSEID = '";
            queryString = queryString + courseID.toString() + "'";
            queryString = queryString + " AND C.TERM = '";
            queryString = queryString + term.toString() + "'";
            queryString = queryString + " AND C.yearOffered = ";
            String newyear = Integer.toString(year);
            queryString = queryString + newyear.toString();
            myCourseOffering = s.executeQuery(queryString);
            while (myCourseOffering.next())
            {
                if (myCourseOffering != null)
               return true;
            }
            return false;
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void updateCourseOffering(KnightEDU.Course.Offering course)
    {
         try {
            PreparedStatement psUpdate;
            psUpdate = conn.prepareStatement("update CourseOffered C SET TERM = ?, yearOffered = ? WHERE C.COURSEID = ?");
            psUpdate.setString(1, course.getTerm().toString());
            psUpdate.setInt(2, course.getYear());
            psUpdate.setString(3, course.getCourseID().toString());
            psUpdate.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public KnightEDU.DBMS.Query.Course.Offering queryCourseOffering()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Class addClass(int classID, int sectionID, int sectionNumber, int capacity)
    {
        try {
            PreparedStatement psInsert;
            psInsert = conn.prepareStatement("insert into CLASSES(id, sectionID, sectionNum) values (?,?,?) ");
            psInsert.setInt(1,classID);
            psInsert.setInt(2,sectionID);
            psInsert.setInt(3,sectionNumber);
            //psInsert.setInt(4,capacity);
            psInsert.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsClass(int classID)
    {
        try {
            Statement s;
            ResultSet myclass;
            s = conn.createStatement();
            String queryString = "select * from CLASSES C WHERE C.ID = ";
            String classid = Integer.toString(classID);
            queryString = queryString + classid;
            //queryString = queryString + " AND C.SECTIONID = ";
            //queryString = queryString + sectionID;
            myclass = s.executeQuery(queryString);
            while (myclass.next())
            {
               if (myclass != null)
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

    public KnightEDU.Class getClass(int classID)
    {
        try {
            Statement s;
            ResultSet myClass;
            s = conn.createStatement();
            String queryString = "select * from CLASSES C WHERE C.ID = ";
            String classid = Integer.toString(classID);
            queryString = queryString + classid;
            //queryString = queryString + " AND C.SECTIONID = ";
            //queryString = queryString + sectionID;
            myClass = s.executeQuery(queryString);
            while (myClass.next())
            {
                int secID = myClass.getInt("sectionID");
                int secNumber = myClass.getInt("sectionNum");
                KnightEDU.Class myclass = new KnightEDU.Class(classID, secID, secNumber, 100);

                return myclass;
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

    public KnightEDU.DBMS.Query.Class queryClass()
    {
        //query("ComponentOffering", whereClause, groupByClause, havingClause);
        //throw new UnsupportedOperationException("Not supported yet.");
         return null;
    }

    public void removeClass(int classID)
    {
        try {
            PreparedStatement psDelete;
            psDelete = conn.prepareStatement("delete FROM CLASSES C WHERE C.ID = (?) ");
            psDelete.setInt(1,classID);
            //psDelete.setInt(2,sectionID);
            psDelete.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    // Needs review
    public void updateClass(Class classObj)
    {
        try {
            PreparedStatement psUpdate;
            psUpdate = conn.prepareStatement("update CLASSES C SET SECTIONID = ?, sectionNum = ? WHERE C.ID = (?)");
            //psUpdate.setInt(1,componentOffering.getComponentID());
            psUpdate.setInt(1,classObj.getSectionID());
            psUpdate.setInt(2,classObj.getSectionNumber());
            //psUpdate.setInt(3,classObj.getCapacity());
            //psUpdate.setString(5,course.getDescription());
            //psUpdate.setString(6,course.getCredits().toString());
            //psUpdate.setString(7,course.getGradeType().toString());
            psUpdate.setInt(3,classObj.getID());
            psUpdate.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public KnightEDU.Component addComponent(KnightEDU.Component.Type type)
    {
        try {
            PreparedStatement psInsert;
            psInsert = conn.prepareStatement("insert into COMPONENT (id, componentType) values (?,?)");
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
            String queryString = "select * from COMPONENT C WHERE C.ID = ";
            String componentid = Integer.toString(componentID);
            queryString = queryString + componentid;

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
            String queryString = "select * from COMPONENT C WHERE C.ID = ";
            String componentid = Integer.toString(componentID);
            queryString = queryString + componentid;
            myComponent = s.executeQuery(queryString);
            while (myComponent.next())
            {
                KnightEDU.Component mycomponent = null;
                int componentType = myComponent.getInt("componentType");
                switch (componentType)
                {
                    case 1: mycomponent = new KnightEDU.Component(componentID, KnightEDU.Component.Type.LECTURE); break;
                    case 2: mycomponent = new KnightEDU.Component(componentID, KnightEDU.Component.Type.LAB); break;
                    case 3: mycomponent = new KnightEDU.Component(componentID, KnightEDU.Component.Type.INDEPENDENT_STUDY); break;
                    case 4: mycomponent = new KnightEDU.Component(componentID, KnightEDU.Component.Type.FIELD); break;
                }
                return mycomponent;
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

    public KnightEDU.DBMS.Query.Component queryComponent()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeComponent(int componentID)
    {
        try {
            PreparedStatement psDelete;
            psDelete = conn.prepareStatement("delete FROM COMPONENT C WHERE C.ID = (?)");
            psDelete.setInt(1,componentID);
            psDelete.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    // NEEDS REVIEW
    public void updateComponent(KnightEDU.Component component)
    {
        try {
            PreparedStatement psUpdate;
            psUpdate = conn.prepareStatement("update COMPONENT C SET componentType = ? WHERE C.ID = ?");
            KnightEDU.Component.Type myType = component.getType();
            psUpdate.setInt(1,myType.ordinal());
            psUpdate.setInt(2,component.getID());
            psUpdate.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isValidCourseID(String prefix, String number, String suffix)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isValidCoursePrefix(String s)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isValidCourseNumber(String s)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isValidCourseSuffix(String s)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    private ResultSet query(String tables, String whereClause, String groupByClause, String havingClause)
    {
        try {
            Statement s;
            ResultSet myResults;
            s = conn.createStatement();
            String queryString = "select * from " + tables;
            if (whereClause != null && !whereClause.equals(""))
                queryString += " WHERE " + whereClause;
            myResults = s.executeQuery(queryString);
            //while (myResults.next())
            //{
             //   if(tables.toLowerCase()=="course")
             //   {



              //  }
              //  System.out.println(myResults.getString("name"));
            //}
            return myResults;
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean isValidCourseID(String courseID)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
