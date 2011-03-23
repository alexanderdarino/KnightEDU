package KnightEDU.courses.classes;

/**
 *
 */
public class Section
{





    /**

     @return
     */
    public int getCapacity()
    {
        return capacity;
    }



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

     @param capacity
     */
    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
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
     @param capacity
     @param days
     @param timeStart
     @param timeEnd
     @param location
     @return
     */
    public static Section create(int sectionID, int capacity, Days days, int timeStart, int timeEnd, String location)
    {
        if (!(sectionID >= 0 && capacity > 0 && timeEnd >= timeStart && location != null)) return null;
        return new Section(sectionID, capacity, days, timeStart, timeEnd, location);
    }

    /**

     @param sectionID
     @param capacity
     @param days
     @param timeStart
     @param timeEnd
     @param location
     */
    protected Section(int sectionID, int capacity, Days days, int timeStart, int timeEnd, String location)
    {
        this.sectionID = sectionID;
        this.capacity = capacity;
        this.days = days;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.location = location;
    }

    /**

     @param capacity
     */
    protected Section(int capacity)
    {
        this.capacity = capacity;
    }

    /**
    The maximum number of students allowed to be enrolled in this component
     */
    protected int capacity;
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
