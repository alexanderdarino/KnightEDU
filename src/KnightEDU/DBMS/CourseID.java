/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KnightEDU.DBMS;

import KnightEDU.Credits;
import KnightEDU.Grade;

public interface CourseID {

    public boolean isValidCourseID(String courseID);
//    public KnightEDU.CourseID addCourseID(String courseID);
//    public boolean containsCourseID(String courseID);
//    public KnightEDU.CourseID getCourseID(String courseID);
//    public KnightEDU.DBMS.Query.CourseID queryCourseID();
//    public void removeCourseID(String courseID);
//    public void updateCourseID(String oldCourseID, String newCourseID);

    public static interface PNS extends CourseID
    {
//        /**
//         *
//         * @param prefix
//         * @param number
//         * @param suffix
//         * @param name
//         * @param description
//         * @param credits
//         * @param gradeType
//         * @return
//         */
//        public KnightEDU.Course addCourseID(String prefix, String number, String suffix);


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

        public int getCoursePrefixLength();
        public int getCourseNumberLength();
        public int getCourseSuffixMaxLength();
    }
}
