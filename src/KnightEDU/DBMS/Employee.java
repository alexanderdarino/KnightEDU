/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KnightEDU.DBMS;

/**
 *
 * @author Alexander Darino
 */
public interface Employee {

    public KnightEDU.DBMS.Query.Employee queryEmployee();
    public KnightEDU.Employee addEmployee(int employeeID, String firstName, String lastName);
    public boolean containsEmployee(int employeeID);
    public KnightEDU.Employee getEmployee (int employeeID);
    public void updateEmployee(KnightEDU.Employee employee);
    public void removeEmployee(int employeeID);

}
