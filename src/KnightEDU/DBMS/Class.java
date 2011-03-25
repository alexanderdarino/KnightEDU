package KnightEDU.DBMS;

import KnightEDU.courses.CourseID;
import KnightEDU.courses.Term;

/**
 *
 * @author Alexander Darino
 */
public interface Class {
    public Class add(CourseID courseID, Term term, int year, int primaryComponentGroupID);
}
