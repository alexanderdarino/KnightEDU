package KnightEDU.DBMS;

import KnightEDU.courses.Credits;
import KnightEDU.courses.Grade;
import java.util.Set;

public interface Course {
    public Set<Course> queryCourse(String whereClause, String groupByClause, String havingClause);
    public Query.Course queryCourse();
    public KnightEDU.courses.Course addCourse(String courseID, String name, String description, Credits credits, Grade.Type gradeType);
    public boolean containsCourse(String courseID);
    public KnightEDU.courses.Course getCourse(String courseID);
    public void updateCourse(KnightEDU.courses.Course course);
    public void removeCourse(String courseID);
    //public boolean isValidCourseID(String courseID);

    public interface PNS extends Course
    {
        public KnightEDU.courses.Course addCourseID(String prefix, String number, String suffix, String name, String description, Credits credits, Grade.Type gradeType);
        public boolean isValidCourseID(String prefix, String number, String suffix);
        public boolean isValidCoursePrefix(String s);
        public boolean isValidCourseNumber(String s);
        public boolean isValidCourseSuffix(String s);
    }
}
