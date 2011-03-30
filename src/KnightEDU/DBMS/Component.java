/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KnightEDU.DBMS;
import KnightEDU.DBMS.SQL.Query;
import java.util.Set;

/**
 *
 * @author Alexander Darino
 */
public interface Component {
    /**
     *
     * @param type
     * @return
     */
    public KnightEDU.Component addComponent(KnightEDU.Component.Type type);
    /**
     *
     * @param componentID
     * @return
     */
    public boolean containsComponent(int componentID);
    /**
     *
     * @param componentID
     * @return
     */
    public KnightEDU.Component getComponent(int componentID);
    /**
     *
     * @return
     */
    public KnightEDU.DBMS.Query.Component queryComponent();
    /**
     *
     @param componentID
     */
//    public Set<KnightEDU.Component> queryComponent(String whereClause, String groupByClause, String havingClause);
//    /**
//     *
//     * @param componentID
//     */
    public void removeComponent(int componentID);
    /**
     *
     * @param component
     */
    public void updateComponent(KnightEDU.Component component);
}
