package KnightEDU.DBMS;

import KnightEDU.CourseID;
import KnightEDU.Term;
import java.util.Set;

/**
 *
 * @author Alexander Darino
 */
public interface Class {
    public KnightEDU.Class addClass (CourseID courseID, Term term, int year, int primaryComponentID);
    public void removeClass(CourseID courseID, Term term, int year);
    public KnightEDU.Class getClass(CourseID courseID, Term term, int year);
    public boolean containsClass(CourseID courseID, Term term, int year);
    public void updateClass(KnightEDU.Class classObj);
    public Set<KnightEDU.Class> queryClass(String whereClause, String groupByClause, String havingClause);
    public Query.Class queryClass();
}
