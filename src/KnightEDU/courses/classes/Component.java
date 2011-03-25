package KnightEDU.courses.classes;

import KnightEDU.courses.Course;
import KnightEDU.courses.Course.Offering;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 */
public class Component implements Iterable<Component_Orig>
{

    /**

     */
    protected final int componentGroupID;
    /**

     */
    protected final Course.Offering courseOffering;

    /**

     @param component
     @return
     */
    public boolean add(Component_Orig component)
    {
        return components.add(component);
    }

    /**

     @param componentID
     @return
     */
    public boolean remove(int componentID)
    {
        Component_Orig toRemove = get(componentID);
        if (toRemove == null) return false;
        return components.remove(toRemove);
    }

    /**

     @param componentID
     @return
     */
    public boolean contains(int componentID)
    {
        Component_Orig r_val = get(componentID);
        return r_val != null ? true : false;
    }

    /**

     @param componentID
     @return
     */
    protected Component_Orig get(int componentID)
    {
        for (Component_Orig i : components)
        {
            if (i.getComponentID() == componentID)
                return i;
        }
        return null;
    }

    /**

     @return
     */
    public Offering getCourseOffering()
    {
        return courseOffering;
    }

    /**

     @return
     */
    public int getComponentGroupID()
    {
        return componentGroupID;
    }
    /**
     */
    protected TreeSet<Component_Orig> components = new TreeSet();
    /**
     */
    protected Type type;
    /**
     */
    protected String label;


    /**

     @param componentGroupID
     @param courseOffering
     @param label
     @param type
     */
    public Component(int componentGroupID, Course.Offering courseOffering, String label, Type type)
    {
        this.componentGroupID = componentGroupID;
        this.courseOffering = courseOffering;
        this.type = type;
        this.label = label;
    }

    /**
    @return
     */
    public Type getType()
    {
        return type;
    }

    public Iterator<Component_Orig> iterator()
    {
        return Collections.unmodifiableSet(components).iterator();
    }
}
