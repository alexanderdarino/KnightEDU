package KnightEDU.DBMS;
import KnightEDU.Term;
import java.util.Set;

/**
 *
 * @author Alexander Darino
 */
public interface Query {


    public static interface Section
    {
        
    }

    public static interface Component
    {
        
    }

    public static interface Class
    {

    }
    /**
     *
     */
    public static interface Course
    {

        public static interface Offering
        {
            public Query.Course.Offering containsCourse(String courseID);
            public Query.Course.Offering offeredTerm(Term term);
            public Query.Course.Offering offeredYear(int year);
            public Set<KnightEDU.Course.Offering> invoke();
        }

        public Query.CourseID specifyCourseID();
        public Query.Course nameContains(String name);
        public Query.Course descriptionContains(String description);
        public Set<KnightEDU.Course> invoke();
    }

    public static interface CourseID
    {
        public static interface PNS
        {
            public PNS containsPrefix(String prefix) throws InvalidPrefixException;
            public PNS containsSuffix(String suffix) throws InvalidSuffixException;
            public PNS containsNumberEqualTo(String number) throws InvalidNumberException;
            public PNS containsNumberLessThan(String number) throws InvalidNumberException;
            public PNS containsNumberGreaterThan(String number) throws InvalidNumberException;
            public Query.Course invoke();
            public static class InvalidPrefixException extends Exception{};
            public static class InvalidSuffixException extends Exception{};
            public static class InvalidNumberException extends Exception{};
        }
    }
}
