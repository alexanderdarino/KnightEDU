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
     * @param componentID
     * @param sectionID
     * @param sectionNumber
     * @param capacity
     * @return
     */
    public KnightEDU.Class addClass(int classID, int sectionID, int sectionNumber, int capacity);
    /**
     *
     * @param componentID
     * @param sectionID
     * @return
     */
    public boolean containsClass(int classID);
    /**
     *
     * @param componentID
     * @param sectionID
     * @return
     */
    public KnightEDU.Class getClass(int classID);
    /**
     *
     * @return
     */
    public Query.Component.Offering queryClass();
    /**
     *
     * @param whereClause
     * @param groupByClause
     * @param havingClause
     * @return
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
    public void updateClass(KnightEDU.Class componentOffering);




    public static interface Offering {
        /**
         *
         * @param courseID
         * @param term
         * @param year
         * @param primaryComponentID
         * @return
         */
        public KnightEDU.Class addCourseOffering(CourseID courseID, Term term, int year, int primaryComponentID);
        /**
         *
         * @param courseID
         * @param term
         * @param year
         */
        public void removeCourseOffering(CourseID courseID, Term term, int year);
        /**
         *
         * @param courseID
         * @param term
         * @param year
         * @return
         */
        public KnightEDU.Course getCourseOffering(CourseID courseID, Term term, int year);
        /**
         *
         * @param courseID
         * @param term
         * @param year
         * @return
         */
        public boolean containsCourseOffering(CourseID courseID, Term term, int year);
        /**
         *
         * @param classObj
         */
        public void updateCourseOffering(KnightEDU.Course course);
    //    /**
    //     *
    //     * @param whereClause
    //     * @param groupByClause
    //     * @param havingClause
    //     * @return
    //     */
    //    public Set<KnightEDU.Class> queryClass(String whereClause, String groupByClause, String havingClause);
        /**
         *
         * @return
         */
        public Query.Course queryCourseOffering();
    }
}
