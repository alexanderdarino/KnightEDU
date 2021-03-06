package KnightEDU.DBMS;

import KnightEDU.Component.Type;
import KnightEDU.CourseID;
import KnightEDU.DBMS.SQL.Query;
import KnightEDU.Credits;
import KnightEDU.Grade;
import KnightEDU.Term;
import KnightEDU.YearParity;
import java.util.Set;

/**
 *
 * @author Alexander Darino
 */
public interface Course {
//    /**
//     *
//     * @param whereClause
//     * @param groupByClause
//     * @param havingClause
//     * @return
//     */
//    public Set<KnightEDU.Course> queryCourse(String whereClause, String groupByClause, String havingClause);
    /**
     *
     * @return
     */
    public KnightEDU.DBMS.Query.Course queryCourse();
    /**
     *
     * @param courseID
     * @param name
     * @param description
     * @param credits
     * @param gradeType
     * @return
     */
    public KnightEDU.Course addCourse(String courseID, String name, String description, Credits credits, Grade.Type gradeType);
    /**
     *
     * @param courseID
     * @return
     */
    public boolean containsCourse(String courseID);
    /**
     *
     * @param courseID
     * @return
     */
    public KnightEDU.Course getCourse(String courseID);
    /**
     *
     * @param course
     */
    public void updateCourse(KnightEDU.Course course);
    /**
     *
     * @param courseID
     */
    public void removeCourse(String courseID);
    //public boolean isValidCourseID(String courseID);

    

    /**

     */
    public static interface Offering
    {
        /**
         *
         * @param courseID
         * @param term
         * @param year
         * @param primaryComponentID
         * @return
         */
        public KnightEDU.Course.Offering addCourseOffering(CourseID courseID, Term term, int year, KnightEDU.Component.Type primaryComponentType);
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
        public KnightEDU.Course.Offering getCourseOffering(String courseID, Term term, int year);
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
         @param course
         */
        public void updateCourseOffering(KnightEDU.Course.Offering courseOffering);
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
        public KnightEDU.DBMS.Query.Course.Offering queryCourseOffering();

        public static interface Component
        {
            public void addCourseOfferingComponent(CourseID courseID, Term term, int year, int componentID);
            public void removeCourseOfferingComponent(CourseID courseID, Term term, int year, int componentID);
        }
    }
    public static interface Schedule
    {
        public KnightEDU.Course.Schedule addCourseSchedule(CourseID courseID, YearParity yearParity, KnightEDU.Term term);
        public boolean containsCourseSchedule (CourseID courseID, YearParity yearParity, KnightEDU.Term term);
        public Set<KnightEDU.Course.Schedule> getCourseSchedules(CourseID courseID);
        public Query.Course.Schedule queryCourseSchedule(CourseID courseID, YearParity yearParity, KnightEDU.Term term);
        public void removeCourseSchedule (CourseID courseID, YearParity yearParity, KnightEDU.Term term);
    }
}
