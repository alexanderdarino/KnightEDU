package KnightEDU;

/**
 *
 * @author Alexander Darino
 */
public class Employee {
    protected final int id;
    protected String firstName, lastName;

    public String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public Employee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
