/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KnightEDU.DBMS;

import KnightEDU.DBMS.SQL.Query;
import KnightEDU.Component;
import java.util.Set;

/**
 *
 * @author Alexander Darino
 */
public interface ComponentOffering {
    /**
     *
     * @param componentID
     * @param sectionID
     * @param sectionNumber
     * @param capacity
     * @return
     */
    public Component.Offering addComponentOffering(int componentID, int sectionID, int sectionNumber, int capacity);
    /**
     *
     * @param componentID
     * @param sectionID
     * @return
     */
    public boolean containsComponentOffering(int componentID, int sectionID);
    /**
     *
     * @param componentID
     * @param sectionID
     * @return
     */
    public Component.Offering getComponentOffering(int componentID, int sectionID);
    /**
     *
     * @return
     */
    public Query.Component.Offering queryComponentOffering();
    /**
     *
     * @param whereClause
     * @param groupByClause
     * @param havingClause
     * @return
     */
//    public Set<Component.Offering> queryComponentOffering(String whereClause, String groupByClause, String havingClause);
//    /**
//     *
//     * @param componentID
//     * @param sectionID
//     */
    public void removeComponentOffering(int componentID, int sectionID);
    /**
     *
     * @param componentOffering
     */
    public void updateComponentOffering(KnightEDU.Component.Offering componentOffering);

}
