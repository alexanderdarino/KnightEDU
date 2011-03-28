package KnightEDU;

import java.util.ArrayList;

/**
 *
 */
public class Component
{

    /**

     */
    protected final int componentID;

    /**

     @param component
     @return
     */
    public boolean addOffering(Offering component)
    {
        return components.add(component);
    }

    /**

     @param componentID
     @return
     */
    public boolean removeOffering(int componentID)
    {
        Offering toRemove = getOffering(componentID);
        if (toRemove == null) return false;
        return components.remove(toRemove);
    }

    /**

     @param componentID
     @return
     */
    public boolean containsOffering(int componentID)
    {
        Offering r_val = getOffering(componentID);
        return r_val != null ? true : false;
    }

    /**

     @param componentID
     @return
     */
    public Offering getOffering(int componentID)
    {
        for (Offering i : components)
        {
            if (i.getComponentID() == componentID)
                return i;
        }
        return null;
    }

    public Offering[] getOfferings()
    {
        return (Offering[]) components.toArray();
    }

    

    /**

     @return
     */
    public int getID()
    {
        return componentID;
    }
    /**
     */
    //protected TreeSet<Offering> components = new TreeSet();
    protected ArrayList<Offering> components = new ArrayList();
    /**
     */
    protected Type type;


    /**

     @param componentID
     @param courseOffering
     @param label
     @param type
     */
    public Component(int componentGroupID, Type type)
    {
        this.componentID = componentGroupID;
        this.type = type;
    }

    /**
    @return
     */
    public Type getType()
    {
        return type;
    }

    public static class Offering
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
        protected final int sectionID;
        /**

         */
        protected final int sectionNumber;

        protected int capacity;

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

         @param componentID
         @param sectionID
         @param sectionNumber
         @param classID
         */
        public Offering(int componentID, int sectionID, int sectionNumber, int capacity)
        {
            this.componentID = componentID;
            this.sectionID = sectionID;
            this.sectionNumber = sectionNumber;
            this.capacity = capacity;
        }
    }

    public static enum Type
    {

        /**
         */
        LECTURE,
        /**

         */
        LAB,
        /**

         */
        INDEPENDENT_STUDY,
        /**

         */
        FIELD
    }
}
