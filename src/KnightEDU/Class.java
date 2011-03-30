package KnightEDU;

/**

 @author Alexander Darino
 */
public class Class
{
    /**

     */
    protected final int ID;

    /**
     
     @return
     */
    public int getID()
    {
        return ID;
    }

    /**

     */
    protected final int sectionID;
    /**

     */
    protected final int sectionNumber;

    /**
     *
     */
    protected int capacity;

    /**
     *
     * @return
     */
    public int getCapacity()
    {
        return capacity;
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

     @param ID
     @param sectionID
     * @param sectionNumber
     * @param capacity
     */
    public Class(int ID, int sectionID, int sectionNumber, int capacity)
    {
        this.ID = ID;
        this.sectionID = sectionID;
        this.sectionNumber = sectionNumber;
        this.capacity = capacity;
    }
}