package KnightEDU.DBMS.SQL;

import KnightEDU.courses.CourseID;
import KnightEDU.courses.Term;
import KnightEDU.courses.classes.Class;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alexander Darino
 */
public class ClassDB {

    /**
     
     */
    public ClassDB()
    {throw new UnsupportedOperationException("Not yet implemented");}

    /**

     @param courseID
     @param term
     @param year
     @param primaryComponentGroupID
     @return
     */
    public Class add(CourseID courseID, Term term, int year, int primaryComponentGroupID)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**

     @param courseID
     @param term
     @param year
     @return
     @throws KnightEDU.courses.classes.Class.PrimaryComponentGroupNotEmptyException
     */
    public boolean remove(CourseID courseID, Term term, int year) throws Class.PrimaryComponentGroupNotEmptyException
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**

     @param courseID
     @param term
     @param year
     @return
     */
    public Class get(CourseID courseID, Term term, int year)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**

     @param classObj
     */
    public void update(Class classObj)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
