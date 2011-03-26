package KnightEDU.DBMS;

import KnightEDU.courses.CourseID;
import KnightEDU.courses.Term;
import java.util.Set;

/**
 *
 * @author Alexander Darino
 */
public interface Class {
    public KnightEDU.courses.classes.Class addClass (CourseID courseID, Term term, int year, int primaryComponentID);
    public void removeClass(CourseID courseID, Term term, int year);
    public KnightEDU.courses.classes.Class getClass(CourseID courseID, Term term, int year);
    public boolean containsClass(CourseID courseID, Term term, int year);
    public void updateClass(KnightEDU.courses.classes.Class classObj);
    public Set<KnightEDU.courses.classes.Class> queryClass(String whereClause, String groupByClause, String havingClause);
    public Query.Class queryClass();
}
