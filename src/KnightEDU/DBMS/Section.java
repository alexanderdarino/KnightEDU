package KnightEDU.DBMS;

import KnightEDU.courses.classes.Days;
import KnightEDU.courses.classes.Location;
import java.util.Set;

/**
 *
 * @author Alexander Darino
 */
public interface Section {

    public Section addSection(Days days, Location location, int timeStart, int timeEnd);
    public void removeSection(String sectionID);
    public Section getSection(String sectionID);
    public Set<Section> querySection(Query.Section query);
    public boolean containsSection(String sectionID);
    public void updateSection(Section section);
}
