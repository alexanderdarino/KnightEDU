package KnightEDU.DBMS;

import KnightEDU.courses.Course;
import KnightEDU.courses.classes.Component;
import KnightEDU.courses.classes.ComponentGroup;

/**

 @author Alexander Darino
 */
public class ComponentDB
{

    /**

     */
    public ComponentDB()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**

     @param sectionID
     @param sectionNumber
     @param classID
     @return
     */
    public Component addComponent(int sectionID, int sectionNumber, int classID)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**

     @param componentID
     @return
     */
    public Component removeComponent(int componentID)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**

     @param sectionID
     @param sectionNumber
     @param classID
     @return
     */
    public Component getComponent(int sectionID, int sectionNumber, int classID)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**

     @param componentID
     @return
     */
    public boolean containsComponent(int componentID)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**

     @return
     */
    public ComponentGroup addComponentGroup()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**

     @param componentGroupID
     @return
     */
    public ComponentGroup removeComponentGroup(int componentGroupID)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**

     @param componentGroupID
     @return
     */
    public ComponentGroup getComponentGroup(int componentGroupID)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**

     @param courseOffering
     @return
     */
    public ComponentGroup[] getComponentGroups(Course.Offering courseOffering)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**

     @param componentGroupID
     @return
     */
    public boolean containsComponentGroup(int componentGroupID)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**

     @param componentGroup
     */
    public void updateComponentGroup(ComponentGroup componentGroup)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
