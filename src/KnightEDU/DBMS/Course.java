package KnightEDU.DBMS;

import KnightEDU.courses.Credits;
import KnightEDU.courses.Grade;
import java.util.Set;

public interface Course {
    public Set<KnightEDU.courses.Course> queryCourse(Query.Course query);
    public KnightEDU.courses.Course addCourse(String courseID, String name, String description, Credits credits, Grade.Type gradeType);
    public boolean containsCourse(String courseID);
    public KnightEDU.courses.Course getCourse(String courseID);
    public void updateCourse(KnightEDU.courses.Course course);
    public KnightEDU.courses.Course removeCourse(String courseID);
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
