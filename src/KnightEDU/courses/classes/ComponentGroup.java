package KnightEDU.courses.classes;

import KnightEDU.courses.Course;
import KnightEDU.courses.Course.Offering;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 */
public class ComponentGroup implements Iterable<Component>
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
    public boolean add(Component component)
    {
        return components.add(component);
    }

    /**

     @param componentID
     @return
     */
    public boolean remove(int componentID)
    {
        Component toRemove = get(componentID);
        if (toRemove == null) return false;
        return components.remove(toRemove);
    }

    /**

     @param componentID
     @return
     */
    public boolean contains(int componentID)
    {
        Component r_val = get(componentID);
        return r_val != null ? true : false;
    }

    /**

     @param componentID
     @return
     */
    protected Component get(int componentID)
    {
        for (Component i : components)
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
    protected TreeSet<Component> components = new TreeSet();
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
    public ComponentGroup(int componentGroupID, Course.Offering courseOffering, String label, Type type)
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

    public Iterator<Component> iterator()
    {
        return Collections.unmodifiableSet(components).iterator();
    }
}
