/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KnightEDU.DBMS.SQL;

import KnightEDU.Course;
import KnightEDU.CourseID;
import KnightEDU.Credits;
import KnightEDU.DBMS.Query.Employee;
import KnightEDU.DBMS.Query.Transcript;
import KnightEDU.Grade;
import KnightEDU.Grade.Type;
import KnightEDU.Instructor;
import KnightEDU.Term;
import KnightEDU.Class;
import KnightEDU.Days;
import KnightEDU.Location;
import KnightEDU.Prerequisites;
import KnightEDU.YearParity;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexander Darino
 */
public class DB implements KnightEDU.DBMS.Course, KnightEDU.DBMS.CourseID.PNS, KnightEDU.DBMS.Section, KnightEDU.DBMS.Course.Offering, KnightEDU.DBMS.Class, KnightEDU.DBMS.Component, KnightEDU.DBMS.Employee, KnightEDU.DBMS.Transcript.Entry, KnightEDU.DBMS.Instructor, KnightEDU.DBMS.Course.Schedule
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
    protected String dbName="C:\\universityDB";

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
        return new KnightEDU.DBMS.SQL.Query.Course(this);
    }

    public Course addCourse(String courseID, String name, String description, Credits credits, Type gradeType)
    {
        try {
            PreparedStatement psInsert = null;
            String sql = "insert into COURSES(ID, NAME, DESCRIPTION, CREDITSMIN, CREDITSMAX, PREREQUISITES) values (?,?,?,?,?,?)";
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
                CourseID courseIDObj = CourseID.PNS.create(prefix,number,"");
                Prerequisites prerequisites = new Prerequisites(myCourses.getString("prerequisites"));
                Course thisCourse = Course.create(courseIDObj,myCourses.getString("NAME"), myCourses.getString("DESCRIPTION"), Credits.createCredits(minCredit, maxCredit), Type.LETTER, prerequisites, getCourseSchedules(courseIDObj));
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

            Statement s;
            ResultSet myResults;
            s = conn.createStatement();
            String queryString = "select MAX (S.id) from SECTIONS S";
            myResults = s.executeQuery(queryString);
            int maxID = 0;
            if (myResults.wasNull())
            {
                maxID = 0;
            }
            else while (myResults.next())
            {
               maxID = myResults.getInt("1");
            }
            int nextID = maxID + 1;
            PreparedStatement psInsert;
            psInsert = conn.prepareStatement("insert into SECTIONS (id, days, timeStart, timeFinish, location) values (?,?,?,?,?)");
            psInsert.setInt(1,nextID);
            psInsert.setString(2,days.toString());
            psInsert.setInt(3,timeStart);
            psInsert.setInt(4,timeEnd);
            psInsert.setString(5,location.toString());
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
            psDelete = conn.prepareStatement("delete FROM SECTIONS S WHERE S.ID = (?)");
            int secID = Integer.parseInt(sectionID);
            psDelete.setInt(1,secID);
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
        throw new UnsupportedOperationException("Not supported yet.");
        //return null;
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

    public Course.Offering getCourseOffering(String courseID, Term term, int year)
    {
        try {
            Statement s;
            ResultSet myCourseOffering;
            s = conn.createStatement();
            String queryString = "select * from CourseOfferings C WHERE C.COURSEID = '";
            queryString = queryString + courseID.toString() + "'";
            queryString = queryString + " AND C.TERM = ";
            queryString = queryString + term.ordinal();
            queryString = queryString + " AND C.yearOffered = ";
            String newyear = Integer.toString(year);
            queryString = queryString + newyear.toString();
            myCourseOffering = s.executeQuery(queryString);
            while (myCourseOffering.next())
            {
                CourseID thiscourseID = new KnightEDU.CourseID(myCourseOffering.getString("courseID"));
                int thisterm = myCourseOffering.getInt("TERM");
                int thisyear = myCourseOffering.getInt("yearOffered");
                int primaryComponentID = myCourseOffering.getInt("primaryComponentID");
                KnightEDU.Course.Offering newOffering = new KnightEDU.Course.Offering(thiscourseID, thisyear, KnightEDU.Term.values()[thisterm], primaryComponentID);

                return newOffering;
            }
            
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
        return new Query.Course.Offering(this);
    }

    public Class addClass(int classID, int sectionID, int sectionNumber, int capacity, int instructorID)
    {
        try {
            PreparedStatement psInsert;
            psInsert = conn.prepareStatement("insert into CLASSES(id, sectionID, sectionNum, instructorID) values (?,?,?,?) ");
            psInsert.setInt(1,classID);
            psInsert.setInt(2,sectionID);
            psInsert.setInt(3,sectionNumber);
            psInsert.setInt(4,instructorID);
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
                int instructorID = myClass.getInt("instructorID");
                KnightEDU.Class myclass = new KnightEDU.Class(classID, secID, secNumber, 100, instructorID);

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
        throw new UnsupportedOperationException("Not supported yet.");
         //return null;
        //return new Query.Class(this);
    }

    public KnightEDU.Class getClass(KnightEDU.CourseID courseID, int year, KnightEDU.Term term, int sectionNum)
    {
        KnightEDU.Course.Offering offering = getCourseOffering(courseID.toString(), term, year);
        Set<Integer> componentIDs = queryComponent().getComponentIDs(offering.getPrimaryComponentID());
        for (int i : componentIDs)
        {
            Set<Integer> classIDs = getComponentClassIDs(i);
            for (int j : classIDs)
            {
                KnightEDU.Class classObj = getClass(j);
                if (classObj.getSectionNumber() == sectionNum)
                    return classObj;
            }
        }
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
            psUpdate = conn.prepareStatement("update CLASSES C SET SECTIONID = ?, sectionNum = ? , instructorID = ? WHERE C.ID = (?)");
            //psUpdate.setInt(1,componentOffering.getComponentID());
            psUpdate.setInt(1,classObj.getSectionID());
            psUpdate.setInt(2,classObj.getSectionNumber());
            psUpdate.setInt(3,classObj.getInstructorID());
            //psUpdate.setInt(3,classObj.getCapacity());
            //psUpdate.setString(5,course.getDescription());
            //psUpdate.setString(6,course.getCredits().toString());
            //psUpdate.setString(7,course.getGradeType().toString());
            psUpdate.setInt(4,classObj.getID());
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
            Statement s;
            ResultSet myResults;
            s = conn.createStatement();
            String queryString = "select MAX (C.id) from COMPONENT C";
            myResults = s.executeQuery(queryString);
            int maxID = 0;
            if (myResults.wasNull())
            {
                maxID = 0;
            }
            else while(myResults.next())
            {
               maxID = myResults.getInt("1");
            }
            int nextID = maxID + 1;
            PreparedStatement psInsert;
            psInsert = conn.prepareStatement("insert into COMPONENT (id, componentType) values (?,?)");
            psInsert.setInt(1,nextID);
            psInsert.setInt(2,type.ordinal());
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
                    case 0: mycomponent = new KnightEDU.Component(componentID, KnightEDU.Component.Type.LECTURE); break;
                    case 1: mycomponent = new KnightEDU.Component(componentID, KnightEDU.Component.Type.LAB); break;
                    case 2: mycomponent = new KnightEDU.Component(componentID, KnightEDU.Component.Type.INDEPENDENT_STUDY); break;
                    case 3: mycomponent = new KnightEDU.Component(componentID, KnightEDU.Component.Type.FIELD); break;
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
        return new Query.Component(this);
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
    protected ResultSet query(String tables, String whereClause, String groupByClause, String havingClause)
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

    public Employee queryEmployee() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public KnightEDU.Employee addEmployee(int employeeID, String firstName, String lastName) {

        try {
            //Statement s;
            //ResultSet myResults;
            //s = conn.createStatement();
            //String queryString = "select MAX (E.id) from Employees E";
            //myResults = s.executeQuery(queryString);
            //int maxID = 0;
            //if (myResults.wasNull())
            //{
            //    maxID = 0;
            //}
            //else while(myResults.next())
            //{
            //   maxID = myResults.getInt("1");
            //}
            //int nextID = maxID + 1;
            PreparedStatement psInsert;
            psInsert = conn.prepareStatement("insert into Employees (id, fname, lname) values (?,?,?)");
            psInsert.setInt(1,employeeID);
            psInsert.setString(2,firstName);
            psInsert.setString(3,lastName);
            //psInsert.setInt(4,primaryComponentID);
            psInsert.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean containsEmployee(int employeeID) {

        try {
            Statement s;
            ResultSet myEmployee;
            s = conn.createStatement();
            String queryString = "select * from Employees E WHERE E.ID = ";
            String componentid = Integer.toString(employeeID);
            queryString = queryString + componentid;

            myEmployee = s.executeQuery(queryString);
            while (myEmployee.next())
            {
               if (myEmployee != null)
               return true;
            }
            return false;
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public KnightEDU.Employee getEmployee(int employeeID) {
        try {
            Statement s;
            ResultSet myEmployee;
            s = conn.createStatement();
            String queryString = "select * from Employees E WHERE E.ID = ";
            String componentid = Integer.toString(employeeID);
            queryString = queryString + componentid;
            myEmployee = s.executeQuery(queryString);
            while (myEmployee.next())
            {
                KnightEDU.Employee  myemp = null;
                String firstname = myEmployee.getString("fname");
                String lastname = myEmployee.getString("lname");
                myemp = new KnightEDU.Employee(employeeID, firstname, lastname);

                return myemp;
               //TODO
            }
            return null;
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateEmployee(KnightEDU.Employee employee) {
        
        try {
            PreparedStatement psUpdate;
            psUpdate = conn.prepareStatement("update Employees E SET fname = ? , lname = ? WHERE E.ID = (?)");
            //psUpdate.setInt(1,componentOffering.getComponentID());
            psUpdate.setString(1,employee.getFirstName());
            psUpdate.setString(2,employee.getLastName());
            //psUpdate.setInt(3,classObj.getCapacity());
            //psUpdate.setString(5,course.getDescription());
            //psUpdate.setString(6,course.getCredits().toString());
            //psUpdate.setString(7,course.getGradeType().toString());
            psUpdate.setInt(3,employee.getId());
            psUpdate.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeEmployee(int employeeID) {
        
        try {
            PreparedStatement psDelete;
            psDelete = conn.prepareStatement("delete FROM Employees E WHERE E.ID = (?)");
            psDelete.setInt(1,employeeID);
            psDelete.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Transcript queryTranscript() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public KnightEDU.Transcript.Entry addTranscriptEntry(int studentID, KnightEDU.CourseID courseID, int year, Term term, Grade.Type gradeType, Grade grade, int credits) {
        
        try {


            PreparedStatement psInsert;
            psInsert = conn.prepareStatement("insert into Transcript (studentID, courseID, yearOffered, term, gradeType, grade, credits) values (?,?,?,?,?,?,?)");
            psInsert.setInt(1,studentID);
            psInsert.setString(2,courseID.toString());
            psInsert.setInt(3,year);
            psInsert.setInt(4,term.ordinal());
            psInsert.setInt(5,gradeType.ordinal());
            psInsert.setString(6,grade.toString());
            psInsert.setInt(7,credits);
            psInsert.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean containsTranscriptEntry(int studentID, KnightEDU.CourseID courseID, int year, Term term) {
        
        try {
            Statement s;
            ResultSet myTranscript;
            s = conn.createStatement();
            String queryString = "select * from Transcript T WHERE T.studentID = ";
            queryString = queryString + Integer.toString(studentID);
            queryString = queryString + " AND T.courseID = '";
            queryString = queryString + courseID.toString() + "'";
            queryString = queryString + " AND T.yearOffered = ";
            String newyear = Integer.toString(year);
            queryString = queryString + newyear.toString();
            queryString = queryString + " AND T.term = ";
            queryString = queryString + term.ordinal();
            myTranscript = s.executeQuery(queryString);
            while (myTranscript.next())
            {
               if (myTranscript != null)
               return true;
            }
            return false;
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public KnightEDU.Transcript.Entry getTranscriptEntry(int studentID, KnightEDU.CourseID courseID, int year, Term term) {

        try {
            Statement s;
            ResultSet myTranscript;
            s = conn.createStatement();
            String queryString = "select * from Transcript T WHERE T.studentID = ";
            queryString = queryString + Integer.toString(studentID);
            queryString = queryString + " AND T.courseID = '";
            queryString = queryString + courseID.toString() + "'";
            queryString = queryString + " AND T.yearOffered = ";
            String newyear = Integer.toString(year);
            queryString = queryString + newyear.toString();
            queryString = queryString + " AND T.term = ";
            queryString = queryString + term.ordinal();
            myTranscript = s.executeQuery(queryString);
            myTranscript.next();
            
                //KnightEDU.Transcript  thistranstript = null;
                //int studentid1 = myTranscript.getInt("studentID");
                //thistranstript = new KnightEDU.Transcript(studentid1);
                String prefix = myTranscript.getString("courseID").substring(0,3);
                String number = myTranscript.getString("courseID").substring(3,7);
                CourseID courseId = CourseID.PNS.create(prefix,number,"");
                int thisterm = myTranscript.getInt("TERM");
                int thisyear = myTranscript.getInt("yearOffered");
                int thisgradetype = myTranscript.getInt("gradeType");
                String thisgrade = myTranscript.getString("grade");
                int thiscredits = myTranscript.getInt("credits");

                KnightEDU.Transcript.Entry myentry = null;
                if (thisterm == 0)
                    myentry = new KnightEDU.Transcript.Entry(courseId, thisyear, KnightEDU.Term.FALL, KnightEDU.Grade.Type.LETTER, KnightEDU.Grade.Letter.create(thisgrade), thiscredits);
                else if (thisterm == 1)
                   myentry = new KnightEDU.Transcript.Entry(courseId, thisyear, KnightEDU.Term.SPRING, KnightEDU.Grade.Type.LETTER, KnightEDU.Grade.Letter.create(thisgrade), thiscredits);
                else if (thisterm == 2)
                    myentry = new KnightEDU.Transcript.Entry(courseId, thisyear, KnightEDU.Term.SUMMER, KnightEDU.Grade.Type.LETTER, KnightEDU.Grade.Letter.create(thisgrade), thiscredits);
                else if (thisterm == 3)
                    myentry = new KnightEDU.Transcript.Entry(courseId, thisyear, KnightEDU.Term.OCCASIONAL, KnightEDU.Grade.Type.LETTER, KnightEDU.Grade.Letter.create(thisgrade), thiscredits);

                return myentry;
               //TODO
            
           
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateTranscriptEntry(int studentID, KnightEDU.Transcript.Entry transcriptEntry) {
        
        try {
            PreparedStatement psUpdate;
            psUpdate = conn.prepareStatement("update Transcript T SET  courseID = ? , yearOffered = ? , term = ? , gradeType = ? , grade = ? , credits = ? WHERE T.studentID = (?)");
            //psUpdate.setInt(1,componentOffering.getComponentID());
            
            psUpdate.setString(1,transcriptEntry.getCourseID().toString());
            psUpdate.setInt(2,transcriptEntry.getYear());
            psUpdate.setInt(3,transcriptEntry.getTerm().ordinal());
            psUpdate.setInt(4,transcriptEntry.getGradeType().ordinal());
            psUpdate.setString(5,transcriptEntry.getGrade().toString());
            psUpdate.setInt(6,transcriptEntry.getCredits());
            psUpdate.setInt(7,studentID);
            psUpdate.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeTranscriptEntry(int studentID, KnightEDU.CourseID courseID, int year, Term term) {
        
        try {
            PreparedStatement psDelete;
            psDelete = conn.prepareStatement("delete FROM Transcript T WHERE T.studentID = ? AND T.courseID = ? AND T.yearOffered = ? AND T.term = ? ");
            psDelete.setInt(1,studentID);
            psDelete.setString(2,courseID.toString());
            psDelete.setInt(3,year);
            psDelete.setInt(4,term.ordinal());
            psDelete.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Instructor getInstructor(int instructorID) {
        
        try {
            Statement s;
            ResultSet myInstructor;
            s = conn.createStatement();
            String queryString = "select * from Classes C WHERE C.instructorID = ";
            String instructorid = Integer.toString(instructorID);
            queryString = queryString + instructorid;
            myInstructor = s.executeQuery(queryString);
            Instructor r_val = new Instructor(instructorID);
            while (myInstructor.next())
            {
                int myclassid = myInstructor.getInt("id");
                
                r_val.addClassID(myclassid);
            }
            return r_val;
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public KnightEDU.Transcript getTranscript(int studentID) {

        try {
            Statement s;
            ResultSet myTranscript;
            s = conn.createStatement();
            String queryString = "select * from Transcripts T WHERE T.studentID = ";
            queryString = queryString + Integer.toString(studentID);
            myTranscript = s.executeQuery(queryString);
            KnightEDU.Transcript  thistranstript = new KnightEDU.Transcript(studentID);
            while(myTranscript.next())
            {
                String prefix = myTranscript.getString("courseID").substring(0,3);
                String number = myTranscript.getString("courseID").substring(3,7);
                CourseID courseId = CourseID.PNS.create(prefix,number,"");
                int thisterm = myTranscript.getInt("TERM");
                int thisyear = myTranscript.getInt("yearOffered");
                int thisgradetype = myTranscript.getInt("gradeType");
                String thisgrade = myTranscript.getString("grade");
                int thiscredits = myTranscript.getInt("credits");

                if (thisterm == 0)
                    thistranstript.addEntry(courseId, thisyear, KnightEDU.Term.FALL, KnightEDU.Grade.Type.LETTER, KnightEDU.Grade.Letter.create(thisgrade), thiscredits);
                else if (thisterm == 1)
                    thistranstript.addEntry(courseId, thisyear, KnightEDU.Term.SPRING, KnightEDU.Grade.Type.LETTER, KnightEDU.Grade.Letter.create(thisgrade), thiscredits);
                else if (thisterm == 2)
                    thistranstript.addEntry(courseId, thisyear, KnightEDU.Term.SUMMER, KnightEDU.Grade.Type.LETTER, KnightEDU.Grade.Letter.create(thisgrade), thiscredits);
                else if (thisterm == 3)
                    thistranstript.addEntry(courseId, thisyear, KnightEDU.Term.OCCASIONAL, KnightEDU.Grade.Type.LETTER, KnightEDU.Grade.Letter.create(thisgrade), thiscredits);

            }
            return thistranstript;
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getCoursePrefixLength()
    {
        return PREFIX_LENGTH;
    }

    public int getCourseNumberLength()
    {
        return NUMBER_LENGTH;
    }

    public int getCourseSuffixMaxLength()
    {
        return SUFFIX_LENGTH;
    }

    public Course.Schedule addCourseSchedule(CourseID courseID, YearParity yearParity, Term term) {
        Course.Schedule r_val = null;
        try {
            PreparedStatement psInsert;
            psInsert = conn.prepareStatement("insert into CourseSchedules(courseid, term, yearparity) values (?,?,?) ");
            psInsert.setString(1,courseID.toString());
            psInsert.setInt(2,yearParity.ordinal());
            psInsert.setInt(3,term.ordinal());
            psInsert.executeUpdate();
            r_val = new Course.Schedule(term, yearParity);
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r_val;
    }

    public boolean containsCourseSchedule(CourseID courseID, YearParity yearParity, Term term) {
        try {
            Statement s;
            ResultSet results;
            s = conn.createStatement();
            String queryString = "select * from courseschedules cs WHERE cs.courseID = '" + courseID.toString() + "' AND cs.yearParity = " + yearParity.ordinal() + " AND cs.term = " + term.ordinal();
            results = s.executeQuery(queryString);
            return results.next();
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Set<KnightEDU.Course.Schedule> getCourseSchedules(CourseID courseID) {
        try {
            Statement s;
            ResultSet results;
            s = conn.createStatement();
            String queryString = "select * from courseschedules cs WHERE cs.courseID = '" + courseID.toString() + "'";
            results = s.executeQuery(queryString);
            Set<KnightEDU.Course.Schedule> r_val = new HashSet();
            while (results.next())
            {
                YearParity resultYearParity = YearParity.values()[results.getInt("yearParity")];
                Term resultTerm = Term.values()[results.getInt("term")];
                r_val.add(new KnightEDU.Course.Schedule(resultTerm, resultYearParity));
            }
            return r_val;
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Query.Course.Schedule queryCourseSchedule(CourseID courseID, YearParity yearParity, Term term) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeCourseSchedule(CourseID courseID, YearParity yearParity, Term term) {
        try {
            Statement s;
            s = conn.createStatement();
            String queryString = "DELETE from courseschedules cs WHERE cs.courseID = '" + courseID.toString() + "' AND cs.yearParity = " + yearParity.ordinal() + " AND cs.term = " + term.ordinal();
            s.executeUpdate(queryString);
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Set<Integer> getComponentClassIDs(int i)
    {
        try {
            Statement s;
            ResultSet results;
            s = conn.createStatement();
            String queryString = "SELECT classID from ComponentClasses CC WHERE CC.componentID = " + i;
            results = s.executeQuery(queryString);
            Set<Integer> r_val = new HashSet();
            while (results.next())
            {
                r_val.add(results.getInt("classID"));
            }
            return r_val;
        }
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
