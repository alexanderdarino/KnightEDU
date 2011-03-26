package KnightEDU.DBMS;

import KnightEDU.Credits;
import KnightEDU.Grade;
import java.util.Set;

public interface Course {
    public Set<KnightEDU.Course> queryCourse(String whereClause, String groupByClause, String havingClause);
    public Query.Course queryCourse();
    public KnightEDU.Course addCourse(String courseID, String name, String description, Credits credits, Grade.Type gradeType);
    public boolean containsCourse(String courseID);
    public KnightEDU.Course getCourse(String courseID);
    public void updateCourse(KnightEDU.Course course);
    public void removeCourse(String courseID);
    //public boolean isValidCourseID(String courseID);

    public interface PNS extends Course
    {
        public KnightEDU.Course addCourseID(String prefix, String number, String suffix, String name, String description, Credits credits, Grade.Type gradeType);
        public boolean isValidCourseID(String prefix, String number, String suffix);
        public boolean isValidCoursePrefix(String s);
        public boolean isValidCourseNumber(String s);
        public boolean isValidCourseSuffix(String s);
    }
}
