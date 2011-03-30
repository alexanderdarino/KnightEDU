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

     @param classObj
     @return
     */
    public boolean addClass(Class classObj)
    {
        return components.add(classObj);
    }

    /**

     @param classID
     @return
     */
    public boolean removeClass(int classID)
    {
        Class toRemove = getClass(classID);
        if (toRemove == null) return false;
        return components.remove(toRemove);
    }

    /**

     @param classID
     @return
     */
    public boolean containsClass(int classID)
    {
        Class r_val = getClass(classID);
        return r_val != null ? true : false;
    }

    /**

     @param classID
     @return
     */
    public Class getClass(int classID)
    {
        for (Class i : components)
        {
            if (i.getID() == classID)
                return i;
        }
        return null;
    }

    /**
     *
     * @return
     */
    public Class[] getClasses()
    {
        return (Class[]) components.toArray();
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
    protected ArrayList<Class> components = new ArrayList();
    /**
     */
    protected Type type;


    /**

     @param componentID
     @param type
     */
    public Component(int componentID, Type type)
    {
        this.componentID = componentID;
        this.type = type;
    }

    /**
    @return
     */
    public Type getType()
    {
        return type;
    }

    /**
     *
     */
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
