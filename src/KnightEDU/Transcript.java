package KnightEDU;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Transcript {

    protected int studentID;

    public Transcript(int studentID)
    {
        this.studentID = studentID;
    }

    Set<Entry> entries = new HashSet();

    public void addEntry(CourseID courseID, int year, Term term, Grade grade, int credits)
    {
        entries.add(new Entry(courseID, year, term, grade, credits));
    }

    public Set<Entry> getEntries()
    {
        return Collections.unmodifiableSet(entries);
    }

    public class Entry {
        protected CourseID courseID;
        protected int year;
        protected Term term;
        protected Grade grade;

        public Entry(CourseID courseID, int year, Term term, Grade grade, int credits) {
            this.courseID = courseID;
            this.year = year;
            this.term = term;
            this.grade = grade;
            this.credits = credits;
        }

        public CourseID getCourseID() {
            return courseID;
        }

        public int getCredits() {
            return credits;
        }

        public Grade getGrade() {
            return grade;
        }

        public Term getTerm() {
            return term;
        }

        public int getYear() {
            return year;
        }
        protected int credits;

    }
}
