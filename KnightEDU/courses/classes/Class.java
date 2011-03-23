package KnightEDU.courses.classes;

import KnightEDU.courses.CourseID;
import KnightEDU.courses.Term;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Alexander Darino
 */
public class Class
{
    /**

     @param courseID
     @param term
     @param year
     @param primaryComponentGroupID
     */
    public Class(CourseID courseID, Term term, int year, int primaryComponentGroupID)
    {
        this.courseID = courseID;
        this.term = term;
        this.year = year;
        this.componentGroupIDs.add(primaryComponentGroupID);
    }

    /**

     @return
     */
    public int componentsTotal()
    {
        return componentGroupIDs.size();
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
    protected ArrayList<Integer> componentGroupIDs = new ArrayList();

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
    public ArrayList<Integer> getComponentGroupIDs()
    {
        return (ArrayList<Integer>) Collections.unmodifiableList(componentGroupIDs);
    }

    /**

     */
    public static class PrimaryComponentGroupNotEmptyException extends Exception
    {

    }

    /**

     @param componentGroupID
     */
    public void addComponentGroup(int componentGroupID)
    {
        if (!componentGroupIDs.contains(componentGroupID))
            componentGroupIDs.add(componentGroupID);
    }

    /**

     @param componentGroupID
     */
    public void removeComponentGroup(int componentGroupID)
    {
        componentGroupIDs.remove(componentGroupID);
    }

    /**

     @param componentGroupID
     @return
     */
    public boolean containsComponentGroup(int componentGroupID)
    {
        return componentGroupIDs.contains(componentGroupID);
    }
}
