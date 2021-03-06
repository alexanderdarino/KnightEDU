package KnightEDU.DBMS;
import KnightEDU.Term;
import java.util.Set;

/**
 *
 * @author Alexander Darino
 */
public interface Query {

    public static class Employee {

        public Employee() {
        }
    }


    public static interface Section
    {
        public Query.Section specifyDays(KnightEDU.Days days);
        public Query.Section startTime(int startTime);
        public Query.Section endTime(int endTime);
        public Set<KnightEDU.Section> invoke();
        public static interface BR
        {
            public Query.Section specifyBuilding(String building);
            public Query.Section specifyRoom(String room);
        }
    }

    public static interface Component
    {
        public Set<Integer> getComponentIDs(int primaryComponentID);
        public Set<Integer> getClassIDs(int componentID);
        
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
            public Query.Course.Offering specifyCourseID(String courseID);
            public Query.Course.Offering offeredTerm(Term term);
            public Query.Course.Offering offeredYear(int year);
            public Set<KnightEDU.Course.Offering> invoke();
        }

        public KnightEDU.DBMS.Query.CourseID specifyCourseID();
        public Query.Course nameContains(String name);
        public Query.Course descriptionContains(String description);
        public Set<KnightEDU.Course> invoke();
    }

    public static interface CourseID
    {
        public static interface PNS extends CourseID
        {
            public PNS containsPrefix(String prefix) throws InvalidPrefixException;
            public PNS containsSuffix(String suffix) throws InvalidSuffixException;
            public PNS containsNumberEqualTo(String number) throws InvalidNumberException;
            public PNS containsNumberLessThan(String number) throws InvalidNumberException;
            public PNS containsNumberGreaterThan(String number) throws InvalidNumberException;
            public Query.Course build();
            public static class InvalidPrefixException extends Exception{};
            public static class InvalidSuffixException extends Exception{};
            public static class InvalidNumberException extends Exception{};
        }
    }

    public static class Transcript {

        public Transcript() {
        }
    }
}
