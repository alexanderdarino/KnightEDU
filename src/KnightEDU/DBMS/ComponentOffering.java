/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KnightEDU.DBMS;

import KnightEDU.courses.classes.Component;
import java.util.Set;

/**
 *
 * @author Alexander Darino
 */
public interface ComponentOffering {
    public Component.Offering addComponentOffering(int componentID, int sectionID, int sectionNumber, int capacity);
    public boolean containsComponentOffering(int componentID, int sectionID);
    public Component.Offering getComponentOffering(int componentID, int sectionID);
    public Query.Component.Offering queryComponentOffering();
    public Set<Component.Offering> queryComponentOffering(String whereClause, String groupByClause, String havingClause);
    public void removeComponentOffering (int componentID, int sectionID);

}
