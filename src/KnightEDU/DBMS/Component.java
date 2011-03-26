/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KnightEDU.DBMS;
import java.util.Set;

/**
 *
 * @author Alexander Darino
 */
public interface Component {
    public KnightEDU.Component addComponent(KnightEDU.Component.Type type);
    public boolean containsComponent(int componentID);
    public KnightEDU.Component getComponent(int componentID);
    public Query.Component queryComponent();
    public Set<KnightEDU.Component> queryComponent(String whereClause, String groupByClause, String havingClause);
    public void removeComponent(int componentID);
    public void updateComponent(KnightEDU.Component component);
}
