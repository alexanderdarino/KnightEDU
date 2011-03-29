package KnightEDU.DBMS;

import KnightEDU.DBMS.SQL.Query;
import KnightEDU.Days;
import KnightEDU.Location;
import java.util.Set;

/**
 *
 * @author Alexander Darino
 */
public interface Section {

    /**
     *
     * @param days
     * @param location
     * @param timeStart
     * @param timeEnd
     * @return
     */
    public Section addSection(Days days, Location location, int timeStart, int timeEnd);
    /**
     *
     * @param sectionID
     */
    public void removeSection(String sectionID);
    /**
     *
     * @param sectionID
     * @return
     */
    public Section getSection(String sectionID);
    /**
     *
     * @return
     */
    public Query.Section querySection();
//    /**
//     *
//     * @param whereClause
//     * @param groupByClause
//     * @param havingClause
//     * @return
//     */
//    public Set<KnightEDU.Section> querySection(String whereClause, String groupByClause, String havingClause);
    /**
     *
     * @param sectionID
     * @return
     */
    public boolean containsSection(String sectionID);
    /**
     *
     * @param section
     */
    public void updateSection(Section section);
}
