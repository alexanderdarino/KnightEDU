package KnightEDU.DBMS;

import KnightEDU.DBMS.SQL.Query;
import KnightEDU.Credits;
import KnightEDU.Grade;
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
    public Query.Course queryCourse();
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
     *
     */
    public interface PNS extends Course
    {
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
        public KnightEDU.Course addCourseID(String prefix, String number, String suffix, String name, String description, Credits credits, Grade.Type gradeType);
        /**
         *
         * @param prefix
         * @param number
         * @param suffix
         * @return
         */
        public boolean isValidCourseID(String prefix, String number, String suffix);
        /**
         *
         * @param s
         * @return
         */
        public boolean isValidCoursePrefix(String s);
        /**
         *
         * @param s
         * @return
         */
        public boolean isValidCourseNumber(String s);
        /**
         *
         * @param s
         * @return
         */
        public boolean isValidCourseSuffix(String s);
    }
}
