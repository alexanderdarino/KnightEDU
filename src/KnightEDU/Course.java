package KnightEDU;

import KnightEDU.Grade.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
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

    protected TreeSet<Schedule> schedules;

    /**
     * Get the value of schedules
     *
     * @return the value of schedules
     */
    public Set<Schedule> getSchedules()
    {
        return Collections.unmodifiableSet(schedules);
    }

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

    protected Course(CourseID id, String name, String description, Credits credits, Type gradeType, Prerequisites prerequisites, Set<Schedule> schedules)
    {
        this.schedules = new TreeSet(
            new Comparator<Course.Schedule>()
            {

                public int compare(Schedule o1, Schedule o2)
                {
                    int compareTo = o1.getTerm().compareTo(o2.getTerm());
                    if (compareTo != 0) return compareTo;
                    return o1.getYearPairity().compareTo(o2.getYearPairity());
                }
            });
        this.schedules.addAll(schedules);
        this.id = id;
        this.gradeType = gradeType;
        this.name = name.trim();
        this.description = description.trim();
        this.credits = credits;
        this.prerequisites = prerequisites;
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
    public static Course create(CourseID id, String name, String description, Credits credits, Grade.Type gradeType, Prerequisites prerequisites, Set<Schedule> schedules)
    {
        if (id == null || name == null || description == null || credits == null || gradeType == null || schedules == null || schedules.isEmpty()) {
            return null;
        }
        return new Course(id, name, description, credits, gradeType, prerequisites, schedules);
    }

    private String scheduleString()
    {
        if (schedules.isEmpty()) return "N/A";
        StringBuilder builder = new StringBuilder();
        Term lastTerm = schedules.first().getTerm();
        Term currentTerm = schedules.first().getTerm();
        TreeSet<YearParity> yearParities = new TreeSet();
        for (Schedule schedule : schedules)
        {
            if (schedule.getTerm() == currentTerm)
            {
                yearParities.add(schedule.getYearPairity());
            }
            else
            {
                builder.append(currentTerm);
                if (!(yearParities.contains(YearParity.EVEN) && yearParities.contains(YearParity.ODD)))
                {
                    builder.append(" ");
                    for (YearParity i : yearParities)
                    {
                        builder.append(i).append("/");
                    }
                    builder.deleteCharAt(builder.length()-1);
                }

                lastTerm = currentTerm;
                currentTerm = schedule.getTerm();
                yearParities = new TreeSet();
                yearParities.add(schedule.getYearPairity());
                builder.append(" ");
            }
        }
        
        builder.append(currentTerm);

        if (!(yearParities.contains(YearParity.EVEN) && yearParities.contains(YearParity.ODD)))
        {
            for (YearParity i : yearParities)
            {
                builder.append(i).append("/");
            }
            builder.deleteCharAt(builder.length()-1);
        }

        return builder.toString();
    }

    /**

     */
    public static class Offering
    {
        protected int primaryComponentID;
        /**

         @param courseID
         @param term
         @param year
         @param primaryComponentGroupID
         */
        public Offering(CourseID courseID, int year, Term term, int primaryComponentID)
        {
            this.courseID = courseID;
            this.term = term;
            this.year = year;
            this.primaryComponentID = primaryComponentID;
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


        public int getPrimaryComponentID()
        {
            return primaryComponentID;
        }
        /**

         */
        public static class PrimaryComponentGroupNotEmptyException extends Exception
        {

        }

    }

    @Override
    public String toString()
    {
        return getName() + "(Description: " + getDescription() + ", " + "Credits: " + getCredits() + ", Grade Type: " + getGradeType() + ", Prerequisites: " + getPrerequisites() + ", Offered: " + scheduleString() + ")";

    }

    public static class Schedule
    {
        protected Term term;
        protected YearParity yearPairity;

        public Schedule(Term term, YearParity yearPairity)
        {
            this.term = term;
            this.yearPairity = yearPairity;
        }

        public Term getTerm()
        {
            return term;
        }

        public YearParity getYearPairity()
        {
            return yearPairity;
        }

    }
}
