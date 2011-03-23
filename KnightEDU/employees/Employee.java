package KnightEDU.employees;

import java.util.Comparator;

/**
 *
 * @author Alexander Darino
 */
public class Employee {
    /**
     
     */
    public static final Comparator<Employee> comparatorName = new Comparator<Employee>()
    {
        public int compare(Employee o1, Employee o2) {
            //int compare_to = o1.getNameLast().compareToIgnoreCase(o2.getNameLast());
            //if (compare_to != 0) return compare_to;
            //return o1.getNameFirst().compareToIgnoreCase(o2.getNameFirst());
            return o1.toString().compareToIgnoreCase(o2.toString());
        }
    };
    /**
     
     */
    public static final Comparator<Employee> comparatorUID = new Comparator<Employee>()
    {
        public int compare(Employee o1, Employee o2) {
            if (o1.getUID() > o2.getUID()) return 1;
            else if (o1.getUID() < o2.getUID()) return -1;

            //assert false: "Employees with duplicate UIDs detected: " + o1 +"; " + o2;
            return -1;
        }
    };
    /**

     */
    /**

     */
    protected String nameLast, nameFirst;
    /**

     */
    protected int uid;

    /**
     
     @param nameFirst
     */
    public void setNameFirst(String nameFirst)
    {
        this.nameFirst = nameFirst;
    }

    /**
     
     @param nameLast
     @param nameFirst
     @param uid
     */
    protected Employee(String nameLast, String nameFirst, int uid)
    {
        this.nameLast = nameLast;
        this.nameFirst = nameFirst;
        this.uid = uid;
    }

    /**
     
     @param nameLast
     */
    public void setNameLast(String nameLast)
    {
        this.nameLast = nameLast;
    }

    /**
     
     @return
     */
    public String getNameFirst()
    {
        return nameFirst;
    }

    @Override
    public String toString() {
        return nameLast + ", " + nameFirst;
    }

    /**
     
     @return
     */
    public String getNameLast()
    {
        return nameLast;
    }

    /**
     
     @return
     */
    public int getUID()
    {
        return uid;
    }
}
