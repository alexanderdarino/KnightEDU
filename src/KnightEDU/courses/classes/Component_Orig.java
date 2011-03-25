package KnightEDU.courses.classes;

/**

 @author Alexander Darino
 */
public class Component_Orig
{
    /**

     */
    protected final int componentID;

    /**

     @return
     */
    public int getComponentID()
    {
        return componentID;
    }
    /**

     */
    protected int sectionID;
    /**

     */
    protected int sectionNumber;
    /**

     */
    protected int classID;

    /**

     @return
     */
    public int getClassID()
    {
        return classID;
    }

    /**

     @return
     */
    public int getSectionID()
    {
        return sectionID;
    }

    /**

     @return
     */
    public int getSectionNumber()
    {
        return sectionNumber;
    }

    /**

     @param componentID
     @param sectionID
     @param sectionNumber
     @param classID
     */
    public Component_Orig(int componentID, int sectionID, int sectionNumber, int classID)
    {
        this.componentID = componentID;
        this.sectionID = sectionID;
        this.sectionNumber = sectionNumber;
        this.classID = classID;
    }
}
