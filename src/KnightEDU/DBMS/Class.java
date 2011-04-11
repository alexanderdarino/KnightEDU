/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KnightEDU.DBMS;

import KnightEDU.CourseID;
import KnightEDU.DBMS.SQL.Query;
import KnightEDU.Term;

/**
 *
 * @author Alexander Darino
 */
public interface Class {
    /**
     *
     @param classID
     * @param sectionID
     * @param sectionNumber
     * @param capacity
     * @return
     */
    public KnightEDU.Class addClass(int classID, int sectionID, int sectionNumber, int capacity, int instructorID);
    /**
     *
     @param classID
     * @return
     */
    public boolean containsClass(int classID);
    /**
     *
     @param classID
     @return
     */
    public KnightEDU.Class getClass(int classID);
    /**
     *
     * @return
     */
    public KnightEDU.DBMS.Query.Class queryClass();
    /**
     *
     @param classID
     */
//    public Set<Component.Offering> queryComponentOffering(String whereClause, String groupByClause, String havingClause);
//    /**
//     *
//     * @param componentID
//     * @param sectionID
//     */
    public void removeClass(int classID);
    /**
     *
     * @param componentOffering
     */
    public void updateClass(KnightEDU.Class classObj);

}
