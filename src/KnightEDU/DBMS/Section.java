package KnightEDU.DBMS;

import KnightEDU.Days;
import KnightEDU.Location;
import java.util.Set;

/**
 *
 * @author Alexander Darino
 */
public interface Section {

    public Section addSection(Days days, Location location, int timeStart, int timeEnd);
    public void removeSection(String sectionID);
    public Section getSection(String sectionID);
    public Query.Section querySection();
    public Set<KnightEDU.Section> querySection(String whereClause, String groupByClause, String havingClause);
    public boolean containsSection(String sectionID);
    public void updateSection(Section section);
}
