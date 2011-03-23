package KnightEDU.employees;

/**
 *
 * @author Alexander Darino
 */
public class Instructor extends Employee{

    /**

     */
    public static final Instructor STAFF = new Instructor("STAFF", "", 0);
    /**

     @param nameLast
     @param nameFirst
     @param uid
     */
    protected Instructor(String nameLast, String nameFirst, int uid)
    {
        super (nameLast, nameFirst, uid);
    }
}
