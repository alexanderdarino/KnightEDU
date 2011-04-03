package KnightEDU;

import KnightEDU.Grade.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * Describes a course identified by a {@link CourseID}.
 * @author Alexander Darino
 @version 3/12/11
@see CourseID
@see Credits
@see Grade
@see Prequisites
@see Course.Offering
 */
public class Course
{

    /**
     * The identifier of the described course
     */
    protected CourseID id;
    /**
    The type of grade assigned for this course
     */
    protected Grade.Type gradeType;

    /**
    Returns the type of grade assigned for this course.
    @return the courses grade type
     */
    public Type getGradeType()
    {
        return gradeType;
    }

    /**
    Set the type of grade assigned for this course.
    @param gradeType the courses grade type
     */
    public void setGradeType(Type gradeType)
    {
        this.gradeType = gradeType;
    }
    /**
     * The name of the course
     */
    protected String name;
    /**
     * The course description
     */
    protected String description;
    /**
     * The quantity of credits offered by the course
     */
    protected Credits credits;

    /**
    Sets the quantity of credits offered by the course.
    @param credits the quantity of credits
     */
    public void setCredits(Credits credits)
    {
        this.credits = credits;
    }

    /**
    Sets the description of the course.
    @param description the course description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
    Sets the name of the course.
    @param name the course name
     */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
     * The course prerequisites
     */
    protected Prerequisites prerequisites;

    /**
    Gets the courses prerequisites.
    @return the courses prerequisites
     */
    public Prerequisites getPrerequisites()
    {
        return prerequisites;
    }

    /**
    Sets the courses prerequisites.
    @param prerequisites the courses prerequisites
     */
    public void setPrerequisites(Prerequisites prerequisites)
    {
        this.prerequisites = prerequisites;
    }
    /**
     * Set of terms when course is regularly scheduled
     */
    protected TreeSet<Offering> offeredTerms = new TreeSet();

    /**
     * Returns the quantity of credits offered by the course.
     * @return quantity of course credits
     */
    public Credits getCredits()
    {
        return credits;
    }

    /**
     * Returns the course description.
     * @return course description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Returns the course identifier.
     * @return the courses identifier
     */
    public CourseID getId()
    {
        return id;
    }

    /**
     * Returns the name of the course.
     * @return the courses name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Constructs a new course object.
     * @param id course identifier
     * @param name course name
     * @param description course description
    @param credits Credits object representing quantity of credits offered
    @param gradeType
     */
    protected Course(CourseID id, String name, String description, Credits credits, Grade.Type gradeType)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.gradeType = gradeType;
    }

    /**
     * Constructs a new course object.
     * @param id course identifier
     * @param name course name
     * @param description course description
     * @param credits Credits object representing quantity of credits offered
    @param gradeType
    @return a new course object. If name, credits, or gradeType are <code>null</code>
    then returns <code>null</code>.
     */
    public static Course create(CourseID id, String name, String description, Credits credits, Grade.Type gradeType)
    {
        if (id == null || name == null || description == null || credits == null || gradeType == null) {
            return null;
        }
        return new Course(id, name, description, credits, gradeType);
    }

//    /**
//     * Sets a course offering for the specified term with the specified year parity.
//     * @param term term of course offering
//     * @param parity year parity of course offering
//     * @return this course
//     */
//    public boolean addOffering(Term term, YearParity parity)
//    {
//        if (isOffered(term) != null) {
//            return false;
//        }
//        offeredTerms.add(new Offering(id, term, parity));
//        return true;
//    }
//
//    /**
//
//    @param term
//    @return
//     */
//    public Offering isOffered(Term term)
//    {
//        for (Offering i : offeredTerms) {
//            if (i.getTerm() == term) {
//                return i;
//            }
//        }
//
//        return null;
//    }

//    /**
//     * Removes a course offering for the specified term
//     * @param term term of course offering
//     * @return <ul>
//        <li><code>true</code> if term was successfully removed</li>
//        <li><code>false</code> if no such term exists</li></ul>
//     */
//    public boolean removeOffering(Term term)
//    {
//        Offering offering = isOffered(term);
//        if (offering == null) {
//            return false;
//        }
//
//        offeredTerms.remove(offering);
//        return true;
//    }


//    ////////////////////////////////////////////////////////////////Register an "Update Event" System
//    /**
//     * Returns the set of course offerings
//     * @return set of course offerings
//     */
//    public Set<Offering> getOfferings()
//    {
//        //return (Set<Offering>) offeredTerms.clone();
//        return Collections.unmodifiableSet(offeredTerms);
//    }

//    /**
//     * Represents a course offering. Courses are offered for a specified term
//     * with a specified year parity.
//     @author Alexander Darino
//     */
//    public static class Offering implements Comparable<Offering>
//    {
//
//        /**
//
//         */
//        protected final CourseID courseID;
//        /**
//         * The term the course is offered in
//         */
//        protected final Term term;
//        /**
//         * The year parity the course is offered in
//         */
//        protected final YearParity parity;
//
//        /**
//         * Creates a new course offering
//         @param courseID
//         @param term term offered
//         * @param parity parity of year offered
//         */
//        public Offering(CourseID courseID, Term term, YearParity parity)
//        {
//            this.courseID = courseID;
//            this.term = term;
//            this.parity = parity;
//        }
//
//        /**
//
//         @return
//         */
//        public CourseID getCourseID()
//        {
//            return courseID;
//        }
//
//        /**
//         * Returns the parity of the offered year
//         * @return parity of offered year
//         */
//        public YearParity getParity()
//        {
//            return parity;
//        }
//
//        /**
//         * Returns the term of the course offering
//         * @return term of offering
//         */
//        public Term getTerm()
//        {
//            return term;
//        }
//
//        public int compareTo(Offering o)
//        {
//            throw new UnsupportedOperationException("Not yet implemented");
//        }
//    }

    /**

     */
    public static class Offering
    {
        /**

         @param courseID
         @param term
         @param year
         @param primaryComponentGroupID
         */
        public Offering(CourseID courseID, int year, Term term, int primaryComponentGroupID)
        {
            this.courseID = courseID;
            this.term = term;
            this.year = year;
            this.componentIDs.add(primaryComponentGroupID);
        }

        /**

         @return
         */
        public int componentsTotal()
        {
            return componentIDs.size();
        }
        /**
         * The course this class covers
         */
        protected CourseID courseID;
        /**

         */
        protected Term term;
        /**

         */
        protected int year;


        /**

         */
        protected ArrayList<Integer> componentIDs = new ArrayList();

        /**

         @return
         */
        public Term getTerm()
        {
            return term;
        }

        /**

         @return
         */
        public int getYear()
        {
            return year;
        }

        /**

         @return
         */
        public CourseID getCourseID()
        {
            return courseID;
        }

        /**

         @return
         */
        public ArrayList<Integer> getComponentIDs()
        {
            return (ArrayList<Integer>) Collections.unmodifiableList(componentIDs);
        }

        /**

         */
        public static class PrimaryComponentGroupNotEmptyException extends Exception
        {

        }

        /**

         @param componentGroupID
         */
        public void addComponent(int componentGroupID)
        {
            if (!componentIDs.contains(componentGroupID))
                componentIDs.add(componentGroupID);
        }

        /**

         @param componentGroupID
         */
        public void removeComponent(int componentGroupID)
        {
            componentIDs.remove(componentGroupID);
        }

        /**

         @param componentGroupID
         @return
         */
        public boolean containsComponent(int componentGroupID)
        {
            return componentIDs.contains(componentGroupID);
        }
    }
}
