package KnightEDU;

/**
 *
 */
public class Section
{
    /**

     @return
     */
    public Days getDays()
    {
        return days;
    }

    /**

     @return
     */
    public String getLocation()
    {
        return location;
    }



    /**

     @return
     */
    public int getTimeEnd()
    {
        return timeEnd;
    }

    /**

     @return
     */
    public int getTimeStart()
    {
        return timeStart;
    }

    /**

     @param location
     */
    public void setLocation(String location)
    {
        this.location = location;
    }

    /**

     @param sectionID
     @param days
     @param timeStart
     @param timeEnd
     @param location
     @return
     */
    public static Section create(int sectionID, Days days, int timeStart, int timeEnd, String location)
    {
        if (!(sectionID >= 0 && timeEnd >= timeStart && location != null)) return null;
        return new Section(sectionID, days, timeStart, timeEnd, location);
    }

    /**

     @param sectionID
     @param days
     @param timeStart
     @param timeEnd
     @param location
     */
    protected Section(int sectionID, Days days, int timeStart, int timeEnd, String location)
    {
        this.sectionID = sectionID;
        this.days = days;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.location = location;
    }

    /**
    The days when the component is scheduled to meet
     */
    protected Days days;
    /**
    The time when this component starts in Military Format
     */
    protected int timeStart;
    /**
    The time when this component ends in Military Format
     */
    protected int timeEnd; // Military format
    /**
    The component's location
     */
    protected String location;

    /**

     */
    protected int sectionID;

    /**

     @return
     */
    public int getSectionID()
    {
        return sectionID;
    }
}
