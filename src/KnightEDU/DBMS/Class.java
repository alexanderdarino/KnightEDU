package KnightEDU.DBMS;

import KnightEDU.DBMS.SQL.Query;
import KnightEDU.CourseID;
import KnightEDU.Term;
import java.util.Set;

/**
 *
 * @author Alexander Darino
 */
public interface Class {
    /**
     * 
     * @param courseID
     * @param term
     * @param year
     * @param primaryComponentID
     * @return
     */
    public KnightEDU.Class addClass(CourseID courseID, Term term, int year, int primaryComponentID);
    /**
     *
     * @param courseID
     * @param term
     * @param year
     */
    public void removeClass(CourseID courseID, Term term, int year);
    /**
     *
     * @param courseID
     * @param term
     * @param year
     * @return
     */
    public KnightEDU.Class getClass(CourseID courseID, Term term, int year);
    /**
     *
     * @param courseID
     * @param term
     * @param year
     * @return
     */
    public boolean containsClass(CourseID courseID, Term term, int year);
    /**
     *
     * @param classObj
     */
    public void updateClass(KnightEDU.Class classObj);
//    /**
//     *
//     * @param whereClause
//     * @param groupByClause
//     * @param havingClause
//     * @return
//     */
//    public Set<KnightEDU.Class> queryClass(String whereClause, String groupByClause, String havingClause);
    /**
     *
     * @return
     */
    public Query.Class queryClass();
}
